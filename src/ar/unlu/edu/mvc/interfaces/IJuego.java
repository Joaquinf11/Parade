package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.exceptions.CartaException;

import java.util.List;
import java.util.Collection;

public interface IJuego {
    IJugador getJugadorTurno();

    void agregarJugador(String nombre);
    List<IJugador> listarJugadores();

    void empezarJuego();

    void tirarCarta(int indice);

    void analizarCartasCarnaval(int[] indices) throws CartaException;
    List<String> listarCartasCarnaval();
    List<String> listarCartasEnMano(String nombre);

    void finalizarTurno()throws CartaException;

    boolean sePuedeComenzar();
    int getCantidadJugadores();

    Collection<List<String>> listarCartasArea(String nombreJugador);
}
