package ar.unlu.edu.mvc;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.vista.paneles.VistaGrafica;

public class Main {


    public static void main(String[] args){
        Juego juego = new Juego();

        IVista vista1= new VistaGrafica();
        IVista vista2= new VistaGrafica();
      //  IVista vista3= new VistaGrafica();
      //  IVista vista4= new VistaGrafica();


        Controlador controlador1= new Controlador(juego,vista1);
        Controlador controlador2= new Controlador(juego,vista2);
     //   Controlador controlador3= new Controlador(juego,vista3);
      //  Controlador controlador4= new Controlador(juego,vista4);

        juego.agregarObservador(controlador1);
        juego.agregarObservador(controlador2);
      //  juego.agregarObservador(controlador3);
      //  juego.agregarObservador(controlador4);

        controlador1.iniciar();
        controlador2.iniciar();
      // controlador3.iniciar();
      //  controlador4.iniciar();
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
