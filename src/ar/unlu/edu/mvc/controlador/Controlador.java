package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.modelo.Evento;

import java.util.List;

public class Controlador implements Observador {
    private final IVista vista;
    private String jugador;
    private final IJuego juego;

    public Controlador(IJuego juego, IVista vista) {
        vista.setControlador(this);
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void actualizar(Evento evento) {
        switch (evento){
            case JUGADOR_AGREGADO:
                if (isTurno()) {
                    this.vista.mostrarMensajeJugadorAgregado();
                    //this.vista.mostrarMensaje("Jugador agregado con exito");
                }
                 break;
            case JUEGO_COMENZADO:
                this.vista.iniciarVentanaJuego();
                break;
            case CAMBIO_TURNO:
             //   this.vista.actualizarMesa(); fijate despues como vas a hacer esto
             //   this.vista.mostrarMensaje("Es el turno de " + getNombreJugadorTurno());
               this.vista.mostrarMensajeCambioTurno();
                if (isTurno()){
                    this.vista.activarCartas();
                }
                break;

        }
    }

    public void iniciar() {
        vista.iniciarVentana();
        vista.mostrarMenuInicial();
    }


    public void agregarJugador(String nombre){
        this.jugador= nombre;
        this.juego.agregarJugador(nombre);
    }

    public boolean isTurno(){
        return this.juego.getJugadorTurno().getNombre().equals(this.jugador);
    }

    public void empezarPartida(){
        this.juego.empezarJuego();
    }

    public List<String> listarCartasCarnaval(){
        return  this.juego.listarCartasCarnaval();
    }

    public List<String> listarCartasEnMano(){
        return  this.juego.listarCartasEnMano(this.jugador);
    }

    public boolean sePuedeComenzar(){
        return this.juego.sePuedeComenzar();
    }

    public int getCantidadJugadores(){
        return this.juego.getCantidadJugadores();
    }

    public String getNombreJugadorTurno(){
        return this.juego.getJugadorTurno().getNombre();
    }

    public void jugarCarta(int cartaMano,int[] cartasCarnaval ){
        this.juego.jugarCarta(cartaMano,cartasCarnaval);
    }
}
