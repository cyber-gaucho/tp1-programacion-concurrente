package org.example;

public class Main {

    public static void main(String[] args) {
        int CANTIDAD_PEDIDOS = 500;
        final String nombreTXT = "LogEstadísticoTXT.txt";
        final String nombreCSV = "LogEstadísticoCSV.csv";

        SynchronizedList<Pedido> pedidosNuevos = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosPreparados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEnTransito = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEntregados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosVerificados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosFallidos = new SynchronizedList<>();
        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);
        
        Thread preparador1 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread preparador2 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread preparador3 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread despachador1 = new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos));
        Thread despachador2 = new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos));
        Thread entregador1 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread entregador2 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread entregador3 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread verificador1 = new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos));
        Thread verificador2 = new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos));
        Thread logger = new Thread(new LoggerManager(nombreTXT, nombreCSV, pedidosVerificados, pedidosFallidos, galponcito200));
        
        for(int i = 1 ; i <= CANTIDAD_PEDIDOS ; i++){
            pedidosNuevos.add(new Pedido());
        }

        logger.start();
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

        logger.interrupt();
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
        
        try {
            logger.join();
            preparador1.join();
            preparador2.join();
            preparador3.join();
            despachador1.join();
            despachador2.join();
            entregador1.join();
            entregador2.join();
            entregador3.join();
            verificador1.join();
            verificador2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo principal fue interrumpido.");
        }
    }
}