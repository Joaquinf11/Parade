package ar.unlu.edu.mvc.controlador;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.unlu.edu.mvc.exceptions.JuegoException;

import ar.unlu.edu.mvc.exceptions.TipoException;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.IVista;


import ar.unlu.edu.mvc.modelo.Evento;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
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
                    this.vista.jugadorAgregado(getUltimoJugadorAgregado());
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
                this.vista.mostrarCarnaval();
                break;
            case CARTA_AGREGADA_AREA:
                this.vista.mostrarAreaDeJuego(this.getNombreJugadorTurno());
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
                this.vista.comienzoUltimaRonda();
                break;
            case RONDA_DESCARTE:
                this.vista.comienzoRondaDescarte();
                break;
            case CARTA_DESCARTADA:
                this.vista.actualizarCartasEnMano();
                break;
            case FIN_JUEGO:
                this.vista.finDelJuego(this.getNombreGanadaor());
                break;

        }
    }

    private String getUltimoJugadorAgregado() {
        try {
            return this.juego.getUltimoJugadorAgregado();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
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
        catch (JuegoException e) {
            this.vista.mostrarMensaje(e.getMessage());
            if (e.getTipo() == TipoException.JUGADOR_INVALIDO){
                this.vista.mostrarMenuInicial();
            }
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
        catch (JuegoException e) {
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
        catch (JuegoException e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public void analizarCartasCarnaval(int [] elegidas){
        try {
            this.juego.analizarCartasCarnaval(elegidas);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (JuegoException e) {
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
        catch (JuegoException e) {
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

    public String getNombreGanador() {
        try {
            return this.juego.definirGanador().getNombre();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarPartida(String nombrePartida,String nombreJugador) {
        try {
                this.jugador=nombreJugador;
                this.juego= this.juego.cargarPartida(nombrePartida);
                this.juego.agregarObservador(this);
                this.juego.agregarJugador(nombreJugador);
                this.vista.partidaCargada();
                this.juego.notificarUltimoEvento();
            } catch (RemoteException e) {
            throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (JuegoException e) {
                if (!e.getTipo().equals(TipoException.JUGADOR_YA_AGREGADO)) {
                    this.vista.mostrarMensaje(e.getMessage());
                }

            }
    }

    public void guardarPartida(String nombrePartida)
    {
        try {
            this.juego.guardarPartida(nombrePartida);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

    public void nuevaPartida() {
        try {
            this.juego.nuevaPartida();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (JuegoException e) {
            this.vista.mostrarMensaje(e.getMessage());
        }
    }

    public List<String> listarCartasAreaDadasVuelta(String nombreJugador) {
        try {
             return this.juego.listarCartasAreaDadasVuelta(nombreJugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public List<IJugador> getJugadoresTabla() {
        try {
            return this.juego.getJugadoresTabla();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
