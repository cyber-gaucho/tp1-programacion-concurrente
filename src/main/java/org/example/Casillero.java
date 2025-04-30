package org.example;

public class Casillero {
    private Estado estado;
    private Integer contador;

    public Casillero(){
        this.estado = Estado.VACIO;
        this.contador = 0;
    }

    // Getters

    public Estado getEstado(){
        return estado;
    }

    public int getContador(){
        return contador;
    }

    // Modificadores de Estado

    public void ocupar(){
        this.estado = Estado.OCUPADO;
        this.contador++;
    }

    public void vaciar(){
        this.estado = Estado.VACIO;
    }

    public void fuerar(){
        this.estado = Estado.FUERA_DE_SERVICIO;
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
