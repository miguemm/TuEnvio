package com.example.TuEnvio.repositorio;

import com.example.TuEnvio.modelo.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRespositorio extends JpaRepository<Camion, String> {
}
