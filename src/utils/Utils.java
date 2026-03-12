package utils;

public class Utils {
    public static void validarPosicion (int fila, int columna){
        if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
            throw new IllegalArgumentException("La posición (" + fila + "," + columna + ") está fuera del rango permitido (0-7).");
        }
    }

    public static int calcularDireccion(int origen, int destino) {
        return Math.abs(Integer.compare(destino, origen));
    }

    public static boolean esDiagonal(int f1, int c1, int f2, int c2) {
        return Math.abs(f1 - f2) == Math.abs(c1 - c2);
    }

    public static boolean esRectilineo(int f1, int c1, int f2, int c2) {
        int dirFila = calcularDireccion(f1, f2);
        int dirColumna = calcularDireccion(c1, c2);

        return (dirFila == 0 && dirColumna == 1) || (dirFila == 1 && dirColumna == 0);
    }
}
