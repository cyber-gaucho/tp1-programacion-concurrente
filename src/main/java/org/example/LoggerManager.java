package org.example;

import java.io.IOException;

import org.example.logger.ConsoleLogger;
import org.example.logger.CsvLogger;
import org.example.logger.TxtLogger;

/**
 * LoggerManager es responsable de gestionar los registros de los pedidos verificados y fallidos.
 * Se encarga de escribir la información en archivos de texto y CSV.
 */
public class LoggerManager implements Runnable {
    private TxtLogger txtLogger;
    private CsvLogger csvLogger;
    private ConsoleLogger consoleLogger;
    private final SynchronizedList<Pedido> pedidosVerificados;
    private final SynchronizedList<Pedido> pedidosFallidos;
    private final CentroDeAlmacenamiento centro;

    public LoggerManager(String nombreTXT, String nombreCSV, SynchronizedList<Pedido> pedidosVerificados,
                        SynchronizedList<Pedido> pedidosFallidos, CentroDeAlmacenamiento centro) {
        try {
            this.txtLogger = new TxtLogger(nombreTXT);
            this.csvLogger = new CsvLogger(nombreCSV);
        } catch (IOException e) {
            System.err.println("Error al crear los archivos de log: " + e.getMessage());
            
        }
        this.consoleLogger = new ConsoleLogger();
        this.pedidosVerificados = pedidosVerificados;
        this.pedidosFallidos = pedidosFallidos;
        
        this.centro = centro;
    }

    @Override
    public void run() {
        long inicio = System.currentTimeMillis();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    int verificados = pedidosVerificados.size();
                    int fallidos = pedidosFallidos.size();
                    long timestamp = System.currentTimeMillis() - inicio;

                    txtLogger.escribir(timestamp, verificados, fallidos);
                    csvLogger.escribir(timestamp, verificados, fallidos);
                    consoleLogger.log(timestamp, verificados, fallidos);

                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // Sale del loop while y ejecuta el bloque finally
                } catch (IOException e) {
                    System.err.println("Error al escribir en los archivos de log: " + e.getMessage());
                }
            }
        } finally {          
            long fin = System.currentTimeMillis();
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
            } catch (IOException e) {
                System.err.println("Error cerrando los archivos de log: " + e.getMessage());
            }

            consoleLogger.log("\nTiempo de ejecución del programa: " + (fin - inicio) + "(ms)");
            consoleLogger.log("\nPedidos verificados: " + pedidosVerificados.size());
            consoleLogger.log("\nPedidos fallidos: " + pedidosFallidos.size());
            consoleLogger.log("\n" + centro.toString());
        }
    }
}