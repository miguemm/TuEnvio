package com.example.TuEnvio.excepciones.custom;

public class EntidadExisteExcepcion extends RuntimeException{

    public EntidadExisteExcepcion(String message) {
        super("La entidad " + message + " ya existe");
    }
}
