package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observado;
import ar.unlu.edu.mvc.interfaces.Observador;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;

public class Juego implements Observado, IJuego {
    private Queue<Jugador> jugadores;
    private Carnaval carnaval;
    private Mazo mazo;
    private Jugador jugadorTurno;
    private List<Observador> observadores;

    public Juego (){
        this.jugadores= new LinkedList<>();
        this.carnaval= new Carnaval();
        this.mazo= new Mazo();
        this.observadores = new ArrayList<>();
    }
     public void setJugadorTurno(Jugador jugador){
        this.jugadorTurno=jugador;
     }

     @Override
    public void agregarJugador (String nombre){
        this.jugadores.add( new Jugador(nombre));
        this.notificar(Evento.JUGADOR_AGREGADO);
    }

    public Carnaval getCarnaval() {
        return this.carnaval;
    }


    public Mazo getMazo() {
        return this.mazo;
    }

    //testear
    public void repartirCartas(){
        for (int i = 1 ; i <= 6 ; i++){
            this.carnaval.agregarCarta(this.mazo.sacarCarta());
        }

        for (int i = 1 ; i <= 5 ; i++ ){
                for (Jugador jugador : this.jugadores){
                    jugador.agarrarCarta(this.mazo.sacarCarta());
                }
        }
    }

    @Override
    public void empezarJuego(){
        this.repartirCartas();
        this.jugar();
    }

    public void cambiarTurno(){
        this.setJugadorTurno(this.jugadores.remove());
        this.notificar(Evento.CAMBIO_TURNO);
    }

    //falta testear esto
    public void jugar(){
         if(!esFinDelJuego()){
            this.cambiarTurno();
        }
         else{
             this.notificar(Evento.ULTIMA_RONDA);
             this.ultimaRonda();
         }
    }

    @Override
    public void jugarCarta(int cartaElegida, int[] cartaElegidasCarnaval){
        Carta carta= this.jugadorTurno.elegirCarta(cartaElegida);
        this.analizarCartasCarnaval(carta,cartaElegidasCarnaval);
    }

    public void analizarCartasCarnaval(Carta carta, int [] cartasElegidas){
        if (!this.carnaval.puedeAgarrarCarnaval(carta)){
            this.notificar(Evento.NO_SE_PUEDE_AGARRAR); // deberia ser un exception o asi esta bien?
        }
        else if (this.carnaval.agarroCartasSalvadasCarnaval(carta.getValor(),cartasElegidas)){
                this.notificar(Evento.ELIGIO_CARTA_SALVADA);
                //como castigo pierde el turno?
        }
        else {
            List<Carta> cartasCarnaval = this.carnaval.getCartas(cartasElegidas);
            int contador=0;
            for (Carta cartaCarnaval : cartasCarnaval) {
                if (carta.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= carta.getValor()) {
                    jugadorTurno.agregarCartaAlAreaDeJuego(cartaCarnaval);

                } else {
                    this.notificar(Evento.CARTA_MAL_ELEGIDA_CARNAVAL);
                    this.carnaval.agregarCarta(cartasElegidas[contador],cartaCarnaval);
                }
                contador++;
            }
        }
        this.carnaval.agregarCarta(carta);
        this.finDeTurno();
    }

    public void finDeTurno(){
        this.notificar(Evento.FIN_TURNO);
        int cantidad=this.jugadorTurno.getCantidadCartasEnMano();
        for (int i=cantidad ; i < 5 ; i++ ){
            this.jugadorTurno.agarrarCarta(this.mazo.sacarCarta());
        }
        this.notificar(Evento.CARTA_AGREGADA_MANO);
        this.jugadores.add(this.jugadorTurno);
    }

    public void ultimaRonda(){
        int contador = this.jugadores.size();
        while (contador != 0){
            this.cambiarTurno();
            contador--;
        }
        this.notificar(Evento.RONDA_DESCARTE);

    }
    public boolean esFinDelJuego(){
        return  hayJugadorCon6colores() || !this.mazo.tieneCartas();
    }

    public boolean hayJugadorCon6colores(){
        for(Jugador jugador : jugadores){
            if (jugador.getArea().tiene6colores()){
                return true;
            }
        }
        return false;
    }

    public void rondaDescarte(){
        int contador = this.jugadores.size();
        while (contador != 0){
            setJugadorTurno(this.jugadores.remove());
            this.notificar(Evento.DESCARTAR_DOS_CARTAS);
            contador--;
        }
        this.notificar(Evento.FIN_JUEGO);
    }
    public void descartarDosCartas(int[] cartasElegidas){
        //deberia considerar el caso en que el jugador elija mas de dos cartas o menos como manejar ese error
        this.jugadorTurno.quitarCarta(cartasElegidas);

    }
    public void calcularPuntos(){
        evaluarAreaDeJuego();
        for (Jugador jugador : this.jugadores){
            jugador.sumarPuntos();
        }
    }
    public IJugador finJuego(){
        this.calcularPuntos();
        return this.definirGanador();
    }

