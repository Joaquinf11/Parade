package ar.unlu.edu.mvc;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.vista.vistagrafica.paneles.VistaGrafica;

public class Main {


    public static void main(String[] args){
        Juego juego = new Juego();

        IVista vista1= new VistaGrafica();
        IVista vista2= new VistaGrafica();
        IVista vista3= new VistaGrafica();
        //IVista vista4= new VistaGrafica();


        ControladorGrafico controladorGrafico1 = new ControladorGrafico(juego,vista1);
        ControladorGrafico controladorGrafico2 = new ControladorGrafico(juego,vista2);
       ControladorGrafico controladorGrafico3 = new ControladorGrafico(juego,vista3);
     //   ControladorGrafico controladorGrafico4 = new ControladorGrafico(juego,vista4);

        juego.agregarObservador(controladorGrafico1);
        juego.agregarObservador(controladorGrafico2);
       juego.agregarObservador(controladorGrafico3);
       // juego.agregarObservador(controladorGrafico4);

        controladorGrafico1.iniciar();
        controladorGrafico2.iniciar();
       controladorGrafico3.iniciar();
     //   controladorGrafico4.iniciar();
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
