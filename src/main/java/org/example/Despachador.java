package org.example;

public class Despachador extends Distribuidor{
    private static int contador = 0;
    private final int id;

    public Despachador(SynchronizedList<Pedido> origen, SynchronizedList<Pedido> exitosos, SynchronizedList<Pedido> fallidos){
        super(origen, exitosos, fallidos);
        this.id = ++contador;
    }

    @Override
    protected double getProbabilidadFallo() {
        return 0.15;
    }

    @Override
    protected void trabajar(Pedido pedido) {
        pedido.setEstado(EstadoPedido.EN_TRANSITO);
        pedido.getCasillero().vaciar();
    }

    @Override
    protected String mensajeExito(){
        return this + ": se despachó el ";
    }

    @Override
    public String toString(){
        return "Despachador " + id;
    }

    @Override
    protected void fallarPedido(Pedido pedido) {
        pedido.setEstado(EstadoPedido.FALLIDO);
        pedido.getCasillero().fueraDeServicio();
    }
}