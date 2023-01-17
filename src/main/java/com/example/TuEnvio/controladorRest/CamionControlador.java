package com.example.TuEnvio.controladorRest;

import com.example.TuEnvio.dominio.CamionDTO;
import com.example.TuEnvio.dominio.Respuesta;
import com.example.TuEnvio.negocio.CamionServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/camion")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class CamionControlador {
    @Autowired
    private CamionServicio camionServicio;

    @PostMapping("")
    ResponseEntity<Respuesta> createCamion(@Valid @RequestBody CamionDTO nuevoCamion){
        return new ResponseEntity<>(camionServicio.createCamion(nuevoCamion), HttpStatus.CREATED);
    }

    @DeleteMapping("")
    ResponseEntity<Respuesta> deleteCamion(@RequestParam @NotBlank(message = "{restriccion.cadenaVacia}") String placa){
        return new ResponseEntity<>(camionServicio.deleteCamion(placa), HttpStatus.OK);
    }

    @PutMapping("")
    ResponseEntity<Respuesta> updateCamion(@Valid @RequestBody CamionDTO nuevoCamion){
        return new ResponseEntity<>(camionServicio.updateCamion(nuevoCamion), HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<Respuesta> getCamionByCode(@RequestParam @NotBlank(message = "{restriccion.cadenaVacia}") String placa){
        return new ResponseEntity<>(camionServicio.getCamionByPlaca(placa), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<Respuesta> findAllByPlacaAsc(@RequestParam @PositiveOrZero int pagina, @RequestParam @PositiveOrZero int tamanio){
        return new ResponseEntity<>(camionServicio.findAllByPlacaAsc(pagina,tamanio), HttpStatus.OK);
    }
}
