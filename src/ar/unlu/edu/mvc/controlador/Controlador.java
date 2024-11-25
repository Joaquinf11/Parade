package ar.unlu.edu.mvc.controlador;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.unlu.edu.mvc.exceptions.CartaException;

import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.IVista;


import ar.unlu.edu.mvc.modelo.Evento;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Controlador implements IControladorRemoto {
    private final IVista vista;
    private String jugador;
    private  IJuego juego;

    public Controlador(IVista vista) {
        vista.setControlador(this);
        this.vista = vista;
    }

    public <T extends IObservableRemoto> void setModeloRemoto(T t) throws RemoteException {
        this.juego = (IJuego) t;
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {
        switch ((Evento) o){
            case JUGADOR_AGREGADO:
                if (isTurno()) {
                    this.vista.jugadorAgregado();
                }
                 break;
            case JUEGO_COMENZADO:
                this.vista.iniciarJuego();
                break;
            case CAMBIO_TURNO:
               this.vista.mostrarMensaje("Es el turno de " + getNombreJugadorTurno());
               if (isTurno()) {
                   this.vista.cambioDeTurno();
               }
                break;
            case CARTA_TIRADA:
                if (isTurno()) {
                    this.vista.cartaTirada();
                }
                break;
            case CARTA_AGREGADA_CARNAVAL:
                if (isTurno()){
                    this.vista.cartaAgregadaCarnaval();
                }
                else{
                    this.vista.mostrarCarnaval();
                }
                break;
            case CARTA_AGREGADA_AREA:
                if (isTurno()){
                    this.vista.mostrarAreaDeJuego();
                }
                else {
                    this.vista.mostrarAreaDeJuegoOponente(getNombreJugadorTurno());
                }
                this.vista.mostrarCarnaval();
                break;
            case MAZO_SIN_CARTAS:
                this.vista.actualizarCantidadCartasMazo();
                break;
            case FIN_TURNO:
                if (isTurno()){
                    this.vista.finDeTurno();
                }
                this.vista.actualizarCantidadCartasMazo();
                break;
            case ULTIMA_RONDA:
                this.vista.mostrarMensaje("Comienza la ULTIMA RONDA");
                break;
            case RONDA_DESCARTE:
                this.vista.comienzoRondaDescarte();
                break;
            case CARTA_DESCARTADA:
                if (isTurno()) {
                    this.vista.actualizarCartasEnMano();
                }
                break;
            case FIN_JUEGO:
                this.vista.mostrarPuntos(this.getNombreGanadaor());

        }
    }

    private String getNombreGanadaor() {
        try {
            return this.juego.definirGanador().getNombre();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void iniciar() {
        this.vista.iniciar();
    }


    public void agregarJugador(String nombre) {
        try {
            this.jugador = nombre;
            this.juego.agregarJugador(nombre);
        }catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public boolean isTurno(){
        try {
            return this.juego.getJugadorTurno().getNombre().equals(this.jugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void empezarPartida(){
        try {
            this.juego.empezarJuego();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public List<String> listarCartasCarnaval(){
        try {
            return  this.juego.listarCartasCarnaval();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public List<IJugador> listarJugadores() {
        try {
            return this.juego.listarJugadores();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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
        try {
            return  this.juego.listarCartasEnMano(this.jugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }



    public String getNombreJugadorTurno(){
        try {
            return this.juego.getJugadorTurno().getNombre();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void jugarCarta(int cartaMano ){
        try {
            this.juego.tirarCarta(cartaMano);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (CartaException e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public void analizarCartasCarnaval(int [] elegidas){
        try {
            this.juego.analizarCartasCarnaval(elegidas);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (CartaException e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }


    public Collection<List<String>> listarCartasArea(String nombreJugador) {
        try {
            return this.juego.listarCartasArea(nombreJugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void finalizarTurno() {
        try {
            this.juego.finalizarTurno();
        }
        catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (CartaException e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }


    public int getCantidadCartasMazo() {
        try {
            return this.juego.getCantidadCartasMazo();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeJugador(String jugador) {
        try {
            this.juego.sacarJugador(jugador,this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public IJuego getJuego() {
        return this.juego;
    }
}
