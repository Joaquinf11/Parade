package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.vista.IVista;
import ar.unlu.edu.mvc.modelo.Evento;

import java.util.List;

public class Controlador implements Observador {
    private IVista vista;
    private IJugador jugador;
    private IJuego juego;

    public Controlador(IJuego juego, IVista vista) {
        vista.setControlador(this);
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void actualizar(Evento evento) {
        switch (evento){
            case JUGADOR_AGREGADO:
                this.vista.mostrarMensajeJugadorAgregado();
                break;
//            case CAMBIO_TURNO:
//                IJugador jugadorTurno= this.juego.getJugadorTurno();
//                if (isTurno(jugadorTurno)){
//                    int cartaBajada= this.vista.pedirCarta();
//                    //la vista tendria que mostrar la carta elegida al lado del carnaval
//                    int[] cartasElegidasCarnaval=this.vista.elegirCartasCarnaval();
//                    this.juego.jugarCarta(cartaBajada,cartasElegidasCarnaval);
//                }
//                else{
//                    //exception?
//                }
//            case NO_SE_PUEDE_AGARRAR:

        }
    }

    public void iniciar() {
        vista.iniciarVentana();
        vista.mostrarMenuInicial();
    }


    public void agregarJugador(String nombre){
        this.juego.agregarJugador(nombre);
    }

    public boolean isTurno(IJugador jugador){
        return this.jugador.equalsNombre(jugador);
    }

    public void empezarPartida(){
        this.juego.empezarJuego();
    }
}
