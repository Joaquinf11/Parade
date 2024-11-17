package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.util.List;

public class Ronda {

    private final Carnaval carnaval;
    private final Mazo mazo;
    protected Jugador jugadorTurno;
    protected Juego juego;
    protected boolean tiroCarta;

    public Ronda(Jugador jugador, Carnaval carnaval,Mazo mazo,Juego juego){
        this.jugadorTurno=jugador;
        this.carnaval= carnaval;
        this.mazo= mazo;
        this.juego=juego;
        this.tiroCarta=false;
    }




    public void tirarCarta(int cartaElegida){
        Carta carta= this.jugadorTurno.elegirCarta(cartaElegida);
        this.juego.notificar(Evento.CARTA_TIRADA);
        this.carnaval.agregarCarta(carta);
        this.juego.notificar(Evento.CARTA_AGREGADA_CARNAVAL);
        this.tiroCarta=true;
    }

    public void analizarCartasCarnaval( int [] cartasElegidas) throws CartaException{
        Carta carta= this.carnaval.getUltimaCarta();
        if (!this.carnaval.puedeAgarrarCarnaval(carta)){
            throw new CartaException("La carta tirada: " + carta.toString() + " tiene mayor valor a la cantidad de cartas que hay en el carnaval");
        }
        else if (this.carnaval.agarroCartasSalvadasCarnaval(carta.getValor(),cartasElegidas)){
            throw new CartaException("Elegiste una carta salvada");
        }
        else if(this.carnaval.faltaAgarrarCartas(carta,cartasElegidas)){
            throw new CartaException("Podes seleccionar mas cartas del carnaval");
        }
        else {
            List<Carta> cartasCarnaval = this.carnaval.getCartas(cartasElegidas);
            int contador= 0;
            for (Carta cartaCarnaval : cartasCarnaval) {
                if (carta.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= carta.getValor()) {
                    jugadorTurno.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    this.juego.notificar(Evento.CARTA_AGREGADA_AREA);
                } else {
                        throw new CartaException("La carta elegida: " + cartaCarnaval.toString() + " no se puede agarrar");
                }
                contador++;
            }
        }
        this.finRonda();
    }


    public void finRonda() throws  CartaException{
        if (tiroCarta && !this.carnaval.faltaAgarrarCartas(this.carnaval.getUltimaCarta(), null)) {
            this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
            juego.finTurno();
            if (esFinDeRonda()) {
                juego.setUltimaRonda(true);
            }
        } else if (this.carnaval.faltaAgarrarCartas(this.carnaval.getUltimaCarta(),null)) {
            throw new CartaException("Debes elegir cartas del carnaval antes de finalizar turno");

        } else{
            throw new CartaException("Debes tirar una carta antes de finalizar tu turno");
        }
    }

    public boolean esFinDeRonda(){
        return  this.jugadorTurno.getArea().tiene6colores() || !this.mazo.tieneCartas();
    }
}
