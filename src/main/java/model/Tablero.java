package model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

import static utils.Utils.*;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement(name="tablero")
public class  Tablero implements Serializable {
    @XmlElementWrapper(name = "piezasBlancas")
    @XmlElements({
            @XmlElement(name = "torre", type = Torre.class),
            @XmlElement(name = "peon", type = Peon.class),
            @XmlElement(name = "alfil", type = Alfil.class),
            @XmlElement(name = "caballo", type = Caballo.class),
            @XmlElement(name = "reina", type = Reina.class),
            @XmlElement(name = "rey", type = Rey.class)
    })
    ArrayList<Pieza> piezasBlancas = new ArrayList<>();
    @XmlElementWrapper(name = "piezasNegras")
    @XmlElements({
            @XmlElement(name = "torre", type = Torre.class),
            @XmlElement(name = "peon", type = Peon.class),
            @XmlElement(name = "alfil", type = Alfil.class),
            @XmlElement(name = "caballo", type = Caballo.class),
            @XmlElement(name = "reina", type = Reina.class),
            @XmlElement(name = "rey", type = Rey.class)
    })
    ArrayList<Pieza> piezasNegras = new ArrayList<>();
    @XmlElementWrapper(name = "piezasEliminadas")
    @XmlElements({
            @XmlElement(name = "torre", type = Torre.class),
            @XmlElement(name = "peon", type = Peon.class),
            @XmlElement(name = "alfil", type = Alfil.class),
            @XmlElement(name = "caballo", type = Caballo.class),
            @XmlElement(name = "reina", type = Reina.class),
            @XmlElement(name = "rey", type = Rey.class)
    })
    ArrayList<Pieza> piezasEliminadas = new ArrayList<>();

    public int puntuacionBlanca = 139;
    public int puntuacionNegra = 139;

    public static final int[] fila = new int[8];
    public static final int[] columna = new int[8];

    private Color turnoActual = Color.BLANCO;

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

    public Color getTurnoActual() {
        return turnoActual;
    }

    private int letraAColumna(char letra) {
        return Character.toUpperCase(letra) - 'A' + 1;
    }

    public String estadoTablero() {
        return "Piezas blancas: " + piezasBlancas + "\nPiezas negras: " + piezasNegras + "\nPiezas eliminadas: " + piezasEliminadas + "\nPuntuación blanca: " + puntuacionBlanca + "\nPuntuación negra: " + puntuacionNegra;
    }

    public void vaciarTablero() {
        piezasBlancas.clear();
        piezasNegras.clear();
        piezasEliminadas.clear();
    }

    public void reiniciarTablero() {
        //las columnas funcionan a la inversa
        vaciarTablero();
        addPieza("Rey", 1, 5, Color.BLANCO);
        addPieza("Rey", 8, 5, Color.NEGRO);
        addPieza("Reina", 1, 4, Color.BLANCO);
        addPieza("Reina", 8, 4, Color.NEGRO);
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
        this.turnoActual = Color.BLANCO;
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
        Pieza pieza = switch (nombre) {
            case "Reina" -> new Reina(fila, columna, color);
            case "Caballo" -> new Caballo(fila, columna, color);
            case "Alfil" -> new Alfil(fila, columna, color);
            case "Torre" -> new Torre(fila, columna, color);
            case "Peon" -> new Peon(fila, columna, color);
            case "Rey" -> new Rey(fila, columna, color);
            default -> throw new IllegalArgumentException("Nombre de pieza no válido: " + nombre);
        };
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

    public boolean hayPiezasEntre(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        int filaIncremento = calcularDireccion(filaOrigen, filaDestino);
        int colIncremento = calcularDireccion(colOrigen, colDestino);
        int filaActual = filaOrigen + filaIncremento;
        int colActual = colOrigen + colIncremento;

        while (filaActual != filaDestino || colActual != colDestino) {
            if (getPieza(filaActual, colActual) != null) {
                return true;
            }
            if (filaActual != filaDestino) filaActual += filaIncremento;
            if (colActual != colDestino) colActual += colIncremento;
        }
        return false;
    }

    public void moverPieza(String origen, String destino) throws MovimientoInvalido {
        int filaOrigen = Character.getNumericValue(origen.charAt(0));
        int colOrigen = letraAColumna(origen.charAt(1));
        int filaDestino = Character.getNumericValue(destino.charAt(0));
        int colDestino = letraAColumna(destino.charAt(1));

        Pieza pDestino = getPieza(filaDestino, colDestino);
        Pieza p = getPieza(filaOrigen, colOrigen);

        if (p == null)
            throw new IllegalArgumentException("No hay pieza en origen.\n");
        if (p.getColor() != this.turnoActual)
            throw new IllegalArgumentException("No es tu turno. Turno de: " + this.turnoActual+"\n");
        if (pDestino instanceof Rey)
            throw new IllegalArgumentException("No se puede capturar al Rey.\n");
        if (pDestino != null && pDestino.getColor() == p.getColor())
            throw new IllegalArgumentException("No puedes capturar tu propia pieza.\n");
        if (origen.equals(destino))
            throw new IllegalArgumentException("La casilla de destino debe ser diferente a la de origen.");
        if (!(p instanceof PiezaSaltadora) && hayPiezasEntre(filaOrigen, colOrigen, filaDestino, colDestino))
            throw new IllegalArgumentException("Hay piezas en medio.\n");

        //Esto para comprobar el jaque, poder volver la pieza a su casilla original
        int filaOriginal = p.getFila();
        int colOriginal = p.getColumna();
        Color colorJugador = p.getColor();

        //Esto es un poco lio, por eso comento todo
        try {
            //Posicion temporal para simular la pieza
            p.movimiento(filaDestino, colDestino, this);
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
                throw new IllegalArgumentException("Tu rey esta en jaque. Debes defenderlo.\n");
            }

            // Se devuelve la pieza simulada eliminada
            if (pDestino != null) {
                if (pDestino.getColor() == Color.BLANCO) piezasBlancas.add(pDestino);
                else piezasNegras.add(pDestino);
                capturarPieza(pDestino);
            }
        } catch (MovimientoInvalido e) {
            throw new MovimientoInvalido("Movimiento no permitido: " + e.getMessage() + "\n");
        }

        cambiarTurno();
        this.puntuacionBlanca = getPuntuacionBlanca();
        this.puntuacionNegra = getPuntuacionNegra();
    }

    public void capturarPieza(Pieza capturada) {
        if (capturada.getColor() == Color.BLANCO) {
            piezasBlancas.remove(capturada);
        } else {
            piezasNegras.remove(capturada);
        }
        piezasEliminadas.add(capturada);
    }

    private void cambiarTurno() {
        if (this.turnoActual == Color.BLANCO) {
            this.turnoActual = Color.NEGRO;
        } else {
            this.turnoActual = Color.BLANCO;
        }
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
                            return true; // Hay jaque
                        }
                    }
                }
            }
        }
        return false; // No hay jaque
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
    }
}
