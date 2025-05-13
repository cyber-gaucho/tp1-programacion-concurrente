package org.example;

public class Main {
/*
    public static void main(String[] args) {

        int CANTIDAD_PEDIDOS = 500;
        long inicio;
        long fin;
        long duracion;
        final String nombreTXT = "LogEstadísticoTXT.txt";
        final String nombreCSV = "LogEstadísticoCSV.csv";

        SynchronizedList<Pedido> pedidosNuevos = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosPreparados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEnTransito = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEntregados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosVerificados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosFallidos = new SynchronizedList<>();
        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        for(int i = 1 ; i <= CANTIDAD_PEDIDOS ; i++){
            pedidosNuevos.add(new Pedido());
        }

        Thread preparador1 = new Thread (new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread preparador2 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread preparador3 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread despachador1 = new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos));
        Thread despachador2 = new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos));
        Thread entregador1 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread entregador2 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread entregador3 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread verificador1 = new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos));
        Thread verificador2 = new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos));
        Thread logger = new Thread(new LoggerManager(nombreTXT, nombreCSV, pedidosVerificados, pedidosFallidos));

        
        // BufferedWriter txtWriter = null;
        // BufferedWriter csvWriter = null;

        // try {
        //     txtWriter = new BufferedWriter(new FileWriter(nombreTXT, false)); // Con el parametro true se sigue escribiendo el mismo archivo
        //     csvWriter = new BufferedWriter(new FileWriter(nombreCSV, false)); // Con el parametro false se sobreescribe el archivo
        //     System.out.println("Se lograron abrir los archivos");
        //     csvWriter.write("Timestamp(ms),Verificados,Fallidos"); // Encabezado para el csv
        //     csvWriter.newLine();
        // } catch (IOException e) {
        //     System.out.println("Error al intentar abrir los archivos");
        //     e.printStackTrace(); 
        // }
        
        inicio = System.currentTimeMillis();

        logger.start();
        preparador1.start();
        preparador2.start();
        preparador3.start();
        despachador1.start();
        despachador2.start();
        entregador1.start();
        entregador2.start();
        entregador3.start();
        verificador1.start();
        verificador2.start();


        while(pedidosFallidos.size() + pedidosVerificados.size() < CANTIDAD_PEDIDOS) {
            try {
            //     long logTime = System.currentTimeMillis();
            //     long timestamp = logTime - inicio;
            //     int verificados = pedidosVerificados.size();
            //     int fallidos = pedidosFallidos.size();

            //     String lineaTxt = "[" + timestamp + " ms] Verificados: " + verificados + ",  Fallidos: " + fallidos;
            //     String lineaCsv = timestamp + "," + verificados + "," + fallidos;

            //     System.out.println(lineaTxt);

            //     if (txtWriter != null) {
            //         escribirLog(txtWriter, lineaTxt);
            //     }
            //     if (csvWriter != null) {
            //         escribirLog(csvWriter, lineaCsv);
            //     }

                Thread.sleep(200);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } 
            // catch (IOException e) {
            //     System.out.println("Error al escribir archivos");
            // }
        }

        logger.interrupt();
        preparador1.interrupt();
        preparador2.interrupt();
        preparador3.interrupt();
        despachador1.interrupt();
        despachador2.interrupt();
        entregador1.interrupt();
        entregador2.interrupt();
        entregador3.interrupt();
        verificador1.interrupt();
        verificador2.interrupt();

        fin = System.currentTimeMillis();
        duracion = fin - inicio;
        
        System.out.println("\nTiempo de ejecución del programa: " + duracion + "(ms)");
        System.out.println("\nPedidos verificados: " + pedidosVerificados.size());
        System.out.println("\nPedidos fallidos: " + pedidosFallidos.size());
        System.out.println("\n" + galponcito200);

        // if (txtWriter != null) {
        //     try {
        //         txtWriter.write("\n======== FIN DE LA EJECUCIÓN ========\n");
        //         txtWriter.write("Duración total: " + duracion + " ms\n");
        //         txtWriter.write("\nPedidos verificados: " + pedidosVerificados.size());
        //         txtWriter.write("\nPedidos fallidos: " + pedidosFallidos.size());
        //         txtWriter.write(galponcito200.toString());
        //         txtWriter.newLine();
        //         txtWriter.flush();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }

        // if (csvWriter != null) {
        //     try {
        //         csvWriter.close();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
    }
*/
    public static void main(String[] args) {

        int CANTIDAD_PEDIDOS = 500;
        long inicio;
        long fin;
        long duracion;
        final String nombreTXT = "LogEstadísticoTXT.txt";
        final String nombreCSV = "LogEstadísticoCSV.csv";

        SynchronizedList<Pedido> pedidosNuevos = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosPreparados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEnTransito = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosEntregados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosVerificados = new SynchronizedList<>();
        SynchronizedList<Pedido> pedidosFallidos = new SynchronizedList<>();
        CentroDeAlmacenamiento galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        for(int i = 1 ; i <= CANTIDAD_PEDIDOS ; i++){
            pedidosNuevos.add(new Pedido());
        }

        Thread preparador1 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread preparador2 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread preparador3 = new Thread(new Preparador(pedidosNuevos, pedidosPreparados, galponcito200));
        Thread despachador1 = new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos));
        Thread despachador2 = new Thread(new Despachador(pedidosPreparados, pedidosEnTransito, pedidosFallidos));
        Thread entregador1 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread entregador2 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread entregador3 = new Thread(new Entregador(pedidosEnTransito, pedidosEntregados, pedidosFallidos));
        Thread verificador1 = new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos));
        Thread verificador2 = new Thread(new Verificador(pedidosEntregados, pedidosVerificados, pedidosFallidos));
        Thread logger = new Thread(new LoggerManager(nombreTXT, nombreCSV, pedidosVerificados, pedidosFallidos));
        
        inicio = System.currentTimeMillis();

        logger.start();
        preparador1.start();
        preparador2.start();
        preparador3.start();
        despachador1.start();
        despachador2.start();
        entregador1.start();
        entregador2.start();
        entregador3.start();
        verificador1.start();
        verificador2.start();


        // while(pedidosFallidos.size() + pedidosVerificados.size() < CANTIDAD_PEDIDOS) {
        //     try {
        //         System.out.println("[" + (System.currentTimeMillis() - inicio) + " ms] Verificados: "
        //                             + pedidosVerificados.size() + ",  Fallidos: " + pedidosFallidos.size());
        //         Thread.sleep(200);
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     } 
        // }

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } 

        fin = System.currentTimeMillis();

        // Interrumpir todos los hilos
        logger.interrupt();
        preparador1.interrupt();
        preparador2.interrupt();
        preparador3.interrupt();
        despachador1.interrupt();
        despachador2.interrupt();
        entregador1.interrupt();
        entregador2.interrupt();
        entregador3.interrupt();
        verificador1.interrupt();
        verificador2.interrupt();

        duracion = fin - inicio;
        
        System.out.println("\nTiempo de ejecución del programa: " + duracion + "(ms)");
        System.out.println("\nPedidos preparados: " + pedidosPreparados.size());
        System.out.println("\nPedidos en transito: " + pedidosEnTransito.size());
        System.out.println("\nPedidos entregados: " + pedidosEntregados.size());
        System.out.println("\nPedidos verificados: " + pedidosVerificados.size());
        System.out.println("\nPedidos fallidos: " + pedidosFallidos.size());
        System.out.println("\n" + galponcito200);
    }
}