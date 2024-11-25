package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;

public class UltimaRonda extends Ronda{
    private static int contador=0;
    private final int cantidadJugadores;

    public UltimaRonda(Jugador jugador, Carnaval carnaval,Mazo mazo,Juego juego,int cantidad){
        super(jugador,carnaval,mazo,juego);
        contador++;
        this.cantidadJugadores= cantidad;
    }

    @Override
    public void finRonda() throws JuegoException {
        if (tiroCarta) {
            if (esFinDeRonda()) {
                this.juego.setRondaDescarte(true);
            }
            this.juego.finTurno();
        }
        else{
            throw new JuegoException("Debes tirar una carta antes de finalizar tu turno", TipoException.TIRAR_CARTA);
            }

    }
    @Override
    public boolean esFinDeRonda(){
        return  contador == cantidadJugadores;
    }
}
