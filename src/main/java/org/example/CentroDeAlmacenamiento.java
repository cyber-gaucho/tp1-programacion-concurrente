package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    // public synchronized Casillero obtenerCasillero() throws noHayCasillerosDisponiblesException {
    //     for (int i = 0; i < this.matriz.length; i++) {
    //         for (int j = 0; j < this.matriz[i].length; j++) {
    //             if (this.matriz[i][j].getEstado() == EstadoCasillero.VACIO) {
    //                 return this.matriz[i][j];
    //             }
    //         }
    //     }
    //     throw new noHayCasillerosDisponiblesException();
    // }

    // public synchronized Casillero obtenerCasillero() throws noHayCasillerosDisponiblesException {
    //     List<Casillero> todos = new ArrayList<>();

    //     // Pasar la matriz a una lista
    //     for (int i = 0; i < this.matriz.length; i++) {
    //         for (int j = 0; j < this.matriz[i].length; j++) {
    //             todos.add(this.matriz[i][j]);
    //         }
    //     }

    //     // Mezclar aleatoriamente
    //     Collections.shuffle(todos);

    //     // Buscar el primero disponible
    //     for (Casillero c : todos) {
    //         if (c.getEstado() == EstadoCasillero.VACIO) {
    //             return c;
    //         }
    //     }

    //     // Si no se encuentra ninguno
    //     throw new noHayCasillerosDisponiblesException();
    // }

    public synchronized Casillero obtenerCasillero() throws noHayCasillerosDisponiblesException {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int total = filas * columnas;
    
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            indices.add(i);
        }
    
        Collections.shuffle(indices); // desordenamos el orden de acceso
    
        for (int idx : indices) {
            int i = idx / columnas;
            int j = idx % columnas;
            Casillero c = matriz[i][j];
            if (c.getEstado() == EstadoCasillero.VACIO) {
                return c;
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
