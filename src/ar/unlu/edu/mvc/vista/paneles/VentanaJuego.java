package ar.unlu.edu.mvc.vista.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.vista.botones.CartaButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JButton carta0J3;
    private JButton carta1J3;
    private JButton carta2J3;
    private JButton carta3J3;
    private JButton carta4J3;
    private JPanel panelCentro;
    private JButton carta0J4;
    private JButton carta1J4;
    private JButton carta2J4;
    private JButton carta3J4;
    private JButton carta4J4;

    private JPanel panelBotones;
    private JButton tirarCartaButton;
    private JButton analizarCartasButton;
    private JButton button3;
    private JPanel panelAmarillo4;
    private JPanel panelVerde4;
    private JPanel panelAzul4;
    private JPanel panelRojo4;
    private JPanel panelVioleta4;
    private JPanel panelNegro4;
    private JPanel panelAmarillo2;
    private JPanel panelVerde2;
    private JPanel panelAzul2;
    private JPanel panelRojo2;
    private JPanel panelVioleta2;
    private JPanel panelNegro2;
    private JLabel nombreJugador3;
    private List<CartaButton> cartasCarnaval;
    private List<CartaButton> cartasEnMano;


    private int [] cartasElegidasCarnaval;
    private int cartaElegidaMano;



    public VentanaJuego(Controlador controlador,VistaGrafica grafica){
        this.controlador= controlador;
        this.vista= grafica;
        this.cartasCarnaval= new ArrayList<>();
        this.cartasEnMano= new ArrayList<>();
        this.cartasElegidasCarnaval= new int[1];

        tirarCartaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.jugarCarta(cartaElegidaMano);
            }
        });
        tirarCartaButton.setEnabled(false);

        analizarCartasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.analizarCartasCarnaval(cartasElegidasCarnaval);
                cartasElegidasCarnaval= new int[1];

            }
        });
        analizarCartasButton.setEnabled(false);
    }

    public void iniciarVentanaJuego()  {
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

                        cartaElegidaMano = (int) button.getClientProperty("indice");
                        button.setBorderPainted(true);


                }
            });
            this.cartasEnMano.add(button);
            this.panelCartasMano1.add(button);
        }

    }

    public void activarBotones(){
        tirarCartaButton.setEnabled(true);
        for (JButton button : this.cartasCarnaval){
            button.setEnabled(true);
        }
        for (JButton button : this.cartasEnMano){
            button.setEnabled(true);
        }
        panelCarnaval.updateUI();
        panelCartasMano1.updateUI();
    }

    public void desactivarCartasEnMano(){
        tirarCartaButton.setEnabled(false);
        for (JButton button : this.cartasEnMano){
            button.setEnabled(false);
        }

    }



    public void desactivarCartasCarnaval(){
        analizarCartasButton.setEnabled(false);
        for (JButton button : this.cartasCarnaval){
            button.setEnabled(false);
        }

    }

    public void actualizarAreaDeJuego(){

    }

    public void activarCartasMano() {
        tirarCartaButton.setEnabled(true);
        for (JButton button : this.cartasEnMano){
            button.setEnabled(true);
        }

    }

    public void activarCartasCarnaval() {
        analizarCartasButton.setEnabled(true);
        for (JButton button : this.cartasCarnaval){
            button.setEnabled(true);
        }

    }

    public void actualizarCartasCarnaval() {
        cartasCarnaval.clear();
        panelCarnaval.removeAll();
        List<String> cartasCarnavalS= this.controlador.listarCartasCarnaval();

        for(int i = 0;  i < cartasCarnavalS.size(); i++) {
            CartaButton button = new CartaButton("imagenes/cartas/" + cartasCarnavalS.get(i) + ".png", "carnaval");
            button.putClientProperty("indice", i);
            button.setEnabled(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartasElegidasCarnaval.length == 1) {
                        cartasElegidasCarnaval[0] = (int) button.getClientProperty("indice");
                    } else {
                        int[] auxiliar = new int[cartasElegidasCarnaval.length + 1];
                        for (int j = 0; j < cartasElegidasCarnaval.length; j++) {
                            auxiliar[j] = cartasElegidasCarnaval[j];
                        }
                        auxiliar[auxiliar.length - 1] = (int) button.getClientProperty("indice");
                        cartasElegidasCarnaval = auxiliar;
                    }

                }
            });

            this.cartasCarnaval.add(button);
            this.panelCarnaval.add(button);
        }
        panelCarnaval.updateUI();
    }

    public void actualizarCartasEnMano() {
        cartasEnMano.clear();
        panelCartasMano1.removeAll();
        List<String> cartasEnManoS= this.controlador.listarCartasEnMano();

        for (int i = 0; i < cartasEnManoS.size(); i++) {

            CartaButton button= new CartaButton("imagenes/cartas/" + cartasEnManoS.get(i) + ".png","mano");
            button.setEnabled(false);
            button.putClientProperty("indice",i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    cartaElegidaMano = (int) button.getClientProperty("indice");
                    button.setBorderPainted(true);


                }
            });
            this.cartasEnMano.add(button);
            this.panelCartasMano1.add(button);
        }
        panelCartasMano1.updateUI();
    }

    public void desactivarTodosLosBotones() {
        desactivarCartasCarnaval();
        desactivarCartasEnMano();
        tirarCartaButton.setEnabled(false);
        analizarCartasButton.setEnabled(false);
    }
}
