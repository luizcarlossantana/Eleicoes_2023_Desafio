package com.luizcarlos.api.model.dtos.votosDTO;

import com.luizcarlos.api.model.Votos;
import lombok.Data;

import java.util.UUID;
@Data
public class InformacoesVotosDTO {

    private UUID idCargo;
    private String nomeCargo;
    private long votos;
    private UUID idCandidato;
    private String nomeCandidato;
}
