package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.exceptions.TipoException;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.modelo.Evento;
import ar.unlu.edu.mvc.vista.vistaConsola.VistaConsola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorConsola implements Observador {
    private final VistaConsola vista;
    private String jugador;
    private final IJuego juego;

    public ControladorConsola(VistaConsola vista, IJuego juego) {
        vista.setControlador(this);
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void actualizar(Evento evento) {
        switch (evento){
            case JUGADOR_AGREGADO:
                if (isTurno()){
                    this.vista.mostrarMensaje("Jugador agregado con exito");
                }
                break;
            case JUEGO_COMENZADO:
                this.vista.setTirarCartaField();
                this.vista.mostrarMesa(this.jugador);
                break;
            case CAMBIO_TURNO:
                this.vista.mostrarMensaje("Es el turno de " + getNombreJugadorTurno());
                if (isTurno()){
                    this.vista.mostrarCarnaval();
                    this.vista.setTirarCartaField();
                    this.vista.activarEntrada();
                    this.vista.mostrarCartasEnMano(getNombreJugadorTurno());
                }
                else{
                    this.vista.desactivarEntrada();
                }
                break;
            case CARTA_TIRADA:
                if (isTurno()){
                    this.vista.setElegirCartaField();
                    this.vista.mostrarMensaje("Elegir cartas del Carnaval");
                }
                else {
                    this.vista.mostrarCarnaval();
                }
                break;
            case CARTA_AGREGADA_CARNAVAL:
                if (!isTurno()) {
                    this.vista.mostrarMensaje(getNombreJugadorTurno() + "\tagrego una carta al CARNAVAL");
                }
                this.vista.mostrarCarnaval();
                break;
            case CARTA_AGREGADA_AREA:
                if (isTurno()){
                    this.vista.mostrarMensaje("Se agrego una carta a tu area de juego");
                }
                else{
                    this.vista.mostrarMensaje(getNombreJugadorTurno()+ "\tagrego una carta a su AREA DE JUEGO");
                }
                break;
            case FIN_TURNO:
                if (isTurno()){
                    this.vista.desactivarEntrada();
                }
                break;
            case ULTIMA_RONDA:
                this.vista.mostrarMensaje("Comienza la ULTIMA RONDA");
                break;
            case RONDA_DESCARTE:
                this.vista.mostrarMensaje("Comienza la Ronda Descarte");
                break;
            case FIN_JUEGO:
                this.vista.mostrarMensaje("Finalizo el juego");
                this.vista.mostrarMensaje("El ganador es " + this.getNombreGanador());
                break;
            }

    }

    private String getNombreGanador() {
        return this.juego.definirGanador().getNombre();
    }

    private boolean isTurno() {
        return this.juego.getJugadorTurno().getNombre().equals(this.jugador);
    }

    private String getNombreJugadorTurno(){
        return this.juego.getJugadorTurno().getNombre();
    }

    public List<String> listarCartasCarnaval() {
        return this.juego.listarCartasCarnaval();
    }

    public List<String> listarCartasMano(String jugador) {
        return this.juego.listarCartasEnMano(jugador);
    }

    public Collection<List<String>> listarCartasArea(String jugador) {
        return this.juego.listarCartasArea(jugador);
    }

    public void agregarJugador(String nombre) {
        try {
            this.jugador = nombre;
            this.juego.agregarJugador(nombre);
        } catch (Exception e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public void empezarPartida(){
        try {
            this.juego.empezarJuego();
        }
        catch (Exception e){
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public void tirarCarta(int carta) {
        this.juego.tirarCarta(carta);
    }

    public void analizarCartasCarnaval(int[] cartasElegidas) {
        try {
            this.juego.analizarCartasCarnaval(cartasElegidas);
        } catch (CartaException e) {
            this.vista.mostrarMensaje(e.getMessage());
            if (e.getTipo() == TipoException.CARTA_MAYORVALOR_DISTINTOCOLOR){
                this.vista.mostrarCarnaval();
            }
        }
    }

    public void iniciar() {
        this.vista.iniciar();
    }

    public List<String> listarNombreJugadores() {
        List<String> resultado= new ArrayList<>();
        List<IJugador> jugadores= this.juego.listarJugadores();
        for (IJugador jugador : jugadores){
            resultado.add(jugador.getNombre());
        }
        return resultado;
    }

    public void finalizarTurno() {
        try {
            this.juego.finalizarTurno();
        }
        catch (CartaException e){
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public int getCantidadCartasMazo() {
        return this.juego.getCantidadCartasMazo();
    }

    public void sacarJugador(String nombre){
        this.juego.sacarJugador(nombre,this);
    }
}
