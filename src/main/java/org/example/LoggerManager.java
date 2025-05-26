package org.example;
import java.io.IOException;

/**
 * LoggerManager es responsable de gestionar los registros de los pedidos verificados y fallidos.
 * Se encarga de escribir la información en archivos de texto y CSV.
 */
public class LoggerManager implements Runnable {
    private TxtLogger txtLogger;
    private CsvLogger csvLogger;
    private final SynchronizedList<Pedido> pedidosVerificados;
    private final SynchronizedList<Pedido> pedidosFallidos;
    private final long inicio;
    private final CentroDeAlmacenamiento centro;

    public LoggerManager(String nombreTXT, String nombreCSV, SynchronizedList<Pedido> pedidosVerificados,
                        SynchronizedList<Pedido> pedidosFallidos, CentroDeAlmacenamiento centro) {
        try {
            this.txtLogger = new TxtLogger(nombreTXT);
            this.csvLogger = new CsvLogger(nombreCSV);
        } catch (IOException e) {
            System.err.println("Error al crear los archivos de log: " + e.getMessage());
            
        }
        this.pedidosVerificados = pedidosVerificados;
        this.pedidosFallidos = pedidosFallidos;
        inicio = System.currentTimeMillis();
        this.centro = centro;
    }

    @Override
    public void run() {
        try {
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
                    System.out.println("\nLoggerManager interrupted");
                    break;
                } catch (IOException e) {
                    System.out.println("Error al escribir en archivos de log.");
                    System.err.println("Error writing to log files: " + e.getMessage());
                }
            }
        } finally {          
            try {
                    txtLogger.escribir("\n========== FIN DE LA EJECUCIÓN ==========");
                    txtLogger.escribir("\nDuración total: " + (System.currentTimeMillis() - inicio) + " ms");
                    txtLogger.escribir("\nPedidos verificados: " + pedidosVerificados.size());
                    txtLogger.escribir("\nPedidos fallidos: " + pedidosFallidos.size());
                    txtLogger.escribir(centro.toString());
                } catch (IOException e) {
                    System.err.println("\nError al escribir el resumen final en los archivos de log: " + e.getMessage());
                }

            
            try {
                txtLogger.close();
                csvLogger.close();
                System.out.println("Archivos de log cerrados correctamente.");
            } catch (IOException e) {
                System.err.println("Error closing log files: " + e.getMessage());
            }
        }
    }
}