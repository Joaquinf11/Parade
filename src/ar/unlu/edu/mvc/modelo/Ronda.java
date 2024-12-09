package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;
import ar.unlu.edu.mvc.interfaces.IJugador;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;

public class Ronda implements Serializable {

    private final Carnaval carnaval;
    private final Mazo mazo;
    protected Jugador jugadorTurno;
    protected Queue<Jugador> jugadores;
    protected Juego juego;
    protected boolean tiroCarta;
    private int[] indicesCartasElegidas=null;
    private  Carta cartaTirada;
    protected Jugador primerJugadorRonda;


    public Ronda(Queue<Jugador> jugadores, Carnaval carnaval,Mazo mazo,Juego juego){
        this.jugadores=jugadores;
        this.carnaval= carnaval;
        this.mazo= mazo;
        this.juego=juego;
        this.tiroCarta=false;
        this.primerJugadorRonda= jugadores.peek();
        this.cambiarTurno();
    }

    protected void cambiarTurno(){
        this.jugadorTurno= this.jugadores.remove();
        this.juego.notificar(Evento.CAMBIO_TURNO);
    }

    public void tirarCarta(int cartaElegida) throws JuegoException {
        Carta carta= this.jugadorTurno.elegirCarta(cartaElegida);
        this.juego.notificar(Evento.CARTA_TIRADA);
        this.carnaval.agregarCarta(carta);
        this.juego.notificar(Evento.CARTA_AGREGADA_CARNAVAL);
        this.tiroCarta=true;
        this.cartaTirada=carta;
    }

    public void agregarCartasAlAreaDeJuego(Carta carta,int[] cartasElegidas) throws JuegoException{
        boolean agrego= false;
        List<Carta> cartasCarnaval = this.carnaval.getCartas(cartasElegidas);
        for (Carta cartaCarnaval : cartasCarnaval) {
            if (carta.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= carta.getValor()) {
                jugadorTurno.agregarCartaAlAreaDeJuego(cartaCarnaval);
                this.carnaval.sacarCarta(cartaCarnaval);
                agrego=true;
            } else {
                throw new JuegoException("La carta elegida: " + cartaCarnaval.toString() + " no se puede agarrar",TipoException.CARTA_EXCEPTION);
            }
        }
        if (agrego){
            this.juego.notificar(Evento.CARTA_AGREGADA_AREA);
        }
        this.finTurno();
    }

    public void finTurno() throws JuegoException {
        if (tiroCarta && !this.carnaval.faltaAgarrarCartas(cartaTirada, indicesCartasElegidas)) {
            this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
            if (!this.mazo.tieneCartas()){
                juego.notificar(Evento.MAZO_SIN_CARTAS);
            }
            if (esFinDeRonda()) {
                juego.setUltimaRonda(true);
            }
        } else if (this.carnaval.faltaAgarrarCartas(this.carnaval.getUltimaCarta(),null)) {
            throw new JuegoException("Debes elegir cartas del carnaval antes de finalizar turno",TipoException.CARTA_EXCEPTION);

        } else{
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno",TipoException.CARTA_EXCEPTION);
        }
    }

    public boolean esFinDeRonda(){
        return  this.jugadorTurno.getArea().tiene6colores() || !this.mazo.tieneCartas();
    }

    public IJugador getJugadorTurno() {
        return this.jugadorTurno;
    }

}
