package ar.unlu.edu.mvc.modelo;


import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.util.List;

public class RondaDescarte extends Ronda {
    private static int contador=0;
    private final int cantidadJugadores;


    public RondaDescarte(Jugador jugador, Carnaval carnaval, Mazo mazo,int cantidadJugadores, Juego juego) {
        super(jugador, carnaval, mazo, juego);
        contador++;
        this.cantidadJugadores=cantidadJugadores;
    }

    @Override
    public void tirarCarta(int cartaElegida) throws CartaException {
        this.jugadorTurno.quitarCarta(cartaElegida);
        juego.notificar(Evento.CARTA_DESCARTADA);
        this.tiroCarta=true;
        this.finRonda();
    }

    @Override
    public void finRonda() throws CartaException{
        if (tiroCarta) {
            if (esFinDeRonda()) {
                agregarCartasEnManoAlArea();
                this.juego.finJuego();
            }
            this.juego.finTurno();
        }
        else {
            throw new CartaException("Debes tirar una carta antes de finalizar tu turno", TipoException.TIRAR_CARTA);
        }
    }

    private void agregarCartasEnManoAlArea() {
        List<Carta> cartas = this.jugadorTurno.getCartas();
        for (Carta carta : cartas ){
            this.jugadorTurno.agregarCartaAlAreaDeJuego(carta);
        }
        this.juego.notificar(Evento.CARTA_AGREGADA_AREA);
    }

    @Override
    public boolean esFinDeRonda(){
        return  contador == cantidadJugadores;
    }

}