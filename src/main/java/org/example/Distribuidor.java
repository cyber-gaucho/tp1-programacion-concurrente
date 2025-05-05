package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Distribuidor extends Thread {
    protected final LinkedBlockingDeque<Pedido> origen;
    protected final LinkedBlockingDeque<Pedido> exitosos;
    protected final LinkedBlockingDeque<Pedido> fallidos;
    protected final Random random = new Random();

    public Distribuidor(LinkedBlockingDeque<Pedido> origen, LinkedBlockingDeque<Pedido> exitosos, LinkedBlockingDeque<Pedido> fallidos){
        this.origen = origen;
        this.exitosos = exitosos;
        this.fallidos = fallidos;
    }

    protected abstract double getProbabilidadFallo();
    protected abstract void cambiarEstado(Pedido pedido);
    protected abstract String mensajeExito();
    protected void fallarPedido(Pedido pedido){
        pedido.setEstado(EstadoPedido.FALLIDO);
    }
    protected long getTiempoDeEspera(){
        double media = 100;
        double desviacion = 10;
        double delay = media + desviacion * random.nextGaussian();
        delay = Math.max(50, Math.min(150, delay)); //propone valores maximos y minimos para el delay
        
        return (long) delay;
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try {
                Pedido pedido = obtenerPedidoAleatorio(origen); //devuelve un pedido o tira excepcion
                cambiarEstado(pedido);

                if (random.nextDouble() < getProbabilidadFallo()){
                    fallarPedido(pedido);
                    fallidos.offer(pedido); // .offer() no tira exception

                    //System.out.println(this + ": Falló el " + pedido);
                }
                else {
                    cambiarEstado(pedido);
                    exitosos.offer(pedido);
                    //System.out.println(mensajeExito() + pedido);
                }

                Thread.sleep(getTiempoDeEspera());
            } catch (ListaVaciaException e) {
                try {
                    //System.out.println(this + ":ERROR LISTA VACIA");
                    Thread.sleep(200); // espera antes de reintentar
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt(); // importante: respetar la interrupción
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // importante: respetar la interrupción
            }
        }
    }

    private Pedido obtenerPedidoAleatorio(LinkedBlockingDeque<Pedido> deque) throws ListaVaciaException {
        synchronized (deque) {
            if (deque.isEmpty()) {
                throw new ListaVaciaException("");
            }
            List<Pedido> snapshot = new ArrayList<>(deque); // copia la deque a una arraylist
            int index = random.nextInt(snapshot.size()); // prepara el indice random
            Pedido elegido = snapshot.get(index); // obtiene el objeto Pedido random que queremos sacar
            deque.remove(elegido);  // elimina solo ese pedido

            return elegido;
        }
    }
}
