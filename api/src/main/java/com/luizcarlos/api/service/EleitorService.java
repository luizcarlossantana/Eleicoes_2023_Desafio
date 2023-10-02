package com.luizcarlos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.luizcarlos.api.exception.VotoDuplicadoException;
import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.model.Eleitor;
import com.luizcarlos.api.model.dtos.EleitorDTO;
import com.luizcarlos.api.model.dtos.InformacoesEleitorDTO;
import com.luizcarlos.api.repository.CargoRepository;
import com.luizcarlos.api.repository.EleitorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

        Optional<Cargo> cargo = cargoRepository.findById(eleitorDTO.getCargo().getId());
        eleitorDTO.setCargo(cargo.get());


        Eleitor eleitor = modelMapper.map(eleitorDTO, Eleitor.class);
        Eleitor eleitorCriado = repository.save(eleitor);
        eleitorDTO = modelMapper.map(eleitorCriado, EleitorDTO.class);

        return eleitorDTO;
    }

    public List<InformacoesEleitorDTO> listarTodosEleitores() {

        List<Eleitor> eleitores = repository.findAll();
        List<InformacoesEleitorDTO> buscarEleitor = eleitores.stream()
                .map(eleitor -> modelMapper.map(eleitor, InformacoesEleitorDTO.class))
                .collect(Collectors.toList());

        return buscarEleitor;
    }

    public EleitorDTO editarEleitor(UUID id, EleitorDTO eleitorDTO) {

        Optional<Eleitor> eleitorId = repository.findById(id);

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

}
