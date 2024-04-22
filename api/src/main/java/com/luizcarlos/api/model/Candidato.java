package com.luizcarlos.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Candidato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonDeserialize(using = UUIDDeserializer.class)
    private UUID id;
    private String nome;
    private Integer numero;
    private String legenda;

    @ManyToOne
    @JoinColumn(name = "idCargo")
    private Cargo cargo;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alteradoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletadoEm;

    private Votos voto;
}
