package com.luizcarlos.api.service;

import com.luizcarlos.api.exception.IdNaoEncontradoException;
import com.luizcarlos.api.exception.VotoDuplicadoException;
import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.model.Eleitor;
import com.luizcarlos.api.dtos.eleitorDTO.EleitorDTO;
import com.luizcarlos.api.dtos.eleitorDTO.InformacoesEleitorDTO;
import com.luizcarlos.api.repository.CargoRepository;
import com.luizcarlos.api.repository.EleitorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class EleitorService {

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private EleitorRepository repository;

    @Autowired
    private CargoRepository cargoRepository;


    public EleitorDTO criarEleitor(EleitorDTO eleitorDTO) {

        validarEleitor(eleitorDTO);

        eleitorDTO.setCriadoEm(LocalDateTime.now());
        eleitorDTO.setDeletadoEm (LocalDateTime.now());
        eleitorDTO.setAlteradoEm(LocalDateTime.now());


        List<Cargo> listaCargos = cargoRepository.findAll();
        for(Cargo cargo : listaCargos){

            if (cargo.getNome().equals("CIDADAO")){

                eleitorDTO.setCargo(cargo);
            }

        }



        Eleitor eleitor = modelMapper.map(eleitorDTO, Eleitor.class);
        Eleitor eleitorCriado = repository.save(eleitor);
        eleitorDTO = modelMapper.map(eleitorCriado, EleitorDTO.class);

        return eleitorDTO;
    }

    public InformacoesEleitorDTO buscarPorId(UUID id) {
        Optional<Eleitor> eleitor = procurarPeloId(id);

        InformacoesEleitorDTO eleitorDTO = modelMapper.map(eleitor, InformacoesEleitorDTO.class);

        return eleitorDTO;
    }

    public List<InformacoesEleitorDTO> listarTodosEleitores() {

        List<Eleitor> eleitores = repository.findAll();
        List<InformacoesEleitorDTO> buscarEleitor = new ArrayList<>();

        for (Eleitor eleitor : eleitores) {
            InformacoesEleitorDTO dto = modelMapper.map(eleitor, InformacoesEleitorDTO.class);
            buscarEleitor.add(dto);
        }



        return buscarEleitor;
    }

    public EleitorDTO editarEleitor(UUID id, EleitorDTO eleitorDTO) {

        Optional<Eleitor> eleitorId = repository.findById(id);

        eleitorDTO.setCriadoEm(LocalDateTime.now());
        eleitorDTO.setDeletadoEm (LocalDateTime.now());
        eleitorDTO.setAlteradoEm(LocalDateTime.now());

        Eleitor eleitor = modelMapper.map(eleitorId, Eleitor.class);
        eleitor.setNome(eleitorDTO.getNome());
        eleitor.setAlteradoEm(eleitorDTO.getAlteradoEm());

        Eleitor eleitorAtualizado = repository.save(eleitor);

        EleitorDTO eleitorAtualizadoDTO = modelMapper.map(eleitorAtualizado, EleitorDTO.class);

        return eleitorAtualizadoDTO;
    }

    public void deletarEleitor(UUID id) {

        repository.deleteById(id);
    }

    private void validarEleitor(EleitorDTO eleitorDTO){


        String eleitor = eleitorDTO.getCpf();
        List<Eleitor> eleitoresExistente = repository.findAll();

        for (Eleitor eleitorDaLista : eleitoresExistente) {
            if (eleitorDaLista.getCpf().equals(eleitor)) {
                throw new VotoDuplicadoException("Já existe um eleitor com essas caracteristicas.");
            }
        }
    }

    private Optional<Eleitor> procurarPeloId(UUID id){
        Optional<Eleitor> eleitorId  = repository.findById(id);

        if (eleitorId.isPresent()) {
            return eleitorId;
        } else {
            throw new IdNaoEncontradoException("Eleitor não encontrado");
        }

    }

}
