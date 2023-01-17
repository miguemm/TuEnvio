package com.example.TuEnvio.repositorio;

import com.example.TuEnvio.modelo.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRespositorio extends JpaRepository<Camion, String> {

    /** Query para obtener todos los camiones de un tipo disponibles*/
    List<Camion> findBytipoProducto(String tipoProducto);
}
