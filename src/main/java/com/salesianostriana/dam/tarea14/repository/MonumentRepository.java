package com.salesianostriana.dam.tarea14.repository;

import com.salesianostriana.dam.tarea14.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, Long> {

}
