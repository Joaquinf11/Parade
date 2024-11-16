package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.interfaces.IJugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Jugador implements IJugador {
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

    @Override
    public boolean equalsNombre(IJugador jugador){
        return this.getNombre().equals(jugador.getNombre());

    }

    //////////////////////////////////
    //FUNCIONES PARA TEST
    //////////////////////////////////

    public void mostrarCartasEnMano(){
        System.out.println(" CARTAS EN MANO DE " + this.getNombre());
        for (Carta carta : this.cartasEnMano){
            System.out.print("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
        }
        System.out.println();
    }

    public void mostrarAreaDeJuego() {

        System.out.println("AREA DE JUEGO");
        Collection<List<Carta>> cartas=this.area.getTodasLasCartas();
        if(cartas.isEmpty()){
            System.out.println("VACIA");
        }
        else {
            for (List<Carta> cartasArea : cartas) {
                for (Carta carta : cartasArea) {
                    System.out.print("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
                }
            }
        }
        System.out.println();

    }

    public void mostrarPuntos() {
        System.out.println(this.puntos);
    }

    public Collection<List<Carta>> getCartasArea() {
        return this.area.getTodasLasCartas();
    }
}
