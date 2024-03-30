package com.luizcarlos.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eleitores")
@Data
public class Eleitor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonDeserialize(using = UUIDDeserializer.class)
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

    private String senha;

    @ManyToOne
    @JoinColumn(name = "idCargo")
    private Cargo cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alteradoEm;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletadoEm;
}
