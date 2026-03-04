package Modelo;

public enum Color {
    BLANCO,
    NEGRO;

    public Color contrario() {
        return this == BLANCO ? NEGRO : BLANCO;
    }
}
