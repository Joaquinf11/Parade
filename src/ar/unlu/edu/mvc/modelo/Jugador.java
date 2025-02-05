package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.interfaces.IJugador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Jugador implements IJugador, Serializable {
    private final String nombre;
    private  List<Carta> cartasEnMano;
    private  AreaDeJuego area;
    private int puntos;
    private int victorias;

    public Jugador(String nombre){
        this.nombre=nombre;
        this.cartasEnMano= new ArrayList<>();
        this.area= new AreaDeJuego();
        this.puntos=0;
        this.victorias=0;
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

    public void resetNuevoJuego(){
        this.puntos=0;
        this.area= new AreaDeJuego();
        this.cartasEnMano= new ArrayList<>();

    }

    public void sumarVictoria(){
        this.victorias++;
    }

    public int getVictorias(){
        return this.victorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return nombre.equals(jugador.nombre);
    }

    public List<String> listarCartasDadasVuelta(){
        return this.area.listarCartasDadasVuelta();
    }
}
