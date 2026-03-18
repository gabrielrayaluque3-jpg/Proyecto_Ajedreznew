package model;

import utils.Utils;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;


public class  Rey extends Pieza {

    public Rey(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public Rey() { }

    public int getPuntos() {
        return 100;
    }

    public boolean movimiento(int nuevaFila, int nuevaColumna,Tablero tablero) throws MovimientoInvalido{

        int distFila = Utils.calcularDistancia(this.getFila(), nuevaFila);
        int distCol = Utils.calcularDistancia(this.getColumna(), nuevaColumna);
        if (distFila <= 1 && distCol <= 1 && (distFila + distCol > 0)) {
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
            return true;
        }
        else {
            throw new MovimientoInvalido("El Rey solo puede moverse 1 casilla.");
        }
    }

    //falta jaque


    @Override
    public Pieza copiar() {

        return new Rey(this.getFila(), this.getColumna(), this.getColor());
    }

    @Override
    public String toString() {
        if (getColor() == Color.BLANCO) {
            return "♚";
        } else {
            return "♔";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rey)) return false;
        if (!super.equals(o)) return false;
        Rey rey = (Rey) o;
        return getFila() == rey.getFila() &&
                getColumna() == rey.getColumna() &&
                getColor() == rey.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFila(), getColumna(), getColor());
    }
}
