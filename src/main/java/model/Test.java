package model;

import model.*;

public class  Test {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.reiniciarTablero();
        System.out.println(tablero.printTablero());
        System.out.println("Reiniciando tablero...");
        tablero.reiniciarTablero();
        System.out.println("Tablero reiniciado:");
        System.out.println(tablero.printTablero());
        System.out.println(tablero);
    }
}
