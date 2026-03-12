package model;
import utils.Utils;
public class Reina extends Pieza {

    public Reina(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) {
        int filaOrigen = getFila();
        int colOrigen = getColumna();


        boolean esRecto = (filaOrigen == nuevaFila || colOrigen == nuevaColumna);
        boolean esDiagonal = Math.abs(nuevaFila - filaOrigen) == Math.abs(nuevaColumna - colOrigen);

        if (!esRecto && !esDiagonal) {
            return false;
        }


        if (tablero.hayPiezasEntre(filaOrigen, colOrigen, nuevaFila, nuevaColumna)) {
            return false;
        }


        Pieza piezaDestino = tablero.getPieza(nuevaFila, nuevaColumna);


        return piezaDestino == null || piezaDestino.getColor() != this.getColor();
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

