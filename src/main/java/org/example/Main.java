package org.example;

public class Main {

    public static void main(String[] args) {
               
        Simulador simulador = new Simulador(
            500,
            1,
            1,
            1,
            1,
            10,
            20,
            "LogEstadísticoTXT.txt",
            "LogEstadísticoCSV.csv"
        );
        
        Simulador simulador2 = new Simulador(
            500,
            10,
            2,
            3,
            2,
            10,
            20,
            "LogEstadísticoTXT2.txt",
            "LogEstadísticoCSV2.csv"
        );

        simulador.ejecutar();
        simulador2.ejecutar();

    }
}