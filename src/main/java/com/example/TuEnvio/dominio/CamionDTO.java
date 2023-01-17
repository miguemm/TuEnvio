package com.example.TuEnvio.dominio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Clase que define el Objeto Camion que sera expuesto al cliente
 */
@Data
public class CamionDTO {

    /** Id, Placa del camion */
    @Size(min = 1, max = 30, message = "{restriccion.tamanioCadena1}")
    @NotBlank(message = "{restriccion.cadenaVacia}")
    private String placa;

    /** Tipo de producto que carga el camion*/
    @NotBlank(message = "{restriccion.cadenaVacia}")
    @Size(min = 1, max = 45, message = "{restriccion.tamanioCadena2}")
    private String tipoProducto;

    /** Numero de cajas que puede transportar el camion */
    @Positive(message = "{restriccion.capacidad}")
    private Integer capacidad;
}
