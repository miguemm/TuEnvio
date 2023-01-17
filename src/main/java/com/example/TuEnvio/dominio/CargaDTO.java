package com.example.TuEnvio.dominio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Clase que define el Objeto Carga que sera enviado por el Cliente
 */
@Data
public class CargaDTO {

    /** Tipo de producto que carga el camion*/
    @NotBlank(message = "{restriccion.cadenaVacia}")
    @Size(min = 1, max = 45, message = "{restriccion.tamanioCadena2}")
    private String tipoProducto;

    /** Numero de cajas que puede transportar el camion */
    @Positive(message = "{restriccion.capacidad}")
    private Integer cantidad;
}
