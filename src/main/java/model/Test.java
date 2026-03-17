package model;

import model.*;

public class  Test {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.reiniciarTablero();
        System.out.println(tablero);
        System.out.println("Reiniciando tablero...");
        tablero.reiniciarTablero();
        System.out.println("Tablero reiniciado:");
        System.out.println(tablero);
        System.out.println(tablero.estadoTablero());
        try {
            tablero.moverPieza("1b","3a");
        } catch (MovimientoInvalido e) {
            throw new RuntimeException(e);
        }
        System.out.println(tablero);
        try {
            tablero.moverPieza("3a","5b");
        } catch (MovimientoInvalido e) {
            throw new RuntimeException(e);
        }
        System.out.println(tablero);
        try {
            tablero.moverPieza("5b","3c");
        } catch (MovimientoInvalido e) {
            throw new RuntimeException(e);
        }
        System.out.println(tablero);
        try {
            tablero.moverPieza("3c","1d");
        } catch (MovimientoInvalido e) {
            throw new RuntimeException(e);
        }
        System.out.println(tablero);
    }
}

