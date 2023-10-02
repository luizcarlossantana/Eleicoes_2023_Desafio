package com.luizcarlos.api.model.dtos;

import java.time.LocalDateTime;
import java.util.UUID;


import com.luizcarlos.api.model.Cargo;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class EleitorDTO {

    private UUID id;
    private String nome;
    private String cpf;
    private Cargo cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alteradoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletadoEm;
}
