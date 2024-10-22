package ar.unlu.edu.mvc.modelo;


import java.util.Collections;
import java.util.Stack;

public class Mazo {
    private Stack<Carta> cartas;

    public Mazo (){
        super();
        for (Color color : Color.values()){
            for (int i = 0; i <= 10 ; i++ ){
                Carta carta= new Carta(i,color);
                this.cartas.push(carta);
            }
        }
        Collections.shuffle(this.cartas);
        // chequear los null pointer, capaz podes meter un Exceptions
    }


    public Carta sacarCarta(){
        Carta carta= this.cartas.peek();
        this.cartas.pop();
        return carta;
        // Exception para pila vacia?
    }
}
