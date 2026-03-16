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
        tablero.moverPieza("1d","2c");
        System.out.println(tablero.printTablero());
        tablero.vaciarTablero();
        System.out.println(tablero.printTablero());
        tablero.addPieza(new Rey(1, 1, Color.BLANCO));
        System.out.println(tablero.printTablero());
        tablero.moverPieza("1a","2a");
        System.out.println(tablero.printTablero());
        tablero.moverPieza("2a","4a");
        System.out.println(tablero.printTablero());
    }
}

