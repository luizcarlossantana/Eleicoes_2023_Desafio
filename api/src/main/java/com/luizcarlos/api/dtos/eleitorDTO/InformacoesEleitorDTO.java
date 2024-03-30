package com.luizcarlos.api.dtos.eleitorDTO;

import java.util.UUID;

import lombok.Data;

@Data
public class InformacoesEleitorDTO {

    private UUID id;
    private String nome;
    private String cpf;
}