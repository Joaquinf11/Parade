package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.controlador.Controlador;


public interface IVista {
   // void mostrarJugadores (List<IJugador> jugadores); //CHEQUEAR SI DESPUES LO VAS A USAR

    void setControlador(Controlador controlador);


    void iniciarVentana();
    void mostrarMenuInicial();

    void mostrarVentanaJuego();


    void iniciarVentanaJuego() ;

    void mostrarMensaje(String texto);

    void activarCartas();

    void activarCartasMano();

    void activarCartasCarnaval();

    void desactivarCartasMano();

    void actualizarCarnaval();

    void desactivarCartasCarnaval();

    void actualizarCartasEnMano();

    void desactivarTodosLosBotones();

    void desactivarUltimaCartaCarnaval();

    void desactivarCartaManoOponente(String nombreJugadorTurno);

    void activarCartaOponente(String nombreJugadorTurno);

    void actualizarAreaDeJuego();

    void actualizarAreaDeJuegoOponente(String nombreJugadorTurno);
}
