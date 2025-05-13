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

    // Getters

    public EstadoCasillero getEstado(){
        return estado;
    }

    public int getContador(){
        return contador;
    }

    // Modificadores de Estado

    public void ocupar(){
        // Si se intenta ocupar un casillero que no está vacio, se lanza un error
        // if(this.estado != EstadoCasillero.VACIO){
        //     System.out.println("=====================================");
        //     System.out.println("\nError en el casillero " + id +".\n");
        //     System.out.println("=====================================\n");
        // }
        
        this.estado = EstadoCasillero.OCUPADO;
        this.contador++;
    }

    public void vaciar(){
        this.estado = EstadoCasillero.VACIO;
    }

    public void fueraDeServicio(){
        this.estado = EstadoCasillero.FUERA_DE_SERVICIO;
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
