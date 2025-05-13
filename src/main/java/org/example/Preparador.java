package org.example;
import java.util.Random;
import org.example.excepciones.ListaVaciaException;
import org.example.excepciones.noHayCasillerosDisponiblesException;

public class Preparador implements Runnable {

    private static int contador = 0;
    private final int id;

    private final SynchronizedList<Pedido> pedidosNuevos;
    private final SynchronizedList<Pedido> pedidosPreparados;
    private final CentroDeAlmacenamiento centro;
    private volatile boolean isActivo = true;
    private final Random random = new Random();

    public Preparador(SynchronizedList<Pedido> pedidosNuevos, SynchronizedList<Pedido> pedidosPreparados, CentroDeAlmacenamiento centro) {
        this.id = ++contador;
        this.pedidosNuevos = pedidosNuevos;
        this.pedidosPreparados = pedidosPreparados;
        this.centro = centro;
    }
    
    // @Override
    // public void run() {
    //     while(isActivo && !Thread.currentThread().isInterrupted()){
    //         try {
    //             Pedido pedido;
    //             // if(!pedidosNuevos.isEmpty()) {
    //             //     pedido = pedidosNuevos.remove(random.nextInt(Math.max(pedidosNuevos.size(), 1)));
    //             // } else {
    //             //     throw new ListaVaciaException("La lista de pedidos nuevos está vacía.");
    //             // }

    //             synchronized (pedidosNuevos) {
    //                 if (!pedidosNuevos.isEmpty()) {
    //                     pedido = pedidosNuevos.remove(random.nextInt(Math.max(pedidosNuevos.size(), 1)));
    //                 } else {
    //                     throw new ListaVaciaException("La lista de pedidos nuevos está vacía.");
    //                 }
    //             }



    //             try {
    //                 Casillero casillero = centro.obtenerCasillero();
    //                 pedido.setCasillero(casillero); //Esto también ocupa el casillero con casillero.ocupar()                
    //                 pedido.setEstado(EstadoPedido.PREPARADO);
                    
    //                 synchronized (pedidosPreparados) {
    //                     pedidosPreparados.add(pedido);
    //                 }
    //                 // System.out.println(this + ": Se preparó el " + pedido);                
    //                 Thread.sleep(getTiempoDeEspera());
    //             } catch (noHayCasillerosDisponiblesException e) {
    //                 try {
    //                     //System.out.println(this + ": ERROR noHayCasillerosDisponibles");
    //                     Thread.sleep(150);
    //                 } catch (InterruptedException ex) {
    //                     Thread.currentThread().interrupt();
    //                     return;
    //                 }
    //             }

    //         } catch (ListaVaciaException e) {
    //             //Cuando la lista de pedidos nuevos está vacía, finaliza el hilo.
    //             System.out.println(this + ": Se acabaron los pedidos nuevos. Fin del hilo.");
    //             Thread.currentThread().interrupt();
    //             return;
    //         } catch (InterruptedException e) {
    //             Thread.currentThread().interrupt();
    //             return;
    //         }
    //     }
    // }

    @Override
    public void run() {
        while (isActivo && !Thread.currentThread().isInterrupted()) {
            try {
                Pedido pedido;
                synchronized (pedidosNuevos) {
                    if (!pedidosNuevos.isEmpty()) {
                        pedido = pedidosNuevos.remove(random.nextInt(Math.max(pedidosNuevos.size(), 1)));
                    } else {
                        throw new ListaVaciaException("La lista de pedidos nuevos está vacía.");
                    }
                }

                try {
                    Casillero casillero = centro.obtenerCasillero(); // May throw noHayCasillerosDisponiblesException
                    pedido.setCasillero(casillero); // Occupies the casillero
                    pedido.setEstado(EstadoPedido.PREPARADO);

                    synchronized (pedidosPreparados) {
                        pedidosPreparados.add(pedido);
                    }
                    // System.out.println(this + ": Se preparó el " + pedido);

                    Thread.sleep(getTiempoDeEspera());
                } catch (noHayCasillerosDisponiblesException e) {
                    // Return the pedido to pedidosNuevos if no casillero is available
                    synchronized (pedidosNuevos) {
                        pedidosNuevos.add(pedido);
                    }
                    // System.out.println(this + ": No hay casilleros disponibles. Pedido devuelto a la lista de nuevos.");
                    Thread.sleep(100); // Retry delay
                }

            } catch (ListaVaciaException e) {
                // When the list of new pedidos is empty, terminate the thread
                System.out.println(this + ": Se acabaron los pedidos nuevos. Fin del hilo.");
                Thread.currentThread().interrupt();
                return;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private long getTiempoDeEspera(){
        double media = 80;
        double desviacion = 15;
        double delay = media + desviacion * random.nextGaussian();//random.nextGaussian() devuelve
        // un doble a partir de una distribución normal con media cera y desvío estándar 1
        delay = Math.max(50, Math.min(110, delay)); //propone valores maximos y minimos para el delay
        
        return (long) delay;
    }

    @Override
    public String toString(){
        return "Preparador " + id;
    }
}
