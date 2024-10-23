package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Jugador implements IJugador{
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

    @Override
    public String getNombre(){
        return this.nombre;
    }

    public AreaDeJuego getArea(){
        return this.area;
    }

    public int getPuntos(){
        return this.puntos;
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

    public void sumarPuntos(){
        this.puntos -= this.area.sumarValorDeCartas();
    }


    public List<Carta> getCartas() {
        return this.cartasEnMano;
    }

    //////////////////////////////////
    //FUNCIONES PARA TEST
    //////////////////////////////////

    public void mostrarCartasEnMano(){
        for (Carta carta : this.cartasEnMano){
            System.out.print("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
        }
        System.out.println();
    }
}
