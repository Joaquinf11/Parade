package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.exceptions.CartaException;

import java.util.List;
import java.util.Collection;

public interface IJuego {
    IJugador getJugadorTurno();

    void sacarJugador(String jugador, Observador observador);

    void agregarJugador(String nombre) throws Exception;

    List<IJugador> listarJugadores();

    void empezarJuego() throws Exception;

    void tirarCarta(int indice) throws CartaException;

    void analizarCartasCarnaval(int[] indices) throws CartaException;
    List<String> listarCartasCarnaval();
    List<String> listarCartasEnMano(String nombre);

    void finalizarTurno()throws CartaException;

    boolean sePuedeComenzar();
    int getCantidadJugadores();

    Collection<List<String>> listarCartasArea(String nombreJugador);

    IJugador definirGanador();

    int getCantidadCartasMazo();

}
