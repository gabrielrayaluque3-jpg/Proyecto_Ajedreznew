package model;

public class Pieza {

    //las posiciones son del 0 al 7
    protected int fila;
    protected int columna;  //las posiciones son del 0 al 7, pero en el tablero serian A-H
    protected Color color;  //es necesario el enum Color para saber si es blanca o negra

    public Pieza() {
    }

    public Pieza(int fila, int columna) {
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
}

