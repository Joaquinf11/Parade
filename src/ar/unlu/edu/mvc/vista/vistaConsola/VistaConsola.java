package ar.unlu.edu.mvc.vista.vistaConsola;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.interfaces.IJugador;
import ar.unlu.edu.mvc.interfaces.IVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VistaConsola extends JFrame implements IVista {
    private Controlador controlador;
    private String jugador;
    private List<String> oponentes;

    private JPanel panelPrincipal;
    private JScrollPane panelSalida;
    private JTextArea areaSalida;

    private JPanel panelEntradas;
    private JTextField entradaField;

    private ActionListener actionMenuInicial;
    private ActionListener actionIngresarJugador;
    private ActionListener actionElegirCartas;
    private ActionListener actionTirarCarta;


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



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PARADE");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        areaSalida.setEditable(false);
        setContentPane(panelPrincipal);
        setVisible(true);



        this.actionMenuInicial=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (entradaField.getText()){
                    case "0" ->{controlador.removeJugador(jugador); dispose();}
                    case "1" -> { mostrarMensaje("Ingrese el nombre del Jugador ");
                                  sacarTodosLosAction();
                                  entradaField.addActionListener(actionIngresarJugador);}
                    case "2" -> {controlador.empezarPartida();}
                    default -> { procesarComandos(entradaField.getText());}

                }
                entradaField.setText("");
            }
        };

        this.actionIngresarJugador = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre= entradaField.getText();
                entradaField.setText("");
                if (comandos.contains(nombre)){
                    procesarComandos(nombre);
                }
                else{
                        mostrarMensaje(nombre);
                        jugador= nombre;
                        controlador.agregarJugador(nombre);
                        mostrarMensaje(menuInicial());
                        setActionMenuInicial();
                }
            }
        };

       this.actionTirarCarta=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = entradaField.getText();
                if (comandos.contains(entrada)) {
                    procesarComandos(entrada);}
                else if (entrada.equals("finalizar turno")) {
                    procesarComandos(entrada);
                }
                 else {
                     mostrarMensaje(entrada);
                     convertirCartaElegidaAInteger(entrada);
                }
                 entradaField.setText("");
            }
        };


        this.actionElegirCartas=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada= entradaField.getText();
                entradaField.setText("");
                if (comandos.contains(entrada)) {
                    procesarComandos(entrada);
                } else if (entrada.equals("finalizar turno")) {
                    procesarComandos(entrada);
                } else {
                    mostrarMensaje(entrada);
                    convertirCartasElegidasAInteger(entrada);
                }
            }
        };
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
            this.controlador.jugarCarta(cartaElegida);
        }
        catch (NumberFormatException e){
            procesarComandos(entrada);
        }
    }

    public void iniciar(){
        mostrarComoJugar();
        mostrarMenuInicial();
    }

    @Override
    public void jugadorAgregado(String nombre) {
        if (nombre.equals(this.jugador)) {
            mostrarMensaje("Jugador agregado con exito");
            sacarTodosLosAction();
            entradaField.addActionListener(this.actionMenuInicial);
        }
    }

    private void sacarTodosLosAction(){
        ActionListener[] listeners = entradaField.getActionListeners();
        for (ActionListener listener : listeners) {
            entradaField.removeActionListener(listener);
        }
    }

    @Override
    public void mostrarPuntos(String nombreGanadaor) {
        comandos.add("tabla");
        List<IJugador> jugadores = this.controlador.listarJugadores();

        for (IJugador jugador : jugadores){
            mostrarMensaje(jugador.getNombre() + " tiene " + jugador.getPuntos() + " puntos") ;
        }
        mostrarMensaje("El ganador es " + nombreGanadaor);
    }

    @Override
    public void actualizarCartasEnMano() {
        mostrarCartasEnMano();
    }

    @Override
    public void comienzoRondaDescarte() {
        actionElegirCartas=null; //TODO CHEQUEAR
        mostrarMensaje("Comienza la RONDA DESCARTE");

    }

    @Override
    public void comienzoUltimaRonda() {
        mostrarMensaje("Comienza la Ultima Ronda");
    }

    @Override
    public void finDelJuego(String nombreGanadaor) {
        mostrarPuntos(nombreGanadaor);
    }

    @Override
    public void partidaCargada() {
        iniciarJuego();
    }

    @Override
    public void mostrarMenuInicial() {
        setActionMenuInicial();
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

    private void setActionMenuInicial() {
       sacarTodosLosAction();
       entradaField.addActionListener(actionMenuInicial);
    }

    public void setActionTirarCartaField() {
        sacarTodosLosAction();
        entradaField.addActionListener(actionTirarCarta);
    }

    public void setActionElegirCarta() {
        sacarTodosLosAction();
        entradaField.addActionListener(actionElegirCartas);
    }

    public void setControlador(Controlador controlador) {
        this.controlador=controlador;
    }

    public void procesarComandos(String comando){
        areaSalida.append(comando + "\n");
        switch (comando){
            case "salir"-> {this.controlador.removeJugador(this.jugador); dispose();}
            case "clear" -> {areaSalida.setText(" ");}
            case "mano" -> {mostrarCartasEnMano();}
            case "carnaval"-> {mostrarCarnaval();}
            case "area" -> { mostrarArea(this.jugador);}
            case "area oponentes"->{mostrarAreaOponentes();}
            case "como jugar"->{ mostrarComoJugar();}
            case "finalizar turno"-> { this.controlador.finalizarTurno();}
            case "mazo" ->{ mostrarMensaje("Cantidad de cartas del mazo: " + this.controlador.getCantidadCartasMazo());}
            case "reglas"->{ mostrarReglas();}
            case "tabla"-> { mostrarPuntos(this.controlador.getNombreGanador());}
            case "comandos"-> { mostrarMensaje("salir:cierra la consola\n" +
                                                " clear: limpia la pantalla de la consola\n" +
                                                "reglas: muestra las reglas del juego\n" +
                                                "como jugar: explica como seleccionar las cartas\n" +
                                                "mano: muestra las cartas en mano\n" +
                                                "canarval: muestra las cartas del carnaval\n" +
                                                "area: muestra las cartas del area de juego\n" +
                                                "area oponentes: muestra el area de todos los oponentes\n" +
                                                "finalizar turno: en caso de que no puedas agarrar cartas del carnaval ingresa este comando\n" +
                                                "mazo: muestra la cantidad de cartas que quedan en el mazo\n" +
                                                "tabla: muestra las puntuaciones de todos los jugadores" );}
            default -> { mostrarMensaje("El comando es invalido, ingrese comandos para ver los comandos dispobiles");}
        }
    }

    public void mostrarMensaje(String mensaje) {
        areaSalida.append(mensaje + "\n");
        areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
        areaSalida.updateUI();
    }

    @Override
    public void iniciarJuego() {
        this.oponentes= this.controlador.listarNombreJugadores();
        this.oponentes.remove(this.jugador);
        setActionTirarCartaField();
        desactivarEntrada();
        mostrarMesa();
    }

    @Override
    public void cambioDeTurno() {
            setActionTirarCartaField();
            activarEntrada();
            mostrarMesa();
    }

    @Override
    public void cartaTirada() {
        setActionElegirCarta();
        mostrarMensaje("Elegir cartas del Carnaval");
    }

    public void mostrarMesa() {
        mostrarAreaOponentes();
        mostrarArea(this.jugador);
        mostrarCarnaval();
        mostrarCartasEnMano();

    }

    public void mostrarComoJugar(){
        mostrarMensaje("BIENVENIDO A PARADE \n" +
                        "Para seleccionar cartas de la Mano ingrese un numero del 1 al 5\n" +
                        "Para seleccionar cartas del Carnaval ingrese los numeros necesarios separados por un espacio. Ejemplo: 1 2 3 4\n" +
                        "Para terminar tu turno ingresa finalizar turno\n" +
                        "Ingresa comandos para ver los comandos disponibles\n"
        );
    }

    @Override
    public void mostrarCarnaval(){
        areaSalida.append("CARNAVAL \n");
        List<String> cartasCarnaval= this.controlador.listarCartasCarnaval();
        areaSalida.append(cartasToString(cartasCarnaval));
        areaSalida.updateUI();
    }

    @Override
    public void cartaAgregadaCarnaval() {
        mostrarCarnaval();
    }

    @Override
    public void mostrarAreaDeJuego(String nombre) {
        mostrarArea(nombre);
    }



    @Override
    public void actualizarCantidadCartasMazo() {
        mostrarMensaje("Cantidad de cartas del mazo: " + this.controlador.getCantidadCartasMazo());
    }

    @Override
    public void finDeTurno() {
        desactivarEntrada();
    }

    public void mostrarCartasEnMano(){

        areaSalida.append("CARTAS EN MANO \n");
        List<String> cartasMano=this.controlador.listarCartasEnMano();
        areaSalida.append(cartasToString(cartasMano));
        areaSalida.updateUI();
    }

    public void mostrarArea(String jugador) {
        Collection<List<String>> cartasArea= this.controlador.listarCartasArea(jugador);
        if (!cartasArea.isEmpty()) {
            String mensaje= "AREA DE JUEGO ";
            if (!jugador.equals(this.jugador)) {
                mensaje +=   jugador ;
            }
            mostrarMensaje(mensaje);
            for (List<String> cartasColor : cartasArea) {
                areaSalida.append(cartasToString(cartasColor));
            }
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
        mostrarMensaje("""
                OBJETIVO DEL JUEGO
                Los jugadores deberán jugar sus cartas de la mano mientras intentan no quedarse cartas del carnaval, pues estas representan puntos negativos.
                Se reparten 5 a cada jugador. Luego se ponen 6 cartas más en el centro de la mesa que representan el carnaval. El principio del carnaval comienza a la izquierda mientras que el final es a la derecha.
                SECUENCIA DEL JUEGO
                
                1. Escoger una carta de su mano y colocarla al final del carnaval;
                2. Si se da el caso, recoger cartas del carnaval y colocarlas en su area de juego;
                3. Robar una carta del mazo.
                 Después, el turno pasa al siguiente jugador que realizará las mismas acciones.
                
                ACCIONES DEL JUEGO
                1.  La carta tirada no se cuenta a la hora de contar el número de cartas en la siguiente acción.
                2. Dependiendo de la carta jugada, algunas cartas serán retiradas del carnaval.
                \t -Si el número de cartas del carnaval es menor o igual al valor numérico de la carta jugada, no se retirará ninguna carta del carnaval.
                \t -Si el número de cartas del carnaval es mayor que el valor de la carta jugada, entonces algunas cartas pueden abandonarlo.
                Para determinar que cartas tienen que ser retiradas, deberán contarse las cartas desde el final del carnaval y hacia el principio, sin tener en cuenta la carta recién jugada.
                Toda carta a partir de esa posición (hasta donde hemos contado) será susceptible de ser retirada.
                Para saber que cartas de las susceptibles de ser retiradas, lo serán efectivamente, se siguen las siguientes reglas:
                \t • Todas las cartas con el mismo color que la carta jugada;
                \t • Todas las cartas de valor igual o inferior al de la carta jugada (véase ejemplo más adelante).
                Las cartas retiradas se disponen en el area de juego del jugador.
                3. El jugador roba una carta del mazo, volviendo a tener 5 cartas en mano.
                
                ULTIMA RONDA
                La última ronda empieza cuando un jugador tiene en su area de juego cartas de los 6 colores presentes en el juego o bien cuando se roba la última carta del mazo.
                Sin embargo, en esta última ronda, los jugadores no robarán carta del mazo (quedándose así con 4 cartas en la mano).
                Tras esta última ronda, comienza la ronda de descarte.
                
                RONDA DESCARTE
                Cada jugador escoge 2 cartas de su mano y las descarta. Las 2 cartas restantes, se colocan en su area de juego\s
                
                PUNTUACION
                Las cartas que aún formen parte del carnaval serán descartadas.
                1. Determinar quien tiene la mayoría de cada color. El jugador o jugadores con la mayoría de cartas de un color, girarán esas cartas y cada una las contará solo como 1 punto.
                2. Después, cada jugador sumará los valores del resto de cartas que tengan boca arriba. Sumar este total con el de las cartas sumadas en el punto anterior.
                3. El ganador sera quien tenga menos puntos negativos acumulados.
                4. En caso de empate ganara quien tenga menos cartas en su area de juego (las que estan dadas vuelta tambien se cuentan).
                """);
    }
}
