package com.luizcarlos.api.controller;


import com.luizcarlos.api.model.dtos.CandidatoDTO;

import com.luizcarlos.api.model.dtos.InformacoesCandidatoDTO;
import com.luizcarlos.api.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

@Autowired
CandidatoService service;

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<CandidatoDTO> criarCandidato(@RequestBody CandidatoDTO candidato){


    CandidatoDTO candidatoCriado= service.criarCandidato(candidato);

    return ResponseEntity.status(200).body(candidatoCriado);
}

@GetMapping("/{id}")
@ResponseStatus(HttpStatus.OK)
public ResponseEntity<InformacoesCandidatoDTO> buscarPorId(@PathVariable UUID id){

    InformacoesCandidatoDTO candidatoId = service.buscarPorId(id);
    return ResponseEntity.ok(candidatoId);
}


@PutMapping("/atualizar-candidato/{id}")
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<CandidatoDTO> editarCandidato(@PathVariable UUID id, @RequestBody CandidatoDTO candidato){

    CandidatoDTO candidatoAtualizado = service.editarCandidato(id,candidato);

    return ResponseEntity.status(200).body(candidatoAtualizado);
}

@GetMapping("/listar")
@ResponseStatus(HttpStatus.OK)
public ResponseEntity<List<InformacoesCandidatoDTO>> listarCandidatos(){



    return ResponseEntity.ok(service.listarTodosCandidatos());
}


@DeleteMapping("/deletar/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public ResponseEntity<Void> deletarCandidato(@PathVariable UUID id){
    

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  
}


}
