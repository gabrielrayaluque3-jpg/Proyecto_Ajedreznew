package view;

import model.Tablero;
import utils.Utils;

public class  MenuPrincipal {

    public void mostrarEstadoPartida(Tablero tablero) {
        System.out.println("--------ESTADO DE LA PARTIDA--------");
        System.out.println("Turno actual: " + tablero.getTurnoActual().name().toUpperCase());
        if (tablero.esReyEnJaque(tablero.getTurnoActual()))
            System.out.println("El REY "+tablero.getTurnoActual().name().toUpperCase()+" ESTÁ EN JAQUE.");
        System.out.println(tablero.estadoTablero());
        System.out.println("\n" + tablero);
        System.out.println("----------------------------------------");
    }

    public static int menuPrincipal() {
        System.out.println("1. Seleccionar pieza");
        System.out.println("2. Reiniciar tablero");
        System.out.println("3. Cargar tablero");
        System.out.println("4. Guardar tablero");
        System.out.println("0. Salir");
        return Utils.pideEntero("Seleccione una opción: ");


    }

    public int menuPiezaSeleccionada() {
        System.out.println("\n1. Mover");
        System.out.println("2. Cancelar");
        return Utils.pideEntero("Seleccione una opción: ");
    }
}



