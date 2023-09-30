package com.luizcarlos.api.service;

import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.dtos.CandidatoDTO;
import com.luizcarlos.api.model.dtos.InfomacoesCandidatoDTO;
import com.luizcarlos.api.repository.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deletarCandidato(UUID id){

        repository.deleteById(id);


    }
}
