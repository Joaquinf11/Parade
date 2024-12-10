package ar.unlu.edu.mvc.modelo;


import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.io.Serializable;
import java.util.Queue;

public class RondaDescarte extends Ronda implements Serializable {
    private final Jugador primerJugadorRonda;
    private int contador;


    public RondaDescarte(Queue<Jugador> jugadores, Carnaval carnaval, Mazo mazo, Juego juego) {
        super(jugadores, carnaval, mazo, juego);
        this.primerJugadorRonda=jugadores.peek();
    }

    @Override
    public void tirarCarta(int cartaElegida) throws JuegoException {
        this.jugadorTurno.quitarCarta(cartaElegida);
        juego.notificar(Evento.CARTA_DESCARTADA);
        this.tiroCarta=true;
        this.finTurno();
    }

    @Override
    public void finTurno() throws JuegoException {
        if (tiroCarta) {
            if (esFinDeRonda()) {
                this.juego.finJuego();
            }
            else{
                this.jugadores.add(this.jugadorTurno);
                this.cambiarTurno();
            }
        }
        else {
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno", TipoException.CARTA_EXCEPTION);
        }
    }



    @Override
    public boolean esFinDeRonda(){
        boolean esPrimerJugador= this.primerJugadorRonda.equals(this.jugadores.peek());
        if (esPrimerJugador && contador != 2){
            contador++;
            return false;
        }
        else if (esPrimerJugador && contador == 2){
            return true;
        }
        else {
            return false;
        }
    }

}