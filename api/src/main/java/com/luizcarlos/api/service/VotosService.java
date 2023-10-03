package com.luizcarlos.api.service;

import com.luizcarlos.api.exception.IdNaoEncontradoException;
import com.luizcarlos.api.exception.VotoDuplicadoException;
import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.model.Eleitor;
import com.luizcarlos.api.model.Votos;
import com.luizcarlos.api.model.dtos.*;
import com.luizcarlos.api.repository.CandidatoRepository;
import com.luizcarlos.api.repository.EleitorRepository;
import com.luizcarlos.api.repository.VotosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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


    public VotosDTO criarVotos(VotosDTO votoDTO, UUID idEleitor) {

        validarVotoDoEleitor(votoDTO);

        Optional<Candidato> candidato = procurarCandidatoPorId(votoDTO.getCandidato().getId());
        Optional<Eleitor> eleitor = procurarEleitorPorId(idEleitor);

        votoDTO.setCandidato(candidato.get());
        votoDTO.setEleitor(eleitor.get());


        Votos voto = modelMapper.map(votoDTO, Votos.class);
        Votos criandoVoto = repository.save(voto);
        votoDTO = modelMapper.map(criandoVoto, VotosDTO.class);

        return votoDTO;
    }


    public List<InformacoesVotosDTO> buscarVencedorPorCargo() {


        return listaDosVencedoresDTO();


    }

    private List<InformacoesVotosDTO> listaDosVencedoresDTO() {

        List<InformacoesVotosDTO> listaDeInformacoesVotos = new ArrayList<>();
        List<InformacoesVotosDTO> listaDosVencedores = new ArrayList<>();
        List<Candidato> listaCandidatos = candidatoRepository.findAll();
        List<Votos> listaVotos = repository.findAll();
        List<InformacoesVotosDTO> lista = new ArrayList<>();

        for (Candidato candidato : listaCandidatos) {
            UUID idCandidato = candidato.getId();
            long contagemDeVotos = 0;

            for (Votos voto : listaVotos) {
                if (idCandidato.equals(voto.getCandidato().getId())) {
                    contagemDeVotos++;
                }
            }

            InformacoesVotosDTO contagemDTO = new InformacoesVotosDTO();
            contagemDTO.setIdCargo(candidato.getCargo().getId());
            contagemDTO.setNomeCargo(candidato.getCargo().getNome());
            contagemDTO.setIdCandidatoVencedor(idCandidato);
            contagemDTO.setNomeCandidatoVencedor(candidato.getNome());
            contagemDTO.setVotos(contagemDeVotos);

            listaDeInformacoesVotos.add(contagemDTO);
        }

        InformacoesVotosDTO candidato = listaDeInformacoesVotos.get(1);


        for (InformacoesVotosDTO concorrente : listaDeInformacoesVotos) {

            if (candidato.getIdCargo() == concorrente.getIdCargo() && candidato.getVotos()<= concorrente.getVotos()) {

                candidato = concorrente;


               listaDosVencedores.add(candidato);



            }



        }










        return listaDosVencedores;


}


    public List<InformacoesVotosDTO> contarVotosPorCandidato() {
        return repository.contarVotosPorCandidato();
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

        Optional<Candidato> candidatoId = candidatoRepository.findById(id);

        if (candidatoId.isPresent()) {
            return candidatoId;
        } else {
            throw new IdNaoEncontradoException("Candidato não encontrado");
        }
    }

    private Optional<Eleitor> procurarEleitorPorId (UUID id){
        Optional<Eleitor> eleitorId = eleitorRepository.findById(id);
        if (eleitorId.isPresent()) {
            return eleitorId;
        } else {
            throw new IdNaoEncontradoException("Eleitor não encontrado");
        }
    }


    private List<Candidato> listarTodosCandidatos(){

        List<Candidato> candidatos = candidatoRepository.findAll();

        return candidatos;



    }
}
