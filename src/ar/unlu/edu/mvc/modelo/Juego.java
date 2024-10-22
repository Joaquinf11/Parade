package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Juego {
    private List<Jugador> jugadores;
    private Carnaval carnaval;
    private Mazo mazo;

    public Juego (){
        this.jugadores= new ArrayList<>();
        this.carnaval= new Carnaval();
        this.mazo= new Mazo();
    }

    public void agregarJugador (Jugador jugador){
        this.jugadores.add(jugador);
    }


    //testear
    public void repartirCartas(){
        for (int i = 1 ; i <= 6 ; i++){
            this.carnaval.agregarCarta(this.mazo.sacarCarta());
        }

        for (int i = 1 ; i <= 5 ; i++ ){
                for (Jugador jugador : this.jugadores){
                    jugador.agarrarCarta(this.mazo.sacarCarta());
                }
        }
    }

    public void jugarTurno(Jugador jugador){
        Carta carta= jugador.elegirCarta(1); // aca tendria que poner el indice que elige el jugador ver como hacer

        if (!this.carnaval.analizarCarnaval(carta)){
            this.carnaval.agregarCarta(carta);
            jugador.agarrarCarta(this.mazo.sacarCarta());
        }
        else {
            boolean agrego= false;
            List<Carta> salvadas = this.carnaval.salvarCartas(carta.getValor());
            for (Carta cartaCarnaval : this.carnaval.getCartas()){
                if (Objects.equals(cartaCarnaval.getColor(),carta.getColor())){
                    jugador.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    this.carnaval.sacarCarta(cartaCarnaval);
                }
                else if (carta.getValor() <= cartaCarnaval.getValor()){
                    jugador.agregarCartaAlAreaDeJuego(carta);
                    this.carnaval.sacarCarta(cartaCarnaval);
                }
            }
            //vuelvo a agregar las salvadas al carnaval
            for (Carta salvada : salvadas){
                this.carnaval.agregarCarta(salvada);
            }
        }
    }
}

