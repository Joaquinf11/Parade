package ar.unlu.edu.mvc.vista;

import ar.unlu.edu.mvc.controlador.Controlador;

public interface IVista {
    void setControlador(Controlador controlador);

    void mostrarMensaje(String mensaje);

    void iniciarJuego();

    void cambioDeTurno();

    void cartaTirada();

    void mostrarCarnaval();

    void mostrarAreaDeJuego(String nombre);

    void actualizarCantidadCartasMazo();

    void finDeTurno();

    void iniciar();

    void jugadorAgregado(String jugador);

    void mostrarPuntos(String nombreGanador);

    void actualizarCartasEnMano();

    void comienzoRondaDescarte();

    void comienzoUltimaRonda();

    void finDelJuego(String nombreGanador);

    void partidaCargada();

    void mostrarMenuInicial();

    void abandonoJugador();
}
