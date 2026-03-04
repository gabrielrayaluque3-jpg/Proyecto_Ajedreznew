package utils;

public class Utils {

    //Valída si una posición "fila y columna" está dentro de los límites que tiene el tablero (0-7).
    public static void validarPosicion (int fila, int columna){
        if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
            throw new IllegalArgumentException("La posición (" + fila + "," + columna + ") está fuera del rango permitido (0-7).");
        }
    }

    //Calcula la dirección del movimiento paso a paso (-1, 0, 1).
    public static int calcularDireccion(int origen, int destino) {
        return Integer.compare(destino, origen);
    }

    //Calcula la distancia absoluta que hay entre dos puntos en una dimensión.
    public static int obtenerDelta(int p1, int p2) {
        return Math.abs(p2 - p1);
    }

    //Para comprobar si el movimiento es en diagonal de la ficha en el tablero
    public static boolean esDiagonal(int f1, int c1, int f2, int c2) {
        return obtenerDelta(f1, f2) == obtenerDelta(c1, c2);
    }

    //Para saber si el movimiento es rectilineo.
    public static boolean esRectilineo(int f1, int c1, int f2, int c2) {
        return obtenerDelta(f1, f2) == obtenerDelta(c1, c2);
    }
}


