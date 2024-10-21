package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

public class AreaDeJuego  extends  Mesa{
    private int cantidadDeCartas;

    public AreaDeJuego(){
        super();
        this.cantidadDeCartas=0;
    }


    @Override
    public void agregarCarta(Carta carta){
        super.agregarCarta(carta);
        this.cantidadDeCartas++;
    }

}
