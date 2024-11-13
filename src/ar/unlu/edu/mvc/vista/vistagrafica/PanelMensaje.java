package ar.unlu.edu.mvc.vista.vistagrafica;

import javax.swing.*;
import java.awt.*;


public class PanelMensaje extends JPanel{
    JLabel label;

    public PanelMensaje(){
       setLayout( new GridBagLayout());;
       this.label= new JLabel();

       this.label.setFont(new Font("Ravie",Font.ITALIC,20));
       this.label.setForeground(new Color(201,217,5));
       add(this.label);
       setBackground(new Color(199,86,195));
       // setBackground(Color.BLACK);
    }

    public void setMensaje(String texto){
        this.label.setText(texto);
    }
}
