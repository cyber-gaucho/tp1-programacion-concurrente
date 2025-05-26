package org.example;

public class Casillero {
    private EstadoCasillero estado;
    private Integer contador;
    private String id;

    public Casillero(int i, int j){
        this.estado = EstadoCasillero.VACIO;
        this.contador = 0;
        this.id = i + "-" + j;
    }

    // Modificadores de Estado
    
    public synchronized boolean ocupar(){
        if(this.estado != EstadoCasillero.VACIO){
            return false;
        }
        this.estado = EstadoCasillero.OCUPADO;
        this.contador++;
        return true;
    }
    
    public void vaciar(){
        this.estado = EstadoCasillero.VACIO;
    }
    
    public void fueraDeServicio(){
        this.estado = EstadoCasillero.FUERA_DE_SERVICIO;
    }

    // Getters

    public EstadoCasillero getEstado(){
        return estado;
    }

    public int getContador(){
        return contador;
    }

    public String getId(){
        return id;
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
