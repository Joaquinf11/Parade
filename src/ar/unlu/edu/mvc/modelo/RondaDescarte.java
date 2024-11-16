package ar.unlu.edu.mvc.modelo;



public class RondaDescarte extends Ronda {
    private static int contador;


    public RondaDescarte(Jugador jugador, Carnaval carnaval, Mazo mazo, Juego juego) {
        super(jugador, carnaval, mazo, juego);
    }

    @Override
    public void tirarCarta(int cartaElegida) {
        this.jugadorTurno.getArea().agregarCarta(this.jugadorTurno.descartarCarta(cartaElegida));
        juego.notificar(Evento.CARTA_TIRADA);
        juego.notificar(Evento.CARTA_AGREGADA_AREA);

    }

    @Override
    public void finRonda() {
        this.juego.finTurno();
        if (esFinDeRonda()) {
            this.juego.finJuego();
        }
    }

}