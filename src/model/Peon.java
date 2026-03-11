package model;

public class Peon extends Pieza {
    public Peon(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public <Tablero> boolean puedoMover(int nuevaFila, int nuevaColumna, Tablero tablero ){
        /**
         * 1. Validar destino dentro del tablero (ya se hace en el constructor,
         *          pero para el movimiento usamos la lógica del tablero)
         */


        int filaOrigen = getFila();
        int colOrigen = getColumna();

        /**
         * 2. Determinar dirección según color (Blancas suben, Negras bajan o viceversa según tu 0-7)
         * Suponiendo: Blancas (Fila 1 -> 7), Negras (Fila 6 -> 0)
         */
        int direccion = (getColor() == Color.BLANCO) ? 1 : -1;
        int filaInicial = (getColor() == Color.BLANCO) ? 1 : 6;

        int distFila = nuevaFila - filaOrigen;
        int distCol = Math.abs(nuevaColumna - colOrigen);

        /**
         * --- LÓGICA DE MOVIMIENTO (No ataque) ---
         */
        if (distCol == 0) {

            Pieza destino = tablero.getPieza(nuevaFila, nuevaColumna);

            /**
             * Movimiento simple (1 casilla adelante)
             */
            if (distFila == direccion && destino == null) {
                return true;
            }

            /**
             * Movimiento inicial doble (2 casillas adelante)
             */
            if (filaOrigen == filaInicial && distFila == 2 * direccion) {
                return destino == null && !tablero.hayPiezasEntre(filaOrigen, colOrigen, nuevaFila, nuevaColumna);
            }
        }


        if (distFila == direccion && distCol == 1) {
            Pieza piezaObjetivo = tablero.getPieza(nuevaFila, nuevaColumna);

            return piezaObjetivo != null && piezaObjetivo.getColor() != this.getColor();
        }

        return false;
    }


    public <Tablero> boolean puedeAtacar(Pieza objetivo, Tablero tablero) {
        /**
         * Un peón puede atacar a una pieza si está en su diagonal frontal
         */
        int direccion = (getColor() == Color.BLANCO) ? 1 : -1;
        int distFila = objetivo.getFila() - getFila();
        int distCol = Math.abs(objetivo.getColumna() - getColumna());

        return distFila == direccion && distCol == 1;
    }


    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna) {
        return false;
    }

    public int getPuntos() {
        return 1;
    }


    public Pieza copiar() {
        return new Peon(getFila(), getColumna(), getColor());
    }


    public String toString() {
        return (getColor() == Color.BLANCO) ? "♙" : "♟";
    }
}
