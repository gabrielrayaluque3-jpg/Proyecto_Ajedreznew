package model;

import java.util.ArrayList;

public class Tablero {
    ArrayList<Pieza> piezasBlancas = new ArrayList<>();
    ArrayList<Pieza> piezasNegras = new ArrayList<>();
    ArrayList<Pieza> piezasEliminadas = new ArrayList<>();

    public static final int[] fila=new int[8];
    public static final int[] columna=new int[8];

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
                if ((fila[f] + columna[c]) % 2 == 1) {
                    resultado = resultado + casillaBlanca;
                } else {
                    resultado = resultado + casillaNegra;
                }
            }
            resultado = resultado + "\n";
        }
        return resultado;
    }

    public void vaciarTablero(){
        piezasBlancas.clear();
        piezasNegras.clear();
        piezasEliminadas.clear();
    }

    public Tablero crearCopiaTablero(){
        Tablero copia = new Tablero();
        for (Pieza pieza : piezasBlancas) {
            copia.addPieza(pieza);
        }
        for (Pieza pieza : piezasNegras) {
            copia.addPieza(pieza);
        }
        return copia;
    }

    private Pieza addPieza(Pieza pieza) {
        return pieza;
    }

    public Pieza addPieza(String nombre,int fila, int columna, Color color) {
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




}
