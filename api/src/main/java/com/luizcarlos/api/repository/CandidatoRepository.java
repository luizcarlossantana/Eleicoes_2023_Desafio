package com.luizcarlos.api.repository;

import com.luizcarlos.api.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
     List<Candidato> findCandidatoByNomeStartingWith(String nome);
     List<Candidato> findByNomeStartingWithAndLegendaStartingWith(String nome, String legenda);

}
