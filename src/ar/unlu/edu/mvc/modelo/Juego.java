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

    public void evaluarAreaDeJuego(){
    // no contempla el caso de que dos jugadores tengan la misma cantidad de cartas y los dos tengan que eliminar sus cartas del area de juego
        Jugador jugador_anterior= this.jugadores.getFirst();
        for (Color color : Color.values()){
            for (Jugador jugador : this.jugadores){
                if (jugador.getArea().getCantidadDeCartasPorColor(color) > jugador_anterior.getArea().getCantidadDeCartasPorColor(color)){
                    jugador_anterior= jugador;
                }
            }

            jugador_anterior.getArea().ponerCartasBocaAbajo(color);

        }
    }

    public void calcularPuntos(){
        for (Jugador jugador : this.jugadores){
            jugador.sumarPuntos();
        }
    }

    public Jugador definirGanador(){
        Jugador jugador_anterior= this.jugadores.getFirst();
        for (Jugador jugador : this.jugadores){
            if (jugador.getPuntos() < jugador_anterior.getPuntos()){
                jugador_anterior = jugador;
            }
        }
        return jugador_anterior;
        // falta considerar el caso en que empaten en puntos, si pasa eso gana el que tiene menos cartas en el area de juego (BOCA ARRIBA Y BOCA ABAJO)

    }

    


}

