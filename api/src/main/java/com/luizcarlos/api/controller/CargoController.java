package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.dtos.CandidatoDTO;
import com.luizcarlos.api.model.dtos.CargoDTO;
import com.luizcarlos.api.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    CargoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CargoDTO> criarCargo(@RequestBody CargoDTO cargo){

        CargoDTO cargoCriado= service.criarCargo(cargo);

        return ResponseEntity.status(200).body(cargoCriado);
    }
}