package model;

import utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;

import static utils.Utils.*;

public class Tablero implements Serializable {
    ArrayList<Pieza> piezasBlancas = new ArrayList<>();
    ArrayList<Pieza> piezasNegras = new ArrayList<>();
    ArrayList<Pieza> piezasEliminadas = new ArrayList<>();

    public int puntuacionBlanca = 139;
    public int puntuacionNegra = 139;

    public static final int[] fila = new int[8];
    public static final int[] columna = new int[8];

    public int getPuntuacionBlanca() {
        int sumaVivas = 0;
        for (Pieza p : piezasBlancas) {
            sumaVivas += p.getPuntos();
        }
        return this.puntuacionBlanca = sumaVivas;
    }

    public int getPuntuacionNegra() {
        int sumaVivas = 0;
        for (Pieza p : piezasNegras) {
            sumaVivas += p.getPuntos();
        }
        return this.puntuacionNegra = sumaVivas;
    }

    private int letraAColumna(char letra) {
        return Character.toUpperCase(letra) - 'A' + 1;
    }

    private char columnaALetra(int col) {
        return (char) ('a' + col - 1);
    }

    public String estadoTablero() {
        return "Piezas blancas: " + piezasBlancas + "\nPiezas negras: " + piezasNegras + "\nPiezas eliminadas: " + piezasEliminadas + "\nPuntuación blanca: " + puntuacionBlanca + "\nPuntuación negra: " + puntuacionNegra;
    }

    public void vaciarTablero() {
        piezasBlancas.clear();
        piezasNegras.clear();
        piezasEliminadas.clear();
    }

    public Tablero crearCopiaTablero() {
        Tablero copia = new Tablero();
        for (Pieza p : piezasBlancas){
            copia.addPieza(p.copiar()); // Usar .copiar()
        }
        for (Pieza p : piezasNegras){
            copia.addPieza(p.copiar());
        }
        return copia;
    }

    public void reiniciarTablero() {
        vaciarTablero();
        addPieza("Rey", 1, 5, Color.BLANCO);
        addPieza("Rey", 8, 4, Color.NEGRO);
        addPieza("Reina", 1, 4, Color.BLANCO);
        addPieza("Reina", 8, 5, Color.NEGRO);
        addPieza("Torre", 1, 1, Color.BLANCO);
        addPieza("Torre", 1, 8, Color.BLANCO);
        addPieza("Torre", 8, 1, Color.NEGRO);
        addPieza("Torre", 8, 8, Color.NEGRO);
        addPieza("Alfil", 1, 3, Color.BLANCO);
        addPieza("Alfil", 1, 6, Color.BLANCO);
        addPieza("Alfil", 8, 3, Color.NEGRO);
        addPieza("Alfil", 8, 6, Color.NEGRO);
        addPieza("Caballo", 1, 2, Color.BLANCO);
        addPieza("Caballo", 1, 7, Color.BLANCO);
        addPieza("Caballo", 8, 2, Color.NEGRO);
        addPieza("Caballo", 8, 7, Color.NEGRO);
        for (int i = 1; i < 9; i++) {
            addPieza("Peon", 2, i, Color.BLANCO);
            addPieza("Peon", 7, i, Color.NEGRO);
        }
    }

    public Pieza addPieza(Pieza pieza) {
        if (pieza.getColor() == Color.BLANCO) {
            piezasBlancas.add(pieza);
        } else {
            piezasNegras.add(pieza);
        }
        return pieza;
    }

    public Pieza addPieza(String nombre, int fila, int columna, Color color) {
        Pieza pieza = null;
        switch (nombre) {
            case "Reina": pieza = new Reina(fila, columna, color); break;
            case "Caballo": pieza = new Caballo(fila, columna, color); break;
            case "Alfil": pieza = new Alfil(fila, columna, color); break;
            case "Torre": pieza = new Torre(fila, columna, color); break;
            case "Peon": pieza = new Peon(fila, columna, color); break;
            case "Rey": pieza = new Rey(fila, columna, color); break;
            default: throw new IllegalArgumentException("Nombre de pieza no válido: " + nombre);
        }
        return addPieza(pieza);
    }

