package com.luizcarlos.api.model.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class InformacoesEleitorDTO {

    private UUID id;
    private String nome;
    private Integer cpf;
}