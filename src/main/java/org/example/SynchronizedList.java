package org.example;

import java.util.ArrayList;
import java.util.List;
import org.example.excepciones.ListaVaciaException;

public class SynchronizedList<T> {
    private final ArrayList<T> list = new ArrayList<>();

    // Add an element to the list
    public synchronized void add(T element) {
        list.add(element);
    }

    // Remove an element from the list
    public synchronized T remove(int index) {
        if (index < 0 || index >= list.size()) {
            throw new ListaVaciaException("La lista está vacía o el índice es inválido.");
        }
        return list.remove(index);
    }

    // Get an element from the list
    public synchronized T get(int index) {
        return list.get(index);
    }

    // Get the size of the list
    public synchronized int size() {
        return list.size();
    }

    // Check if the list is empty
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    // Retrieve all elements (optional, for debugging purposes)
    public synchronized List<T> getAll() {
        return new ArrayList<>(list); // Return a copy to avoid external modification
    }
}