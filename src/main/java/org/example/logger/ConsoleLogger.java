package org.example.logger;

public class ConsoleLogger {
    public void log(long timestamp, int verificados, int fallidos) {
        System.out.println("[" + timestamp + " ms] Verificados: " + verificados + ", Fallidos: " + fallidos);
    }
    
    public void log(String message) {
        System.out.println(message);
    }
}