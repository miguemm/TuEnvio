package com.example.TuEnvio.mapper;

import com.example.TuEnvio.dominio.CamionDTO;
import com.example.TuEnvio.modelo.Camion;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que retorna el objeto encargado de mapera un CamionDTO a la entidad Camion
 */
@Configuration
public class CamionMapper {
    @Bean
    public ModelMapper camionModelMapper(){
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }
}
