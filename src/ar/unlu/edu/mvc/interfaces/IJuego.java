package ar.unlu.edu.mvc.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.unlu.edu.mvc.exceptions.JuegoException;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Collection;

public interface IJuego  extends IObservableRemoto, Serializable {
    IJugador getJugadorTurno() throws RemoteException;

    void sacarJugador(String jugador, IObservadorRemoto o)throws RemoteException;

    void agregarJugador(String nombre) throws JuegoException,RemoteException;

    List<IJugador> listarJugadores() throws RemoteException;

    void empezarJuego() throws JuegoException, RemoteException;

    void tirarCarta(int indice) throws JuegoException, RemoteException;

    void analizarCartasCarnaval(int[] indices) throws JuegoException, RemoteException;

    List<String> listarCartasCarnaval()throws RemoteException;

    List<String> listarCartasEnMano(String nombre)throws RemoteException;

    void finalizarTurno()throws JuegoException, RemoteException;

    boolean sePuedeComenzar() throws RemoteException;

    Collection<List<String>> listarCartasArea(String nombreJugador) throws RemoteException;

    IJugador definirGanador() throws RemoteException;

    int getCantidadCartasMazo() throws RemoteException;

    IJuego cargarPartida(String nombrePartida) throws  IOException, ClassNotFoundException;

    void guardarPartida(String nombrePartida) throws RemoteException, IOException;

    String getUltimoJugadorAgregado() throws  RemoteException;

    void notificarUltimoEvento() throws RemoteException;

    void nuevaPartida() throws RemoteException, JuegoException;
}
