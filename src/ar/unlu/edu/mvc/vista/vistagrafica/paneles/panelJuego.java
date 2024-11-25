package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.vista.vistagrafica.botones.CartaButton;
import ar.unlu.edu.mvc.vista.vistagrafica.botones.LabelVertical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class panelJuego {
    Controlador controlador;
    VistaGrafica vista;

    protected JPanel panelVentanaJuego;
    private JPanel panelJugador1;
    private JPanel panelJugador2;
    private JPanel panelJugador3;
    private JPanel panelJugador4;
    private JPanel panelCarnaval;
    private JPanel panelCartasMano1;
    private JPanel panelArea1;
    private JPanel panelArea2;
    private JPanel panelArea3;
    private JPanel panelArea4;
    private JPanel panelCentro;

    private JPanel panelBotones;
    private JButton tirarCartaButton;
    private JButton analizarCartasButton;
    private JButton finalizarTurnoButton;
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
    private JPanel panelNombre2;
    private JLabel nombre2Label;
    private JPanel panelCartasNombre3;
    private JPanel panelNombre3;
    private LabelVertical nombre3Label;
    private JPanel panelCartasNombre4;
    private JPanel panelNombre4;
    private JPanel panelAmarillo1;
    private JPanel panelVerde1;
    private JPanel panelAzul1;
    private JPanel panelRojo1;
    private JPanel panelVioleta1;
    private JPanel panelNegro1;
    private JPanel panelAmarillo3;
    private JPanel panelVerde3;
    private JPanel panelAzul3;
    private JPanel panelRojo3;
    private JPanel panelVioleta3;
    private JPanel panelNegro3;
    private JPanel panelMazo;

    private JLabel cantCartasMazo;
    private JLabel mazoLabel;
    private LabelVertical nombre4Label;
    private CartaButton mazoButton;
    private List<CartaButton> cartasCarnaval;
    private List<CartaButton> cartasEnMano;
    private int [] cartasElegidasCarnaval;
    private int cartaElegidaMano;

    private List<String> oponentes;


    public panelJuego(Controlador controlador, VistaGrafica grafica){
        this.controlador = controlador;
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
            }
        });
        analizarCartasButton.setEnabled(false);

        finalizarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.finalizarTurno();
            }
        });
    }

    public JPanel getPanel(){
        return this.panelVentanaJuego;
    }

    public void iniciarVentanaJuego()  {
        mazoButton = new CartaButton("imagenes/Carta,dorso.jpg","dorso vertical");
        panelMazo.add(mazoButton,BorderLayout.CENTER);
        setCantCartasMazo(this.controlador.getCantidadCartasMazo());


        panelAmarillo1.setLayout(new BoxLayout(panelAmarillo1,BoxLayout.Y_AXIS));
        panelVerde1.setLayout(new BoxLayout(panelVerde1,BoxLayout.Y_AXIS));
        panelAzul1.setLayout(new BoxLayout(panelAzul1,BoxLayout.Y_AXIS));
        panelRojo1.setLayout(new BoxLayout(panelRojo1,BoxLayout.Y_AXIS));
        panelVioleta1.setLayout(new BoxLayout(panelVioleta1,BoxLayout.Y_AXIS));
        panelNegro1.setLayout(new BoxLayout(panelNegro1,BoxLayout.Y_AXIS));



        panelAmarillo2.setLayout(new BoxLayout(panelAmarillo2,BoxLayout.Y_AXIS));
        panelVerde2.setLayout(new BoxLayout(panelVerde2,BoxLayout.Y_AXIS));
        panelAzul2.setLayout(new BoxLayout(panelAzul2,BoxLayout.Y_AXIS));
        panelRojo2.setLayout(new BoxLayout(panelRojo2,BoxLayout.Y_AXIS));
        panelVioleta2.setLayout(new BoxLayout(panelVioleta2,BoxLayout.Y_AXIS));
        panelNegro2.setLayout(new BoxLayout(panelNegro2,BoxLayout.Y_AXIS));


        panelAmarillo3.setLayout(new BoxLayout(panelAmarillo3,BoxLayout.X_AXIS));
        panelVerde3.setLayout(new BoxLayout(panelVerde3,BoxLayout.X_AXIS));
        panelAzul3.setLayout(new BoxLayout(panelAzul3,BoxLayout.X_AXIS));
        panelRojo3.setLayout(new BoxLayout(panelRojo3,BoxLayout.X_AXIS));
        panelVioleta3.setLayout(new BoxLayout(panelVioleta3,BoxLayout.X_AXIS));
        panelNegro3.setLayout(new BoxLayout(panelNegro3,BoxLayout.X_AXIS));

        panelArea3.setLayout(new GridLayout(6,1));
        panelArea3.add(panelAmarillo3,0,0);
        panelArea3.add(panelVerde3,1,0);
        panelArea3.add(panelAzul3,2,0);
        panelArea3.add(panelRojo3,3,0);
        panelArea3.add(panelVioleta3,4,0);
        panelArea3.add(panelNegro3,5,0);


        panelAmarillo4.setLayout(new BoxLayout(panelAmarillo4,BoxLayout.X_AXIS));
        panelVerde4.setLayout(new BoxLayout(panelVerde4,BoxLayout.X_AXIS));
        panelAzul4.setLayout(new BoxLayout(panelAzul4,BoxLayout.X_AXIS));
        panelRojo4.setLayout(new BoxLayout(panelRojo4,BoxLayout.X_AXIS));
        panelVioleta4.setLayout(new BoxLayout(panelVioleta4,BoxLayout.X_AXIS));
        panelNegro4.setLayout(new BoxLayout(panelNegro4,BoxLayout.X_AXIS));

        panelArea4.setLayout(new GridLayout(6,1));
        panelArea4.add(panelAmarillo4,0,0);
        panelArea4.add(panelVerde4,1,0);
        panelArea4.add(panelAzul4,2,0);
        panelArea4.add(panelRojo4,3,0);
        panelArea4.add(panelVioleta4,4,0);
        panelArea4.add(panelNegro4,5,0);

        this.oponentes =this.controlador.listarNombreJugadores();
        this.oponentes.remove(vista.getNombreJugador());



    actualizarCartasCarnaval();
    actualizarCartasEnMano();

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
        this.panelCarnaval.updateUI();
        this.panelBotones.updateUI();
        this.panelVentanaJuego.updateUI();
    }

    public void actualizarCartasCarnaval() {
        cartasCarnaval.clear();
        panelCarnaval.removeAll();
        List<String> cartasCarnavalS= this.controlador.listarCartasCarnaval();

        for(int i = 0;  i < cartasCarnavalS.size(); i++) {
            CartaButton button = new CartaButton("imagenes/cartas/" + cartasCarnavalS.get(i) + ".png", "carnaval");
            button.putClientProperty("indice", i);
            button.setBorder(BorderFactory.createLineBorder(new Color(201, 217, 5), 5));
            button.setBorderPainted(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartasElegidasCarnaval[0] == - 1) {
                        cartasElegidasCarnaval[0] = (int) button.getClientProperty("indice");
                        button.setBorderPainted(true);
                    } else {
                        int nuevaCarta= (int) button.getClientProperty("indice");
                        if (arrayTieneLaCarta(nuevaCarta)){
                            sacarCartaDelArray(nuevaCarta);
                            button.setBorderPainted(false);
                        }
                        else {
                            agregarCartaAlArray(nuevaCarta);
                            button.setBorderPainted(true);
                        }

                    }

                }
            });

            this.cartasCarnaval.add(button);
            this.panelCarnaval.add(button);
        }
        panelCarnaval.updateUI();
    }

    private void agregarCartaAlArray(int nuevaCarta) {
        int[] auxiliar = new int[cartasElegidasCarnaval.length + 1];
        for (int j = 0; j < cartasElegidasCarnaval.length; j++) {
            auxiliar[j] = cartasElegidasCarnaval[j];
        }
        auxiliar[auxiliar.length - 1] = nuevaCarta;
        cartasElegidasCarnaval = auxiliar;
    }

    private void sacarCartaDelArray(int nuevaCarta) {
        if (cartasElegidasCarnaval.length == 1){
            cartasElegidasCarnaval= new int[1];
            cartasElegidasCarnaval[0]=-1;
        }
        else {
            int[] auxiliar = new int[cartasElegidasCarnaval.length - 1];
            int i = 0;
            for (int carta : this.cartasElegidasCarnaval) {
                if (carta != nuevaCarta) {
                    auxiliar[i] = carta;
                    i++;
                }
            }
            this.cartasElegidasCarnaval = auxiliar;
        }
    }

    private boolean arrayTieneLaCarta(int nuevaCarta) {
        for (int carta: this.cartasElegidasCarnaval){
            if (carta == nuevaCarta){
                return true;
            }
        }
        return false;
    }



    public void desactivarTodosLosBotones() {
        desactivarCartasCarnaval();
        desactivarCartasEnMano();
        tirarCartaButton.setEnabled(false);
        analizarCartasButton.setEnabled(false);
        finalizarTurnoButton.setEnabled(false);
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
    public void actualizarAreaDeJuego() {
        JPanel[] paneles = {panelAmarillo1, panelVerde1, panelRojo1, panelAzul1, panelNegro1, panelVioleta1, panelArea1};

        clearPaneles(paneles);

        Collection<List<String>> cartas = this.controlador.listarCartasArea(vista.getNombreJugador());
        List<String> colores = getColores(cartas);

        int indiceColor = 0;
        for (List<String> cartasPorColor : cartas) {
            for (int i = 0; i < cartasPorColor.size(); i++) {
                String tipo;
                if (i != cartasPorColor.size() - 1) {
                    tipo= "numero area";
                }
                else {
                    tipo= "ultima area";
                }
                tipo+=" vertical";
                CartaButton button = new CartaButton("imagenes/cartas/" + cartasPorColor.get(i) + ".png", tipo);

                agregarCartaAPanelColor(paneles, colores.get(indiceColor), button);
            }
            indiceColor++;
        }
        updateUIPaneles(paneles);
    }

    public void actualizarAreaOponente(String oponente) {
        JPanel[] paneles = getPanelesOponente(oponente);
        if (paneles == null) return;

        clearPaneles(paneles);

        Collection<List<String>> cartas = this.controlador.listarCartasArea(oponente);
        List<String> colores = getColores(cartas);

        int indiceColor = 0;
        for (List<String> cartasPorColor : cartas) {
            for (int i = 0; i < cartasPorColor.size(); i++) {
                String tipo;
                if (i != cartasPorColor.size() - 1) {
                    tipo= "numero area ";
                }
                else {
                    tipo= "ultima area ";
                }
                tipo+= getTipoCarta(oponente);
                CartaButton button = new CartaButton("imagenes/cartas/" + cartasPorColor.get(i) + ".png", tipo);
                agregarCartaAPanelColor(paneles, colores.get(indiceColor), button);
            }
            indiceColor++;
        }
        updateUIPaneles(paneles);
    }

    private JPanel[] getPanelesOponente(String oponente) {
        if (oponente.equals(nombre2Label.getText())) {
            return new JPanel[]{panelAmarillo2, panelVerde2, panelRojo2, panelAzul2, panelNegro2, panelVioleta2, panelArea2};
        } else if (oponente.equals(nombre3Label.getText())) {
            return new JPanel[]{panelAmarillo3, panelVerde3, panelRojo3, panelAzul3, panelNegro3, panelVioleta3, panelArea3};
        } else if (oponente.equals(nombre4Label.getText())) {
            return new JPanel[]{panelAmarillo4, panelVerde4, panelRojo4, panelAzul4, panelNegro4, panelVioleta4, panelArea4};
        }
        return null; // No se encontró el oponente.
    }

    private void clearPaneles(JPanel[] paneles) {
        for (int i = 0; i < paneles.length - 1; i++) { // Excluye el último panel (área principal).
            paneles[i].removeAll();
        }
    }

    private List<String> getColores(Collection<List<String>> cartas) {
        List<String> colores = new ArrayList<>();
        for (List<String> cartasPorColor : cartas) {
            String color = cartasPorColor.get(0).split(",")[0]; // Obtener el color de la primera carta.
            colores.add(color);
        }
        return colores;
    }

    private String getTipoCarta(String oponente) {
        if (oponente.equals(nombre2Label.getText())) return "vertical";
        if (oponente.equals(nombre3Label.getText())) return "horizontal derecha";
        if (oponente.equals(nombre4Label.getText())) return "horizontal izquierda";
        return "vertical"; // Valor predeterminado.
    }

    private void agregarCartaAPanelColor(JPanel[] paneles, String color, CartaButton button) {
        switch (color) {
            case "VERDE" -> paneles[1].add(button);
            case "ROJO" -> paneles[2].add(button);
            case "AMARILLO" -> paneles[0].add(button);
            case "AZUL" -> paneles[3].add(button);
            case "NEGRO" -> paneles[4].add(button);
            case "VIOLETA" -> paneles[5].add(button);
        }
    }

    private void updateUIPaneles(JPanel[] paneles) {
        JPanel panelPrincipal = paneles[paneles.length - 1];
        panelPrincipal.setVisible(true);
        panelPrincipal.updateUI();
    }

    public void desactivaBotonAnalizarCartas() {
        analizarCartasButton.setEnabled(false);
    }

    public void setCantCartasMazo(int cantidad){
        cantCartasMazo.setText(cantidad+"" );
    }

    public void setFinalizarButton(boolean b) {
        finalizarTurnoButton.setEnabled(b);
    }

    public void removePanelCarnaval() {
        panelCentro.removeAll();
        panelCentro.setVisible(false);
    }

    public void clearCartasElegidas() {
        cartasElegidasCarnaval= new int[1];
        cartasElegidasCarnaval[0]=-1;
    }
}
