package model;

import java.util.ArrayList;

import static utils.Utils.calcularDireccion;

public class Tablero {
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

    public String printTablero() {
        String casillaBlanca = "░";
        String casillaNegra = "▓";
        String resultado = "";

        for (int i = 0; i < 8; i++) {
            fila[i] = i;
            columna[i] = i;
        }

        for (int f = 0; f < fila.length; f++) {
            for (int c = 0; c < columna.length; c++) {
                Pieza p = getPieza(fila[f], columna[c]);
                if (p != null) {
                    resultado = resultado + p;
                } else {
                    if ((fila[f] + columna[c]) % 2 == 1) {
                        resultado = resultado + casillaBlanca;
                    } else {
                        resultado = resultado + casillaNegra;
                    }
                }
            }
            resultado = resultado + "\n";
        }
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
        addPieza("Reina", 0, 3, Color.BLANCO);
        addPieza("Reina", 7, 3, Color.NEGRO);
        addPieza("Torre", 0, 0, Color.BLANCO);
        addPieza("Torre", 0, 7, Color.BLANCO);
        addPieza("Torre", 7, 0, Color.NEGRO);
        addPieza("Torre", 7, 7, Color.NEGRO);
        addPieza("Alfil", 0, 2, Color.BLANCO);
        addPieza("Alfil", 0, 5, Color.BLANCO);
        addPieza("Alfil", 7, 2, Color.NEGRO);
        addPieza("Alfil", 7, 5, Color.NEGRO);
        addPieza("Caballo", 0, 1, Color.BLANCO);
        addPieza("Caballo", 0, 6, Color.BLANCO);
        addPieza("Caballo", 7, 1, Color.NEGRO);
        addPieza("Caballo", 7, 6, Color.NEGRO);
        for (int i = 0; i < 8; i++) {
            addPieza("Peon", 1, i, Color.BLANCO);
            addPieza("Peon", 6, i, Color.NEGRO);
        }
    }

    private Pieza addPieza(Pieza pieza) {
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

        while (filaActual != filaDestino && colActual != colDestino) {
            if (getPieza(filaActual, colActual) != null) {
                return true;
            }
            filaActual += filaIncremento;
            colActual += colIncremento;
        }
        return false;
    }

    public void moverPieza(Pieza pieza, int nuevaFila, int nuevaColumna) {

        Pieza piezaEnDestino = getPieza(nuevaFila, nuevaColumna);
        if (!pieza.sePuedeMover(piezaEnDestino)) {
            throw new IllegalArgumentException("La pieza no puede moverse a la posición (" + nuevaFila + "," + nuevaColumna + ").");
        }
        else if (!pieza.puedeAtacar(piezaEnDestino)) {
            throw new IllegalArgumentException("La pieza no puede atacar a la posición (" + nuevaFila + "," + nuevaColumna + ").");
        }else if (pieza instanceof Rey && getPieza(nuevaFila, nuevaColumna) instanceof Rey) {
            throw new IllegalArgumentException("No se puede capturar al Rey.");
        }else {
            capturarPieza(piezaEnDestino);
            pieza.movimiento(nuevaFila, nuevaColumna, this);
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
}



