package com.luizcarlos.api.repository;

import com.luizcarlos.api.model.Votos;
import com.luizcarlos.api.model.dtos.InformacoesVotosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VotosRepository extends JpaRepository<Votos, UUID> {

    @Query(value = "SELECT c.id AS idCandidato, c.nome AS nomeCandidato, c.idcargo AS idCargo, c.cargo_nome AS nomeCargo, COUNT(v.id) AS votos " +
            "FROM candidato c LEFT JOIN votos v ON c.id = v.idcandidato " +
            "GROUP BY c.id, c.nome, c.idcargo, c.cargo_nome " +
            "ORDER BY votos DESC", nativeQuery = true)
    List<InformacoesVotosDTO> contarVotosPorCandidato();
}
