package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

}
