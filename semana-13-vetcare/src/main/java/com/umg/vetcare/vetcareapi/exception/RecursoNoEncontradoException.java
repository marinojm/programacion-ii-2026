package com.umg.vetcare.vetcareapi.exception;

public class RecursoNoEncontradoException
        extends RuntimeException {

    public RecursoNoEncontradoException(
            String mensaje) {

        super(mensaje);
    }
}