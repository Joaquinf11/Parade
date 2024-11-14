package ar.unlu.edu.mvc.interfaces;

import java.util.List;

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
}
