package ar.unlu.edu.mvc.modelo;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import ar.unlu.edu.mvc.exceptions.JuegoException;
import ar.unlu.edu.mvc.exceptions.TipoException;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.persistencia.Serializador;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

public class Juego extends ObservableRemoto implements Serializable, IJuego {
    private List<Jugador> jugadores;
    private Carnaval carnaval;
    private Mazo mazo;
    private  Ronda ronda;
    private Evento ultimoEvento;
    private TablaTop tablaTop;

    public Juego (){
        this.jugadores= new LinkedList<>();
        this.carnaval= new Carnaval();
        this.mazo= new Mazo();
        Serializador serializador= new Serializador("TablaTop");
        try {
            this.tablaTop= (TablaTop) serializador.recuperar();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (this.tablaTop== null)
        {
            this.tablaTop= new TablaTop();
        }

    }

    @Override
    public int getCantidadCartasMazo(){
        return this.mazo.getCantidadCartas();
    }


    @Override
    public IJuego cargarPartida(String nombrePartida) throws IOException, ClassNotFoundException {
        Serializador repo= new Serializador(nombrePartida);
        return (IJuego)repo.recuperar();
    }

    @Override
    public void guardarPartida(String nombrePartida) throws IOException {
        Serializador repo= new Serializador(nombrePartida);
        repo.persistir(this);
    }

    @Override
    public String getUltimoJugadorAgregado() throws RemoteException {
        return this.jugadores.getLast().getNombre();
    }

    @Override
    public void notificarUltimoEvento() throws RemoteException {
        notificar(this.ultimoEvento);
    }

    @Override
    public void nuevaPartida() throws RemoteException, JuegoException {
        for(Jugador jugador: this.jugadores){
            jugador.resetNuevoJuego();
        }
        this.carnaval= new Carnaval();
        this.mazo= new Mazo();
        this.ronda=null;
        this.empezarJuego();
    }

    @Override
    public List<String> listarCartasAreaDadasVuelta(String nombreJugador) throws RemoteException {
        return buscarJugador(nombreJugador).listarCartasDadasVuelta();
    }


    @Override
    public void sacarJugador(String nombre, IObservadorRemoto o) throws RemoteException {
        Jugador jugador = buscarJugador(nombre);
        this.jugadores.remove(jugador);
        removerObservador(o);
    }

    public void setUltimaRonda(Queue<Jugador> jugadores){
        this.notificar(Evento.ULTIMA_RONDA);
        this.ronda= new UltimaRonda(jugadores,this.carnaval,this.mazo,this);
        this.ronda.cambiarTurno();
    }

    public void setRondaDescarte(Queue<Jugador>jugadores){
        this.notificar(Evento.RONDA_DESCARTE);
        this.ronda= new RondaDescarte(new LinkedList<>(this.jugadores),this.carnaval,this.mazo,this);
        this.ronda.cambiarTurno();
    }

     private Jugador buscarJugador(String nombre){
        for (Jugador jugador : this.jugadores){
            if (jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
     }

     @Override
    public void agregarJugador (String nombre) throws JuegoException ,RemoteException{
        if (nombre.isEmpty()){
            throw new JuegoException("El nombre ingresado es invalido", TipoException.JUGADOR_INVALIDO);
        }
        else if (this.jugadores.size() == 4){
            throw  new JuegoException("No se pueden agregar mas jugadores",TipoException.JUGADOR_INVALIDO);
        }
        else if(buscarJugador(nombre) == null ) {
            Jugador jugador = new Jugador(nombre);
            this.jugadores.add(jugador);
            this.notificar(Evento.JUGADOR_AGREGADO);
        }
        else {
            throw new JuegoException("El jugador ya se encuentra agregado",TipoException.JUGADOR_YA_AGREGADO);
        }
    }

    private void repartirCartas(){
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
    public void empezarJuego() throws JuegoException , RemoteException{
        if(sePuedeComenzar()) {
            this.repartirCartas();
            this.notificar(Evento.JUEGO_COMENZADO);
            this.ronda= new Ronda(new LinkedList<>(this.jugadores),this.carnaval,this.mazo,this);
            this.ronda.cambiarTurno();
        }
        else {
            throw new JuegoException("Fatan jugadores",TipoException.FALTAN_JUGADORES);
        }
    }


    @Override
    public void tirarCarta(int cartaElegida) throws JuegoException,RemoteException {
        this.ronda.tirarCarta(cartaElegida);
    }

    @Override
    public void analizarCartasCarnaval (int[] cartasElegidas) throws JuegoException,RemoteException{
           this.ronda.analizarCartasCarnaval(cartasElegidas);
    }

    @Override
    public void finalizarTurno()throws JuegoException, RemoteException{
        this.ronda.finTurno();
    }


    public void finJuego(){
        agregarCartasEnManoAlArea();
        this.calcularPuntos();
        Serializador serializador= new Serializador("TablaTop");
        try {
            serializador.persistir(this.tablaTop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.notificar(Evento.FIN_JUEGO);
    }

    private void agregarCartasEnManoAlArea() {
        for (Jugador jugador : this.jugadores) {
            List<Carta> cartas = jugador.sacarCartasEnMano();
            this.notificar(Evento.CARTA_DESCARTADA);
            for (Carta carta : cartas ){
                jugador.agregarCartaAlAreaDeJuego(carta);
            }
            this.notificar(Evento.CARTA_AGREGADA_AREA);
        }
    }

   private void calcularPuntos(){
        evaluarAreaDeJuego();
        for (Jugador jugador : this.jugadores){
            jugador.sumarPuntos();
        }
    }

   private void evaluarAreaDeJuego(){
        Jugador jugador_anterior= this.jugadores.getFirst();
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
                else if (jugador_anterior.getArea().getCantidadDeCartasPorColor(color) != 0 && jugadoresConMasCartas.isEmpty()){
                         jugadoresConMasCartas.add(jugador_anterior);
                }
            }
            for (Jugador jugador : jugadoresConMasCartas){
                jugador.getArea().ponerCartasBocaAbajo(color);
            }

        }
    }

    @Override
    public Jugador definirGanador()throws RemoteException{
        List<Jugador> jugadoresConMenosPuntos= new ArrayList<>();
        Jugador jugador_anterior= this.jugadores.getFirst();
        for (Jugador jugador : this.jugadores){
            if (jugador.getPuntos() > jugador_anterior.getPuntos()){
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
        jugador_anterior.sumarVictoria();
        tablaTop.agregarJugador(jugador_anterior);
        return jugador_anterior;
        // TODO falta considerar el caso en que sea un empate TOTAL
    }

    @Override
    public boolean sePuedeComenzar()throws RemoteException{
        return this.jugadores.size() > 1;
    }


    @Override
    public Collection<List<String>> listarCartasArea(String nombreJugador)throws RemoteException {
        Collection<List<String>> resultado= new ArrayList<>();
        Jugador jugador= buscarJugador(nombreJugador);
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
    public List<IJugador> listarJugadores()throws RemoteException{
        List<IJugador> jugadores = new ArrayList<>();
        jugadores.addAll(this.jugadores);
        return jugadores;
    }

    @Override
    public IJugador getJugadorTurno() throws RemoteException{
        return this.ronda.getJugadorTurno();
    }

    @Override
    public List<String> listarCartasCarnaval()throws RemoteException{
        List<String> resultado= new ArrayList<>();
        for (Carta carta : this.carnaval.getCartas()){
            resultado.add(carta.toString());
        }
        return resultado;
    }

    @Override
    public List<String> listarCartasEnMano(String nombre) throws RemoteException{
        Jugador jugador= this.buscarJugador(nombre);
        List<String> resultado= new ArrayList<>();
        for (Carta carta : jugador.getCartas()){
            resultado.add(carta.toString());
        }
        return resultado;
    }


    public void notificar(Evento evento) {
        try {
            notificarObservadores(evento);
            this.ultimoEvento=evento;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }
}


