package com.luizcarlos.api.model.dtos;

import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Eleitor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class VotosDTO {

    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;
    private Candidato candidato;
    private Eleitor eleitor;
}
