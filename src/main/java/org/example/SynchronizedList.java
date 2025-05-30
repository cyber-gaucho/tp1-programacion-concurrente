package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.example.excepciones.ListaVaciaException;

/**
 * Clase que representa una lista sincronizada.
 * Esta clase proporciona métodos para agregar, eliminar y acceder a elementos de manera segura en un entorno multihilo.
 * 
 * @param <T> El tipo de elementos que contendrá la lista.
 */
public class SynchronizedList<T> {
    private final ArrayList<T> list = new ArrayList<>();
    private final Random random = new Random();

    public synchronized void add(T element) {
        list.add(element);
    }

    public synchronized T remove(int index) {
        return list.remove(index);
    }

    public synchronized T removeRandom() throws ListaVaciaException {
        if (list.isEmpty()) {
            throw new ListaVaciaException("La lista está vacía.");
        }
        int index = random.nextInt(list.size());
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