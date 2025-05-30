package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.excepciones.noHayCasillerosDisponiblesException;

public class CentroDeAlmacenamiento {
    private final Casillero[][] matriz;
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

    /**
     * Este método devuelve un casillero disponible de la matriz.
     * Si no hay casilleros disponibles, lanza una excepción noHayCasillerosDisponiblesException.
     * El método utiliza una lista de índices para acceder a los casilleros de manera aleatoria.
     * Primero, se crea una lista de índices que representan la posición de cada casillero en la matriz.
     * Luego, se desordena la lista de índices para acceder a los casilleros en un orden aleatorio.
     * Finalmente, se itera sobre la lista de índices y se intenta ocupar cada casillero.
     * Si se encuentra un casillero disponible, se devuelve.
     * Si no se encuentra ningún casillero disponible, se lanza una excepción.
     * @return Casillero disponible
     * @throws noHayCasillerosDisponiblesException
     * 
     */
    public Casillero obtenerCasillero() throws noHayCasillerosDisponiblesException {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int total = filas * columnas;
    
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            indices.add(i);
        }
    
        Collections.shuffle(indices);
    
        for (int idx : indices) {
            int i = idx / columnas;
            int j = idx % columnas;
            Casillero c = matriz[i][j];
            if (c.ocupar()) {
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
        sb.append(nombre).append(":\n");
        for (Casillero[] casilleros : matriz) {
            for (int j = 0; j < casilleros.length; j++) {
                sb.append(casilleros[j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n").append("Total de casilleros: ").append(matriz.length * matriz[0].length);
        sb.append("\n").append("Total de casilleros ocupados: ").append(getCasillerosOcupados());
        sb.append("\n").append("Total de casilleros disponibles: ").append(getCasillerosDisponibles());
        sb.append("\n").append("Total de casilleros fuera de servicio: ").append(getCasillerosFueraDeServicio());
        return sb.toString();
    }

    public List<Casillero> getCasilleros() {
        List<Casillero> casilleros = new ArrayList<>();
        for (Casillero[] fila : matriz) {
            Collections.addAll(casilleros, fila);
        }
        return casilleros;
    }
}
