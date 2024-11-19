package ar.unlu.edu.mvc.modelo;
import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observado;
import ar.unlu.edu.mvc.interfaces.Observador;

import java.util.*;

public class Juego implements Observado, IJuego {
    private Queue<Jugador> jugadores;
    private Carnaval carnaval;
    private Mazo mazo;
    private Jugador jugadorTurno;
    private List<Observador> observadores;
    private boolean ultimaRonda;
    private  Ronda ronda;
    private boolean rondaDescarte;

    public Juego (){
        this.jugadores= new LinkedList<>();
        this.carnaval= new Carnaval();
        this.mazo= new Mazo();
        this.observadores = new ArrayList<>();
        this.ultimaRonda=false;
        this.rondaDescarte=false;

    }

    public int getCantidadCartasMazo(){
        return this.mazo.getCantidadCartas();
    }

    @Override
    public void sacarJugador(String nombre, Observador observador) {
        Jugador jugador = buscarJugador(nombre);
        this.jugadores.remove(jugador);
        this.observadores.remove(observador);
    }

    public void setJugadorTurno(Jugador jugador){
        this.jugadorTurno=jugador;
     }
    public void setUltimaRonda(boolean b){
        this.notificar(Evento.ULTIMA_RONDA);
        this.ultimaRonda=b;
    }

    public void setRondaDescarte(boolean b){
        this.notificar(Evento.RONDA_DESCARTE);
        this.rondaDescarte=b;
    }

     public Jugador buscarJugador(String nombre){
        for (Jugador jugador : this.jugadores){
            if (jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
     }

     @Override
    public void agregarJugador (String nombre) throws Exception{
        if(buscarJugador(nombre) == null || !nombre.equals(this.jugadorTurno.getNombre())) {
            Jugador jugador = new Jugador(nombre);
            this.setJugadorTurno(jugador);
            this.jugadores.add(jugador);
            this.notificar(Evento.JUGADOR_AGREGADO);
        }
        else {
            throw new Exception("El jugador ya se encuentra agregado");
        }
    }

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
    public void empezarJuego() throws Exception{
        if(sePuedeComenzar()) {
            this.repartirCartas();
            this.notificar(Evento.JUEGO_COMENZADO);
            this.cambiarTurno();
        }
        else {
            throw new Exception("Fatan jugadores");
        }
    }

    public void cambiarTurno(){
        this.setJugadorTurno(this.jugadores.remove());
        this.notificar(Evento.CAMBIO_TURNO);
        if (this.ultimaRonda && !this.rondaDescarte){

            this.ronda= new UltimaRonda(jugadorTurno,carnaval,null,this,this.jugadores.size() + 1);
        }
        else if(this.rondaDescarte){

            this.ronda= new RondaDescarte(jugadorTurno,carnaval,null,this.jugadores.size(),this);
        }
        else {
            this.ronda= new Ronda(jugadorTurno,carnaval,mazo,this);
        }
    }

    @Override
    public void tirarCarta(int cartaElegida){
        this.ronda.tirarCarta(cartaElegida);
    }

    @Override
    public void analizarCartasCarnaval (int[] cartasElegidas) throws CartaException{
        this.ronda.analizarCartasCarnaval(cartasElegidas);
    }

    @Override
    public void finalizarTurno()throws CartaException{
        this.ronda.finRonda();
    }

    public void finTurno() {
        this.notificar(Evento.FIN_TURNO);
        this.jugadores.add(this.jugadorTurno);
        this.cambiarTurno();
    }
    public void calcularPuntos(){
        evaluarAreaDeJuego();
        for (Jugador jugador : this.jugadores){
            jugador.sumarPuntos();
        }
    }
    public void finJuego(){
        this.notificar(Evento.FIN_JUEGO);
        this.calcularPuntos();
    }

    public void evaluarAreaDeJuego(){
        Jugador jugador_anterior= this.jugadores.peek();
        List<Jugador> jugadoresConMasCartas= new ArrayList<>();
        for (Color color : Color.values()){
            jugadoresConMasCartas.clear();
            for (Jugador jugador : this.jugadores){
                if (jugador.getArea().getCantidadDeCartasPorColor(color) != 0 && !jugador_anterior.equals(jugador)) {
                    if (jugador.getArea().getCantidadDeCartasPorColor(color) > jugador_anterior.getArea().getCantidadDeCartasPorColor(color)) {
                        jugador_anterior = jugador;
                        jugadoresConMasCartas.clear();
                        jugadoresConMasCartas.add(jugador);
                    } else if (jugador.getArea().getCantidadDeCartasPorColor(color) == jugador_anterior.getArea().getCantidadDeCartasPorColor(color)) {
                        if (!jugadoresConMasCartas.contains(jugador_anterior)){
                            jugadoresConMasCartas.add(jugador_anterior);
                        }
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
        Jugador jugador_anterior= this.jugadores.peek();
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
        if (jugadoresConMenosPuntos.size() > 1){
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

    @Override
    public boolean sePuedeComenzar(){
        return this.jugadores.size() > 1;
    }

    @Override
    public int getCantidadJugadores(){
        return this.jugadores.size();
    }

    @Override
    public Collection<List<String>> listarCartasArea(String nombreJugador) {
        Collection<List<String>> resultado= new ArrayList<>();
        Jugador jugador= buscarJugador(nombreJugador);
        if (jugador == null){
            jugador= this.jugadorTurno;
        }
        for (List<Carta> cartas : jugador.getCartasArea()) {
            List<String> listaNueva= new ArrayList<>();
            for (Carta carta : cartas) {
                listaNueva.add(carta.toString());
            }
            resultado.add(listaNueva);
        }
        return resultado;
    }

    @Override
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
    public List<String> listarCartasCarnaval(){
        List<String> resultado= new ArrayList<>();
        for (Carta carta : this.carnaval.getCartas()){
            resultado.add(carta.toString());
        }
        return resultado;
    }

    @Override
    public List<String> listarCartasEnMano(String nombre){
        Jugador jugador= this.buscarJugador(nombre);
        if (jugador == null){
            jugador= this.jugadorTurno;
        }
        List<String> resultado= new ArrayList<>();
        for (Carta carta : jugador.getCartas()){
            resultado.add(carta.toString());
        }
        return resultado;
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

    public Carnaval getCarnaval() {
        return this.carnaval;
    }
}


