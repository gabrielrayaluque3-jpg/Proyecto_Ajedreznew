package controller;

import model.Color;
import model.Pieza;
import model.Tablero;
import view.MenuPrincipal;

public class TableroController {
    private Tablero tablero;
    private Color turnoActual;
    private Pieza piezaSeleccionada;
    private MenuPrincipal vista;

    public TableroController(MenuPrincipal vista) {
        this.tablero = new Tablero();
        this.tablero.reiniciarTablero();
        this.turnoActual = Color.BLANCO;
        this.piezaSeleccionada = null;
        this.vista = vista;
    }

    public void gestionarSeleccion() {
        if (piezaSeleccionada == null) return;

        boolean finSubmenu = false;

        while (!finSubmenu) {
            // 1. LLAMADA CORREGIDA: Usamos el nombre que tienes en MenuPrincipal
            int opcion = vista.menuPiezaSeleccionada(
                    piezaSeleccionada.getClass().getSimpleName()
            );

            switch (opcion) {
                case 1: // MOVER
                    // 2. LLAMADA CORREGIDA: Usamos 'solicitarCoordenadasSeleccion'
                    // porque es el que existe en tu vista
                    int[] destino = vista.solicitarCoordenadasSeleccion();

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
                            System.out.println("Movimiento realizado con éxito.");

                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 2: // CANCELAR
                    piezaSeleccionada = null;
                    finSubmenu = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void cambiarTurno() {
        this.turnoActual = (turnoActual == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
    }

    // Getters y Setters
    public Tablero getTablero() { return tablero; }
    public Color getTurnoActual() { return turnoActual; }
    public void setPiezaSeleccionada(Pieza piezaSeleccionada) { this.piezaSeleccionada = piezaSeleccionada; }
}
