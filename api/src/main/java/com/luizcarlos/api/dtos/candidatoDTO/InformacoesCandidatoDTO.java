package com.luizcarlos.api.dtos.candidatoDTO;

import com.luizcarlos.api.model.Cargo;
import lombok.Data;

import java.util.UUID;

@Data
public class InformacoesCandidatoDTO {
    private UUID id;
    private String nome;
    private Integer numero;
    private String legenda;
    private Cargo cargo;
}
