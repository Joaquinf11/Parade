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




    public void tirarCarta(int cartaElegida){
        Carta carta= this.jugadorTurno.elegirCarta(cartaElegida);
        this.juego.notificar(Evento.CARTA_TIRADA);
        System.out.println("Carta tirada " + carta.getColor() + " " + carta.getValor()); //BORRAR
        this.carnaval.agregarCarta(carta);
        System.out.println("Carta agregada al carnaval " + carta.getColor() + " " + carta.getValor()); //BORRAR
        this.juego.notificar(Evento.CARTA_AGREGADA_CARNAVAL);
        if (cartaElegida == -1){
            this.finRonda();
        }
    }

    public void analizarCartasCarnaval( int [] cartasElegidas){
        Carta carta= this.carnaval.getUltimaCarta();
        if (!this.carnaval.puedeAgarrarCarnaval(carta)){
            this.juego.notificar(Evento.NO_SE_PUEDE_AGARRAR); // debe ser un exception
            System.out.println("NO SE PUEDE AGARRAR"); //BORRAR
        }
        else if (this.carnaval.agarroCartasSalvadasCarnaval(carta.getValor(),cartasElegidas)){
            this.juego.notificar(Evento.ELIGIO_CARTA_SALVADA);// debe ser un exception
            System.out.println("CARTA SALVADA"); //BORRAR
        }
        else {
            List<Carta> cartasCarnaval = this.carnaval.getCartas(cartasElegidas);
            int contador= 0;
            for (Carta cartaCarnaval : cartasCarnaval) {
                if (carta.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= carta.getValor()) {
                    jugadorTurno.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    this.juego.notificar(Evento.CARTA_AGREGADA_AREA);
                    System.out.println("CARTA AGREGADA AL AREA DE JUEGO " + cartaCarnaval.getColor() + " " + cartaCarnaval.getValor()); //BORRAR
                } else {
                    this.juego.notificar(Evento.CARTA_MAL_ELEGIDA_CARNAVAL); // debe ser un exception
                    System.out.println("CARTA MAL ELEGIDA  " + cartaCarnaval.getColor() + " " + cartaCarnaval.getValor()); //BORRAR
                    this.carnaval.agregarCarta(cartasElegidas[contador],cartaCarnaval);
                }
                contador++;
            }
        }
        this.finRonda();
    }


    public void finRonda(){
        this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
        juego.finTurno();
        if (esFinDeRonda()){
            juego.setUltimaRonda(true);
        }
    }

    public boolean esFinDeRonda(){
        return  this.jugadorTurno.getArea().tiene6colores() || !this.mazo.tieneCartas();
    }
}
