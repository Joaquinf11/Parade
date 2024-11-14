package ar.unlu.edu.mvc.vista.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.vista.botones.CartaButton;
import ar.unlu.edu.mvc.vista.botones.LabelVertical;

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
    private JPanel panelCentro;

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
    private JPanel panelCartasNombre;
    private JPanel panelNombre2;
    private JLabel nombre2Label;
    private JPanel panelCartasNombre3;
    private JPanel panelNombre3;
    private LabelVertical nombre3Label;
    private JPanel panelCartasNombre4;
    private JPanel panelNombre4;
    private JButton carta1B4;
    private JButton carta2B4;
    private JButton carta3B4;
    private JButton carta4B4;
    private JButton carta0B4;
    private JButton carta0B3;
    private JButton carta1B3;
    private JButton carta2B3;
    private JButton carta3B3;
    private JButton carta4B3;
    private JButton carta0B2;
    private JButton carta1B2;
    private JButton carta2B2;
    private JButton carta3B2;
    private JButton carta4B2;
    private LabelVertical nombre4Label;

    private List<CartaButton> cartasCarnaval;
    private List<CartaButton> cartasEnMano;
    private int [] cartasElegidasCarnaval;
    private int cartaElegidaMano;

    private List<String> oponentes;


    public VentanaJuego(Controlador controlador,VistaGrafica grafica){
        this.controlador= controlador;
        this.vista= grafica;
        this.cartasCarnaval= new ArrayList<>();
        this.cartasEnMano= new ArrayList<>();
        this.cartasElegidasCarnaval= new int[1];
        this.cartasElegidasCarnaval[0]= -1;

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
                cartasElegidasCarnaval[0]=-1;    //CONTROLADOR?

            }
        });
        analizarCartasButton.setEnabled(false);
    }

    public void iniciarVentanaJuego()  {

        this.oponentes =this.controlador.listarNombreJugadores();
        this.oponentes.remove(vista.getNombreJugador());
        ImageIcon imageDorsoHorizontal= new ImageIcon("imagenes/Carta,dorsoHorizontal.jpg");
        imageDorsoHorizontal.setImage(imageDorsoHorizontal.getImage().getScaledInstance(85,60,Image.SCALE_SMOOTH));
        for (int i = 0; i < oponentes.size(); i++) {
            String nombre = oponentes.get(i);
           if (!nombre.equals(vista.getNombreJugador())){
               if (i==0){
                   nombre2Label.setText(nombre);
                   ImageIcon image= new ImageIcon("imagenes/Carta,dorso.jpg");
                   image.setImage(image.getImage().getScaledInstance(60,85,Image.SCALE_SMOOTH));
                   carta0B2.setIcon(image);
                   carta1B2.setIcon(image);
                   carta2B2.setIcon(image);
                   carta3B2.setIcon(image);
                   carta4B2.setIcon(image);
                   panelJugador2.setVisible(true);
               }
               else if (i==1) {
                   nombre3Label = new LabelVertical(nombre);
                   panelCartasNombre3.add(nombre3Label, BorderLayout.EAST);
                   carta0B3.setIcon(imageDorsoHorizontal);
                   carta1B3.setIcon(imageDorsoHorizontal);
                   carta2B3.setIcon(imageDorsoHorizontal);
                   carta3B3.setIcon(imageDorsoHorizontal);
                   carta4B3.setIcon(imageDorsoHorizontal);
                   panelJugador3.setVisible(true);
               }
               else if (i==2) {
                   nombre4Label= new LabelVertical(nombre);
                   panelCartasNombre4.add(nombre4Label, BorderLayout.WEST);
                   carta0B4.setIcon(imageDorsoHorizontal);
                   carta1B4.setIcon(imageDorsoHorizontal);
                   carta2B4.setIcon(imageDorsoHorizontal);
                   carta3B4.setIcon(imageDorsoHorizontal);
                   carta4B4.setIcon(imageDorsoHorizontal);
                   panelJugador4.setVisible(true);
               }
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
                    if (cartasElegidasCarnaval[0] == -1){
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
                    if (cartasElegidasCarnaval[0] == - 1) {
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

    public void desactivarUltimaCartaCarnaval() {
        cartasCarnaval.getLast().setEnabled(false);
    }
}
