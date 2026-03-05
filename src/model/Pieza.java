package model;

import java.util.Objects;

public abstract class Pieza {

    //las posiciones son del 0 al 7
    protected int fila;
    protected int columna;  //las posiciones son del 0 al 7, pero en el tablero serian A-H
    protected Color color;  //es necesario el enum Color para saber si es blanca o negra
    //los puntos se añade como un final int en cada clase (final para que no cambie)

    public Pieza() {
    }

    public Pieza(int fila, int columna,Color color) {
        if (fila < 0 || fila > 7) {
            throw new IllegalArgumentException("La fila debe estar entre 0 y 7. Valor recibido: " + fila);
        }
        if (columna < 0 || columna > 7) {
            throw new IllegalArgumentException("La columna debe estar entre 0 y 7. Valor recibido: " + columna);
        }

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
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract boolean sePuedeMover(int nuevaFila, int nuevaColumna);

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

    @Override
    public String toString() {
        return "Fila: " + fila + ", Columna: " + columna + ", Color: " + color;
    }

}