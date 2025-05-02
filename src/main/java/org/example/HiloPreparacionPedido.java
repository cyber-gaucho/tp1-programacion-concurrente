package org.example;

public class HiloPreparacionPedido extends Thread {
    private final CentroDeAlmacenamiento centro;
    private final Pedido pedido;

    public HiloPreparacionPedido(CentroDeAlmacenamiento centro, Pedido pedido) {
        this.centro = centro;
        this.pedido = pedido;
    }

    @Override
    public void run() {
        // Intentamos asignar el pedido a un casillero vacío
        Casillero casillero = centro.obtenerCasillero(pedido);

        if (casillero == null) {
            System.out.println("No se pudo asignar el pedido " + pedido);
        } else {
            // El pedido fue asignado correctamente
            System.out.println("Pedido " + pedido + " asignado correctamente.");
        }
    }
}