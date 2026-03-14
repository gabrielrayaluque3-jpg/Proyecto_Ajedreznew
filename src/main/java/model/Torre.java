package model;


import utils.Utils;

public class  Torre extends Pieza {
    public Torre(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna,Tablero tablero) {

        if (Utils.esRectilineo(getFila(),getColumna(), nuevaFila, nuevaColumna)){
            return true;
        }
        return false;
    }

    @Override
    public int getPuntos() {
        return 5;
    }

    @Override
    public Pieza copiar() {
        return new Torre(getFila(), getColumna(), getColor());
    }
    @Override
    public String toString() {
        if (getColor() == Color.BLANCO) {
            return "♜";
        } else {
            return "♖";
        }
    }
}
