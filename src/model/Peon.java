package model;

public class Peon extends Pieza {

    public Peon(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna) {

        int filaOrigen = getFila();
        int colOrigen = getColumna();

        int direccion = (getColor() == Color.BLANCO) ? 1 : -1;
        int filaInicial = (getColor() == Color.BLANCO) ? 1 : 6;

        int distFila = nuevaFila - filaOrigen;
        int distCol = Math.abs(nuevaColumna - colOrigen);

        if (distCol == 0) {

            if (distFila == direccion) {
                return true;
            }

            if (filaOrigen == filaInicial && distFila == 2 * direccion) {
                return true;
            }
        }

        if (distFila == direccion && distCol == 1) {
            return true;
        }

        return false;
    }

    @Override
    public int getPuntos() {
        return 1;
    }

    @Override
    public Pieza copiar() {
        return new Peon(getFila(), getColumna(), getColor());
    }

    @Override
    public String toString() {
        return (getColor() == Color.BLANCO) ? "♙" : "♟";
    }
}