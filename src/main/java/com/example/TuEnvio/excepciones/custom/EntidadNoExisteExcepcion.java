package com.example.TuEnvio.excepciones.custom;

public class EntidadNoExisteExcepcion extends RuntimeException{

    public EntidadNoExisteExcepcion(String message) {
        super("La entidad " + message + " no existe");
    }

}
