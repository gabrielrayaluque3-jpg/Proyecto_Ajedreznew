package model;

import utils.Utils;

public class  Peon extends Pieza {

    public Peon(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) throws MovimientoInvalido {
        int filaOrigen = getFila();
        int colOrigen = getColumna();

        int distFila = nuevaFila - filaOrigen;//no se usa calcularDistancia pq contiene math.abs
        int distCol = Utils.calcularDistancia(colOrigen, nuevaColumna);

        int direccion = (getColor() == Color.BLANCO) ? 1 : -1;
        int filaInicial = (getColor() == Color.BLANCO) ? 2 : 7;

        if (distCol == 0) {
            // Movimiento simple
            if (distFila == direccion) {
                if (tablero.getPieza(nuevaFila, nuevaColumna) == null) {
                    this.setFila(nuevaFila);
                    return true;
                }
                throw new MovimientoInvalido("El peón tiene el camino bloqueado.");
            }

            // Movimiento doble desde casilla inicial
            if (filaOrigen == filaInicial && distFila == 2 * direccion) {
                return true;
            }else{
                throw new MovimientoInvalido("El peon solo se puede mover 2 casillas en su posicion inicial.");
            }

        }

       //Captura en diagonal
        if (distFila == direccion && distCol == 1) {
            Pieza piezaDestino = tablero.getPieza(nuevaFila, nuevaColumna);
            if (piezaDestino != null && piezaDestino.getColor() != this.getColor()) {
                this.setFila(nuevaFila);
                this.setColumna(nuevaColumna);
                return true;
            }
            throw new MovimientoInvalido("El peón solo puede mover en diagonal para capturar.");
        }

        throw new MovimientoInvalido("Movimiento del peón inválido.");
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
        if (getColor() == Color.BLANCO) {
            return "♟";
        } else {
            return "♙";
        }
    }
}