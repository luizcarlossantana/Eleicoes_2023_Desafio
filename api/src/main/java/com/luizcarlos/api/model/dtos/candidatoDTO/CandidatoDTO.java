package com.luizcarlos.api.model.dtos.candidatoDTO;

import com.luizcarlos.api.model.Cargo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;
@Data

public class CandidatoDTO {
    private UUID id;
    private String nome;
    private Integer numero;
    private String legenda;
    private Cargo cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alteradoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletadoEm;

}
