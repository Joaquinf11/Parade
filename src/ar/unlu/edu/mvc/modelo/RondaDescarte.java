package ar.unlu.edu.mvc.modelo;



public class RondaDescarte extends Ronda {
    private static int contador;


    public RondaDescarte(Jugador jugador, Carnaval carnaval, Mazo mazo, Juego juego) {
        super(jugador, carnaval, mazo, juego);
    }

    public void jugarCarta(int cartaElegida, int[] cartaElegidasCarnaval) {
        Carta carta = this.jugadorTurno.elegirCarta(cartaElegida);
        this.analizarCartasCarnaval(carta, cartaElegidasCarnaval);
    }

    @Override
    public void finRonda() {
        this.juego.finTurno();
        if (esFinDeRonda()) {
            this.juego.finJuego();
        }
    }

}