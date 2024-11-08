package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.vista.IVista;
import ar.unlu.edu.mvc.vista.VistaConsola;
import ar.unlu.edu.mvc.modelo.Evento;

import java.util.List;

public class Controlador implements Observador {
    private IVista vista;
    private IJugador jugador;
    private IJuego juego;

    public Controlador(IJuego juego, IVista vista) {
        vista.registrarControlador(this);
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void actualizar(Evento evento) {
        switch (evento){
            case JUGADOR_AGREGADO:
                List<IJugador> jugadores= juego.listarJugadores();
                this.vista.mostrarJugadores(jugadores);
                break;
            case CAMBIO_TURNO:
                IJugador jugadorTurno= this.juego.getJugadorTurno();
                if (jugadorTurno.equalsNombre(this.jugador)){
                    int cartaBajada= this.vista.pedirCarta();
                    //la vista tendria que mostrar la carta elegida al lado del carnaval
                    int[] cartasElegidasCarnaval=this.vista.elegirCartasCarnaval();
                    this.juego.jugarCarta(cartaBajada,cartasElegidasCarnaval);
                }
                else{
                    //exception?
                }
            case MAL_ELEGIDO_CARNAVAL:
                this.vista.mostra
        }
    }

    public void iniciar() {
        int opcion= vista.mostrarMenuInicial();

        while (opcion != 0) {

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    this.vista.ingresarJugadores();
                case 2:
                    this.juego.empezarJuego();
                default:
                    System.out.println("Ingrese una opcion validad");
            }
            opcion= vista.mostrarMenuInicial();
        }
    }

    public void agregarJugador(String nombre){
        this.juego.agregarJugador(nombre);
    }
}
