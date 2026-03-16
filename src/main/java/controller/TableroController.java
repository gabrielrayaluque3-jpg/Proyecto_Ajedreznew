package controller;

import model.Color;
import model.Pieza;
import model.Tablero;
import view.MenuPrincipal;

import javax.swing.text.View;
import java.util.Scanner;

public class  TableroController {
    private Tablero tablero;
    private Color turnoActual;
    private Pieza piezaSeleccionada;

    public TableroController() {
        this.tablero = new Tablero();
        this.turnoActual = Color.BLANCO;
        this.piezaSeleccionada = null;
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

    public void ejecutarMenu() {
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
                    System.out.println("-> Guardando tablero...");
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