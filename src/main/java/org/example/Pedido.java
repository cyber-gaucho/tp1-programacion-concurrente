package org.example;

public class Pedido {
    private static int contador = 0;
    private final int id;
    private Casillero casillero;
    private EstadoPedido estado;


    public Pedido() {
        this.id = ++contador;
        this.estado = EstadoPedido.NUEVO;
    }

    public Pedido(int id){
        this.id = id;
        this.estado = EstadoPedido.NUEVO;
    }

    public int getId() {
        return id;
    }

    public void setCasillero(Casillero casillero) {
        this.casillero = casillero;
        casillero.ocupar();
    }

    public Casillero getCasillero() {
        return casillero;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        if(estado == EstadoPedido.EN_TRANSITO ){
            casillero.vaciar();
        }
        if(estado == EstadoPedido.FALLIDO){
            if(casillero != null) casillero.fueraDeServicio();
        }
    }

    @Override
    public String toString() {
        return "Pedido #" + id;
    }
}