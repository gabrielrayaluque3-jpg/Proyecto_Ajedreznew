package model;

import DataAccess.XMLManager;
import utils.Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Objects;

@XmlSeeAlso({Rey.class, Reina.class, Torre.class, Alfil.class, Caballo.class, Peon.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class  Pieza implements Serializable {

    //las posiciones son del 0 al 7
    protected int fila;      // en el tablero, son las filas de 1-8
    protected int columna;  //en el tablero serian A-H
    protected Color color;  //es necesario el enum Color para saber si es blanca o negra
    //los puntos se añade como un final int en cada clase (final para que no cambie)

    public Pieza() {
    }

    public Pieza(int fila, int columna,Color color) {
        Utils.validarPosicion(fila,columna);

        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser nulo.");
        }
        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        if (fila < 0 || fila > 7) {
            throw new IllegalArgumentException("La fila debe estar entre 0 y 7. Valor recibido: " + fila);
        }else {
            this.fila = fila;
        }
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        if (columna < 0 || columna > 7) {
            throw new IllegalArgumentException("La columna debe estar entre 0 y 7. Valor recibido: " + columna);
        }else {
            this.columna = columna;
        }
    }

    public Color getColor() {
        return color;
    }

    public boolean puedeAtacar(Pieza objetivo){
        if (objetivo == null) {
            return false;
        }
        else{
            return this.color != objetivo.getColor();
        }
    }

    public boolean sePuedeMover(Pieza objetivo){
        return objetivo == null;
    }

    public abstract boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) throws MovimientoInvalido;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pieza pieza = (Pieza) o;
        return fila == pieza.fila && columna == pieza.columna && color == pieza.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna, color);
    }

    public abstract int getPuntos();

    public abstract Pieza copiar();

    @Override
    public String toString() {
        return "Fila: " + fila + ", Columna: " + columna + ", Color: " + color;
    }

}
