package org.example;

import java.util.Random;

import org.example.excepciones.ListaVaciaException;

/**
 * Clase abstracta que representa un distribuidor de pedidos.
 * Esta clase implementa la interfaz Runnable y proporciona la funcionalidad básica
 * para procesar pedidos.
 */
public abstract class Distribuidor implements Runnable {
    protected final SynchronizedList<Pedido> origen;
    protected final SynchronizedList<Pedido> exitosos;
    protected final SynchronizedList<Pedido> fallidos;
    protected final Random random = new Random();

    public Distribuidor(SynchronizedList<Pedido> origen, SynchronizedList<Pedido> exitosos, SynchronizedList<Pedido> fallidos){
        this.origen = origen;
        this.exitosos = exitosos;
        this.fallidos = fallidos;
    }

    protected abstract double getProbabilidadFallo();
    protected abstract void trabajar(Pedido pedido);
    protected abstract String mensajeExito();

    protected void fallarPedido(Pedido pedido){
        pedido.setEstado(EstadoPedido.FALLIDO);
    }

    protected long getTiempoDeEspera(){
        double media = 80;
        double desviacion = 15;
        double delay = media + desviacion * random.nextGaussian(); 
        delay = Math.max(50, Math.min(110, delay));
        
        return (long) delay;
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            Pedido pedido = null;
            try {
                pedido = origen.removeRandom();
                
                if (random.nextDouble() < getProbabilidadFallo()){
                    fallarPedido(pedido);
                    fallidos.add(pedido);
                }
                else {
                    trabajar(pedido);
                    exitosos.add(pedido);
                }

                Thread.sleep(getTiempoDeEspera());
            } catch (ListaVaciaException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
