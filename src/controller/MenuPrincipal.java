package controller;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarEstadoPartida(String tableroDibujado, String turno, int puntosB, int puntosN, String piezasMuertas, boolean jaque) {
        System.out.println("--------ESTADO DE LA PARTIDA--------");
        System.out.println("Turno actual: " + turno.toUpperCase());
        if (jaque) System.out.println("¡ATENCIÓN! El rey está en JAQUE ");

        System.out.println("\n" + tableroDibujado);

        System.out.println("\nPuntuación: Blancas [" + puntosB + "] - Negras [" + puntosN + "]");
        System.out.println("Piezas eliminadas: " + piezasMuertas);
        System.out.println("----------------------------------------");
    }

    public int menuPrincipal() {
        System.out.println("1. Seleccionar pieza");
        System.out.println("2. Reiniciar tablero");
        System.out.println("3. Cargar tablero");
        System.out.println("4. Guardar tablero");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opción: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
