package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Stack;

public class Mazo implements Serializable {
    private final Stack<Carta> cartas;

    public Mazo() {
        this.cartas = new Stack<>();
        for (Color color : Color.values()) {
            for (int i = 0; i <= 10; i++) {
                Carta carta = new Carta(i, color);
                this.cartas.push(carta);
            }
        }
        Collections.shuffle(this.cartas);
    }

    public Carta sacarCarta() {
        if (!this.cartas.isEmpty()) {
            return this.cartas.pop();
        }
        return null;
    }

    public Stack<Carta> getCartas() {
        Stack<Carta> copia = new Stack<Carta>();
        copia.addAll(this.cartas);
        return copia;
    }

    public boolean tieneCartas() {
        return !this.cartas.isEmpty();
    }

    public int getCantidadCartas() {
        return this.cartas.size();
    }
}
