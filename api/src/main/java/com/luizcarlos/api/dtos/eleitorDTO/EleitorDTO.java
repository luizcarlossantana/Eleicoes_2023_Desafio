package com.luizcarlos.api.dtos.eleitorDTO;

import java.time.LocalDateTime;
import java.util.UUID;


import com.luizcarlos.api.model.Cargo;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class EleitorDTO {

    private UUID id;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String whatsapp;
    @NotNull
    private String estado;
    @NotNull
    private String cidade;
    @NotNull
    private String cpf;

    private Cargo cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alteradoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletadoEm;
}
