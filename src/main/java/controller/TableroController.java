package controller;

import model.Color;
import model.Pieza;
import model.Tablero;

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
}