package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.interfaces.IJugador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Jugador implements IJugador, Serializable {
    private final String nombre;
    private final List<Carta> cartasEnMano;
    private final AreaDeJuego area;
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

    public int getCantidadCartasEnArea(){
        return this.area.getCantidadDeCartasTotales();
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


    public Carta descartarCarta(int indice){
        Carta resultado=this.cartasEnMano.get(indice);
        this.cartasEnMano.remove(indice);
        return resultado;
    }

    public void quitarCarta(int indice){
            this.cartasEnMano.remove(indice);

    }
    public void quitarCarta(Carta carta){
        this.cartasEnMano.remove(carta);

    }

    public Carta elegirCarta(int indice){
        return descartarCarta(indice);
    }

    public void agregarCartaAlAreaDeJuego(Carta carta){
        this.area.agregarCarta(carta);
    }

    public void sumarPuntos(){
       this.puntos=this.area.calcularPuntos();

    }


    public List<Carta> getCartas() {
        return this.cartasEnMano;
    }


    public Collection<List<Carta>> getCartasArea() {
        return this.area.getTodasLasCartas();
    }

    public List<Carta> sacarCartasEnMano() {
        List<Carta> cartas= new ArrayList<>(cartasEnMano);
        this.cartasEnMano.clear();
        return cartas;
    }
}
