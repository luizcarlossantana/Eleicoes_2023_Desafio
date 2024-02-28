package com.luizcarlos.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Votos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCandidato")
    private Candidato candidato;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idEleitor")
    private Eleitor eleitor;
}
