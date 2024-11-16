package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.modelo.Evento;
import ar.unlu.edu.mvc.vista.vistaConsola.VentanaConsola;
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
                this.vista.mostrarMesa();
                break;
            case CAMBIO_TURNO:
                this.vista.mostrarMensaje("Es el turno de " + getNombreJugadorTurno());
                if (isTurno()){
                    this.vista.activarEntrada();
                }
                break;
            case CARTA_TIRADA:
                //muestro un mensaje de la carta tirada?
                this.vista.mostrarCarnaval();
                break;
            case CARTA_AGREGADA_CARNAVAL:
                //CHEQUEAR DEJASTE ACAC
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

    public List<String> listarCartasMano() {
        return this.juego.listarCartasEnMano(getNombreJugadorTurno());
    }
}
