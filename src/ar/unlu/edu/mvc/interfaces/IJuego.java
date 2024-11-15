package ar.unlu.edu.mvc.interfaces;

import java.util.List;
import java.util.Collection;

public interface IJuego {
    IJugador getJugadorTurno();

    void agregarJugador(String nombre);
    List<IJugador> listarJugadores();

    void empezarJuego();

    void tirarCarta(int indice);

    void analizarCartasCarnaval(int[] indices);
    List<String> listarCartasCarnaval();
    List<String> listarCartasEnMano(String nombre);
    boolean sePuedeComenzar();
    int getCantidadJugadores();

    Collection<List<String>> listarCartasArea(String nombreJugador);
}
