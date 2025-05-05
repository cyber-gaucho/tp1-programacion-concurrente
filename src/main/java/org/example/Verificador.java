package org.example;

import java.util.concurrent.LinkedBlockingDeque;

public class Verificador extends Distribuidor{
    private static int contador = 0;
    private final int id;

    public Verificador(LinkedBlockingDeque<Pedido> origen, LinkedBlockingDeque<Pedido> exitosos, LinkedBlockingDeque<Pedido> fallidos){
        super(origen, exitosos, fallidos);
        this.id = ++contador;
    }

    @Override
    protected double getProbabilidadFallo() {
        return 0.05;
    }

    @Override
    protected void cambiarEstado(Pedido pedido) {
        pedido.setEstado(EstadoPedido.VERIFICADO);
    }

    @Override
    protected String mensajeExito(){
        return this + ": se verificó el ";
    }

    @Override
    public String toString(){
        return "Verificador " + id;
    }
}