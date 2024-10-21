package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

public  abstract class Mesa {
    private List<Carta> cartas;
    private int cantidadDeCartas;

    public Mesa(){
        this.cartas= new ArrayList<>();
        this.cantidadDeCartas=0;
    }

    public  void agregarCarta(Carta carta){
        this.cartas.add(carta);
        this.cantidadDeCartas++;
    }
    public void sacarCarta(Carta carta){
        this.cartas.remove(carta);
        this.cantidadDeCartas--;
    }
}
