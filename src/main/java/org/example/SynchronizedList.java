package org.example;

import java.util.ArrayList;
import java.util.List;
import org.example.excepciones.ListaVaciaException;

/**
 * Clase que representa una lista sincronizada.
 * Esta clase proporciona métodos para agregar, eliminar y acceder a elementos de manera segura en un entorno multihilo.
 * 
 * @param <T> El tipo de elementos que contendrá la lista.
 */
public class SynchronizedList<T> {
    private final ArrayList<T> list = new ArrayList<>();

    public synchronized void add(T element) {
        list.add(element);
    }

    public synchronized T remove(int index) {
        if (index < 0 || index >= list.size()) {
            throw new ListaVaciaException("La lista está vacía o el índice es inválido.");
        }
        return list.remove(index);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    public synchronized List<T> getAll() {
        return new ArrayList<>(list);
    }
}