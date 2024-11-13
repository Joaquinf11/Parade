package ar.unlu.edu.mvc.modelo;

import java.util.List;

public class Ronda {

    private final Carnaval carnaval;
    private final Mazo mazo;
    protected Jugador jugadorTurno;
    protected Juego juego;

    public Ronda(Jugador jugador, Carnaval carnaval,Mazo mazo,Juego juego){
        this.jugadorTurno=jugador;
        this.carnaval= carnaval;
        this.mazo= mazo;
        this.juego=juego;
    }




    public void jugarCarta(int cartaElegida, int[] cartaElegidasCarnaval){
        Carta carta= this.jugadorTurno.elegirCarta(cartaElegida);
        this.analizarCartasCarnaval(carta,cartaElegidasCarnaval);
    }

    public void analizarCartasCarnaval(Carta carta, int [] cartasElegidas){
        if (!this.carnaval.puedeAgarrarCarnaval(carta)){

        }
        else if (this.carnaval.agarroCartasSalvadasCarnaval(carta.getValor(),cartasElegidas)){
            //como castigo pierde el turno?
        }
        else {
            List<Carta> cartasCarnaval = this.carnaval.getCartas(cartasElegidas);
            int contador= 0;
            for (Carta cartaCarnaval : cartasCarnaval) {
                if (carta.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= carta.getValor()) {
                    jugadorTurno.agregarCartaAlAreaDeJuego(cartaCarnaval);

                } else {
                    this.carnaval.agregarCarta(cartasElegidas[contador],cartaCarnaval);
                }
                contador++;
            }
        }
        this.carnaval.agregarCarta(carta);
        this.finRonda();
    }


    public void finRonda(){
        int cantidad=this.jugadorTurno.getCantidadCartasEnMano();
        for (int i=cantidad ; i < 5 ; i++ ){
            this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
        }
        juego.finTurno();
        if (esFinDeRonda()){
            juego.setUltimaRonda(true);
        }
    }

    public boolean esFinDeRonda(){
        return  this.jugadorTurno.getArea().tiene6colores() || !this.mazo.tieneCartas();
    }
}
