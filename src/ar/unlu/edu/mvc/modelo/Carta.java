package ar.unlu.edu.mvc.modelo;

public class Carta {
    private int valor;
    private Colores color;

    public Carta(int valor, Colores color) {
        this.valor = valor;
        this.color = color;
    }

    public int getValor() {
        return this.valor;
    }

    public Colores getColor() {
        return this.color;
    }
}
