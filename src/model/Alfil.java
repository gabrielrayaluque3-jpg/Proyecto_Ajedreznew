package model;

public class Alfil extends Pieza {
    final int puntos = 3;

    public Alfil(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public boolean sePuedeMover(int nuevaFila, int nuevaColumna) {
        int distFila = nuevaFila - getFila();
        int distCol = nuevaColumna - getColumna();
        return distFila == distCol;
    }

    @Override
    public String toString() {
        if (getColor() == Color.BLANCO) {
            return "♝";
        } else {
            return "♗";
        }
    }
}

