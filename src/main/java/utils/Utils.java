package utils;

import model.*;

public class  Utils {
    public static void validarPosicion (int fila, int columna){
        if (fila < 1 || fila > 8 || columna < 1 || columna > 8) {
            throw new IllegalArgumentException("La posición (" + fila + "," + columna + ") está fuera del rango permitido (1-8).");
        }
    }

    public static int calcularDistancia(int origen, int destino) {
        return Math.abs(destino - origen);
    }
    public static int calcularDireccion(int origen, int destino) {
        return Integer.compare(destino, origen);
    }

    public static boolean esDiagonal(int f1, int c1, int f2, int c2) {
        return Math.abs(f1 - f2) == Math.abs(c1 - c2) && (f1 != f2) ;
    }

    public static boolean esRectilineo(int f1, int c1, int f2, int c2) {
        int dirFila = calcularDireccion(f1, f2);
        int dirColumna = calcularDireccion(c1, c2);

        return (dirFila == 0 && dirColumna == 1) || (dirFila == 1 && dirColumna == 0);
    }
    public static boolean validarTrayectoria(Pieza p, int filaDestino, int columnaDestino) {
        int filaOrigen = p.getFila();
        int columnaOrigen = p.getColumna();

        //El guin bajo hace que el switch ignore el nombre de la variable
        switch (p) {
            case Torre _ -> {
                return filaOrigen == filaDestino || columnaOrigen == columnaDestino;
            }
            case Alfil _ -> {
                return Math.abs(filaOrigen - filaDestino) == Math.abs(columnaOrigen - columnaDestino);
            }
            case Reina _ -> {
                return filaOrigen == filaDestino || columnaOrigen == columnaDestino || Math.abs(filaOrigen - filaDestino) == Math.abs(columnaOrigen - columnaDestino);
            }
            case Caballo _ -> {
                int diferenciaFila = Math.abs(filaOrigen - filaDestino);
                int diferenciaColumna = Math.abs(columnaOrigen - columnaDestino);
                return (diferenciaFila == 2 && diferenciaColumna == 1) || (diferenciaFila == 1 && diferenciaColumna == 2);
            }
            case Peon _ -> {
                int dir = (p.getColor() == Color.BLANCO) ? 1 : -1;
                return (filaDestino == filaOrigen + dir) && Math.abs(columnaDestino  - columnaOrigen) == 1;
            }
            default -> {
            }
        }
        return false;
    }
}
