package org.example;
import java.util.Random;

import org.example.excepciones.ListaVaciaException;

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
        double delay = media + desviacion * random.nextGaussian(); //random.nextGaussian() devuelve
        // un double a partir de una distribución normal con media 0 y desvío estándar 1
        delay = Math.max(50, Math.min(110, delay)); //propone valores máximos y mínimos para el delay
        
        return (long) delay;
    }

    @Override
    public void run(){
        int contadorExitosos = 0;
        int contadorFallidos = 0;
        while(!Thread.currentThread().isInterrupted()){
            
            try {
                Pedido pedido;
                if(!origen.isEmpty()) {
                    pedido = origen.remove(random.nextInt(Math.max(origen.size(), 1))); //el argumento de nextInt() debe ser > 0
                } else {
                    throw new ListaVaciaException("La lista de origen está vacía.");
                }

                if (random.nextDouble() < getProbabilidadFallo()){
                    fallarPedido(pedido);
                    fallidos.add(pedido);
                    contadorFallidos++;
                    //System.out.println(this + ": Falló el " + pedido);
                }
                else {
                    trabajar(pedido);
                    exitosos.add(pedido);
                    contadorExitosos++;
                    //System.out.println(mensajeExito() + pedido);
                }

                Thread.sleep(getTiempoDeEspera());

            
            } catch (ListaVaciaException e) {
                try {
                    //System.out.println(this + ":ERROR LISTA VACIA");
                    Thread.sleep(100); // espera antes de reintentar
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt(); // importante: respetar la interrupción
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // importante: respetar la interrupción
            }
        }
        System.out.println(this + ": Se procesaron " + contadorExitosos + " pedidos exitosos y " + contadorFallidos + " pedidos fallidos.");
    }
}
