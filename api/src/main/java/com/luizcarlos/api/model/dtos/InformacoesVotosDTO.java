package com.luizcarlos.api.model.dtos;

import com.luizcarlos.api.model.Votos;
import lombok.Data;

import java.util.UUID;
@Data
public class InformacoesVotosDTO {

    private UUID idCargo;
    private String nomeCargo;
    private long votos;
    private UUID idCandidatoVencedor;
    private String nomeCandidatoVencedor;
}
