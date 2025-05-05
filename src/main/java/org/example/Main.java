package org.example;
import java.util.concurrent.LinkedBlockingDeque;

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

//    public static void main(String[] args) {
//
//        int CANTIDAD_PEDIDOS = 50;
//        LinkedBlockingDeque<Pedido> pedidosNuevos = new LinkedBlockingDeque<>();
//        LinkedBlockingDeque<Pedido> pedidosPreparados = new LinkedBlockingDeque<>();
//        LinkedBlockingDeque<Pedido> pedidosEnTransito = new LinkedBlockingDeque<>();
//        LinkedBlockingDeque<Pedido> pedidosEntregados = new LinkedBlockingDeque<>();
//        LinkedBlockingDeque<Pedido> pedidosVerificados = new LinkedBlockingDeque<>();
//        LinkedBlockingDeque<Pedido> pedidosFallidos = new LinkedBlockingDeque<>();
//        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);
//
//        for(int i = 1 ; i <= CANTIDAD_PEDIDOS ; i++){
//            pedidosNuevos.add(new Pedido());
//        }
//
//        Preparador preparador1 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
//        Preparador preparador2 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
//        Preparador preparador3 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
//        Despachador despachador1 = new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos);
//        Despachador despachador2 = new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos);
//
//
//        preparador1.start();
//        preparador2.start();
//        preparador3.start();
//        despachador1.start();
//        despachador2.start();
//
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        preparador1.interrupt();
//        preparador2.interrupt();
//        preparador3.interrupt();
//        despachador1.interrupt();
//        despachador2.interrupt();
//
//        System.out.println(galponcito200);
//
//        System.out.println("\nPedidos preparados: " + pedidosEnTransito.size());
//        for(Pedido indice : pedidosEnTransito){
//            System.out.println(indice);
//        }
//
//        System.out.println("\nPedidos fallidos: " + pedidosFallidos.size());
//        for(Pedido indice : pedidosFallidos){
//            System.out.println(indice);
//        }
//    }

    public static void main(String[] args) {

        int CANTIDAD_PEDIDOS = 500;
        LinkedBlockingDeque<Pedido> pedidosNuevos = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Pedido> pedidosPreparados = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Pedido> pedidosEnTransito = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Pedido> pedidosEntregados = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Pedido> pedidosVerificados = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Pedido> pedidosFallidos = new LinkedBlockingDeque<>();
        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        for(int i = 1 ; i <= CANTIDAD_PEDIDOS ; i++){
            pedidosNuevos.add(new Pedido());
        }

        Preparador preparador1 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
        Preparador preparador2 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
        Preparador preparador3 = new Preparador(pedidosNuevos, pedidosPreparados, galponcito200);
        Despachador despachador1 = new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos);
        Despachador despachador2 = new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos);
        Entregador entregador1 = new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos);
        Entregador entregador2 = new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos);
        Entregador entregador3 = new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos);
        Verificador verificador1 = new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos);
        Verificador verificador2 = new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos);

        preparador1.start();
        preparador2.start();
        preparador3.start();
        despachador1.start();
        despachador2.start();
        entregador1.start();
        entregador2.start();
        entregador3.start();
        verificador1.start();
        verificador2.start();


        while(pedidosFallidos.size() + pedidosVerificados.size() < CANTIDAD_PEDIDOS) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        preparador1.interrupt();
        preparador2.interrupt();
        preparador3.interrupt();
        despachador1.interrupt();
        despachador2.interrupt();
        entregador1.interrupt();
        entregador2.interrupt();
        entregador3.interrupt();
        verificador1.interrupt();
        verificador2.interrupt();

        //System.out.println(galponcito200);

        System.out.println("\nPedidos verificados: " + pedidosVerificados.size());
//        for(Pedido indice : pedidosVerificados){
//            System.out.println(indice);
//        }

        System.out.println("\nPedidos fallidos: " + pedidosFallidos.size());
//        for(Pedido indice : pedidosFallidos){
//            System.out.println(indice);
//        }
    }
}


