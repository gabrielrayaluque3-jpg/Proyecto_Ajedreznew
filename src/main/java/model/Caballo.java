package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import utils.Utils;

public class Caballo extends Pieza implements PiezaSaltadora, Serializable {

    public Caballo(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public Caballo() { super(); }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) throws MovimientoInvalido {
        int distFila = Utils.calcularDistancia(this.getFila(), nuevaFila);
        int distCol = Utils.calcularDistancia(this.getColumna(), nuevaColumna);

        if ((distFila == 2 && distCol == 1) || (distFila == 1 && distCol == 2)){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
            return true;
        } else {
            throw new MovimientoInvalido("El caballo solo se mueve en forma de L.");
        }
    }

    public int getPuntos() {
        return 3;
    }

    @Override
    public Pieza copiar() {
        return new Caballo(getFila(), getColumna(), getColor());
    }

    @Override
    public boolean puedeSaltar() {
        return true;
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