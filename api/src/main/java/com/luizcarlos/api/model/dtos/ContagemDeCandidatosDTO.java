package com.luizcarlos.api.model.dtos;

import lombok.Data;

import java.util.UUID;
@Data
public class ContagemDeCandidatosDTO {
    private UUID idCargo;
    private String nomeCargo;
    private long votos;
    private UUID idCandidato;
    private String nomeCandidato;
}

