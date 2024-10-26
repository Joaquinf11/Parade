package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.modelo.IJugador;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.observer.Observador;
import ar.unlu.edu.mvc.vista.VistaConsola;
import ar.unlu.edu.mvc.modelo.Evento;

import java.util.List;

public class Controlador implements Observador {
    private VistaConsola vista;

    private Juego juego;

    public Controlador(Juego juego, VistaConsola vista) {
        vista.registrarControlador(this);
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void notificar(Evento evento) {
        switch (evento){
            case JUGADOR_AGREGADO:
                List<IJugador> jugadores= juego.listarJugadores();
                this.vista.mostrarJugadores(jugadores);
                break;
        }
    }

    public void iniciar() {
        int opcion= vista.mostrarMenuInicial();;

        while (opcion != 0) {

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    this.vista.ingresarJugadores();
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
