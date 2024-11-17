package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.exceptions.CartaException;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.modelo.Evento;
import ar.unlu.edu.mvc.vista.vistaConsola.VentanaConsola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.*;

public class ControladorConsola implements Observador {
    private final VentanaConsola vista;
    private String jugador;
    private final IJuego juego;

    public ControladorConsola(VentanaConsola vista, IJuego juego) {
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
                //muestro un mensaje de la carta tirada?
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
                    this.vista.mostrarArea(getNombreJugadorTurno());
                }
                else{
                    this.vista.mostrarMensaje(getNombreJugadorTurno()+ "\tagrego una carta a su AREA DE JUEGO");
                }
                break;
            case CARTA_MAL_ELEGIDA_CARNAVAL:
                if (isTurno()){
                    this.vista.mostrarMensaje("CARTA MAL ELEGIDA");//CHEQUEAR
                }
            case FIN_TURNO:
                if (isTurno()){
                    this.vista.desactivarEntrada();
                }
                break;

            }

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
        this.jugador=nombre;
        this.juego.agregarJugador(nombre);
    }

    public boolean sePuedeComenzar() {
        return this.juego.sePuedeComenzar();
    }

    public void empezarPartida(){
        this.juego.empezarJuego();
    }

    public void tirarCarta(int carta) {
        this.juego.tirarCarta(carta);
    }

    public void analizarCartasCarnaval(int[] cartasElegidas) {
        try {
            this.juego.analizarCartasCarnaval(cartasElegidas);
        } catch (CartaException e) {
            this.vista.mostrarMensaje(e.getMessage());
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
}
