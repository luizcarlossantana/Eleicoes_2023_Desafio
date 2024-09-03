package com.luizcarlos.api.service;

import com.luizcarlos.api.exception.IdNaoEncontradoException;
import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.dtos.candidatoDTO.CandidatoDTO;
import com.luizcarlos.api.dtos.candidatoDTO.InformacoesCandidatoDTO;
import com.luizcarlos.api.repository.CandidatoRepository;
import com.luizcarlos.api.repository.CargoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidatoService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CandidatoRepository repository;
    @Autowired
    CargoRepository cargoRepository;

    public CandidatoDTO criarCandidato(CandidatoDTO candidatoDTO){

        validarCandidato(candidatoDTO);

        Optional<Cargo> cargo = cargoRepository.findById(candidatoDTO.getCargo());

        candidatoDTO.setCriadoEm(LocalDateTime.now());
        candidatoDTO.setDeletadoEm (LocalDateTime.now());
        candidatoDTO.setAlteradoEm(LocalDateTime.now());
        candidatoDTO.setCargo(cargo.get().getId());

        Candidato candidato = modelMapper.map(candidatoDTO,Candidato.class);
        candidato.setCargo(cargo.get());

        Candidato candidatoCriado = repository.save(candidato);
        candidatoDTO = modelMapper.map(candidatoCriado,CandidatoDTO.class);
        candidatoDTO.setCargo(candidato.getCargo().getId());

        return candidatoDTO;

    }

    public List<InformacoesCandidatoDTO> listarTodosCandidatos(){

        List<Candidato> candidatos = repository.findAll();
        List<InformacoesCandidatoDTO> buscarCandidato = new ArrayList<>();

        for (Candidato candidato : candidatos) {
            InformacoesCandidatoDTO dto = modelMapper.map(candidato, InformacoesCandidatoDTO.class);
            buscarCandidato.add(dto);
        }

        return buscarCandidato;



    }
    

        
        


    public InformacoesCandidatoDTO buscarPorId(UUID id){

        Candidato candidatoId = procurarPeloId(id).get();

        InformacoesCandidatoDTO candidatoDTO = modelMapper.map(candidatoId,InformacoesCandidatoDTO.class);
        return candidatoDTO;

    }
public void testandoCandidato(){
    System.out.println(repository.findCandidatoByNomeStartingWith("L"));
}
    private Optional<Candidato> procurarPeloId(UUID id){
        Optional<Candidato> candidatoId = repository.findById(id);

        if (candidatoId.isPresent()) {
            return candidatoId;
        } else {
            throw new IdNaoEncontradoException("Candidato não encontrado");
        }
    }

    private void validarCandidato(CandidatoDTO candidatoDTO){
        Integer candidato = candidatoDTO.getNumero();
        List<Candidato> candidatosExistente = repository.findAll();

        for (Candidato candidatoDaLista : candidatosExistente) {
            if (candidatoDaLista.getNumero().equals(candidato)) {
                throw new IdNaoEncontradoException("Já existe um candidato com essas caracteristicas.");
            }
        }

    }

    public CandidatoDTO editarCandidato(UUID id,CandidatoDTO candidatoDTO){

       Optional<Candidato> candidatoId = procurarPeloId(id);
       Candidato candidato = candidatoId.get();
       candidato.setNome(candidatoDTO.getNome());
       candidato.setCargo(null);
       candidato.setLegenda(candidatoDTO.getLegenda());
       candidato.setAlteradoEm(LocalDateTime.now());
       candidato.setNumero(candidatoDTO.getNumero());

       Candidato candidatoAtualizado = repository.save(candidato);

       CandidatoDTO candidatoAtualizadoDTO = modelMapper.map(candidatoAtualizado,CandidatoDTO.class);

       return candidatoAtualizadoDTO;


    }


    public void deletarCandidato(UUID id){

        repository.deleteById(id);


    }
}
