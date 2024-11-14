package ar.unlu.edu.mvc.controlador;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.Observador;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.modelo.Evento;

import java.io.IOException;
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
    public void actualizar(Evento evento)  {
        switch (evento){
            case JUGADOR_AGREGADO:
                if (isTurno()) {
                    this.vista.mostrarMensaje("Jugador agregado con exito");
                    //this.vista.mostrarMensaje("Jugador agregado con exito");
                }
                 break;
            case JUEGO_COMENZADO:
                this.vista.iniciarVentanaJuego();
                break;
            case CAMBIO_TURNO:
             //   this.vista.actualizarMesa(); fijate despues como vas a hacer esto
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
                    this.vista.mostrarMensaje("Seleccione cartas del Carnaval"); // no se si es necesario
                }
                break;
            case CARTA_AGREGADA_CARNAVAL:
                this.vista.actualizarCarnaval();
                if (isTurno()){
                    this.vista.activarCartasCarnaval();
                    this.vista.desactivarUltimaCartaCarnaval();
                }
                break;
            case CARTA_AGREGADA_MANO:
                if (isTurno()){
                    this.vista.actualizarCartasEnMano();
                }
                break;
            case CARTA_AGREGADA_AREA, CARTA_MAL_ELEGIDA_CARNAVAL:
                this.vista.actualizarCarnaval();
                break;
            case FIN_TURNO:
                if (isTurno()){
                    this.vista.actualizarCartasEnMano();
                    this.vista.desactivarTodosLosBotones();
                    this.vista.mostrarMensaje("finalizo tu turno");
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

    public void jugarCarta(int cartaMano ){
        this.juego.tirarCarta(cartaMano);
    }

    public void analizarCartasCarnaval(int [] elegidas){
        this.juego.analizarCartasCarnaval(elegidas);
    }
}
