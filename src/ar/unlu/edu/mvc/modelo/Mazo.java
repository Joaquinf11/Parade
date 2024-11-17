package ar.unlu.edu.mvc.modelo;



import java.util.Collections;
import java.util.Stack;

public class Mazo {
    private Stack<Carta> cartas;

    public Mazo (){
        this.cartas= new Stack<>();
        for (Color color : Color.values()){
            for (int i = 0; i <= 10 ; i++ ){
                Carta carta= new Carta(i,color);
                this.cartas.push(carta);
            }
        }
        Collections.shuffle(this.cartas);
    }


    public Carta sacarCarta(){
        if (!this.cartas.isEmpty()) {
            return this.cartas.pop();
        }
        return null;
        // Exception para pila vacia?
    }

    public Stack<Carta> getCartas() {
        return this.cartas;
    }

    public boolean tieneCartas(){
        return !this.cartas.isEmpty();
    }

    public int getCantidadCartas() {
        return this.cartas.size();
    }
}
