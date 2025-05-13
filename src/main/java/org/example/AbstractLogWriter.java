package org.example;

import java.io.BufferedWriter;
import java.io.IOException;

public class AbstractLogWriter {
    protected BufferedWriter writer;

    void write(String message) throws IOException{
        writer.write(message);
        writer.newLine();
        writer.flush();
    };


    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
}