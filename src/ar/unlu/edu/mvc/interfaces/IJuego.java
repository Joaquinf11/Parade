package ar.unlu.edu.mvc.interfaces;

import java.util.List;

public interface IJuego {
    IJugador getJugadorTurno();

    void agregarJugador(String nombre);
    List<IJugador> listarJugadores();

    void empezarJuego();

    void jugarCarta(int indice, int[] cartasElegidasCarnaval);

    List<String> listarCartasCarnaval();
    List<String> listarCartasEnMano();
    boolean sePuedeComenzar();
}
