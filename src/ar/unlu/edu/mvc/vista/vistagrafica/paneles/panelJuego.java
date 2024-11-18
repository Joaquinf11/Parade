package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;
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
    ControladorGrafico controladorGrafico;
    VistaGrafica vista;

    protected JPanel panelVentanaJuego;
    private JPanel panelJugador1;
    private JPanel panelJugador2;
    private JPanel panelJugador3;
    private JPanel panelJugador4;
    private JPanel panelCarnaval;
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


    public panelJuego(ControladorGrafico controladorGrafico, VistaGrafica grafica){
        this.controladorGrafico = controladorGrafico;
        this.vista= grafica;
        this.cartasCarnaval= new ArrayList<>();
        this.cartasEnMano= new ArrayList<>();
        this.cartasElegidasCarnaval= new int[1];
        this.cartasElegidasCarnaval[0]= -1;

        tirarCartaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorGrafico.jugarCarta(cartaElegidaMano);
                finalizarTurnoButton.setEnabled(true); //CONTROLADOR?
            }
        });
        tirarCartaButton.setEnabled(false);

        analizarCartasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorGrafico.analizarCartasCarnaval(cartasElegidasCarnaval);
                cartasElegidasCarnaval= new int[1];
                cartasElegidasCarnaval[0]=-1;    //CONTROLADOR?

            }
        });
        analizarCartasButton.setEnabled(false);

        finalizarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorGrafico.finalizarTurno();
                finalizarTurnoButton.setEnabled(false);

            }
        });
    }

    public JPanel getPanel(){
        return this.panelVentanaJuego;
    }

    public void iniciarVentanaJuego()  {
        mazoButton = new CartaButton("imagenes/Carta,dorso.jpg","mano");
        setCantCartasMazo(this.controladorGrafico.getCantidadCartasMazo());
        panelMazo.add(mazoButton,BorderLayout.CENTER);


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

        this.oponentes =this.controladorGrafico.listarNombreJugadores();
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


        List<String> cartasCarnavalS= this.controladorGrafico.listarCartasCarnaval();

        for (int i = 0;  i < cartasCarnavalS.size(); i++) {
            CartaButton button= new CartaButton("imagenes/cartas/" + cartasCarnavalS.get(i) + ".png","carnaval");
            button.setEnabled(false);
            button.putClientProperty("indice",i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartasElegidasCarnaval[0] == -1){
                        cartasElegidasCarnaval[0] = (int) button.getClientProperty("indice");
                        button.setDisabledSelectedIcon(button.getIcon());
                    }
                    else {
                        int [] auxiliar = new int[cartasElegidasCarnaval.length + 1];
                        for (int j = 0; j < cartasElegidasCarnaval.length; j++) {
                            auxiliar[j]= cartasElegidasCarnaval[j];
                        }
                        auxiliar [ auxiliar.length - 1]= (int) button.getClientProperty("indice");
                        cartasElegidasCarnaval = auxiliar;
                    }
                    button.setEnabled(false);

                }
            });

            this.cartasCarnaval.add(button);
            this.panelCarnaval.add(button);
        }


        List<String> cartasEnManoS= this.controladorGrafico.listarCartasEnMano();

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

    public void actualizarAreaDeJuego() {
        panelAmarillo1.removeAll();
        panelVerde1.removeAll();
        panelRojo1.removeAll();
        panelAzul1.removeAll();
        panelNegro1.removeAll();
        panelVioleta1.removeAll();

        Collection<List<String>> cartas = this.controladorGrafico.listarCartasArea(vista.getNombreJugador());
        List<String> colores = new ArrayList<>();

      for (List<String> cartasPorColor: cartas){
          String color= cartasPorColor.getFirst().split(",")[0];
          colores.add(color);
      }

        String tipo;

        int indiceColor=0;
        for (List<String> cartasPorColor : cartas) {
            for (int i = 0; i < cartasPorColor.size(); i++) {
                if (i != cartasPorColor.size() - 1) {
                    tipo = "numero area vertical";
                } else {
                    tipo = "ultima area vertical";
                }
                CartaButton button = new CartaButton("imagenes/cartas/" + cartasPorColor.get(i) + ".png", tipo);
                switch (colores.get(indiceColor)) {
                    case "VERDE" -> panelVerde1.add(button);
                    case "ROJO" -> panelRojo1.add(button);
                    case "AMARILLO" -> panelAmarillo1.add(button);
                    case "AZUL" -> panelAzul1.add(button);
                    case "NEGRO" -> panelNegro1.add(button);
                    case "VIOLETA" -> panelVioleta1.add(button);
                }
            }
            indiceColor++;
        }
        panelArea1.setVisible(true);
        panelArea1.updateUI();
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
        List<String> cartasCarnavalS= this.controladorGrafico.listarCartasCarnaval();

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
        List<String> cartasEnManoS= this.controladorGrafico.listarCartasEnMano();

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

    public void desactivarCartaManoOponente(String oponente) {
        if (oponente.equals(nombre2Label.getText())){
            carta0B2.setVisible(false);
            panelCartasMano2.updateUI();
        } else if (oponente.equals(nombre3Label.getText())) {
            carta0B3.setVisible(false);
            panelCartasMano3.updateUI();
        }else if (oponente.equals(nombre4Label.getText())) {
            carta0B4.setVisible(false);
            panelCartasMano4.updateUI();
        }
    }

    public void activarCartaOponente(String oponente) {
        if (oponente.equals(nombre2Label.getText())){
            carta0B2.setVisible(true);
            panelCartasMano2.updateUI();
        } else if (oponente.equals(nombre3Label.getText())) {
            carta0B3.setVisible(true);
            panelCartasMano3.updateUI();
        }else if (oponente.equals(nombre4Label.getText())) {
            carta0B4.setVisible(true);
            panelCartasMano4.updateUI();
        }
    }

    public void actualizarAreaOponente(String oponente) {
        if (oponente.equals(nombre2Label.getText())){
            panelAmarillo2.removeAll();
            panelVerde2.removeAll();
            panelRojo2.removeAll();
            panelAzul2.removeAll();
            panelNegro2.removeAll();
            panelVioleta2.removeAll();

            Collection<List<String>> cartas = this.controladorGrafico.listarCartasArea(oponente);
            List<String> colores = new ArrayList<>();

            for (List<String> cartasPorColor: cartas){
                String color= cartasPorColor.getFirst().split(",")[0];
                colores.add(color);
            }

            String tipo;

            int indiceColor=0;
            for (List<String> cartasPorColor : cartas) {
                for (int i = 0; i < cartasPorColor.size(); i++) {
                    if (i != cartasPorColor.size() - 1) {
                        tipo = "numero area vertical";
                    } else {
                        tipo = "ultima area vertical";
                    }
                    CartaButton button = new CartaButton("imagenes/cartas/" + cartasPorColor.get(i) + ".png", tipo);
                    switch (colores.get(indiceColor)) {
                        case "VERDE" -> panelVerde2.add(button);
                        case "ROJO" -> panelRojo2.add(button);
                        case "AMARILLO" -> panelAmarillo2.add(button);
                        case "AZUL" -> panelAzul2.add(button);
                        case "NEGRO" -> panelNegro2.add(button);
                        case "VIOLETA" -> panelVioleta2.add(button);
                    }
                }
                indiceColor++;
            }
            panelArea2.setVisible(true);
            panelArea2.updateUI();
        } else if (oponente.equals(nombre3Label.getText())) {
            panelAmarillo3.removeAll();
            panelVerde3.removeAll();
            panelRojo3.removeAll();
            panelAzul3.removeAll();
            panelNegro3.removeAll();
            panelVioleta3.removeAll();

            Collection<List<String>> cartas = this.controladorGrafico.listarCartasArea(oponente);
            List<String> colores = new ArrayList<>();

            for (List<String> cartasPorColor: cartas){
                String color= cartasPorColor.getFirst().split(",")[0];
                colores.add(color);
            }

            String tipo;

            int indiceColor=0;
            for (List<String> cartasPorColor : cartas) {
                for (int i = 0; i < cartasPorColor.size(); i++) {
                    if (i != cartasPorColor.size() - 1) {
                        tipo = "numero area horizontal derecha";
                    } else {
                        tipo = "ultima area horizontal derecha";
                    }
                    CartaButton button = new CartaButton("imagenes/cartas/" + cartasPorColor.get(i) + ".png", tipo);
                    switch (colores.get(indiceColor)) {
                        case "VERDE" -> panelVerde3.add(button);
                        case "ROJO" -> panelRojo3.add(button);
                        case "AMARILLO" -> panelAmarillo3.add(button);
                        case "AZUL" -> panelAzul3.add(button);
                        case "NEGRO" -> panelNegro3.add(button);
                        case "VIOLETA" -> panelVioleta3.add(button);
                    }
                }
                indiceColor++;
            }
            panelArea3.setVisible(true);
            panelArea3.updateUI();


        }else if (oponente.equals(nombre4Label.getText())) {
            panelAmarillo4.removeAll();
            panelVerde4.removeAll();
            panelRojo4.removeAll();
            panelAzul4.removeAll();
            panelNegro4.removeAll();
            panelVioleta4.removeAll();

            Collection<List<String>> cartas = this.controladorGrafico.listarCartasArea(oponente);
            List<String> colores = new ArrayList<>();

            for (List<String> cartasPorColor: cartas){
                String color= cartasPorColor.getFirst().split(",")[0];
                colores.add(color);
            }

            String tipo;

            int indiceColor=0;
            for (List<String> cartasPorColor : cartas) {
                for (int i = 0; i < cartasPorColor.size(); i++) {
                    if (i != cartasPorColor.size() - 1) {
                        tipo = "numero area horizontal izquierda";
                    } else {
                        tipo = "ultima area horizontal izquierda";
                    }
                    CartaButton button = new CartaButton("imagenes/cartas/" + cartasPorColor.get(i) + ".png", tipo);
                    switch (colores.get(indiceColor)) {
                        case "VERDE" -> panelVerde4.add(button);
                        case "ROJO" -> panelRojo4.add(button);
                        case "AMARILLO" -> panelAmarillo4.add(button);
                        case "AZUL" -> panelAzul4.add(button);
                        case "NEGRO" -> panelNegro4.add(button);
                        case "VIOLETA" -> panelVioleta4.add(button);
                    }
                }
                indiceColor++;
            }
            panelArea4.setVisible(true);
            panelArea4.updateUI();
        }
    }

    public void desactivaBotonAnalizarCartas() {
        analizarCartasButton.setEnabled(false);
    }

    public void setCantCartasMazo(int cantidad){
        cantCartasMazo.setText("Cantidad cartas: " + cantidad );
    }
}
