package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;
import ar.unlu.edu.mvc.interfaces.IJugador;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;

public class Ronda implements Serializable {

    protected final Carnaval carnaval;
    private final Mazo mazo;
    protected Jugador jugadorTurno;
    protected Queue<Jugador> jugadores;
    protected Juego juego;
    protected boolean tiroCarta;
    protected int[] indicesCartasElegidas = null;
    private Carta cartaTirada;
    protected boolean agrego;


    public Ronda(Queue<Jugador> jugadores, Carnaval carnaval, Mazo mazo, Juego juego) {
        this.jugadores = jugadores;
        this.carnaval = carnaval;
        this.mazo = mazo;
        this.juego = juego;
        this.tiroCarta = false;
        this.agrego=false;
    }

    protected void cambiarTurno() {
        this.jugadorTurno = this.jugadores.remove();
        this.juego.notificar(Evento.CAMBIO_TURNO);
    }

    public void tirarCarta(int cartaElegida) throws JuegoException {
        Carta carta = this.jugadorTurno.elegirCarta(cartaElegida);
        this.juego.notificar(Evento.CARTA_TIRADA);
        this.carnaval.agregarCarta(carta);
        this.juego.notificar(Evento.CARTA_AGREGADA_CARNAVAL);
        this.tiroCarta = true;
        this.cartaTirada = carta;
    }

    public void analizarCartasCarnaval(int[] cartasElegidas) throws JuegoException {
        if (!this.carnaval.puedeAgarrarCarnaval(this.cartaTirada)) {
            throw new JuegoException("La carta tirada: " + this.cartaTirada.toString() + " tiene mayor valor a la cantidad de cartas que hay en el carnaval", TipoException.CARTA_EXCEPTION);
        } else if (this.carnaval.agarroCartasSalvadasCarnaval(this.cartaTirada.getValor(), cartasElegidas)) {
            throw new JuegoException("Elegiste una carta salvada", TipoException.CARTA_EXCEPTION);
        } else if (this.carnaval.faltaAgarrarCartas(this.cartaTirada, cartasElegidas)) {
            throw new JuegoException("Podes seleccionar mas cartas del carnaval", TipoException.CARTA_EXCEPTION);
        } else {
            List<Carta> cartasCarnaval = this.carnaval.getCartas(cartasElegidas);
            for (Carta cartaCarnaval : cartasCarnaval) {
                if (this.cartaTirada.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= this.cartaTirada.getValor()) {
                    jugadorTurno.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    this.carnaval.sacarCarta(cartaCarnaval);
                    agrego = true;
                } else {
                    throw new JuegoException("La carta elegida: " + cartaCarnaval.toString() + " no se puede agarrar", TipoException.CARTA_EXCEPTION);
                }
            }
            if (agrego) {
                this.juego.notificar(Evento.CARTA_AGREGADA_AREA);
                this.indicesCartasElegidas = cartasElegidas;
            }
            this.finTurno();
        }
    }

    public void finTurno() throws JuegoException {
        boolean faltanCartasCarnaval=this.carnaval.faltaAgarrarCartas(this.cartaTirada, indicesCartasElegidas);
        if (tiroCarta && !faltanCartasCarnaval) {
            this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
            this.juego.notificar(Evento.FIN_TURNO);
            if (!this.mazo.tieneCartas()){
                juego.notificar(Evento.MAZO_SIN_CARTAS);
            }
            if (esFinDeRonda()){
                this.jugadores.add(this.jugadorTurno);
                this.juego.setUltimaRonda(this.jugadores);
            }
            else {
                this.indicesCartasElegidas=null;
                this.agrego=false;
                this.jugadores.add(this.jugadorTurno);
                this.cambiarTurno();
            }
        } else if (faltanCartasCarnaval) {
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
