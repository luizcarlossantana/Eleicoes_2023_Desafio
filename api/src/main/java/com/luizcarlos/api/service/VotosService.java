package com.luizcarlos.api.service;

import com.luizcarlos.api.exception.VotoDuplicadoException;
import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Eleitor;
import com.luizcarlos.api.model.Votos;
import com.luizcarlos.api.model.dtos.VotosDTO;
import com.luizcarlos.api.repository.CandidatoRepository;
import com.luizcarlos.api.repository.EleitorRepository;
import com.luizcarlos.api.repository.VotosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VotosService {

    @Autowired
    private VotosRepository repository;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private EleitorRepository eleitorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public VotosDTO criarVotos(VotosDTO votoDTO){

        validarVotoDoEleitor(votoDTO);

        Optional<Candidato> candidato = procurarCandidatoPorId(votoDTO.getCandidato().getId());
        Optional<Eleitor> eleitor = procurarEleitorPorId(votoDTO.getEleitor().getId());

        votoDTO.setCandidato(candidato.get());
        votoDTO.setEleitor(eleitor.get());


        Votos voto = modelMapper.map(votoDTO,Votos.class);
        Votos criandoVoto = repository.save(voto);
        votoDTO = modelMapper.map(criandoVoto,VotosDTO.class);

        return votoDTO;
    }

    private void validarVotoDoEleitor(VotosDTO votoDTO) throws VotoDuplicadoException {


        UUID eleitor = votoDTO.getEleitor().getId();
        List<Votos> votosExistente = repository.findAll();

        for (Votos votosDaLista : votosExistente) {
            if (votosDaLista.getEleitor().getId().equals(eleitor)) {
                throw new VotoDuplicadoException("Já existe um voto para este eleitor.");
            }
        }


    }



    private Optional<Candidato> procurarCandidatoPorId (UUID id){

        return candidatoRepository.findById(id);
    }

    private Optional<Eleitor> procurarEleitorPorId (UUID id){
        return eleitorRepository.findById(id);
    }
}
