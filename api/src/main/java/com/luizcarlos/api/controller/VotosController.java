package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.dtos.VotosDTO;
import com.luizcarlos.api.service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private VotosService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VotosDTO> criarVotos(@RequestBody VotosDTO voto){

        VotosDTO votoCriado = service.criarVotos(voto);

        return ResponseEntity.ok().body(votoCriado);

    }
}
