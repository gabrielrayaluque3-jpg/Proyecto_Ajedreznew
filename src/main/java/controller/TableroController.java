package controller;

import model.Color;
import model.Pieza;
import model.Tablero;
import utils.Utils;
import view.MenuPrincipal;

import javax.swing.text.View;
import java.util.Scanner;

public class  TableroController {
    private Tablero tablero;
    private Color turnoActual;
    private Pieza piezaSeleccionada;
    private MenuPrincipal vista;

    public TableroController( MenuPrincipal vista ) {
        this.tablero = new Tablero();
        this.turnoActual = Color.BLANCO;
        this.piezaSeleccionada = null;
        this.vista = vista;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Color getTurnoActual() {
        return turnoActual;
    }

    public Pieza getPiezaSeleccionada() {
        return piezaSeleccionada;
    }

    public void setPiezaSeleccionada(Pieza piezaSeleccionada) {
        this.piezaSeleccionada = piezaSeleccionada;
    }

    public void setTurnoActual(Color turnoActual) {
        this.turnoActual = turnoActual;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    // GESTIONAR PIEZA SELECCIONADA


    public void gestionarSeleccion() {

        boolean finSubmenu = false;

        while (!finSubmenu) {

            int opcion = vista.menuPiezaSeleccionada(
                    piezaSeleccionada.getClass().getSimpleName()
            );

            switch (opcion) {

                case 1: // MOVER

                    int[] destino = vista.solicitarCoordenadas();

                    if (destino != null) {

                        try {

                            tablero.moverPieza(
                                    piezaSeleccionada.getFila(),
                                    piezaSeleccionada.getColumna(),
                                    destino[0],
                                    destino[1]
                            );

                            cambiarTurno();
                            piezaSeleccionada = null;
                            finSubmenu = true;

                        } catch (IllegalArgumentException e) {

                            System.out.println("Movimiento no válido: " + e.getMessage());
                        }
                    }

                    break;

                case 2: // CANCELAR

                    piezaSeleccionada = null;
                    finSubmenu = true;
                    break;

                default:

                    System.out.println("Opción no válida.");
            }
        }
    }


// CAMBIAR TURNO


    private void cambiarTurno() {

        if (turnoActual == Color.BLANCO) {
            turnoActual = Color.NEGRO;
        } else {
            turnoActual = Color.BLANCO;
        }
    }

}


    public void ejecutarMenu() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            MenuPrincipal.mostrarEstadoPartida(
                    tablero.toString(),
                    turnoActual.toString(),
                    0, 0,
                    "Ninguna",
                    false
            );

            int opcion = MenuPrincipal.menuPrincipal();

            switch (opcion) {
                case 1:
                    System.out.println("-> Seleccionando pieza...");
                    break;
                case 2:
                    System.out.println("-> Reiniciando tablero...");
                    this.tablero = new Tablero();
                    break;
                case 3:
                    System.out.println("-> Cargando tablero...");
                    break;
                case 4:
                    System.out.print("Introduce el nombre: ");
                    String nombre=sc.nextLine();
                    System.out.println("-> Guardando tablero...");
                    Utils.guardarTablero(this.tablero,nombre);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
