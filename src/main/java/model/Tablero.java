package model;

import java.io.Serializable;
import java.util.ArrayList;

import static utils.Utils.calcularDireccion;

public class  Tablero implements Serializable {
    ArrayList<Pieza> piezasBlancas = new ArrayList<>();
    ArrayList<Pieza> piezasNegras = new ArrayList<>();
    ArrayList<Pieza> piezasEliminadas = new ArrayList<>();

    public int puntuacionBlanca = 139;
    public int puntuacionNegra = 139;

    public static final int[] fila = new int[8];
    public static final int[] columna = new int[8];

    public int getPuntuacionBlanca(){
        int sumaVivas = 0;
        for (Pieza p : piezasBlancas) {
            sumaVivas += p.getPuntos();
        }

        int sumaEliminadas = 0;
        for (Pieza p : piezasEliminadas) {
            if (p.getColor() == Color.BLANCO) {
                sumaEliminadas += p.getPuntos();
            }
        }
        return this.puntuacionBlanca = sumaVivas - sumaEliminadas;
    }

    public int getPuntuacionNegra(){
        int sumaVivas = 0;
        for (Pieza p : piezasNegras) {
            sumaVivas += p.getPuntos();
        }

        int sumaEliminadas = 0;
        for (Pieza p : piezasEliminadas) {
            if (p.getColor() == Color.NEGRO) {
                sumaEliminadas += p.getPuntos();
            }
        }
        return this.puntuacionNegra = sumaVivas - sumaEliminadas;
    }

    private int letraAColumna(char letra) {
        return Character.toUpperCase(letra) - 'A' + 1;
    }

    private char columnaALetra(int col) {
        return (char) ('a' + col - 1);
    }

    public String printTablero() {
        String casillaBlanca = "░";
        String casillaNegra = "▓";
        String resultado = "";

        for (int i = 0; i < 8; i++) {
            fila[i] = i + 1;
            columna[i] = i + 1;
        }

        for (int f = 7; f >= 0; f--) {
            resultado = resultado + fila[f] + " ";
            for (int c = 0; c < 8; c++) {
                Pieza p = getPieza(fila[f], columna[c]);
                if (p != null) {
                    resultado = resultado + p;
                } else {
                    if ((fila[f] + columna[c]) % 2 == 0) {
                        resultado = resultado + casillaNegra;
                    } else {
                        resultado = resultado + casillaBlanca;
                    }
                }
            }
            resultado = resultado + "\n";
        }
        resultado = resultado + "  a b c d e f g h\n";
        return resultado;
    }

    public void vaciarTablero() {
        piezasBlancas.clear();
        piezasNegras.clear();
        piezasEliminadas.clear();
    }

    public Tablero crearCopiaTablero() {
        Tablero copia = new Tablero();
        for (Pieza pieza : piezasBlancas) {
            copia.addPieza(pieza);
        }
        for (Pieza pieza : piezasNegras) {
            copia.addPieza(pieza);
        }
        return copia;
    }

    public void reiniciarTablero() {
        vaciarTablero();
        addPieza("Rey", 1, 5, Color.BLANCO);
        addPieza("Rey", 8, 4, Color.NEGRO);
        addPieza("Reina", 1, 4, Color.BLANCO);
        addPieza("Reina", 8, 5, Color.NEGRO);
        addPieza("Torre", 1, 1, Color.BLANCO);
        addPieza("Torre", 1, 8, Color.BLANCO);
        addPieza("Torre", 8, 1, Color.NEGRO);
        addPieza("Torre", 8, 8, Color.NEGRO);
        addPieza("Alfil", 1, 3, Color.BLANCO);
        addPieza("Alfil", 1, 6, Color.BLANCO);
        addPieza("Alfil", 8, 3, Color.NEGRO);
        addPieza("Alfil", 8, 6, Color.NEGRO);
        addPieza("Caballo", 1, 2, Color.BLANCO);
        addPieza("Caballo", 1, 7, Color.BLANCO);
        addPieza("Caballo", 8, 2, Color.NEGRO);
        addPieza("Caballo", 8, 7, Color.NEGRO);
        for (int i = 1; i < 9; i++) {
            addPieza("Peon", 2, i, Color.BLANCO);
            addPieza("Peon", 7, i, Color.NEGRO);
        }
    }

