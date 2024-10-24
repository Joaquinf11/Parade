package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.observer.Observado;
import ar.unlu.edu.mvc.observer.Observador;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;
import java.util.Objects;

public class Juego implements Observado {
    private List<Jugador> jugadores;
    private Carnaval carnaval;
    private Mazo mazo;

    private List<Observador> observadores;

    public Juego (){
        this.jugadores= new ArrayList<>();
        this.carnaval= new Carnaval();
        this.mazo= new Mazo();
        this.observadores = new ArrayList<>();
    }

    public void agregarJugador (String nombre){
        this.jugadores.add( new Jugador(nombre));
        this.notificar(Evento.JUGADOR_AGREGADO);
    }

    public Carnaval getCarnaval() {
        return this.carnaval;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
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

    //falta testear esto
    public void manejarTurnos(int indice){//el indice no va como parametro pero lo puse ahora para testear nomas
        Queue<Jugador> jugadores= new LinkedList<>();
        jugadores.addAll(this.jugadores);


        while (!esFinDelJuego()){
            Jugador jugador=jugadores.remove();
            this.jugarTurno(jugador,indice);
            jugadores.add(jugador);
        }
        //jugar 1 ronda mas, seguro lo podes mejorar, tenes que chequear que se haga bien
        int contador=jugadores.size();
        while (esFinDelJuego() && contador != 0){
            Jugador jugador=jugadores.remove();
            this.jugarTurno(jugador,indice);
            jugadores.add(jugador);
            contador--;
        }

        if (esFinDelJuego()){
            //faltaria hacer descartar dos cartas de la mano a cada jugador cuando termina el ultimo turno
            calcularPuntos();
        }

    }



    public void jugarTurno(Jugador jugador,int indice){ //el indice no se si va como parametro pero lo puse ahora para testear nomas
        Carta carta= jugador.elegirCarta(indice); // aca tendria que poner el indice que elige el jugador ver como hacer

        if (!this.carnaval.analizarCarnaval(carta)){
            this.carnaval.agregarCarta(carta);

        }
        else {
            List<Carta> salvadas = this.carnaval.salvarCartas(carta.getValor());
            Iterator<Carta> iter= this.carnaval.getCartas().iterator();
            while(iter.hasNext()){
                Carta cartaCarnaval = iter.next();
                if (Objects.equals(cartaCarnaval.getColor(),carta.getColor())){
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

    public void calcularPuntos(){
        evaluarAreaDeJuego();
        for (Jugador jugador : this.jugadores){
            jugador.sumarPuntos();
        }
    }

    // a re contra re mil chequear esta funcion
    public void evaluarAreaDeJuego(){
        Jugador jugador_anterior= this.jugadores.getFirst();
        List<Jugador> jugadoresConMasCartas= new ArrayList<>();
        for (Color color : Color.values()){
            for (Jugador jugador : this.jugadores){
                if (jugador.getArea().getCantidadDeCartasPorColor(color) > jugador_anterior.getArea().getCantidadDeCartasPorColor(color)){
                    jugador_anterior= jugador;
                    jugadoresConMasCartas.clear();
                    jugadoresConMasCartas.add(jugador);
                }
                else if (jugador.getArea().getCantidadDeCartasPorColor(color) == jugador_anterior.getArea().getCantidadDeCartasPorColor(color)){
                    jugadoresConMasCartas.add(jugador);
                }
            }
            for (Jugador jugador : jugadoresConMasCartas){
                jugador.getArea().ponerCartasBocaAbajo(color);
            }

        }
    }


    public Jugador definirGanador(){
        List<Jugador> jugadoresConMenosPuntos= new ArrayList<>();
        Jugador jugador_anterior= this.jugadores.getFirst();
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
    public void agregarObservador(Observador observador) {
        if(!this.observadores.contains(observador)){
            this.observadores.add(observador);
        }
    }

    @Override
    public void notificar(Evento evento) {
        for (Observador observador: this.observadores){
            observador.notificar(evento);
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
}

