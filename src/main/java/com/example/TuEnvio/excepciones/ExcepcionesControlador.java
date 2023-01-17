package com.example.TuEnvio.excepciones;

import com.example.TuEnvio.dominio.Respuesta;
import com.example.TuEnvio.excepciones.custom.EntidadExisteExcepcion;
import com.example.TuEnvio.excepciones.custom.EntidadNoExisteExcepcion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcepcionesControlador {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Bad Request: " + e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadExisteExcepcion.class)
    public ResponseEntity<Respuesta> entityExistsException(final HttpServletRequest req, final EntidadExisteExcepcion ex) {

        Respuesta respuesta = Respuesta.builder()
                .mensajeDesarrollador(ex.getMessage() + "\n" + req.getRequestURL().toString() + "\n" + req.getMethod())
                .mensajeUsuario(ex.getMessage())
                .data(HttpStatus.NOT_ACCEPTABLE.value())
                .build();

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntidadNoExisteExcepcion.class)
    public ResponseEntity<Respuesta> entityDoesntExistsException(final HttpServletRequest req, final EntidadNoExisteExcepcion ex) {
        Respuesta respuesta = Respuesta.builder()
                .mensajeDesarrollador(ex.getMessage() + "\n" + req.getRequestURL().toString() + "\n" + req.getMethod())
                .mensajeUsuario(ex.getMessage())
                .data(HttpStatus.NOT_ACCEPTABLE.value())
                .build();

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_ACCEPTABLE);
    }


}
