package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.dtos.InformacoesVotosDTO;
import com.luizcarlos.api.model.dtos.VotosDTO;
import com.luizcarlos.api.service.VotosService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private VotosService service;

    @PostMapping("/eleitor/{idEleitor}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VotosDTO> criarVotos(@RequestBody VotosDTO voto, @PathVariable UUID idEleitor){

        VotosDTO votoCriado = service.criarVotos(voto,idEleitor);

        return ResponseEntity.ok().body(votoCriado);

    }

    @GetMapping("/candidato/relatorio")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InformacoesVotosDTO>> buscarVencedorPorCargo(){


        return ResponseEntity.ok(service.buscarVencedorPorCargo());
    }
}
