package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.util.List;

public class Ronda {

    private final Carnaval carnaval;
    private final Mazo mazo;
    protected Jugador jugadorTurno;
    protected Juego juego;
    protected boolean tiroCarta;
    private int[] indicesCartasElegidas=null;
    private  Carta cartaTirada;


    public Ronda(Jugador jugador, Carnaval carnaval,Mazo mazo,Juego juego){
        this.jugadorTurno=jugador;
        this.carnaval= carnaval;
        this.mazo= mazo;
        this.juego=juego;
        this.tiroCarta=false;
    }




    public void tirarCarta(int cartaElegida) throws JuegoException {
        Carta carta= this.jugadorTurno.elegirCarta(cartaElegida);
        this.juego.notificar(Evento.CARTA_TIRADA);
        this.carnaval.agregarCarta(carta);
        this.juego.notificar(Evento.CARTA_AGREGADA_CARNAVAL);
        this.tiroCarta=true;
        this.cartaTirada=carta;
    }

    public void analizarCartasCarnaval( int [] cartasElegidas) throws JuegoException {
        boolean agrego=false;
        Carta carta= this.carnaval.getUltimaCarta();
        if (!this.carnaval.puedeAgarrarCarnaval(carta)){
            throw new JuegoException("La carta tirada: " + carta.toString() + " tiene mayor valor a la cantidad de cartas que hay en el carnaval",TipoException.CARTA_EXCEPTION);
        }
        else if (this.carnaval.agarroCartasSalvadasCarnaval(carta.getValor(),cartasElegidas)){
            throw new JuegoException("Elegiste una carta salvada",TipoException.CARTA_EXCEPTION);
        }
        else if(this.carnaval.faltaAgarrarCartas(carta,cartasElegidas)){
            throw new JuegoException("Podes seleccionar mas cartas del carnaval",TipoException.CARTA_EXCEPTION);
        }
        else {
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
        }
        if (agrego){
            this.juego.notificar(Evento.CARTA_AGREGADA_AREA);
        }
        this.indicesCartasElegidas= cartasElegidas;
        this.finRonda();
    }


    public void finRonda() throws JuegoException {
        if (tiroCarta && !this.carnaval.faltaAgarrarCartas(cartaTirada, indicesCartasElegidas)) {
            this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
            if (!this.mazo.tieneCartas()){
                juego.notificar(Evento.MAZO_SIN_CARTAS);
            }
            if (esFinDeRonda()) {
                juego.setUltimaRonda(true);
            }
            juego.finTurno();
        } else if (this.carnaval.faltaAgarrarCartas(this.carnaval.getUltimaCarta(),null)) {
            throw new JuegoException("Debes elegir cartas del carnaval antes de finalizar turno",TipoException.CARTA_EXCEPTION);

        } else{
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno",TipoException.CARTA_EXCEPTION);
        }
    }

    public boolean esFinDeRonda(){
        return  this.jugadorTurno.getArea().tiene6colores() || !this.mazo.tieneCartas();
    }
}
