package org.example;

public class Entregador extends Distribuidor{
    private static int contador = 0;
    private final int id;

    public Entregador(SynchronizedList<Pedido> origen, SynchronizedList<Pedido> exitosos, SynchronizedList<Pedido> fallidos){
        super(origen, exitosos, fallidos);
        this.id = ++contador;
    }

    @Override
    protected double getProbabilidadFallo() {
        return 0.10;
    }

    @Override
    protected void trabajar(Pedido pedido) {
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