package ar.unlu.edu.mvc.vista.vistagrafica;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.util.List;

public class VentanaJuego {
    Controlador controlador;
    VistaGrafica vista;

    protected JPanel panelVentanaJuego;
    private JPanel panelJugador1;
    private JPanel panelJugador2;
    private JPanel panelJugador3;
    private JPanel panelJugador4;
    private JPanel panelCarnaval;
    private JButton color0;
    private JButton color1;
    private JButton color2;
    private JButton color3;
    private JButton color4;
    private JButton color5;
    private JPanel panelCartasMano1;
    private JPanel panelArea1;
    private JPanel panelCartasMano2;
    private JPanel panelArea2;
    private JButton carta0J2;
    private JButton carta1J2;
    private JButton carta2J2;
    private JButton carta3J2;
    private JButton carta4J2;
    private JPanel panelCartasMano3;
    private JPanel panelArea3;
    private JPanel panelArea4;
    private JPanel panelCartasMano4;
    private JButton color0J2;
    private JButton color1J2;
    private JButton color2J2;
    private JButton color3J2;
    private JButton color4J2;
    private JButton color5J2;
    private JButton carta0J3;
    private JButton carta1J3;
    private JButton carta2J3;
    private JButton carta3J3;
    private JButton carta4J3;
    private JButton color0J3;
    private JButton color1J3;
    private JButton color2J3;
    private JButton color3J3;
    private JButton color4J3;
    private JButton color5J3;
    private JPanel panelCentro;
    private JButton color0J4;
    private JButton color1J4;
    private JButton color2J4;
    private JButton color3J4;
    private JButton color4J4;
    private JButton color5J4;
    private JButton carta0J4;
    private JButton carta1J4;
    private JButton carta2J4;
    private JButton carta3J4;
    private JButton carta4J4;
    private JButton carta0C;
    private JButton carta1C;
    private JButton carta2C;
    private JButton carta3C;
    private JButton carta4C;
    private JButton carta5C;

    public VentanaJuego(Controlador controlador,VistaGrafica grafica){
        this.controlador= controlador;
        this.vista= grafica;
        //esto creo que despues lo podes sacar directamente
        carta0C.setVisible(false);
        carta1C.setVisible(false);
        carta2C.setVisible(false);
        carta3C.setVisible(false);
        carta4C.setVisible(false);
        carta5C.setVisible(false);
    }

    public void iniciarVentanaJuego(){
        panelArea1.setVisible(false);
        panelArea2.setVisible(false);
        panelArea3.setVisible(false);
        panelArea4.setVisible(false);

        List<String> cartasCarnaval= this.controlador.listarCartasCarnaval();
        JButton[] buttons= new JButton[6];

        for (int i = 0; i < buttons.length && i < cartasCarnaval.size(); i++) {
            buttons[i]= new JButton(cartasCarnaval.get(i));
            panelCarnaval.add(buttons[i]);
        }

        List<String> cartasEnMano= this.controlador.listarCartasEnMano();
        buttons= new JButton[5];
        for (int i = 0; i < buttons.length && i < cartasEnMano.size(); i++) {
            buttons[i]= new JButton(cartasEnMano.get(i));
            panelCartasMano1.add(buttons[i]);
        }

    }

}
