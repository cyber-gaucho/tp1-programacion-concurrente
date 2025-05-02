package org.example;
import java.util.ArrayList;
import java.util.List;

/*public class Main {
    public static void main(String[] args) {
        CentroDeAlmacenamiento Galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        System.out.println(Galponcito200);

        for (int i = 0; i < 200; i++) {
            Galponcito200.obtenerCasillero().ocupar();
        } //Ocupamos toditos los casilleros

        System.out.println(Galponcito200);

        try{
            Galponcito200.obtenerCasillero().ocupar();
        }
        catch (NullPointerException e){
            System.out.println("Apa");
        }
    }
}*/
/*public class Main {
    public static void main(String[] args) {
        // Crear el centro de almacenamiento con 5 filas y 5 columnas
        CentroDeAlmacenamiento galponcito = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        // Imprimir estado inicial
        System.out.println("Estado inicial:");
        System.out.println(galponcito);

        // Intentar ocupar casilleros aleatorios
        System.out.println("\nOcupando casilleros aleatorios...");
        for (int i = 0; i < 25; i++) {
            Casillero casillero = galponcito.obtenerCasillero();
            if (casillero != null) {
                System.out.println("Casillero ocupado: " + casillero);
            } else {
                System.out.println("No hay más casilleros disponibles.");
            }
        }

        // Imprimir estado final
        System.out.println("\nEstado final:");
        System.out.println(galponcito);
    }
}*/

public class Main {
    public static void main(String[] args) {
        CentroDeAlmacenamiento centro = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        List<HiloPreparacionPedido> hilos = new ArrayList<>();

        // Crear 3 hilos para preparar los pedidos
        for (int i = 0; i < 10; i++) {
            // Simulamos que los pedidos son creados de alguna forma
            Pedido pedido = new Pedido();

            // Crear el hilo para preparar el pedido
            HiloPreparacionPedido hilo = new HiloPreparacionPedido(centro, pedido);
            hilos.add(hilo);  // Añadir el hilo a la lista
            hilo.start();  // Iniciar el hilo de preparación de pedidos
        }

        // Esperar que todos los hilos terminen
        for (HiloPreparacionPedido hilo : hilos) {
            try {
                hilo.join();  // Esperamos a que el hilo termine su ejecución
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el estado final de los casilleros
        System.out.println(centro);
    }
}


