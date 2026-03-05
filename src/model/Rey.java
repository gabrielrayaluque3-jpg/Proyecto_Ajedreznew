package model;

import utils.Utils;

import java.util.Objects;


public class Rey extends Pieza {

    private static final long serialVersionUID = 1L;
    private static final int PUNTOS = 100; //

    public Rey(int fila, int columna, Color color) {
        super(fila, columna, color);
    }


    public int getPuntos() {
        return PUNTOS;
    }

    public boolean sePuedeMover(int nuevaFila, int nuevaColumna) {

        Utils.validarPosicion(nuevaFila,nuevaColumna);

        int difFila = Math.abs(this.getFila() - nuevaFila);
        int difColumna = Math.abs(this.getColumna() - nuevaColumna);

        if (difFila > 1 || difColumna > 1 || (difFila == 0 && difColumna == 0)) {
            return false;
        }


        Pieza piezaDestino = tablero.getPieza(nuevaFila, nuevaColumna);
        if (piezaDestino != null && piezaDestino.getColor() == this.getColor()) {
            return false;
        }

        return true;
    }


    public boolean puedeAtacar(Pieza otraPieza) {
        boolean puede=false;
        int difFila = Math.abs(this.getFila() - otraPieza.getFila());
        int difColumna = Math.abs(this.getColumna() - otraPieza.getColumna());
        if (difFila <= 1 && difColumna <= 1 && (difFila != 0 || difColumna != 0)){
            puede = true;
        }
        return puede;
    }


    public void mover(int nuevaFila, int nuevaColumna, Tablero tablero) {

        if (!puedeMover(nuevaFila, nuevaColumna, tablero)) {
            throw new IllegalArgumentException("Movimiento no permitido para el Rey.");
        }


        Pieza piezaDestino = tablero.getPieza(nuevaFila, nuevaColumna);
        if (piezaDestino instanceof Rey) {
            throw new IllegalArgumentException("No se puede capturar al Rey.");
        }


        this.setFila(nuevaFila);
        this.setColumna(nuevaColumna);
    }


    public Pieza copiar() {

        return new Rey(this.getFila(), this.getColumna(), this.getColor());
    }

    @Override
    public String toString() {
        return (this.getColor() == Color.BLANCO) ? "♔" : "♚";
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