package model;

public class Alfil extends Pieza {
    final int puntos = 3;

    public Alfil(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) {
        int distFila = nuevaFila - getFila();
        int distCol = nuevaColumna - getColumna();
        return distFila == distCol;
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public Pieza copiar() {
        return null;
    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna) {
        int distFila = nuevaFila - getFila();
        int distCol = nuevaColumna - getColumna();
        return distFila == distCol;
    }

    public int getPuntos() {
        return 3;
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
