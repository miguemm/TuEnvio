package com.example.TuEnvio.dominio;

import lombok.Builder;
import lombok.Data;

/**
 * Clase que define un DTO generico para las respuestas de las peticiones a los servicios Rest. Este encapsula el resultado del consumo de
 * las Apis y le adiciona atributos de control
 *
 * @param <T>
 *            Objeto de respuesta para las transacciones realizadas en cada Api *
 */
@Data
@Builder
public class Respuesta<T> {
    /** Mensaje informativo para el usuario */
    private String mensajeUsuario;

    /** Mensaje informativo para los desarrolladores */
    private String mensajeDesarrollador;

    /** Objeto con la respuesta de la transaccion */
    private T data;
}
