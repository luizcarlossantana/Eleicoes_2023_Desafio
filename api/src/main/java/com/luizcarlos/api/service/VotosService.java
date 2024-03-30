package com.luizcarlos.api.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.luizcarlos.api.exception.IdNaoEncontradoException;
import com.luizcarlos.api.exception.VotoDuplicadoException;
import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Eleitor;
import com.luizcarlos.api.model.Votos;
import com.luizcarlos.api.dtos.votosDTO.InformacoesVotosDTO;
import com.luizcarlos.api.dtos.votosDTO.VotosDTO;
import com.luizcarlos.api.repository.CandidatoRepository;
import com.luizcarlos.api.repository.CargoRepository;
import com.luizcarlos.api.repository.EleitorRepository;
import com.luizcarlos.api.repository.VotosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private CargoRepository cargoRepository;

    @Autowired
    private ModelMapper modelMapper;


    public VotosDTO criarVotos(VotosDTO votoDTO, @JsonDeserialize(using = UUIDDeserializer.class) UUID idEleitor) {

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


    public List<List<InformacoesVotosDTO>> buscarCandidatosPorCargo(){


        List<InformacoesVotosDTO> listaDeInformacoesVotos = new ArrayList<>();
        List<Candidato> listaCandidatos = candidatoRepository.findAll();
        List<Votos> listaVotos = repository.findAll();
        List<List<InformacoesVotosDTO>> listasPorCargo = new ArrayList<>();

        for (Candidato candidato : listaCandidatos) {
            UUID idCandidato = candidato.getId();
            Integer contagemDeVotos = 0;

            for (Votos voto : listaVotos) {
                if (idCandidato.equals(voto.getCandidato().getId())) {
                    contagemDeVotos++;
                }
            }

            InformacoesVotosDTO contagemDTO = new InformacoesVotosDTO();
            contagemDTO.setIdCargo(candidato.getCargo().getId());
            contagemDTO.setNomeCargo(candidato.getCargo().getNome());
            contagemDTO.setIdCandidato(idCandidato);
            contagemDTO.setNomeCandidato(candidato.getNome());
            contagemDTO.setVotos(contagemDeVotos);

            listaDeInformacoesVotos.add(contagemDTO);


        }



        for (InformacoesVotosDTO dto : listaDeInformacoesVotos) {
            UUID idCargo = dto.getIdCargo();

            boolean listaEncontrada = false;

            // Itera sobre as listas existentes
            for (List<InformacoesVotosDTO> lista : listasPorCargo) {
                // Se a lista não estiver vazia e o ID do cargo corresponder, adiciona o DTO
                if (lista.size() > 0 && lista.get(0).getIdCargo() == idCargo) {
                    lista.add(dto);
                    listaEncontrada = true;
                    break;
                }
            }

            // Se não encontrou uma lista para o cargo, cria uma nova
            if (!listaEncontrada) {
                List<InformacoesVotosDTO> novaLista = new ArrayList<>();
                novaLista.add(dto);
                listasPorCargo.add(novaLista);
            }
        }
        return listasPorCargo;
}

    public List<InformacoesVotosDTO> buscarVencedorPorCargo() {

        List<InformacoesVotosDTO> listaDosVencedores = new ArrayList<>();
       List<List<InformacoesVotosDTO>> listasPorCargo = buscarCandidatosPorCargo();

        for (List<InformacoesVotosDTO> concorrente: listasPorCargo) {

            InformacoesVotosDTO comparante = concorrente.get(1);
            for (InformacoesVotosDTO  rival: concorrente) {

                if (rival.getVotos()>= comparante.getVotos()){
                    comparante = rival;

                    listaDosVencedores.add(comparante);
                }
            }
        }
        return listaDosVencedores;
}





    private void validarVotoDoEleitor(VotosDTO votoDTO) throws VotoDuplicadoException {


        UUID eleitor = votoDTO.getEleitor().getId();
        Candidato candidato = procurarCandidatoPorId(votoDTO.getCandidato().getId()).get();
        List<Votos> votosExistente = repository.findAll();

        for (Votos votosDaLista : votosExistente) {

            if (
                    votosDaLista.getEleitor().getId().equals(eleitor) && votosDaLista.getCandidato().getCargo().getId().equals(candidato.getCargo().getId()) ) {
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
