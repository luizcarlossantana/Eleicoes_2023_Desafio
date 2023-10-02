package com.luizcarlos.api.service;


import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.model.dtos.CargoDTO;
import com.luizcarlos.api.repository.CargoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CargoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CargoRepository repository;

    public CargoDTO criarCargo(CargoDTO cargoDTO){

        cargoDTO.setCriadoEm(LocalDateTime.now());
        cargoDTO.setDeletadoEm (LocalDateTime.now());
        cargoDTO.setAlteradoEm(LocalDateTime.now());

        Cargo cargo = modelMapper.map(cargoDTO, Cargo.class);

        Cargo cargoCriado = repository.save(cargo);
       cargoDTO = modelMapper.map(cargoCriado,CargoDTO.class);

        return cargoDTO;

    }

}
