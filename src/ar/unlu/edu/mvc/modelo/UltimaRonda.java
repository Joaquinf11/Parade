package ar.unlu.edu.mvc.modelo;

public class UltimaRonda extends Ronda{
    private  int contador=0;
    private final int cantidadJugadores;

    public UltimaRonda(Jugador jugador, Carnaval carnaval,Mazo mazo,Juego juego,int cantidad){
        super(jugador,carnaval,mazo,juego);
        contador++;
        this.cantidadJugadores= cantidad;
    }

    @Override
    public void finRonda(){
        this.juego.finTurno();
        if (esFinDeRonda()){
            this.juego.setRondaDescarte(true);
        }
    }

    @Override
    public boolean esFinDeRonda(){
        return  contador == cantidadJugadores;
    }
}