    public Pieza getPieza(int fila, int columna) {
        for (Pieza pieza : piezasBlancas) {
            if (pieza.getFila() == fila && pieza.getColumna() == columna) {
                return pieza;
            }
        }
        for (Pieza pieza : piezasNegras) {
            if (pieza.getFila() == fila && pieza.getColumna() == columna) {
                return pieza;
            }
        }
        return null;
    }

    // NUEVO MÉTODO PARA EL CONTROLADOR (Recibe INT)
    public void moverPieza(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        Pieza p = getPieza(filaOrigen, colOrigen);
        Pieza pDestino = getPieza(filaDestino, colDestino);

        if (p == null) {
            throw new IllegalArgumentException("No hay ninguna pieza en la posición seleccionada.");
        while (filaActual != filaDestino || colActual != colDestino) {
            if (getPieza(filaActual, colActual) != null) {
                return true;
            }
            if (filaActual != filaDestino) filaActual += filaIncremento;
            if (colActual != colDestino) colActual += colIncremento;
        }

    public void moverPieza(String origen, String destino) throws MovimientoInvalido{
        int filaOrigen = Character.getNumericValue(origen.charAt(0));
        int colOrigen = letraAColumna(origen.charAt(1));
        // Validación de movimiento
        if (!p.sePuedeMover(pDestino)) {
            throw new IllegalArgumentException("Esa pieza no puede moverse así.");
        }

        // Gestión de capturas
        if (pDestino != null) {
            if (pDestino.getColor() == p.getColor()) {
                throw new IllegalArgumentException("No puedes capturar tus propias piezas.");
            }
            if (pDestino instanceof Rey) {
                throw new IllegalArgumentException("No se puede capturar al Rey.");
            }
            capturarPieza(pDestino);
        int filaDestino = Character.getNumericValue(destino.charAt(0));
        int colDestino = letraAColumna(destino.charAt(1));

        Pieza pDestino = getPieza(filaDestino, colDestino);
        Pieza p = getPieza(filaOrigen, colOrigen);
        if (p == null) throw new IllegalArgumentException("No hay pieza en origen.\n");
        if (pDestino instanceof Rey) throw new IllegalArgumentException("No se puede capturar al Rey.\n");
        if (pDestino != null && pDestino.getColor() == p.getColor())
            throw new IllegalArgumentException("No puedes capturar tu propia pieza.\n");
        if (origen.equals(destino)) {
            throw new IllegalArgumentException("La casilla de destino debe ser diferente a la de origen.");
        }if (!(p instanceof PiezaSaltadora) && hayPiezasEntre(filaOrigen, colOrigen, filaDestino, colDestino)) {
            throw new IllegalArgumentException("Hay piezas en medio.\n");
        }

        //Esto para comprobar el jaque, poder volver la pieza a su casilla original
        int filaOriginal = p.getFila();
        int colOriginal = p.getColumna();
        Color colorJugador = p.getColor();

        //Esto es un poco lio, por eso comento todo
        try {
            //Posicion temporal para simular la pieza
            p.setFila(filaDestino);
            p.setColumna(colDestino);

            //se quita temporalmente la pieza de su lista para simular jaque
            if (pDestino != null) {
                if (pDestino.getColor() == Color.BLANCO) piezasBlancas.remove(pDestino);
                else piezasNegras.remove(pDestino);
            }

            //Esto comprueba que el movimiento no deje a nuestro propio rey en jaque
            if (esReyEnJaque(colorJugador)) {
                // Una vez comprobado, se deshacen los cambios
                p.setFila(filaOriginal);
                p.setColumna(colOriginal);
                if (pDestino != null) {
                    if (pDestino.getColor() == Color.BLANCO) piezasBlancas.add(pDestino);
                    else piezasNegras.add(pDestino);
                }
                throw new IllegalArgumentException("Movimiento ilegal: Tu rey queda en jaque.\n");
            }

            //Si no hay jaque de ningun rey, se vuelve al movimiento normal
            p.setFila(filaOriginal);
            p.setColumna(colOriginal);

            // Si p.movimiento lanza excepción, saltará al catch
            p.movimiento(filaDestino, colDestino, this);

            // Se devuelve la pieza simulada eliminada
            if (pDestino != null) {
                if (pDestino.getColor() == Color.BLANCO) piezasBlancas.add(pDestino);
                else piezasNegras.add(pDestino);
                capturarPieza(pDestino);
            }
        } catch (MovimientoInvalido e) {
            throw new IllegalArgumentException("Movimiento no permitido: " + e.getMessage() + "\n");
        }

        this.puntuacionBlanca = getPuntuacionBlanca();
        this.puntuacionNegra = getPuntuacionNegra();
        // Realizar el movimiento físico
        p.movimiento(filaDestino, colDestino, this);

        // Actualizar puntuaciones
        getPuntuacionBlanca();
        getPuntuacionNegra();
    }

    public void capturarPieza(Pieza capturada) {
        if (capturada.getColor() == Color.BLANCO) {
            piezasBlancas.remove(capturada);
        } else {
            piezasNegras.remove(capturada);
        }
        piezasEliminadas.add(capturada);
    }
    public boolean esReyEnJaque(Color colorDelRey) {
        Pieza elRey = null;
        ArrayList<Pieza> aliadas = (colorDelRey == Color.BLANCO) ? piezasBlancas : piezasNegras;
        ArrayList<Pieza> enemigas = (colorDelRey == Color.BLANCO) ? piezasNegras : piezasBlancas;

        for (Pieza p : aliadas) {
            if (p instanceof Rey) {
                elRey = p;
            }
        }
        if (elRey != null) {
            for (Pieza enemiga : enemigas) {
                if (enemiga.puedeAtacar(elRey)) {
                    if (validarTrayectoria(enemiga, elRey.getFila(), elRey.getColumna())) {
                        if (enemiga instanceof PiezaSaltadora || !hayPiezasEntre(enemiga.getFila(), enemiga.getColumna(), elRey.getFila(), elRey.getColumna())) {
                            return true; // ¡JAQUE DETECTADO!
                        }
                    }
                }
            }
        }


    public boolean jaque() {
        Rey reyNegro = null;
        Rey reyBlanco = null;

        for (Pieza p : piezasBlancas) { if (p instanceof Rey) reyBlanco = (Rey) p; }
        for (Pieza p : piezasNegras) { if (p instanceof Rey) reyNegro = (Rey) p; }

        if (reyBlanco != null) {
            for (Pieza p : piezasNegras) {
                if (p.puedeAtacar(reyBlanco)) return true;
            }
        }
        if (reyNegro != null) {
            for (Pieza p : piezasBlancas) {
                if (p.puedeAtacar(reyNegro)) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String casillaBlanca = "░";
        String casillaNegra = "▓";
        String resultado = "";

        for (int i = 0; i < 8; i++) {
            fila[i] = i + 1;
            columna[i] = i + 1;
        }

        for (int f = 7; f >= 0; f--) {
            resultado = resultado + fila[f] + " ";
            for (int c = 0; c < 8; c++) {
                Pieza p = getPieza(fila[f], columna[c]);
                if (p != null) {
                    resultado = resultado + p;
                } else {
                    if ((fila[f] + columna[c]) % 2 == 0) {
                        resultado = resultado + casillaNegra;
                    } else {
                        resultado = resultado + casillaBlanca;
                    }
                }
            }
            resultado = resultado + "\n";
        }
        resultado = resultado + "  a b c d e f g h\n";
        return resultado;
        return "Piezas blancas: " + piezasBlancas.size() + " | Piezas negras: " + piezasNegras.size();
    }
}