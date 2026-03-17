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
        try{
            tablero.moverPieza("2b","3b");
        }catch (MovimientoInvalido e){
            System.out.println(e.getMessage());
        }
        System.out.println(tablero);
        try{
            tablero.moverPieza("7e","5e");
        }catch (MovimientoInvalido e){
            System.out.println(e.getMessage());
        }
        System.out.println(tablero);
        try{
            tablero.moverPieza("2d","4d");
        }catch (MovimientoInvalido e){
            System.out.println(e.getMessage());
        }
        System.out.println(tablero);

        try{
            tablero.moverPieza("5e","4d");
        }catch (MovimientoInvalido e){
            System.out.println(e.getMessage());
        }
        System.out.println(tablero);
        System.out.println(tablero.estadoTablero());
    }
}

