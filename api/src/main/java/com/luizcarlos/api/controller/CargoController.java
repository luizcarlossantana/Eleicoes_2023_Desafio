package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.dtos.CargoDTO;
import com.luizcarlos.api.model.dtos.InformacoesCargosDTO;
import com.luizcarlos.api.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cargos")
public class CargoController {

        @Autowired
        CargoService service;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<CargoDTO> criarCargo(@RequestBody CargoDTO cargo) {

            CargoDTO cargoCriado = service.criarCargo(cargo);

            return ResponseEntity.status(200).body(cargoCriado);
        }

        @PutMapping("/atualizar-cargo/{id}")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<CargoDTO> editarCargo(@PathVariable UUID id, @RequestBody CargoDTO cargo) {

            CargoDTO cargoAtualizado = service.editarCargo(id, cargo);

            return ResponseEntity.status(200).body(cargoAtualizado);
        }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InformacoesCargosDTO> buscarPorId(@PathVariable UUID id){

        InformacoesCargosDTO cargoId = service.buscarPorId(id);
        return ResponseEntity.ok(cargoId);
    }

        @GetMapping("/listar")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<CargoDTO>> listarCargo() {

            return ResponseEntity.ok(service.listarTodosCargos());
        }

        @DeleteMapping("/deletar/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<Void> deletarCargo(@PathVariable UUID id) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }
