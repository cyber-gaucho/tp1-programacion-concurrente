package org.example;

import java.util.concurrent.LinkedBlockingDeque;

public class Despachador extends Distribuidor{
    private static int contador = 0;
    private final int id;

    public Despachador(LinkedBlockingDeque<Pedido> origen, LinkedBlockingDeque<Pedido> exitosos, LinkedBlockingDeque<Pedido> fallidos){
        super(origen, exitosos, fallidos);
        this.id = ++contador;
    }

    @Override
    protected double getProbabilidadFallo() {
        return 0.15;
    }

    @Override
    protected void cambiarEstado(Pedido pedido) {
        pedido.setEstado(EstadoPedido.EN_TRANSITO);
    }

    @Override
    protected String mensajeExito(){
        return this + ": se despachó el ";
    }

    @Override
    public String toString(){
        return "Despachador " + id;
    }
}