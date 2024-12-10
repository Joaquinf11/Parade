package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.io.Serializable;
import java.util.Queue;

public class UltimaRonda extends Ronda implements Serializable {
   private Jugador primerJugadorRonda;

    public UltimaRonda(Queue<Jugador> jugadores, Carnaval carnaval, Mazo mazo, Juego juego){
        super(jugadores,carnaval,mazo,juego);
        this.primerJugadorRonda=this.jugadores.peek();
    }

    @Override
    public void finTurno() throws JuegoException {
        if (tiroCarta) {
            if (esFinDeRonda()) {
                this.juego.setRondaDescarte();
            }
            this.cambiarTurno();
        }
        else{
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno", TipoException.CARTA_EXCEPTION);
        }

    }
    @Override
    public boolean esFinDeRonda(){
        return  contador == cantidadJugadores;
    }
}
