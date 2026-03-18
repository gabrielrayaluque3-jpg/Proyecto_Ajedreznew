package model;

import utils.Utils;

import javax.xml.bind.annotation.XmlRootElement;

public class  Alfil extends Pieza {

    public Alfil(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public Alfil() {  }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) throws MovimientoInvalido {
        if(Utils.esDiagonal(getFila(),getColumna(),nuevaFila,nuevaColumna)){
            return true;
        }else{
            throw new MovimientoInvalido("El alfil solo se mueve en diagonal.");
        }
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public Pieza copiar() {
        return new Alfil(getFila(), getColumna(), getColor());
    }
    @Override
    public String toString() {
        if (getColor() != Color.BLANCO) {
            return "♝";
        } else {
            return "♗";
        }
    }
}
