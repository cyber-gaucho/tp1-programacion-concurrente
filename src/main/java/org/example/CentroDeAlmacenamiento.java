package org.example;

import java.util.ArrayList;
import java.util.List;

public class CentroDeAlmacenamiento {
    private final Casillero[][] matriz;
    //private final Random rand = new Random();
    private final String nombre;
    private final List<Pedido> pedidosEnPreparacion = new ArrayList<>();

    public CentroDeAlmacenamiento(String nombreCentro, int alto, int ancho){
        matriz = new Casillero[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                matriz[i][j] = new Casillero();
            }
        }

        this.nombre = nombreCentro;
    }


 /*   public Casillero obtenerCasillero(){
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                if (this.matriz[i][j].getEstado() == Estado.VACIO) {
                    return this.matriz[i][j];
                }
            }
        }
        //throw tremenda noHayCasillerosDisponiblesException
        return null;
    }*/

    public synchronized Casillero obtenerCasillero(Pedido pedido) {
        int alto = matriz.length;
        int ancho = matriz[0].length;
        int intentosMaximos = alto * ancho;

        for (int i = 0; i < intentosMaximos; i++) {
            // Usamos Math.random() para obtener un índice aleatorio de fila y columna
            int fila = (int) Math.floor(Math.random() * alto);
            int columna = (int) Math.floor(Math.random() * ancho);
            Casillero casillero = matriz[fila][columna];

            // Verificamos que el casillero esté VACIO antes de ocuparlo
            synchronized (casillero) {
                if (casillero.getEstado() == Estado.VACIO) {
                    casillero.ocupar();  // Marcamos como ocupado
                    pedidosEnPreparacion.add(pedido);
                    //System.out.println("Casillero ocupado en fila: " + fila + ", columna: " + columna);
                    System.out.println("Pedido " + pedido + " preparado en fila: " + fila + ", columna: " + columna);
                    return casillero;  // Devolvemos el casillero ocupado
                }
            }
        }

        return null;  // Si no se encuentra un casillero vacío, devolvemos null
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
