package com.luizcarlos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.luizcarlos.api.model.Eleitor;
import com.luizcarlos.api.model.dtos.EleitorDTO;
import com.luizcarlos.api.model.dtos.InformacoesEleitorDTO;
import com.luizcarlos.api.repository.EleitorRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EleitorService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private EleitorRepository repository;


    public EleitorDTO criarEleitor(EleitorDTO eleitorDTO) {

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

}
