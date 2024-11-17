package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.exceptions.TipoException;

public class UltimaRonda extends Ronda{
    private  int contador=0;
    private final int cantidadJugadores;

    public UltimaRonda(Jugador jugador, Carnaval carnaval,Mazo mazo,Juego juego,int cantidad){
        super(jugador,carnaval,mazo,juego);
        contador++;
        this.cantidadJugadores= cantidad;
    }

    @Override
    public void finRonda() throws CartaException{
        if (tiroCarta) {
            this.juego.finTurno();
            if (esFinDeRonda()) {
                this.juego.setRondaDescarte(true);
            }
        }
        else{
            throw new CartaException("Debes tirar una carta antes de finalizar tu turno", TipoException.TIRAR_CARTA);
            }

    }
    @Override
    public boolean esFinDeRonda(){
        return  contador == cantidadJugadores;
    }
}
