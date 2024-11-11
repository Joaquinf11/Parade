package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.controlador.Controlador;


public interface IVista {
   // void mostrarJugadores (List<IJugador> jugadores);

    void setControlador(Controlador controlador);

   // int pedirCarta();

   // int [] elegirCartasCarnaval();

    void iniciarVentana();
    void mostrarMenuInicial();
    void  mostrarMensajeJugadorAgregado();
    void mostrarVentanaJuego();
    // void ingresarJugadores();
    void iniciarVentanaJuego();

    void mostrarMensajeCambioTurno();
}
