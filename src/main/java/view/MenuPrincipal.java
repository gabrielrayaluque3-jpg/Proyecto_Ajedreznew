package view;

import java.util.Scanner;
import controller.TableroController;
import model.Pieza;

public class  MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private TableroController controller;

    public MenuPrincipal() {
        controller = new TableroController(this);
    }
public class  MenuPrincipal  {
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarEstadoPartida(String tableroDibujado, String turno, int puntosB, int puntosN, String piezasMuertas, boolean jaque) {
        System.out.println("--------ESTADO DE LA PARTIDA--------");
        System.out.println("Turno actual: " + turno.toUpperCase());
        if (jaque) System.out.println("¡ATENCIÓN! El rey está en JAQUE ");

        System.out.println("\n" + tableroDibujado);

        System.out.println("\nPuntuación: Blancas [" + puntosB + "] - Negras [" + puntosN + "]");
        System.out.println("Piezas eliminadas: " + piezasMuertas);
        System.out.println("----------------------------------------");
    }

    public static int menuPrincipal() {
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

    public int menuPiezaSeleccionada(String nombrePieza) {
        System.out.println("\n--- PIEZA SELECCIONADA: " + nombrePieza + " ---");
        System.out.println("1. Mover");
        System.out.println("2. Cancelar");
        System.out.print("Seleccione una opción: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // PEDIR COORDENADAS DESTINO

    public int[] solicitarCoordenadas() {

        try {

            System.out.print("Fila destino (0-7): ");
            int fila = Integer.parseInt(scanner.nextLine());

            System.out.print("Columna destino (0-7): ");
            int columna = Integer.parseInt(scanner.nextLine());

            if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
                System.out.println("Coordenadas fuera del tablero.");
                return null;
            }

            return new int[]{fila, columna};

        } catch (NumberFormatException e) {

            System.out.println("Entrada no válida.");
            return null;
        }
    }


    // PEDIR PIEZA A SELECCIONAR

    public int[] solicitarCoordenadasSeleccion() {

        try {

            System.out.print("Fila de la pieza (0-7): ");
            int fila = Integer.parseInt(scanner.nextLine());

            System.out.print("Columna de la pieza (0-7): ");
            int columna = Integer.parseInt(scanner.nextLine());

            return new int[]{fila, columna};

        } catch (NumberFormatException e) {

            System.out.println("Entrada no válida.");
            return null;
        }
    }


    // INICIAR JUEGO


    public void iniciar() {

        boolean salir = false;

        while (!salir) {

            int opcion = menuPrincipal();

            switch (opcion) {

                case 1: // Seleccionar pieza

                    int[] origen = solicitarCoordenadasSeleccion();

                    if (origen != null) {

                        Pieza pieza = controller.getTablero().getPieza(origen[0], origen[1]);

                        if (pieza != null && pieza.getColor() == controller.getTurnoActual()) {

                            controller.setPiezaSeleccionada(pieza);
                            controller.gestionarSeleccion();

                        } else {

                            System.out.println("No puedes seleccionar esa pieza.");
                        }
                    }

                    break;

                case 2:

                    controller.getTablero().reiniciarTablero();
                    System.out.println("Tablero reiniciado.");

                    break;

                case 3:

                    System.out.println("Cargar tablero no implementado.");

                    break;

                case 4:

                    System.out.println("Guardar tablero no implementado.");

                    break;

                case 0:

                    salir = true;
                    System.out.println("Saliendo del juego...");

                    break;

                default:

                    System.out.println("Opción no válida.");
            }
        }
    }
}



