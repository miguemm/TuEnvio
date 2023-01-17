package com.example.TuEnvio.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Clase que define la entidad Camion para el mapeo de objetos relacional a la base de datos
 */
@Entity
@Data
public class Camion {

    /** Id, Placa del camion */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 30)
    @NotNull
    private Integer placa;

    /** Tipo de producto que carga el camion*/
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_producto")
    private String tipoProducto;

    /** Numero de cajas que puede transportar el camion */
    @NotNull
    private Integer capacidad;

    /** Numero de cajas que se encuentran cargadas de en camion*/
    @NotNull
    @Column(name = "estado_carga", columnDefinition = "integer default 0")
    private Integer estadoCarga;

}
