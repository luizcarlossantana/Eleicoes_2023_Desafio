package com.luizcarlos.api.model.dtos.votosDTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.luizcarlos.api.model.Candidato;
import com.luizcarlos.api.model.Eleitor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class VotosDTO {

    @JsonDeserialize(using = UUIDDeserializer.class)
    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;
    private Candidato candidato;
    private Eleitor eleitor;
}
