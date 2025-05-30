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
    private final Random random = new Random();

    public Preparador(SynchronizedList<Pedido> pedidosNuevos, SynchronizedList<Pedido> pedidosPreparados, CentroDeAlmacenamiento centro) {
        this.id = ++contador;
        this.pedidosNuevos = pedidosNuevos;
        this.pedidosPreparados = pedidosPreparados;
        this.centro = centro;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Pedido pedido = null;
            try {
                pedido = pedidosNuevos.removeRandom();

                Casillero casillero = centro.obtenerCasillero();
                
                pedido.setCasillero(casillero);
                pedido.setEstado(EstadoPedido.PREPARADO);                    
                pedidosPreparados.add(pedido);
                Thread.sleep(getTiempoDeEspera());

            } catch (ListaVaciaException e) {                
                Thread.currentThread().interrupt();
                return;
            } catch (noHayCasillerosDisponiblesException e) {           
                if (pedido != null) {
                    pedidosNuevos.add(pedido);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                return;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * Calcula un tiempo de espera aleatorio basado en una distribución normal.
     * 
     * Este método genera un valor aleatorio utilizando una distribución normal (gaussiana)
     * con una media de 80 milisegundos y una desviación estándar de 15 milisegundos.
     * El valor generado se ajusta para que esté dentro del rango de 50 a 110 milisegundos.
     * El resultado se convierte a un valor de tipo `long` antes de ser retornado.
     * 
     * @return Un valor aleatorio limitado entre 50 y 110.
     */
    private long getTiempoDeEspera(){
        double media = 80;
        double desviacion = 15;
        double delay = media + desviacion * random.nextGaussian();
        delay = Math.max(50, Math.min(110, delay));        
        return (long) delay;
    }

    @Override
    public String toString(){
        return "Preparador " + id;
    }
}