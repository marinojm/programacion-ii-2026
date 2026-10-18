package com.umg.vetcare.vetcareapi.exception;

public class CodigoDuplicadoException extends RuntimeException {

    public CodigoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}