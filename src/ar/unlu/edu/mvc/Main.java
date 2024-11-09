package ar.unlu.edu.mvc;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.vista.IVista;
import ar.unlu.edu.mvc.vista.vistagrafica.VentanaMenuInicial;
import ar.unlu.edu.mvc.vista.vistagrafica.VistaGrafica;

public class Main {


    public static void main(String[] args){
        Juego juego = new Juego();
        IVista vista= new VistaGrafica();
       Controlador controlador= new Controlador(juego,vista);

       juego.agregarObservador(controlador);
       controlador.iniciar();
    }



//    private Juego juego;
//    private VistaConsola vistaConsola;
//     private Controlador controlador;
//    public Main() {
//        this.juego = new Juego();
//        this.vistaConsola = new VistaConsola();
//        this.controlador = new Controlador(this.juego, this.vistaConsola);
//        this.juego.agregarObservador(this.controlador);
//    }
}
