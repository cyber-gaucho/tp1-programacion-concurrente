package org.example;

public class Usuario {
    private final String nombre;
    private final int id;

    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
