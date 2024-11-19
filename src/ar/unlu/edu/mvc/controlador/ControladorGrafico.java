package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.exceptions.TipoException;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observador;

import ar.unlu.edu.mvc.modelo.Evento;
import ar.unlu.edu.mvc.vista.vistagrafica.paneles.VistaGrafica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorGrafico implements Observador {
    private final VistaGrafica vista;
    private String jugador;
    private final IJuego juego;

    public ControladorGrafico(IJuego juego, VistaGrafica vista) {
        vista.setControlador(this);
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void actualizar(Evento evento)  {
        switch (evento){
            case JUGADOR_AGREGADO:
                if (isTurno()) {
                    this.vista.mostrarMensaje("Jugador agregado con exito");
                }
                 break;
            case JUEGO_COMENZADO:
                this.vista.iniciarVentanaJuego();
                if (isTurno()){
                    this.vista.desactivarAgregarJugador();
                }
                break;
            case CAMBIO_TURNO:
               this.vista.mostrarMensaje("Es el turno de " + getNombreJugadorTurno());
                if (isTurno()){
                    this.vista.activarCartasMano();
                }
                break;
            case CARTA_TIRADA:
                if (isTurno()) {
                    this.vista.actualizarCartasEnMano();
                    this.vista.desactivarCartasMano();
                    this.vista.activarCartasCarnaval();
                    this.vista.setFinalizarTurno(true);
                }
                else{
                    this.vista.desactivarCartaManoOponente(getNombreJugadorTurno());
                }
                break;
            case CARTA_AGREGADA_CARNAVAL:
                this.vista.actualizarCarnaval();
                if (isTurno()){
                    this.vista.activarCartasCarnaval();
                    this.vista.desactivarUltimaCartaCarnaval();
                }
                break;
            case CARTA_AGREGADA_AREA:
                if (isTurno()){
                    this.vista.actualizarAreaDeJuego();
                }
                else {
                    this.vista.actualizarAreaDeJuegoOponente(getNombreJugadorTurno());
                }
                this.vista.actualizarCarnaval();
                break;
            case MAZO_SIN_CARTAS:
                this.vista.setCantidadCartasMazo(0);
                break;
            case FIN_TURNO:
                if (isTurno()){
                    this.vista.actualizarCartasEnMano();
                    this.vista.desactivarTodosLosBotones();
                    this.vista.setFinalizarTurno(false);
                }
                else{
                    this.vista.activarCartaOponente(getNombreJugadorTurno());
                }
                this.vista.setCantidadCartasMazo(this.getCantidadCartasMazo());
                break;
            case ULTIMA_RONDA:
                this.vista.mostrarMensaje("Comienza la ULTIMA RONDA");
                break;
            case RONDA_DESCARTE:
                this.vista.mostrarMensaje("Comienza la RONDA DESCARTE");
                this.vista.desactivaBotonAnalizarCartas();
                break;

            case FIN_JUEGO:
                this.vista.mostrarMensaje(this.getNombreGanadaor());

        }
    }

    private String getNombreGanadaor() {
        return this.juego.definirGanador().getNombre();
    }

    public void iniciar() {
        vista.iniciarVentana();
        vista.mostrarMenuInicial();
    }


    public void agregarJugador(String nombre) {
        try {
            this.jugador = nombre;
            this.juego.agregarJugador(nombre);
        } catch (Exception e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public boolean isTurno(){
        return this.juego.getJugadorTurno().getNombre().equals(this.jugador);
    }

    public void empezarPartida(){
        try {
            this.juego.empezarJuego();
        }
        catch (Exception e){
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public List<String> listarCartasCarnaval(){
        return  this.juego.listarCartasCarnaval();
    }
    public List<IJugador> listarJugadores() {
        return this.juego.listarJugadores();
    }
    public List<String> listarNombreJugadores() {
        List<String> resultado= new ArrayList<>();
        List<IJugador> jugadores= listarJugadores();
        for (IJugador jugador: jugadores) {
            resultado.add(jugador.getNombre());
        }
        return resultado;
    }
    public List<String> listarCartasEnMano(){
        return  this.juego.listarCartasEnMano(this.jugador);
    }



    public String getNombreJugadorTurno(){
        return this.juego.getJugadorTurno().getNombre();
    }

    public void jugarCarta(int cartaMano ){
        this.juego.tirarCarta(cartaMano);
    }

    public void analizarCartasCarnaval(int [] elegidas){
        try {
            this.juego.analizarCartasCarnaval(elegidas);
        }
        catch (CartaException e){
            if (e.getTipo() == TipoException.CARTA_MAYORVALOR_DISTINTOCOLOR){
                this.vista.actualizarCarnaval();
            }
            this.vista.mostrarMensaje(e.getMessage());
        }
    }


    public Collection<List<String>> listarCartasArea(String nombreJugador) {
       return this.juego.listarCartasArea(nombreJugador);
    }

    public void finalizarTurno() {
        try {
            this.juego.finalizarTurno();
        }
        catch (CartaException e){
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public int getCantidadCartasEnMano(){
        return this.juego.getCantidadCartasMazo();
    }

    public int getCantidadCartasMazo() {
        return this.juego.getCantidadCartasMazo();
    }

    public void removeJugador(String jugador) {
        this.juego.sacarJugador(jugador,this);

    }
}
