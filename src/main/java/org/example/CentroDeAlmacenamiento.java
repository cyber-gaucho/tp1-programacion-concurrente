package org.example;

//import java.util.Random;

public class CentroDeAlmacenamiento {
    private final Casillero[][] matriz;
    //private final Random rand = new Random();
    private final String nombre;

    public CentroDeAlmacenamiento(String nombreCentro, int alto, int ancho){
        matriz = new Casillero[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                matriz[i][j] = new Casillero(i,j);
            }
        }
        this.nombre = nombreCentro;
    }

    public synchronized Casillero obtenerCasillero() throws noHayCasillerosDisponiblesException {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                if (this.matriz[i][j].getEstado() == EstadoCasillero.VACIO) {
                    return this.matriz[i][j];
                }
            }
        }
        throw new noHayCasillerosDisponiblesException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre + ": \n");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(this.matriz[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
