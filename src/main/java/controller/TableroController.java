package controller;

import model.MovimientoInvalido;
import model.Tablero;
import utils.Utils;
import view.MenuPrincipal;

import java.awt.*;

import static utils.Utils.pideString;

public class  TableroController {
    private Tablero tablero;
    private final MenuPrincipal vista;

    public TableroController( MenuPrincipal vista ) {
        this.tablero = new Tablero();
        tablero.reiniciarTablero();
        this.vista = vista;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    // GESTIONAR PIEZA SELECCIONADA
    public void menuFuncional() {
        boolean finalizarMenu=false;
        while (!finalizarMenu) {
            vista.mostrarEstadoPartida(tablero);
            switch (MenuPrincipal.menuPrincipal()) {
                case 1:
                    gestionarSeleccion();
                    break;
                case 2:
                    String confirmacion = pideString("¿Estás seguro de que quieres reiniciar el tablero? (Si/No): ");
                    if (confirmacion.equalsIgnoreCase("Si")) {
                        tablero.reiniciarTablero();
                    }
                    break;
                case 3:
                    this.tablero=Utils.cargarTablero(tablero, pideString("Introduce la ruta del archivo: "));
                    break;
                case 4:
                    Utils.guardarTablero(tablero, pideString("Introduce la ruta del archivo: "));
                    break;
                case 0:
                    finalizarMenu = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void gestionarSeleccion() {

        boolean finSubmenu = false;

        while (!finSubmenu) {

            int opcion = vista.menuPiezaSeleccionada();

            switch (opcion) {

                case 1: // MOVER

                    boolean movimientoValido = false;
                    while (!movimientoValido) {
                        try {
                            String origen = pideString("\nIntroduce casilla origen: ");
                            String destino = pideString("\nIntroduce casilla destino: ");

                            tablero.moverPieza(origen, destino);

                            System.out.println("Movimiento realizado con éxito");
                            movimientoValido = true;
                            finSubmenu = true;
                        } catch (IllegalArgumentException | MovimientoInvalido e) {
                            System.out.println("\n" + e.getMessage());
                            System.out.println("Por favor, introduce un movimiento válido.\n");
                        }
                        System.out.println(tablero);
                    }
                    break;

                case 2: // CANCELAR
                    finSubmenu = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}
