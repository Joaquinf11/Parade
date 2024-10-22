package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> cartasEnMano;
    private AreaDeJuego area;
    private int puntos;

    public Jugador(String nombre){
        this.nombre=nombre;
        this.cartasEnMano= new ArrayList<>();
        this.area= new AreaDeJuego();
        this.puntos=0;
    }

    public AreaDeJuego getArea(){
        return this.area;
    }

    public void agarrarCarta(Carta carta){
        this.cartasEnMano.add(carta);
    }

    public void quitarCarta(Carta carta){
        this.cartasEnMano.remove(carta);
    }

    public Carta elegirCarta(int indice){
        Carta carta= this.cartasEnMano.get(indice);
        quitarCarta(carta);
        return carta;
    }

    public void agregarCartaAlAreaDeJuego(Carta carta){
        this.area.agregarCarta(carta);
    }

    public void sumarPuntos(int sumar){
        this.puntos -= sumar;
    }
}
