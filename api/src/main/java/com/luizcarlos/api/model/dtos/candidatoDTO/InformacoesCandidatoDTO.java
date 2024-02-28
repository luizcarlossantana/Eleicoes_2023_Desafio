package com.luizcarlos.api.model.dtos.candidatoDTO;

import com.luizcarlos.api.model.Cargo;
import com.luizcarlos.api.model.dtos.cargoDTO.CargoDTO;
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
