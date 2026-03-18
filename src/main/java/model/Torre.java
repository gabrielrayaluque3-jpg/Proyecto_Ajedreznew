package model;


import utils.Utils;

import javax.xml.bind.annotation.XmlRootElement;

public class  Torre extends Pieza {
    public Torre(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public Torre() { }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna,Tablero tablero) throws MovimientoInvalido {

        if (Utils.esRectilineo(getFila(),getColumna(), nuevaFila, nuevaColumna)){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
            return true;
        }else{
            throw new MovimientoInvalido("La torre solo se mueve en línea recta.");
        }
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
        if (getColor() != Color.BLANCO) {
            return "♜";
        } else {
            return "♖";
        }
    }
}
