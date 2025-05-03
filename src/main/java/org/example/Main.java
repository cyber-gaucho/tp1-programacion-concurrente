package org.example;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
    public static void main(String[] args) {
        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        System.out.println(galponcito200);

        for (int i = 0; i < 200; i++) {
            galponcito200.obtenerCasillero().ocupar();
        } //Ocupamos toditos los casilleros

        System.out.println(galponcito200);

        try{
            galponcito200.obtenerCasillero().ocupar();
        }
        catch (NullPointerException e){
            System.out.println("Apa");
        }
    }
    */

    public static void main(String[] args) {

        int CANTIDAD_PEDIDOS = 50;
        ArrayList<Pedido> pedidosNuevos = new ArrayList<>();
        ArrayList<Pedido> pedidosPreparados = new ArrayList<>();
        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        for(int i = 1 ; i <= CANTIDAD_PEDIDOS ; i++){
            pedidosNuevos.add(new Pedido());
        }

        Preparador preparador1 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
        Preparador preparador2 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
        Preparador preparador3 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);

        preparador1.start();
        preparador2.start();
        preparador3.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        preparador1.interrupt();
        preparador2.interrupt();
        preparador3.interrupt();

        System.out.println("\nPedidos preparados: " + pedidosPreparados.size());
        System.out.println(galponcito200);
    }
}


