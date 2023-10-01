package com.luizcarlos.api.repository;

import java.util.UUID;

import com.luizcarlos.api.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

}