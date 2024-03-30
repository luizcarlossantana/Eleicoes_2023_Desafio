package com.luizcarlos.api.service;

import com.luizcarlos.api.exception.IdNaoEncontradoException;
import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.dtos.cargoDTO.CargoDTO;
import com.luizcarlos.api.dtos.cargoDTO.InformacoesCargosDTO;
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
public class CargoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CargoRepository repository;

    public CargoDTO criarCargo(CargoDTO cargoDTO) {

        cargoDTO.setCriadoEm(LocalDateTime.now());
        cargoDTO.setDeletadoEm(LocalDateTime.now());
        cargoDTO.setAlteradoEm(LocalDateTime.now());

        Cargo cargo = modelMapper.map(cargoDTO, Cargo.class);

        Cargo cargoCriado = repository.save(cargo);
        cargoDTO = modelMapper.map(cargoCriado, CargoDTO.class);

        return cargoDTO;

    }

    public InformacoesCargosDTO buscarPorId(UUID id) {
        Optional<Cargo> cargo = procurarPeloId(id);

        InformacoesCargosDTO cargosDTO = modelMapper.map(cargo, InformacoesCargosDTO.class);

        return cargosDTO;
    }

    private Optional<Cargo> procurarPeloId(UUID id){
        Optional<Cargo> cargoId = repository.findById(id);

        if (cargoId.isPresent()) {
            return cargoId;
        } else {
            throw new IdNaoEncontradoException("Cargo não encontrado");
        }
    }

    public List<CargoDTO> listarTodosCargos() {

        List<Cargo> cargos = repository.findAll();
        List<CargoDTO> buscarCargo = new ArrayList<>();

        for (Cargo cargo : cargos) {
            CargoDTO dto = modelMapper.map(cargo, CargoDTO.class);
            buscarCargo.add(dto);
        }

        return buscarCargo;

    }

    public CargoDTO editarCargo(UUID id, CargoDTO cargoDTO) {

        Optional<Cargo> cargoId = repository.findById(id);

        Cargo cargo = modelMapper.map(cargoId, Cargo.class);

        cargo.setNome(cargoDTO.getNome());
        cargo.setAlteradoEm(cargoDTO.getAlteradoEm());

        Cargo cargoAtualizado = repository.save(cargo);

        CargoDTO cargoAtualizadoDTO = modelMapper.map(cargoAtualizado, CargoDTO.class);

        return cargoAtualizadoDTO;
    }

    public void deletarCargo(UUID id) {

        repository.deleteById(id);
    }
}