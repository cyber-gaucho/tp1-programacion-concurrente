package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtLogger extends AbstractLogWriter {
    

    public TxtLogger(String fileName) throws IOException {
        
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }
        this.writer = new BufferedWriter(new FileWriter(fileName, false));
    }

    public void escribir(long timestamp, int verificados, int fallidos) throws IOException {
        String lineaTxt = "[" + timestamp + " ms] Verificados: " + verificados + ",  Fallidos: " + fallidos;
        write(lineaTxt);
    }

    public void escribir(String mensaje) throws IOException {
        write(mensaje);
    }

}