package model;

import utils.Utils;
import javax.xml.bind.annotation.XmlRootElement;

public class  Peon extends Pieza {

    public Peon(int fila, int columna, Color color) {
        super(fila, columna, color);
    }

    public Peon() {  }

    @Override
    public boolean movimiento(int nuevaFila, int nuevaColumna, Tablero tablero) throws MovimientoInvalido {
        int filaOrigen = getFila();
        int colOrigen = getColumna();

        int distFila = nuevaFila - filaOrigen;//no se usa calcularDistancia pq contiene math.abs
        int distCol = Utils.calcularDistancia(colOrigen, nuevaColumna);

        int direccion = (getColor() == Color.NEGRO) ? 1 : -1;
        int filaInicial = (getColor() == Color.NEGRO) ? 2 : 7;

        if (distCol == 0) {
            // Movimiento simple
            if (distFila == direccion) {
                if (tablero.getPieza(nuevaFila, nuevaColumna) == null) {
                    this.setFila(nuevaFila);
                    this.setColumna(nuevaColumna);
                    return true;
                }
                throw new MovimientoInvalido("El peón tiene el camino bloqueado.");
            }

            // Movimiento doble desde casilla inicial
            if (filaOrigen == filaInicial && distFila == 2 * direccion) {
                this.setFila(nuevaFila);
                this.setColumna(nuevaColumna);
                return true;
            }else{
                throw new MovimientoInvalido("El peon solo se puede mover 2 casillas en su posicion inicial.");
            }

        }

       //Captura en diagonal
        else if (distCol == 1 && distFila == direccion) {
            Pieza piezaDestino = tablero.getPieza(nuevaFila, nuevaColumna);

            if (piezaDestino == null) {
                throw new MovimientoInvalido("El peón solo mueve en diagonal si es para capturar.");
            }

            if (piezaDestino.getColor() == this.getColor()) {
                throw new MovimientoInvalido("No puedes capturar tus propias piezas.");
            }

            // Si llegó aquí, es captura legal
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
            return true;
        }

        throw new MovimientoInvalido("Movimiento de peón no permitido.");
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