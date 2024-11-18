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
    private final JTextField tirarCartaField;
    private final JTextField elegirCartasField;
    private final JTextField entradaIngresarJugadorField;

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
        comandos.add("mazo");
        comandos.add("reglas");


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
                    case "2" -> {controlador.empezarPartida();}
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
                    procesarComandos(entrada);}
                else if (entrada.equals("finalizar turno")) {
                    procesarComandos(entrada);
                }
                 else {
                     mostrarMensaje(entrada);
                     convertirCartaElegidaAInteger(entrada);
                }
                 tirarCartaField.setText("");
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
                    mostrarMensaje(entrada);
                    convertirCartasElegidasAInteger(entrada);


                }
                elegirCartasField.setText("");
            }
        });
    }

    private void convertirCartasElegidasAInteger(String entrada) {
        try{
            String[] partes = entrada.split(" ");
            int[] cartasElegidas = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                cartasElegidas[i] = Integer.parseInt(partes[i]) - 1;
            }
            controlador.analizarCartasCarnaval(cartasElegidas);
        }
        catch (NumberFormatException e){
            procesarComandos(entrada);
        }
    }

    private void convertirCartaElegidaAInteger(String entrada) {
        try{
            int cartaElegida= Integer.parseInt(entrada) - 1;
            this.controlador.tirarCarta(cartaElegida);
        }
        catch (NumberFormatException e){
            procesarComandos(entrada);
        }
    }

    public void iniciar(){

        mostrarComoJugar();
        setMenuField();
        areaSalida.append(menuInicial());
    }

    public String menuInicial(){
        return """
                MENU INCIAL
                0-Salir\s
                1-Agregar Jugador
                2-Comenzar Partida
                """;
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
        areaSalida.append(comando + "\n");
        switch (comando){
            case "salir"-> {this.controlador.sacarJugador(this.jugador); dispose();}
            case "clear" -> {areaSalida.setText(" ");}
            case "mano" -> {mostrarCartasEnMano(this.jugador);}
            case "carnaval"-> {mostrarCarnaval();}
            case "area" -> { mostrarArea(this.jugador);}
            case "area oponentes"->{mostrarAreaOponentes();}
            case "como jugar"->{ mostrarComoJugar();}
            case "finalizar turno"-> { this.controlador.finalizarTurno();}
            case "mazo" ->{ mostrarMensaje("Cantidad de cartas del mazo: " + this.controlador.getCantidadCartasMazo());}
            case "reglas"->{ mostrarReglas();}
            case "comandos"-> { mostrarMensaje("salir:cierra la consola\n" +
                                                " clear: limpia la pantalla de la consola\n" +
                                                "mano: muestra las cartas en mano\n" +
                                                "canarval: muestra las cartas del carnaval\n" +
                                                "area: muestra las cartas del area de juego\n" +
                                                "area oponentes: muestra el area de todos los oponentes\n" +
                                                "como jugar: explica como seleccionar las cartas\n" +
                                                "finalizar turno: en caso de que no puedas agarrar cartas del carnaval ingresa este comando\n" +
                                                "mazo: muestra la cantidad de cartas que quedan en el mazo\n" +
                                                "reglas: muestra las reglas del juego\n");}
            default -> { mostrarMensaje("El comando es invalido, ingrese comandos para ver los comandos dispobiles");}
        }
    }

    public void mostrarMensaje(String mensaje) {
        areaSalida.append(mensaje + "\n");
    }

    public void mostrarMesa(String jugador) {
        this.oponentes= this.controlador.listarNombreJugadores();
        this.oponentes.remove(this.jugador);
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

    public void mostrarReglas(){
        mostrarMensaje("OBJETIVO DEL JUEGO\n" +
                        "Los jugadores deberán jugar sus cartas de la mano mientras intentan no quedarse cartas del carnaval, pues estas representan puntos negativos.\n" +
                        "Se reparten 5 a cada jugador. Luego se ponen 6 cartas más en el centro de la mesa que representan el carnaval. El principio del carnaval comienza a la izquierda mientras que el final es a la derecha.\n" +
                        "SECUENCIA DEL JUEGO\n" +
                        "\n" +
                        "Durante su turno cada jugador llevará a cabo las siguientes acciones en el orden descrito:\n"  +
                        "1. Escoger una carta de su mano y colocarla al final del carnaval;\n" +
                        "2. Si se da el caso, recoger cartas del carnaval y colocarlas en su area de juego;\n" +
                        "3. Robar una carta del mazo.\n" +
                        " Después, el turno pasa al siguiente jugador que realizará las mismas acciones.\n" +
                         "\n" +
                        "ACCIONES DEL JUEGO\n" +
                    "1.  La carta tirada no se cuenta a la hora de contar el número de cartas en la siguiente acción.\n" +
                    "2. Dependiendo de la carta jugada, algunas cartas serán retiradas del carnaval.\n" +
                    "\t -Si el número de cartas del carnaval es menor o igual al valor numérico de la carta jugada, no se retirará ninguna carta del carnaval.\n" +
                    "\t -Si el número de cartas del carnaval es mayor que el valor de la carta jugada, entonces algunas cartas pueden abandonarlo.\n" +
                "Para determinar que cartas tienen que ser retiradas, deberán contarse las cartas desde el final del carnaval y hacia el principio, sin tener en cuenta la carta recién jugada.\n" +
                "Toda carta a partir de esa posición (hasta donde hemos contado) será susceptible de ser retirada.\n" +
                "Para saber que cartas de las susceptibles de ser retiradas, lo serán efectivamente, se siguen las siguientes reglas:\n" +
                "\t • Todas las cartas con el mismo color que la carta jugada;\n" +
                "\t • Todas las cartas de valor igual o inferior al de la carta jugada (véase ejemplo más adelante).\n" +
                "Las cartas retiradas se disponen en el area de juego del jugador.\n" +
               "3. El jugador roba una carta del mazo, volviendo a tener 5 cartas en mano.\n" +
                "\n" +
                "ULTIMA RONDA\n" +
                "La última ronda empieza cuando un jugador tiene en su area de juego cartas de los 6 colores presentes en el juego o bien cuando se roba la última carta del mazo.\n" +
                "Sin embargo, en esta última ronda, los jugadores no robarán carta del mazo (quedándose así con 4 cartas en la mano).\n" +
                "Tras esta última ronda, comienza la ronda de descarte.\n" +
                "\n" +
                "RONDA DESCARTE\n" +
                "Cada jugador escoge 2 cartas de su mano y las descarta. Las 2 cartas restantes, se colocan en su area de juego \n" +
                "\n" +
                "PUNTUACION\n" +
                "Las cartas que aún formen parte del carnaval serán descartadas.\n" +
                "1. Determinar quien tiene la mayoría de cada color. El jugador o jugadores con la mayoría de cartas de un color, girarán esas cartas y cada una las contará solo como 1 punto.\n" +
                "2. Después, cada jugador sumará los valores del resto de cartas que tengan boca arriba. Sumar este total con el de las cartas sumadas en el punto anterior.\n" +
                "3. El ganador sera quien tenga menos puntos negativos acumulados.\n" +
                "4. En caso de empate ganara quien tenga menos cartas en su area de juego (las que estan dadas vuelta tambien se cuentan).\n");
    }
}
