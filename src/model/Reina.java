package model;
import utils.Utils;
public class Reina extends Pieza {

    public Reina(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) {
        if (Utils.esRectilineo(getFila(), getColumna(), nuevaFila, nuevaColumna) && !Utils.esDiagonal(getFila(), getColumna(), nuevaFila, nuevaColumna)) {
            return true;
        }
        return false;
    }

    @Override
    public int getPuntos() {
        return 9;
    }

    @Override
    public Pieza copiar() {
        return new Reina(getFila(), getColumna(), getColor());
    }

    @Override
    public String toString() {
        return (getColor() == Color.BLANCO) ? "♕" : "♛";
    }

}

