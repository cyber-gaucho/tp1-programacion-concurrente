package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.excepciones.noHayCasillerosDisponiblesException;

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
                c.ocupar();
                return c;
            }
        }
    
        throw new noHayCasillerosDisponiblesException();
    }

    public int getCasillerosOcupados() {
        int ocupados = 0;
        for (Casillero[] casilleros : matriz) {
            for (Casillero c : casilleros) {
                if (c.getEstado() == EstadoCasillero.OCUPADO) {
                    ocupados++;
                }
            }
        }
        return ocupados;
    }

    public int getCasillerosDisponibles() {
        int disponibles = 0;
        for (Casillero[] casilleros : matriz) {
            for (Casillero c : casilleros) {
                if (c.getEstado() == EstadoCasillero.VACIO) {
                    disponibles++;
                }
            }
        }
        return disponibles;
    }

    public int getCasillerosFueraDeServicio() {
        int fueraDeServicio = 0;
        for (Casillero[] casilleros : matriz) {
            for (Casillero c : casilleros) {
                if (c.getEstado() == EstadoCasillero.FUERA_DE_SERVICIO) {
                    fueraDeServicio++;
                }
            }
        }
        return fueraDeServicio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(": \n");
        for (Casillero[] casilleros : matriz) {
            for (int j = 0; j < casilleros.length; j++) {
                sb.append(casilleros[j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("Total de casilleros: ").append(matriz.length * matriz[0].length).append("\n");
        sb.append("Total de casilleros ocupados: ").append(getCasillerosOcupados()).append("\n");
        sb.append("Total de casilleros disponibles: ").append(getCasillerosDisponibles()).append("\n");
        sb.append("Total de casilleros fuera de servicio: ").append(getCasillerosFueraDeServicio()).append("\n");
        return sb.toString();
    }
}
