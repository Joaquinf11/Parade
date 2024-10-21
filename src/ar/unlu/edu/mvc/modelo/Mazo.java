package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo extends Mesa{
    private List<Carta> cartas;

    public Mazo (){
        super();
        for (Colores color : Colores.values()){
            for (int i = 0; i <= 10 ; i++ ){
                Carta carta= new Carta(i,color);
                this.agregarCarta(carta);
            }
        }
    }

    public void mezclar(){
        Collections.shuffle(this.cartas);
    }
}
