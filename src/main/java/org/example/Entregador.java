package org.example;

import java.util.concurrent.LinkedBlockingDeque;

public class Entregador extends Distribuidor{
    private static int contador = 0;
    private final int id;

    public Entregador(LinkedBlockingDeque<Pedido> origen, LinkedBlockingDeque<Pedido> exitosos, LinkedBlockingDeque<Pedido> fallidos){
        super(origen, exitosos, fallidos);
        this.id = ++contador;
    }

    @Override
    protected double getProbabilidadFallo() {
        return 0.10;
    }

    @Override
    protected void cambiarEstado(Pedido pedido) {
        pedido.setEstado(EstadoPedido.ENTREGADO);
    }

    @Override
    protected String mensajeExito(){
        return this + ": se entregó el ";
    }

    @Override
    public String toString(){
        return "Entregador " + id;
    }
}