    public void evaluarAreaDeJuego(){
        Jugador jugador_anterior= this.jugadores.remove();
        List<Jugador> jugadoresConMasCartas= new ArrayList<>();
        for (Color color : Color.values()){
            for (Jugador jugador : this.jugadores){
                if (jugador.getArea().getCantidadDeCartasPorColor(color) != 0 && !jugador_anterior.equals(jugador)) {
                    if (jugador.getArea().getCantidadDeCartasPorColor(color) > jugador_anterior.getArea().getCantidadDeCartasPorColor(color)) {
                        jugador_anterior = jugador;
                        jugadoresConMasCartas.clear();
                        jugadoresConMasCartas.add(jugador);
                    } else if (jugador.getArea().getCantidadDeCartasPorColor(color) == jugador_anterior.getArea().getCantidadDeCartasPorColor(color)) {
                        jugadoresConMasCartas.add(jugador);
                    }
                }
            }
            for (Jugador jugador : jugadoresConMasCartas){
                jugador.getArea().ponerCartasBocaAbajo(color);
            }

        }
    }

    public Jugador definirGanador(){
        List<Jugador> jugadoresConMenosPuntos= new ArrayList<>();
        Jugador jugador_anterior= this.jugadores.remove();
        for (Jugador jugador : this.jugadores){
            if (jugador.getPuntos() < jugador_anterior.getPuntos()){
                jugador_anterior = jugador;
                jugadoresConMenosPuntos.clear();
                jugadoresConMenosPuntos.add(jugador_anterior);
            }
            else if( jugador.getPuntos() == jugador_anterior.getPuntos()){
                jugadoresConMenosPuntos.add(jugador);
            }
        }
        if (jugadoresConMenosPuntos.size() == 1){
            return jugador_anterior;
        }
        else{
            jugador_anterior= jugadoresConMenosPuntos.removeFirst();
            for (Jugador jugador : jugadoresConMenosPuntos){
                if (jugador.getCantidadCartasEnArea() < jugador_anterior.getCantidadCartasEnArea()){
                    jugador_anterior = jugador;
                }
            }
        }

        return jugador_anterior;
        // falta considerar el caso en que sea un empate TOTAL
    }

    public List<IJugador> listarJugadores(){
        List<IJugador> jugadores = new ArrayList<>();
        jugadores.addAll(this.jugadores);
        return jugadores;
    }

    @Override
    public IJugador getJugadorTurno(){
        return this.jugadorTurno;
    }

    @Override
    public void agregarObservador(Observador observador) {
        if(!this.observadores.contains(observador)){
            this.observadores.add(observador);
        }
    }

    @Override
    public void notificar(Evento evento) {
        for (Observador observador: this.observadores){
            observador.actualizar(evento);
        }
    }



//////////////////////////////////
    //FUNCIONES PARA TEST
    //////////////////////////////////


    public void mostrarCartasEnManoPorJugador(){
        for (Jugador jugador : this.jugadores){
            System.out.println(jugador.getNombre());
            jugador.mostrarCartasEnMano();
        }
    }

    public Queue<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void jugarTurnoViejo(Jugador jugador,int indice){ //el indice no se si va como parametro pero lo puse ahora para testear nomas
        Carta carta= jugador.elegirCarta(indice); // aca tendria que poner el indice que elige el jugador ver como hacer

        if (!this.carnaval.puedeAgarrarCarnaval(carta)){
            this.carnaval.agregarCarta(carta);

        }
        else {
            List<Carta> salvadas = this.carnaval.salvarCartas(carta.getValor());
            Iterator<Carta> iter= this.carnaval.getCartas().iterator();
            while(iter.hasNext()){
                Carta cartaCarnaval = iter.next();
                if (carta.equalsColor(cartaCarnaval)){
                    jugador.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    iter.remove();
                }
                else if (carta.getValor() <= cartaCarnaval.getValor()){
                    jugador.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    iter.remove();
                }
            }
            //vuelvo a agregar las salvadas al carnaval
            for (Carta salvada : salvadas){
                this.carnaval.agregarCarta(salvada);
            }
            this.carnaval.agregarCarta(carta);
        }
        int cantidad=jugador.getCantidadCartasEnMano();
        for (int i=cantidad ; i <= 5 ; i++ ){
            jugador.agarrarCarta(this.mazo.sacarCarta());
        }
    }
}


