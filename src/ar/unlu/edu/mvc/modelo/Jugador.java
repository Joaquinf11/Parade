package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> cartasEnMano;
    private AreaDeJuego area;

    public Jugador(String nombre){
        this.nombre=nombre;
        this.cartasEnMano= new ArrayList<>();
        this.area= new AreaDeJuego();
    }

    public void agregarCartaEnMano(Carta carta){
        this.cartasEnMano.add(carta);
    }

    public void quitarCartaEnMano(Carta carta){
        this.cartasEnMano.remove(carta);
    }
}
