package org.example;

public class ConsoleLogger {
    public void log(long timestamp, int verificados, int fallidos) {
        System.out.println("[" + timestamp + " ms] Verificados: " + verificados + ", Fallidos: " + fallidos);
    }
}