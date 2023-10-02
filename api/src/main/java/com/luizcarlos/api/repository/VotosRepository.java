package com.luizcarlos.api.repository;

import com.luizcarlos.api.model.Votos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotosRepository extends JpaRepository<Votos, UUID> {
}
