package ar.unlu.edu.mvc.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.unlu.edu.mvc.exceptions.CartaException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Collection;

public interface IJuego  extends IObservableRemoto, Serializable {
    IJugador getJugadorTurno() throws RemoteException;

    void sacarJugador(String jugador, IObservadorRemoto o)throws RemoteException;

    void agregarJugador(String nombre) throws Exception;

    List<IJugador> listarJugadores() throws RemoteException;

    void empezarJuego() throws Exception, RemoteException;

    void tirarCarta(int indice) throws CartaException , RemoteException;

    void analizarCartasCarnaval(int[] indices) throws CartaException, RemoteException;

    List<String> listarCartasCarnaval()throws RemoteException;

    List<String> listarCartasEnMano(String nombre)throws RemoteException;

    void finalizarTurno()throws CartaException , RemoteException;

    boolean sePuedeComenzar() throws RemoteException;

    Collection<List<String>> listarCartasArea(String nombreJugador) throws RemoteException;

    IJugador definirGanador() throws RemoteException;

    int getCantidadCartasMazo() throws RemoteException;

}
