package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class Preparador extends Thread {

    private static int contador = 0;
    private final int id;

    private final LinkedBlockingDeque<Pedido> pedidosNuevos;
    private final LinkedBlockingDeque<Pedido> pedidosPreparados;
    private final CentroDeAlmacenamiento centro;
    private volatile boolean isActivo = true;
    private final Random random = new Random();

    public Preparador(LinkedBlockingDeque<Pedido> pedidosNuevos, LinkedBlockingDeque<Pedido> pedidosPreparados, CentroDeAlmacenamiento centro) {
        this.id = ++contador;
        this.pedidosNuevos = pedidosNuevos;
        this.pedidosPreparados = pedidosPreparados;
        this.centro = centro;
    }
    
    @Override
    public void run() {
        while(isActivo && !Thread.currentThread().isInterrupted()){
            try {
                Casillero casillero = centro.obtenerCasillero();
                Pedido pedido = obtenerPedidoAleatorio(pedidosNuevos);

                if (pedido == null) {
                    isActivo = false;
                    break;
                }

                pedido.setEstado(EstadoPedido.PREPARADO);
                pedido.setCasillero(casillero); //Esto también ocupa el casillero con casillero.ocupar()
                
                pedidosPreparados.put(pedido);
                //System.out.println(this + ": Se preparó el " + pedido);
                
                Thread.sleep(getTiempoDeEspera());

            } catch (noHayCasillerosDisponiblesException e) {
                try {
                    //System.out.println(this + ": ERROR noHayCasillerosDisponibles");
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    
    private Pedido obtenerPedidoAleatorio(LinkedBlockingDeque<Pedido> deque) {
        synchronized (deque) {
            if (deque.isEmpty()) return null;

            List<Pedido> snapshot = new ArrayList<>(deque); // copia la deque a una arraylist
            int index = random.nextInt(snapshot.size()); // prepara el indice random
            Pedido elegido = snapshot.get(index); // obtiene el objeto Pedido random que queremos sacar
            deque.remove(elegido);  // elimina solo ese pedido
          
            return elegido;
        }
    }

    private long getTiempoDeEspera(){
        double media = 75;
        double desviacion = 10;
        double delay = media + desviacion * random.nextGaussian();
        delay = Math.max(25, Math.min(125, delay)); //propone valores maximos y minimos para el delay
        
        return (long) delay;
    }

    @Override
    public String toString(){
        return "Preparador " + id;
    }
}
