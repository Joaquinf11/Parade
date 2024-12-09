package ar.unlu.edu.mvc.modelo;


import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.io.Serializable;

public class RondaDescarte extends Ronda implements Serializable {
    private static int contador=0;
    private final int cantidadJugadores;


    public RondaDescarte(Jugador jugador, Carnaval carnaval, Mazo mazo,int cantidadJugadores, Juego juego) {
        super(jugador, carnaval, mazo, juego);
        contador++;
        this.cantidadJugadores=cantidadJugadores;
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
                this.juego.finTurno();
            }
        }
        else {
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno", TipoException.CARTA_EXCEPTION);
        }
    }



    @Override
    public boolean esFinDeRonda(){
        return  contador == (cantidadJugadores * 2);
    }

}