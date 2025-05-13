package org.example.excepciones;

public class ListaVaciaException extends RuntimeException {
    public ListaVaciaException(String message) {
        super(message);
    }
}
