package model;


import utils.Utils;

public class Torre extends Pieza {

    private int fila;
    private int columna;
    private Color color;

    public Torre(int fila, int columna, Color color) {
        if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
            throw new IllegalArgumentException("Posición fuera del tablero");
        }

        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }

    public boolean puedeMover(int nuevaFila, int nuevaColumna, Tablero tablero) {

        if (nuevaFila < 0 || nuevaFila > 7 || nuevaColumna < 0 || nuevaColumna > 7) {
            return false;
        }

        if (nuevaFila == fila && nuevaColumna == columna) {
            return false;
        }

        boolean movimientoRecto =
                nuevaFila == fila ||
                        nuevaColumna == columna;

        if (!movimientoRecto) {
            return false;
        }

        if (tablero.hayPiezasEntre(fila, columna, nuevaFila, nuevaColumna)) {
            return false;
        }

        Pieza destino = tablero.getPieza(nuevaFila, nuevaColumna);

        if (destino != null && destino.getColor() == this.color) {
            return false;
        }

        return true;
    }

    public int getPuntos() {
        return 5;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna,Tablero tablero) {


        if (Utils.esRectilineo(getFila(),getColumna(), nuevaFila, nuevaColumna)){
            return true;
        }


        if (Tablero.hayPiezasEntre(getFila(), getColumna(), nuevaFila, nuevaColumna)) {
            return false;
        }

        Pieza destino = Tablero.getPieza(nuevaFila, nuevaColumna);

        if (destino != null && destino.getColor() == this.color) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return color == Color.BLANCO ? "♖" : "♜";
    }
}