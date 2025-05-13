package org.example;
import java.io.IOException;

public class LoggerManager implements Runnable {
    private TxtLogger txtLogger;
    private CsvLogger csvLogger;
    private final SynchronizedList<Pedido> pedidosVerificados;
    private final SynchronizedList<Pedido> pedidosFallidos;
    private final long inicio;

    // private volatile boolean isActivo = true;

    public LoggerManager(String nombreTXT, String nombreCSV, SynchronizedList<Pedido> pedidosVerificados, SynchronizedList<Pedido> pedidosFallidos){
        try {
            this.txtLogger = new TxtLogger(nombreTXT);
            this.csvLogger = new CsvLogger(nombreCSV);
        } catch (IOException e) {
            System.err.println("Error al crear los archivos de log: " + e.getMessage());
            
        }
        this.pedidosVerificados = pedidosVerificados;
        this.pedidosFallidos = pedidosFallidos;
        inicio = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int verificados = pedidosVerificados.size();
                int fallidos = pedidosFallidos.size();
                long timestamp = System.currentTimeMillis() - inicio;

                txtLogger.escribir(timestamp, verificados, fallidos);
                csvLogger.escribir(timestamp, verificados, fallidos);

                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("LoggerManager interrupted");
                return;
            } catch (IOException e) {
                System.out.println("Error al escribir en archivos de log.");
                System.err.println("Error writing to log files: " + e.getMessage());
            }
        }

        // Cerrar los archivos de log al finalizar
        try {
            txtLogger.close();
            csvLogger.close();
        } catch (IOException e) {
            System.err.println("Error closing log files: " + e.getMessage());
        }
    }
}