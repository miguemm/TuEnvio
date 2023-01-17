package com.example.TuEnvio.excepciones.custom;

public class CapacidadExcedidaExcepcion extends RuntimeException{

    public CapacidadExcedidaExcepcion(String message) {
        super("Peticino rechazada " + message);
    }
}
