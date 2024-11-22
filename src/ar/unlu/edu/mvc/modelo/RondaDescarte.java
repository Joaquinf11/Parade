package ar.unlu.edu.mvc.modelo;


import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.exceptions.TipoException;

public class RondaDescarte extends Ronda {
    private static int contador=0;
    private final int cantidadJugadores;


    public RondaDescarte(Jugador jugador, Carnaval carnaval, Mazo mazo,int cantidadJugadores, Juego juego) {
        super(jugador, carnaval, mazo, juego);
        contador++;
        this.cantidadJugadores=cantidadJugadores;
    }

    @Override
    public void tirarCarta(int cartaElegida) {
        this.jugadorTurno.getArea().agregarCarta(this.jugadorTurno.descartarCarta(cartaElegida));
        juego.notificar(Evento.CARTA_DESCARTADA);
        juego.notificar(Evento.CARTA_AGREGADA_AREA);
        this.tiroCarta=true;
    }

    @Override
    public void finRonda() throws CartaException{
        if (tiroCarta) {
            if (esFinDeRonda()) {
                this.juego.finJuego();
            }
            this.juego.finTurno();
        }
        else {
            throw new CartaException("Debes tirar una carta antes de finalizar tu turno", TipoException.TIRAR_CARTA);
        }
    }

    @Override
    public boolean esFinDeRonda(){
        return  contador == cantidadJugadores;
    }

}