package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.dtos.votosDTO.InformacoesVotosDTO;
import com.luizcarlos.api.model.dtos.votosDTO.VotosDTO;
import com.luizcarlos.api.service.VotosService;
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

    @GetMapping("/relatorio/cargo/candidatos-vencedores")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InformacoesVotosDTO>> buscarVencedorPorCargo(){


        return ResponseEntity.ok(service.buscarVencedorPorCargo());
    }

    @GetMapping("/relatorio/candidatos-Cargos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<List<InformacoesVotosDTO>>> buscarCandidatosPorCargo(){


        return ResponseEntity.ok(service.buscarCandidatosPorCargo());
    }
}
