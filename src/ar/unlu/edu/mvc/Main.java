package ar.unlu.edu.mvc;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.vista.VistaConsola;

public class Main {
    private Juego juego;
    private VistaConsola vistaConsola;
     private Controlador controlador;
    public Main() {
        this.juego = new Juego();
        this.vistaConsola = new VistaConsola();
        this.controlador = new Controlador(this.juego, this.vistaConsola);
        this.juego.agregarObservador(this.controlador);
    }
}
