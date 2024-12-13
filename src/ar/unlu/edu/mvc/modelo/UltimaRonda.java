package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;

import java.io.Serializable;
import java.util.Queue;

public class UltimaRonda extends Ronda implements Serializable {
    private final Jugador primerJugadorRonda;

    public UltimaRonda(Queue<Jugador> jugadores, Carnaval carnaval, Mazo mazo, Juego juego){
        super(jugadores,carnaval,mazo,juego);
        this.primerJugadorRonda=this.jugadores.peek();
    }


    @Override
    public void finTurno() throws JuegoException {
        boolean faltanCartasCarnaval=this.carnaval.faltaAgarrarCartas(this.carnaval.getUltimaCarta(), indicesCartasElegidas);
        if (tiroCarta && !faltanCartasCarnaval) {
            if (esFinDeRonda()) {
                this.jugadores.add(this.jugadorTurno);
                this.juego.setRondaDescarte(this.jugadores);
            }
            else{
                this.indicesCartasElegidas=null;
                this.agrego=false;
                this.juego.notificar(Evento.FIN_TURNO);
                this.jugadores.add(this.jugadorTurno);
                this.cambiarTurno();
            }
        }else if (faltanCartasCarnaval) {
            throw new JuegoException("Debes elegir cartas del carnaval antes de finalizar turno",TipoException.CARTA_EXCEPTION);

        }
        else{
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno", TipoException.CARTA_EXCEPTION);
        }

    }
    @Override
    public boolean esFinDeRonda(){
        return  this.primerJugadorRonda.equals(this.jugadores.peek());
    }
}
