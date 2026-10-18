package com.umg.vetcare.vetcareapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            RecursoNoEncontradoException.class
    )
    public ResponseEntity<Map<String, Object>>
    manejarNoEncontrado(
            RecursoNoEncontradoException error) {

        return crearRespuesta(
                HttpStatus.NOT_FOUND,
                error.getMessage()
        );
    }

    @ExceptionHandler(
            CodigoDuplicadoException.class
    )
    public ResponseEntity<Map<String, Object>>
    manejarDuplicado(
            CodigoDuplicadoException error) {

        return crearRespuesta(
                HttpStatus.CONFLICT,
                error.getMessage()
        );
    }

    private ResponseEntity<Map<String, Object>>
    crearRespuesta(HttpStatus estado, String mensaje) {
        Map<String, Object> respuesta =
                new LinkedHashMap<>();

        respuesta.put(
                "fecha",
                LocalDateTime.now()
        );

        respuesta.put(
                "estado",
                estado.value()
        );

        respuesta.put(
                "mensaje",
                mensaje
        );

        return ResponseEntity
                .status(estado)
                .body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>
    manejarValidacion(MethodArgumentNotValidException error) {
        Map<String, String> errores =
                new LinkedHashMap<>();
        error.getBindingResult()
                .getFieldErrors()
                .forEach(campo -> errores.put(
                        campo.getField(),
                        campo.getDefaultMessage()
                ));

        Map<String, Object> respuesta =
                new LinkedHashMap<>();

        respuesta.put("estado",HttpStatus.BAD_REQUEST.value());
        respuesta.put("mensaje","Los datos enviados no son válidos");
        respuesta.put("errores",errores);

        return ResponseEntity
                .badRequest()
                .body(respuesta);
    }
}