package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.controlador.Controlador;

public interface IVista {
    void setControlador(Controlador controlador);
    void mostrarMensaje(String mensaje);

    void iniciarJuego();



    void cambioDeTurno();

    void cartaTirada();

    void mostrarCarnaval();

    void cartaAgregadaCarnaval();

    void mostrarAreaDeJuego();

    void mostrarAreaDeJuegoOponente(String nombreJugadorTurno);

    void actualizarCantidadCartasMazo();

    void finDeTurno();

    void iniciar();

    void jugadorAgregado(String jugador);

    void mostrarPuntos(String nombreGanadaor);

    void actualizarCartasEnMano();

    void comienzoRondaDescarte();

    void comienzoUltimaRonda();

    void finDelJuego(String nombreGanadaor);

    void partidaCargada();

    void mostrarMenuInicial();
}
