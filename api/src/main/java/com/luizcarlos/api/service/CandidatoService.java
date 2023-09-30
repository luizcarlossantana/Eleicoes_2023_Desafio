package com.luizcarlos.api.service;

import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.dtos.CandidatoDTO;
import com.luizcarlos.api.model.dtos.InfomacoesCandidatoDTO;
import com.luizcarlos.api.repository.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CandidatoRepository repository;

    public CandidatoDTO criarCandidato(CandidatoDTO candidatoDTO){

        Candidato candidato = modelMapper.map(candidatoDTO,Candidato.class);
        Candidato candidatoCriado = repository.save(candidato);
        candidatoDTO = modelMapper.map(candidatoCriado,CandidatoDTO.class);

        return candidatoDTO;

    }

    public List<InfomacoesCandidatoDTO> listarTodosCandidatos(){

        List<Candidato> candidatos = repository.findAll();
        List<InfomacoesCandidatoDTO> buscarCandidato =   candidatos.stream()
                .map(candidato -> modelMapper.map(candidato, InfomacoesCandidatoDTO.class))
                .collect(Collectors.toList());
        return buscarCandidato;


    }

    public CandidatoDTO editarCandidato(UUID id,CandidatoDTO candidatoDTO){

       Optional<Candidato> candidatoId = repository.findById(id);
       Candidato candidato = candidatoId.get();
       candidato.setNome(candidatoDTO.getNome());
       candidato.setCargo(candidatoDTO.getCargo());
       candidato.setLegenda(candidatoDTO.getLegenda());
       candidato.setAlteradoEm(candidatoDTO.getAlteradoEm());
       candidato.setNumero(candidatoDTO.getNumero());

       Candidato candidatoAtualizado = repository.save(candidato);

       CandidatoDTO candidatoAtualizadoDTO = modelMapper.map(candidatoAtualizado,CandidatoDTO.class);

       return candidatoAtualizadoDTO;


    }


    public void deletarCandidato(UUID id){

        repository.deleteById(id);


    }
}
