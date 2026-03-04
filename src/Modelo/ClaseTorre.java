package Modelo;

public class Torre extends Pieza {

    public Torre(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean puedeMover(int nuevaFila, int nuevaColumna, Tablero tablero) {

        // Este es el ️destino de la pieza dentro del tablero
        if (!Utils.posicionValida(nuevaFila, nuevaColumna)) {
            return false;
        }

        // Esto sirve para no quedarse en la misma casilla
        if (nuevaFila == getFila() && nuevaColumna == getColumna()) {
            return false;
        }

        // Este es el movimiento recto (Según sea Vertical o Horizontal)
        boolean movimientoValido =
                nuevaFila == getFila() ||
                        nuevaColumna == getColumna();

        if (!movimientoValido) {
            return false;
        }

        // Esto sirve para comprobar las piezas intermedias (Esta pieza no es saltadora)
        if (tablero.hayPiezasEntre(getFila(), getColumna(), nuevaFila, nuevaColumna)) {
            return false;
        }

        // Esto se utiliza para no capturar la pieza del mismo color
        Pieza destino = tablero.getPieza(nuevaFila, nuevaColumna);

        if (destino != null && destino.getColor() == this.getColor()) {
            return false;
        }

        return true;
    }

    @Override
    public int getPuntos() {
        return 5;
    }

    @Override
    public Pieza copia() {
        return new Torre(getFila(), getColumna(), getColor());
    }

    @Override
    public String toString() {
        return getColor() == Color.BLANCO ? "♖" : "♜";
    }
}
