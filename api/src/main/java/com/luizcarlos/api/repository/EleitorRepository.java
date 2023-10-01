package com.luizcarlos.api.repository;

import java.util.UUID;

import com.luizcarlos.api.model.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, UUID>{

}
