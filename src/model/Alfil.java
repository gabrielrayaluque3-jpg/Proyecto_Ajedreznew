package model;

public class Alfil extends Pieza {
    final int puntos = 3;

    public Alfil(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) {
        if(Utils.esDiagonal(getFila(),getColumna(),nuevaFila,nuevaColumna)){
            return true;
        }
        return false;
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public Pieza copiar() {
        return null;

    @Override
    public Pieza copiar() {
        return new Alfil(getFila(), getColumna(), getColor());
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
