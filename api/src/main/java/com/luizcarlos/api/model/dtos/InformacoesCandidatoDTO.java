package com.luizcarlos.api.model.dtos;

import lombok.Data;

@Data
public class InformacoesCandidatoDTO {
    private String nome;
    private Integer numero;
    private String legenda;
    private String cargo;
}
