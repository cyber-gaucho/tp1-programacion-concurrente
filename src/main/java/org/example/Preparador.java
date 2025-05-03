package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Preparador extends Thread {

    private static int contador = 0;
    private final int id;

    private final ArrayList<Pedido> pedidosNuevos;
    private final ArrayList<Pedido> pedidosPreparados;
    private final CentroDeAlmacenamiento centro;
    private volatile boolean isActivo = true;
    private final Random random = new Random();
    private final Object lockPedidos = new Object();


    public Preparador(ArrayList<Pedido> pedidosNuevos, ArrayList<Pedido> pedidosPreparados, CentroDeAlmacenamiento centro) {
        this.id = ++contador;
        this.pedidosNuevos = pedidosNuevos;
        this.pedidosPreparados = pedidosPreparados;
        this.centro = centro;
    }
    @Override
    public String toString(){
        return "Preparador " + id;
    }

    @Override
    public void run() {
        while(isActivo){
            try {
                Casillero casillero = centro.obtenerCasillero();
                Pedido pedido;
                synchronized (lockPedidos) {
                    if (pedidosNuevos.isEmpty()) {
                        isActivo = false;
                        break;
                    }
                    pedido = pedidosNuevos.remove(random.nextInt(pedidosNuevos.size()));
                }
                pedido.setEstado(EstadoPedido.PREPARADO);
                pedido.setCasillero(casillero); //Esto también ocupa el casillero con casillero.ocupar()

                synchronized (lockPedidos) {
                    pedidosPreparados.add(pedido);
                    System.out.println(this + ": Se preparó el " + pedido);
                }
            } catch (noHayCasillerosDisponiblesException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
