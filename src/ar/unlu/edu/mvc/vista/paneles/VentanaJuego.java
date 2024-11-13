package ar.unlu.edu.mvc.vista.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.vista.botones.CartaButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    private JPanel panelBotones;
    private JButton jugarCartasButton;
    private JButton button2;
    private JButton button3;
    private JLabel nombreJugador3;
    private List<CartaButton> cartasCarnaval;
    private List<CartaButton> cartasEnMano;
    private boolean cartaEnManoSeleccionada;

    private int [] cartasElegidasCarnaval;
    private int cartaElegidaMano;



    public VentanaJuego(Controlador controlador,VistaGrafica grafica){
        this.controlador= controlador;
        this.vista= grafica;
        this.cartasCarnaval= new ArrayList<>();
        this.cartasEnMano= new ArrayList<>();
        this.cartaEnManoSeleccionada= false;
        this.cartasElegidasCarnaval= new int[1];

        jugarCartasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.jugarCarta(cartaElegidaMano,cartasElegidasCarnaval);
                cartasElegidasCarnaval= new int[1];

            }
        });
        jugarCartasButton.setEnabled(false);
    }

    public void iniciarVentanaJuego(){
        panelArea1.setVisible(false);
        panelArea2.setVisible(false);
        panelArea3.setVisible(false);
        panelArea4.setVisible(false);

        int cantidadJugadores= this.controlador.getCantidadJugadores();

        if (cantidadJugadores == 2){
            panelJugador3.setVisible(false);
            panelJugador4.setVisible(false);
            for (int i = 0; i < 5; i++) {
                JButton cartaDorso= new CartaButton("imagenes/Carta,dorso.jpg","dorso vertical");
                panelCartasMano2.add(cartaDorso);
            }

        }
        else if (cantidadJugadores == 3){
            panelJugador4.setVisible(false);
            for (int i = 0; i < 5; i++) {
                JButton cartaDorso= new CartaButton("imagenes/Carta,dorso.jpg","dorso vertical");
                panelCartasMano2.add(cartaDorso);
            }
            for (int i = 0; i < 5; i++) {
                JButton cartaDorso= new CartaButton("imagenes/Carta,dorso.jpg","dorso horizontal");
                panelCartasMano3.add(cartaDorso);
            }
        }
        else{
            for (int i = 0; i < 5; i++) {
                JButton cartaDorso= new CartaButton("imagenes/Carta,dorso.jpg","dorso vertical");
                panelCartasMano2.add(cartaDorso);
            }
            for (int i = 0; i < 5; i++) {
                JButton cartaDorso= new CartaButton("imagenes/Carta,dorso.jpg","dorso horizontal");
                panelCartasMano3.add(cartaDorso);
            }
            for (int i = 0; i < 5; i++) {
                JButton cartaDorso= new CartaButton("imagenes/Carta,dorso.jpg","dorso horizontal");
                panelCartasMano4.add(cartaDorso);
            }
        }

        List<String> cartasCarnavalS= this.controlador.listarCartasCarnaval();

        for (int i = 0;  i < cartasCarnavalS.size(); i++) {
            CartaButton button= new CartaButton("imagenes/cartas/" + cartasCarnavalS.get(i) + ".png","carnaval");
            button.setEnabled(false);
            button.putClientProperty("indice",i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartasElegidasCarnaval.length == 1){
                        cartasElegidasCarnaval[0] = (int) button.getClientProperty("indice");
                    }
                    else {
                        int [] auxiliar = new int[cartasElegidasCarnaval.length + 1];
                        for (int j = 0; j < cartasElegidasCarnaval.length; j++) {
                            auxiliar[j]= cartasElegidasCarnaval[j];
                        }
                        auxiliar [ auxiliar.length - 1]= (int) button.getClientProperty("indice");
                        cartasElegidasCarnaval = auxiliar;
                    }

                }
            });

            this.cartasCarnaval.add(button);
            this.panelCarnaval.add(button);
        }


        List<String> cartasEnManoS= this.controlador.listarCartasEnMano();

        for (int i = 0; i < cartasEnManoS.size(); i++) {

            CartaButton button= new CartaButton("imagenes/cartas/" + cartasEnManoS.get(i) + ".png","mano");
            button.setEnabled(false);
            button.putClientProperty("indice",i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartaEnManoSeleccionada){
                        desactivarCartasEnMano();
                    }
                    else {
                         cartaElegidaMano = (int) button.getClientProperty("indice");
                        button.setForeground(Color.RED);
                        cartaEnManoSeleccionada=true;


                    }
                }
            });
            this.cartasEnMano.add(button);
            this.panelCartasMano1.add(button);
        }

    }

    public void activarBotones(){
        jugarCartasButton.setEnabled(true);
        for (JButton button : this.cartasCarnaval){
            button.setEnabled(true);
        }
        for (JButton button : this.cartasEnMano){
            button.setEnabled(true);
        }
    }

    public void desactivarCartasEnMano(){
        for (JButton button : this.cartasEnMano){
            button.setEnabled(false);
        }
    }



    public void desactivarCartasCarnaval(){
        for (JButton button : this.cartasCarnaval){
            button.setEnabled(false);
        }
    }
}
