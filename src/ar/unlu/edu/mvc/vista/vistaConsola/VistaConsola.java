package ar.unlu.edu.mvc.vista.vistaConsola;

import ar.unlu.edu.mvc.controlador.ControladorConsola;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VistaConsola extends JFrame{
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
    private int[] cartasElegidas;


    private final  List<String> comandos;



    public VistaConsola(){
        comandos= new ArrayList<>();
        comandos.add("comandos");
        comandos.add("carnaval");
        comandos.add("area");
        comandos.add("mano");
        comandos.add("salir");
        comandos.add("clear");
        comandos.add("area oponentes");
        comandos.add("como jugar");


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
                                    controlador.empezarPartida();}
                                   else{ mostrarMensaje("Faltan jugadores");}}
                    default -> { procesarComandos(entradaMenuField.getText());}

                }
                entradaMenuField.setText("");
            }
        });

        entradaIngresarJugadorField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre= entradaIngresarJugadorField.getText();
                if (comandos.contains(nombre)){
                    procesarComandos(nombre);
                }
                else{
                    if (nombre.isEmpty()){
                        mostrarMensaje("El nombre ingresado es invalido");
                    }
                    else{
                        controlador.agregarJugador(nombre); //CHEQUEAR falta ver que no este agregado
                        jugador= nombre;
                        setMenuField(); //CONTROLADOR?
                    }
                }
                entradaIngresarJugadorField.setText("");
            }
        });

        tirarCartaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = tirarCartaField.getText();
                if (comandos.contains(entrada)) {
                    procesarComandos(entrada);
                } else {
                    controlador.tirarCarta(Integer.parseInt(entrada) - 1);
                    tirarCartaField.setText("");//EXCEPTION por si ingresa uno novalido
                }
            }
        });


        elegirCartasField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada= elegirCartasField.getText();
                if (comandos.contains(entrada)) {
                    procesarComandos(entrada);
                } else if (entrada.equals("finalizar turno")) {
                    procesarComandos(entrada);
                } else {
                    String[] partes = entrada.split(" ");
                    int[] cartasElegidas = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {
                        cartasElegidas[i] = Integer.parseInt(partes[i]) - 1;  //EXCEPTION manejo de error por mala conversion o letra ingresa invalida
                    }
                    controlador.analizarCartasCarnaval(cartasElegidas);
                    elegirCartasField.setText("");
                }
            }
        });
    }

    public void iniciar(){
        this.oponentes= this.controlador.listarNombreJugadores();
        this.oponentes.remove(this.jugador);
        mostrarComoJugar();
        setMenuField();
        areaSalida.append(menuInicial());
    }

    public String menuInicial(){
        return  "MENU INCIAL\n" +
                "0-Salir \n" +
                "1-Agregar Jugador\n" +
                "2-Comenzar Partida\n";
    }

    private void setMenuField() {
        panelEntradas.removeAll();
        panelEntradas.add(entradaMenuField,BorderLayout.SOUTH);
        panelEntradas.updateUI();
    }

    public void setIngresarJugadorField(){
        panelEntradas.removeAll();
        panelEntradas.add(entradaIngresarJugadorField,BorderLayout.SOUTH);
        panelEntradas.updateUI();
    }

    public void setTirarCartaField() {
        panelEntradas.removeAll();
        panelEntradas.add(tirarCartaField,BorderLayout.SOUTH);
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

    public void procesarComandos(String comando){
        areaSalida.append(comando);
        switch (comando){
            case "salir"-> {System.exit(0);}
            case "clear" -> {areaSalida.setText(" ");}
            case "mano" -> {mostrarCartasEnMano(this.jugador);}
            case "carnaval"-> {mostrarCarnaval();}
            case "area" -> { mostrarArea(this.jugador);}
            case "area oponentes"->{mostrarAreaOponentes();}
            case "como jugar"->{ mostrarComoJugar();}
            case "finalizar turno"-> { this.controlador.finalizarTurno();}
            case "comandos"-> { mostrarMensaje("salir:cierra la consola\n" +
                                                " clear: limpia la pantalla de la consola\n" +
                                                "mano: muestra las cartas en mano\n" +
                                                "canarval: muestra las cartas del carnaval\n" +
                                                "area: muestra las cartas del area de juego\n" +
                                                "area oponentes: muestra el area de todos los oponentes" +
                                                "como jugar: explica como seleccionar las cartas" +
                                                "finalizar turno: en caso de que no puedas agarrar cartas del carnaval ingresa este comando");}
            default -> { mostrarMensaje("El comando es invalido, ingrese comandos para ver los comandos dispobiles");}
        }
    }

    public void mostrarMensaje(String mensaje) {
        areaSalida.append(mensaje + "\n");
    }

    public void mostrarMesa(String jugador) {
        mostrarCarnaval();
        mostrarCartasEnMano(jugador);

    }

    public void mostrarComoJugar(){
        mostrarMensaje("BIENVENIDO A PARADE \n" +
                        "Para seleccionar cartas de la Mano ingrese un numero del 1 al 5\n" +
                        "Para seleccionar cartas del Carnaval ingrese los numeros necesarios separados por un espacio. Ejemplo: 1 2 3 4\n" +
                        "Si necesitas ver las cartas ingresa comandos y eligi lo que necesitas ver\n"
        );
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
        areaSalida.append("AREA DE JUEGO DE " + jugador + "\n");
        Collection<List<String>> cartasArea= this.controlador.listarCartasArea(jugador);
        for (List<String> cartasColor : cartasArea){
            areaSalida.append(cartasToString(cartasColor));
        }
    }

    public void mostrarAreaOponentes(){
        for (String jugador : this.oponentes){
            mostrarArea(jugador);
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
