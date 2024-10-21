package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

public  abstract class Mesa {
    private List<Carta> cartas;

    public Mesa(){
        this.cartas= new ArrayList<>();
    }

    public  void agregarCarta(Carta carta){
        this.cartas.add(carta);
    }
    public void sacarCarta(Carta carta){
        this.cartas.remove(carta);
    }
}