    public Pieza addPieza(Pieza pieza) {
        if (pieza.getColor() == Color.BLANCO) {
            piezasBlancas.add(pieza);
        } else {
            piezasNegras.add(pieza);
        }
        return pieza;
    }

    public Pieza addPieza(String nombre, int fila, int columna, Color color) {
        Pieza pieza = null;
        switch (nombre) {
            case "Reina":
                pieza = new Reina(fila, columna, color);
                break;
            case "Caballo":
                pieza = new Caballo(fila, columna, color);
                break;
            case "Alfil":
                pieza = new Alfil(fila, columna, color);
                break;
            case "Torre":
                pieza = new Torre(fila, columna, color);
                break;
            case "Peon":
                pieza = new Peon(fila, columna, color);
                break;
            case "Rey":
                pieza = new Rey(fila, columna, color);
                break;
            default:
                throw new IllegalArgumentException("Nombre de pieza no válido: " + nombre);
        }
        return addPieza(pieza);
    }

    public Pieza getPieza(int fila, int columna) {
        for (Pieza pieza : piezasBlancas) {
            if (pieza.getFila() == fila && pieza.getColumna() == columna) {
                return pieza;
            }
        }
        for (Pieza pieza : piezasNegras) {
            if (pieza.getFila() == fila && pieza.getColumna() == columna) {
                return pieza;
            }
        }
        return null;
    }

    public boolean hayPiezasEntre(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        int filaIncremento = calcularDireccion(filaOrigen, filaDestino);
        int colIncremento = calcularDireccion(colOrigen, colDestino);
        int filaActual = filaOrigen + filaIncremento;
        int colActual = colOrigen + colIncremento;

        while (filaActual != filaDestino || colActual != colDestino) {
            if (getPieza(filaActual, colActual) != null) {
                return true;
            }
            if (filaActual != filaDestino) filaActual += filaIncremento;
            if (colActual != colDestino) colActual += colIncremento;
        }
        return false;
    }

    public void moverPieza(String origen, String destino) {
        int filaOrigen = Character.getNumericValue(origen.charAt(0));
        int colOrigen = letraAColumna(origen.charAt(1));

        int filaDestino = Character.getNumericValue(destino.charAt(0));
        int colDestino = letraAColumna(destino.charAt(1));

        Pieza pDestino = getPieza(filaDestino, colDestino);
        Pieza p = getPieza(filaOrigen, colOrigen);
        if (p == null) {
            throw new IllegalArgumentException("No hay ninguna pieza en la posición " + filaOrigen + "," + colOrigen + ".");
        } else if(pDestino!=null) {
            try {
                p.movimiento(filaDestino, colDestino, this);
            } catch (MovimientoInvalido e){
                if (hayPiezasEntre(filaOrigen, colOrigen, filaDestino, colDestino)) {
                    throws e
                } else if (!p.puedeAtacar(pDestino)) {
                    throw new IllegalArgumentException("La pieza es del mismo color");
                } else if (jaque()) {
                    throw new IllegalArgumentException("Tu rey está en jaque. No puedes moverte");
                } else {
                    p.setFila(filaDestino);
                    p.setColumna(colDestino);
                    capturarPieza(pDestino);
                }
            }
        }

        getPuntuacionBlanca();
        getPuntuacionNegra();
    }

    public void capturarPieza(Pieza capturada) {
        if (capturada.getColor() == Color.BLANCO) {
            piezasBlancas.remove(capturada);
        } else {
            piezasNegras.remove(capturada);
        }
        piezasEliminadas.add(capturada);
    }
    public boolean jaque(){
        Rey reyNegro=null;
        Rey reyBlanco=null;

        for (Pieza p : piezasBlancas) {
            if (p instanceof Rey) {
                reyBlanco = (Rey) p;
            }
        }

        for (Pieza p : piezasNegras) {
            if (p instanceof Rey) {
                reyNegro = (Rey) p;
            }
        }

        for (Pieza p : piezasNegras) {
            if (p.puedeAtacar(reyBlanco)) {
                return true;
            }
        }

        for (Pieza p : piezasBlancas) {
            if (p.puedeAtacar(reyNegro)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Piezas blancas: " + piezasBlancas + "\nPiezas negras: " + piezasNegras + "\nPiezas eliminadas: " + piezasEliminadas + "\nPuntuación blanca: " + puntuacionBlanca + "\nPuntuación negra: " + puntuacionNegra;
    }
}



