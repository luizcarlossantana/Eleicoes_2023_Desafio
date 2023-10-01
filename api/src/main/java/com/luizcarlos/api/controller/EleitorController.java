package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.dtos.EleitorDTO;
import com.luizcarlos.api.model.dtos.InformacoesEleitorDTO;
import com.luizcarlos.api.service.EleitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/eleitores")
public class EleitorController {

    @Autowired
    private EleitorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EleitorDTO> criarEleitor(@RequestBody EleitorDTO eleitor) {

        EleitorDTO eleitorCriado = service.criarEleitor(eleitor);

        return ResponseEntity.status(200).body(eleitorCriado);
    }

    @PutMapping("/atualizar-eleitor/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EleitorDTO> editarEleitor(@PathVariable UUID id, @RequestBody EleitorDTO eleitor) {

        EleitorDTO eleitorAtualizado = service.editarEleitor(id, eleitor);

        return ResponseEntity.status(200).body(eleitorAtualizado);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InformacoesEleitorDTO>> listarEleitor() {

        return ResponseEntity.ok(service.listarTodosEleitores());
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarEleitor(@PathVariable UUID id) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
