package org.example;

import java.util.ArrayList;
import java.util.List;

public class Simulador {
    private int cantidadPedidos;
    private int cantPreparadores;
    private int cantDespachadores;
    private int cantEntregadores;
    private int cantVerificadores;
    private int filasCentro;
    private int columnasCentro;
    private String nombreTXT;
    private String nombreCSV;

    public Simulador(
        int cantidadPedidos, 
        int cantPreparadores, 
        int cantDespachadores, 
        int cantEntregadores, 
        int cantVerificadores, 
        int filasCentro, 
        int columnasCentro, 
        String nombreTXT, 
        String nombreCSV
    ) {
        this.cantidadPedidos = cantidadPedidos;
        this.cantPreparadores = cantPreparadores;
        this.cantDespachadores = cantDespachadores;
        this.cantEntregadores = cantEntregadores;
        this.cantVerificadores = cantVerificadores;
        this.filasCentro = filasCentro;
        this.columnasCentro = columnasCentro;
        this.nombreTXT = nombreTXT;
        this.nombreCSV = nombreCSV;
    }

    public void ejecutar() {
        SynchronizedList<Pedido> pedidosNuevos = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosPreparados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEnTransito = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEntregados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosVerificados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosFallidos = new SynchronizedList<>();
        CentroDeAlmacenamiento centro = new CentroDeAlmacenamiento(filasCentro, columnasCentro);

        List<Thread> hilos = new ArrayList<>();

        // Crear hilos preparadores
        for (int i = 0; i < cantPreparadores; i++) {
            hilos.add(new Thread(new Preparador(pedidosNuevos, pedidosPreparados, centro)));
        }
        // Crear hilos despachadores
        for (int i = 0; i < cantDespachadores; i++) {
            hilos.add(new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos)));
        }
        // Crear hilos entregadores
        for (int i = 0; i < cantEntregadores; i++) {
            hilos.add(new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos)));
        }
        // Crear hilos verificadores
        for (int i = 0; i < cantVerificadores; i++) {
            hilos.add(new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos)));
        }
        // Logger
        Thread logger = new Thread(new LoggerManager(nombreTXT, nombreCSV, pedidosVerificados, pedidosFallidos, centro));
        hilos.add(logger);

        // Cargar pedidos
        for (int i = 1; i <= cantidadPedidos; i++) {
            pedidosNuevos.add(new Pedido());
        }

        long inicio = System.currentTimeMillis();

        // Lanzar hilos
        for (Thread t : hilos) {
            t.start();
        }

        // Esperar a que terminen los pedidos
        while (pedidosFallidos.size() + pedidosVerificados.size() < cantidadPedidos) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long fin = System.currentTimeMillis();

        // Interrumpir hilos
        for (Thread t : hilos) {
            t.interrupt();
        }

        // Esperar que terminen
        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nTiempo de ejecución del programa: " + (fin - inicio) + "(ms)");
        System.out.println("\nPedidos preparados: " + pedidosPreparados.size());
        System.out.println("\nPedidos en transito: " + pedidosEnTransito.size());
        System.out.println("\nPedidos entregados: " + pedidosEntregados.size());
        System.out.println("\nPedidos verificados: " + pedidosVerificados.size());
        System.out.println("\nPedidos fallidos: " + pedidosFallidos.size());
        System.out.println("\n" + centro);
    }
}