package utils;

public class Utils {
    public static void validarPosicion (int fila, int columna){
        if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
            throw new IllegalArgumentException("La posición (" + fila + "," + columna + ") está fuera del rango permitido (0-7).");
        }
    }

    public static int calcularDireccion(int origen, int destino) {
        return Integer.compare(destino, origen);
    }

    public static int obtenerDelta(int p1, int p2) {
        return Math.abs(p2 - p1);
    }

    public static boolean esDiagonal(int f1, int c1, int f2, int c2) {
        return obtenerDelta(f1, f2) == obtenerDelta(c1, c2);
    }

    public static boolean esRectilineo(int f1, int c1, int f2, int c2) {
        return obtenerDelta(f1, f2) == obtenerDelta(c1, c2);
    }
}
