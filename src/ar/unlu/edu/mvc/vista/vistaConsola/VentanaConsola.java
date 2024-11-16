package ar.unlu.edu.mvc.vista.vistaConsola;

import ar.unlu.edu.mvc.controlador.ControladorConsola;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VentanaConsola extends JFrame{
    private ControladorConsola controlador;

    private JPanel panelPrincipal;
    private JPanel panelEntrada;
    private JTextField entradaField;
    private JScrollPane panelSalida;
    private JTextArea areaSalida;


    public VentanaConsola (){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PARADE");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        areaSalida.setEditable(false);
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
        //panelSalida.setLayout(new BoxLayout(panelSalida,BoxLayout.Y_AXIS));
    }

    public String menuInicial(){
        return  "0-Salir" +
                "1-Agregar Jugador" +
                "2-Comenzar Partida";
    }


    public void setControlador(ControladorConsola controladorConsola) {
        this.controlador=controladorConsola;
    }

    public void mostrarMensaje(String mensaje) {
        areaSalida.append(mensaje);
    }

    public void mostrarMesa() {

    }

    public void mostrarCarnaval(){
        List<String> cartasCarnaval= this.controlador.listarCartasCarnaval();
        areaSalida.append(cartasToString(cartasCarnaval));
    }

    public void mostrarCartasEnMano(){
        List<String> cartasMano=this.controlador.listarCartasMano();
        areaSalida.append(cartasToString(cartasMano));
     }
    public String cartasToString(List<String> cartas){
        String resultado="";
        for (String carta : cartas){
            resultado += " [ " + carta + " ] ";
        }
        return resultado;
    }

    public void activarEntrada() {
        panelEntrada.setEnabled(true);
    }
}
