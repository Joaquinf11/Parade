package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.observer.Observado;
import ar.unlu.edu.mvc.observer.Observador;

import java.util.*;

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
            calcularPuntos();
        }

    }



    public void jugarTurno(Jugador jugador,int indice){ //el indice no se si va como parametro pero lo puse ahora para testear nomas
        Carta carta= jugador.elegirCarta(indice); // aca tendria que poner el indice que elige el jugador ver como hacer

        boolean agrego= false; //falta ver en caso de que el jugador pueda agregar cartas a su area de juego, que pasa con la carta que tiro va al carnaval o al area?


        if (!this.carnaval.analizarCarnaval(carta)){
            this.carnaval.agregarCarta(carta);
            jugador.agarrarCarta(this.mazo.sacarCarta());
        }
        else {
            List<Carta> salvadas = this.carnaval.salvarCartas(carta.getValor());
            for (Carta cartaCarnaval : this.carnaval.getCartas()){
                if (Objects.equals(cartaCarnaval.getColor(),carta.getColor())){
                    jugador.agregarCartaAlAreaDeJuego(cartaCarnaval);
                    this.carnaval.sacarCarta(cartaCarnaval);
                    agrego=true;
                }
                else if (carta.getValor() <= cartaCarnaval.getValor()){
                    jugador.agregarCartaAlAreaDeJuego(carta);
                    this.carnaval.sacarCarta(cartaCarnaval);
                    agrego=true;
                }
            }
            //vuelvo a agregar las salvadas al carnaval
            for (Carta salvada : salvadas){
                this.carnaval.agregarCarta(salvada);
            }
            if (!agrego){
                this.carnaval.agregarCarta(carta);
            }

        }
    }

    public void evaluarAreaDeJuego(){
    // no contempla el caso de que dos jugadores tengan la misma cantidad de cartas y los dos tengan que eliminar sus cartas del area de juego
        Jugador jugador_anterior= this.jugadores.getFirst();
        for (Color color : Color.values()){
            for (Jugador jugador : this.jugadores){
                if (jugador.getArea().getCantidadDeCartasPorColor(color) > jugador_anterior.getArea().getCantidadDeCartasPorColor(color)){
                    jugador_anterior= jugador;
                }
            }

            jugador_anterior.getArea().ponerCartasBocaAbajo(color);

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
        for (Jugador jugador : this.jugadores){
            jugador.sumarPuntos();
        }
    }

    public Jugador definirGanador(){
        Jugador jugador_anterior= this.jugadores.getFirst();
        for (Jugador jugador : this.jugadores){
            if (jugador.getPuntos() < jugador_anterior.getPuntos()){
                jugador_anterior = jugador;
            }
        }
        return jugador_anterior;
        // falta considerar el caso en que empaten en puntos, si pasa eso gana el que tiene menos cartas en el area de juego (BOCA ARRIBA Y BOCA ABAJO)

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

