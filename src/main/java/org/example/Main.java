package org.example;

public class Main {
    public static void main(String[] args) {
        CentroDeAlmacenamiento Galponcito200 = new CentroDeAlmacenamiento("Galponcito", 10, 20);

        System.out.println(Galponcito200);

        for (int i = 0; i < 200; i++) {
            Galponcito200.obtenerCasillero().ocupar();
        } //Ocupamos toditos los casilleros

        System.out.println(Galponcito200);

        try{
            Galponcito200.obtenerCasillero().ocupar();
        }
        catch (NullPointerException e){
            System.out.println("Apa");
        }
    }
}