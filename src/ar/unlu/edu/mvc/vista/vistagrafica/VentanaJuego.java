package ar.unlu.edu.mvc.vista.vistagrafica;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;

public class VentanaJuego {
    Controlador controlador;

    private JPanel panelJuego;
    private JPanel panelCartasEnMano;
    private JButton carta0;
    private JButton carta1;
    private JButton carta2;
    private JButton carta3;
    private JButton carta4;
    private JPanel panelMesa;
    private JPanel panelCarnaval;
    private JPanel panelAreaDeJuego;

   public void setControlador(Controlador controlador){
       this.controlador=controlador;
   }

   public void inicializar(){

   }

   public void agregarCartasEnMano(){}
}
