package model;

public class Caballo extends Pieza {
    final int puntos = 3;

    public Caballo(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) {
        int distFila = nuevaFila - getFila();
        int distCol = nuevaColumna - getColumna();
        return (distFila == 2 && distCol == 1) || (distFila == 1 && distCol == 2);
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public Pieza copiar() {
        return new Caballo(getFila(), getColumna(), getColor());
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