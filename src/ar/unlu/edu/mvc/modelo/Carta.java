package ar.unlu.edu.mvc.modelo;

public class Carta {
    private int valor;
    private Color color;

    public Carta(int valor, Color color) {
        this.valor = valor;
        this.color = color;
    }

    public int getValor() {
        return this.valor;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean equalsColor(Carta carta){
        return carta.getColor() == this.color;
    }

}
