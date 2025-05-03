package org.example;

public class Casillero {
    private EstadoCasillero estado;
    private Integer contador;

    public Casillero(){
        this.estado = EstadoCasillero.VACIO;
        this.contador = 0;
    }

    // Getters

    public EstadoCasillero getEstado(){
        return estado;
    }

    public int getContador(){
        return contador;
    }

    // Modificadores de Estado

    public void ocupar(){
        this.estado = EstadoCasillero.OCUPADO;
        this.contador++;
    }

    public void vaciar(){
        this.estado = EstadoCasillero.VACIO;
    }

    public void fueraDeServicio(){
        this.estado = EstadoCasillero.FUERA_DE_SERVICIO;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(contador);
        switch (this.estado) {
            case VACIO -> sb.append("(V)");
            case OCUPADO -> sb.append("(O)");
            case FUERA_DE_SERVICIO -> sb.append("(X)");
        }
        return sb.toString();
    }
}
