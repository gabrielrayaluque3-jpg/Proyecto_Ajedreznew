package model;

public class Rey extends Pieza{
    public Rey(int fila, int columna, Color color, int puntos){
        super(fila, columna, color, puntos);
    }

    private static boolean sePuedeMover(int newFila, int newColumna){
        return true;
    }
}
