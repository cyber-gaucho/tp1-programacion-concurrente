package org.example.logger;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class AbstractLogWriter {
    protected BufferedWriter writer;

    public void write(String message) throws IOException{
        writer.write(message);
        writer.newLine();
        writer.flush();
    };

    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
}