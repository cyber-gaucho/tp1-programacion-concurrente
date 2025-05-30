package org.example.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvLogger extends AbstractLogWriter {
    public CsvLogger(String fileName) throws IOException {
        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }
        this.writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.write("Timestamp(ms),Verificados,Fallidos");
        writer.newLine();
    }

    public void escribir(long timestamp, int verificados, int fallidos) throws IOException {
        String lineaCsv = timestamp + "," + verificados + "," + fallidos;
        write(lineaCsv);
    }
}