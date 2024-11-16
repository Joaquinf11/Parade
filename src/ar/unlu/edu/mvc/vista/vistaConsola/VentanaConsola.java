package ar.unlu.edu.mvc.vista.vistaConsola;

import ar.unlu.edu.mvc.controlador.ControladorConsola;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VentanaConsola extends JFrame{
    private ControladorConsola controlador;
    private String jugador;
    private List<String> oponentes;

    private JPanel panelPrincipal;
    private JScrollPane panelSalida;
    private JTextArea areaSalida;

    private JPanel panelEntradas;
    private JTextField entradaMenuField;
    private JTextField tirarCartaField;
    private JTextField elegirCartasField;
    private JTextField entradaIngresarJugadorField;


    private int cartaTirada;
    private boolean tiroCarta;
    private int[] cartasElegidas;
    private boolean eligioCartas;

    public VentanaConsola (){
        this.tiroCarta=false;
        this.eligioCartas=false;

        //CHEQUEAR los el panel entrada puede ir en un first las before afte, investigar
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PARADE");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        areaSalida.setEditable(false);
        setContentPane(panelPrincipal);
        setVisible(true);

        tirarCartaField= new JTextField();
        tirarCartaField.setBackground(Color.BLACK);
        tirarCartaField.setFont(new Font("Consola",Font.PLAIN,14));
        tirarCartaField.setForeground(Color.WHITE);

        elegirCartasField=new JTextField();
        elegirCartasField.setBackground(Color.BLACK);
        elegirCartasField.setFont(new Font("Consola",Font.PLAIN,14));
        elegirCartasField.setForeground(Color.WHITE);
        elegirCartasField.setOpaque(false);

        entradaIngresarJugadorField=new JTextField();
        entradaIngresarJugadorField.setBackground(Color.BLACK);
        entradaIngresarJugadorField.setFont(new Font("Consola",Font.PLAIN,14));
        entradaIngresarJugadorField.setForeground(Color.WHITE);
        entradaIngresarJugadorField.setOpaque(false);


        entradaMenuField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (entradaMenuField.getText()){
                    case "0" ->{System.exit(0); } //CHEQUEAR como hacer para sacar jguador
                    case "1" -> { mostrarMensaje("Ingrese el nombre del Jugador "); setIngresarJugadorField();}
                    case "2" -> { if (controlador.sePuedeComenzar()){ //CONTROLADOR? asi esta bien?
                                    controlador.empezarPartida();
                                    setJuegoTextField();}
                                   else{ mostrarMensaje("Faltan jugadores");}
                    }

                }
                entradaMenuField.setText("");
            }
        });

        entradaIngresarJugadorField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre= entradaIngresarJugadorField.getText();
                if (nombre.isEmpty()){
                    mostrarMensaje("El nombre ingresado es invalido");
                }
                else{
                    controlador.agregarJugador(nombre); //CHEQUEAR falta ver que no este agregado
                    jugador= nombre;
                    setMenuField(); //CONTROLADOR?
                }
                entradaIngresarJugadorField.setText("");
            }
        });

        tirarCartaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.tirarCarta(Integer.parseInt(tirarCartaField.getText()));
                tirarCartaField.setText("");//EXCEPTION por si ingresa uno novalido
            }
        });

        elegirCartasField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto= elegirCartasField.getText();
                texto= texto.trim().replaceAll("\\s","n");
                elegirCartasField.setText(texto);

            }
        });
        elegirCartasField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] partes= elegirCartasField.getText().split(",");
                int[] cartasElegidas= new int[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    cartasElegidas[i]= Integer.parseInt(partes[i]);
                }
                controlador.analizarCartasCarnaval(cartasElegidas);
                elegirCartasField.setText("");     //EXCEPTION manejo de error por mala conversion o letra ingresa invalida
            }
        });
    }
    public void iniciar(){
        setMenuField();
        areaSalida.append(menuInicial());
    }

    private void setMenuField() {
        panelEntradas.removeAll();
        panelEntradas.add(entradaMenuField,BorderLayout.SOUTH);
        panelEntradas.updateUI();
    }

    private void setJuegoTextField() {
        panelEntradas.removeAll();
        panelEntradas.add(tirarCartaField,BorderLayout.SOUTH);
        panelEntradas.updateUI();
    }

    public String menuInicial(){
        return  "0-Salir \n" +
                "1-Agregar Jugador\n" +
                "2-Comenzar Partida\n";
    }

    public void setIngresarJugadorField(){
        panelEntradas.removeAll();
        panelEntradas.add(entradaIngresarJugadorField,BorderLayout.SOUTH);
        panelEntradas.updateUI();
    }

    public void setElegirCartaField() {
        panelEntradas.removeAll();
        panelEntradas.add(elegirCartasField,BorderLayout.SOUTH);
        panelEntradas.updateUI();
    }

    public void setControlador(ControladorConsola controladorConsola) {
        this.controlador=controladorConsola;
    }


    public void mostrarMensaje(String mensaje) {
        areaSalida.append(mensaje + "\n");
    }

    public void mostrarMesa(String jugador) {
        mostrarCarnaval();
        mostrarCartasEnMano(jugador);

    }

    public void mostrarCarnaval(){
        areaSalida.append("CARNAVAL \n");
        List<String> cartasCarnaval= this.controlador.listarCartasCarnaval();
        areaSalida.append(cartasToString(cartasCarnaval));
    }

    public void mostrarCartasEnMano(String jugador){
        areaSalida.append("CARTAS EN MANO \n");
        List<String> cartasMano=this.controlador.listarCartasMano(jugador);
        areaSalida.append(cartasToString(cartasMano));
     }
    public void mostrarArea(String jugador) {
        areaSalida.append("AREA DE JUEGO \n");
        Collection<List<String>> cartasArea= this.controlador.listarCartasArea(jugador);
        for (List<String> cartasColor : cartasArea){
            areaSalida.append(cartasToString(cartasColor));
        }
    }
    public String cartasToString(List<String> cartas){
        String resultado="";
        for (String carta : cartas){
            resultado += " [ " + carta + " ] ";
        }
        resultado+= "\n";
        return resultado;
    }

    public void activarEntrada() {
        panelEntradas.setEnabled(true);
    }

    public void desactivarEntrada() {
        panelEntradas.setEnabled(false);
    }

}
