package model;

public class Caballo extends Pieza {
    final int puntos = 3;

    public Caballo(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public boolean sePuedeMover(int nuevaFila, int nuevaColumna) {
        int distFila = nuevaFila - getFila();
        int distCol = nuevaColumna - getColumna();
        return (distFila == 2 && distCol == 1) || (distFila == 1 && distCol == 2);
    }

    @Override
    public String toString() {
        if (getColor() == Color.BLANCO) {
            return "♞";
        } else {
            return "♘";
        }
    }
}