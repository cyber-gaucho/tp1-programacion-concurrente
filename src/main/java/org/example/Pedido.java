package org.example;

public class Pedido {
    private static int contador = 0;
    private final int id;

    public Pedido() {
        this.id = ++contador;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Pedido #" + id;
    }
}