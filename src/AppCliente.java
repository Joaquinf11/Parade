import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.vista.vistaConsola.VistaConsola;
import ar.unlu.edu.mvc.vista.vistagrafica.paneles.VistaGrafica;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppCliente {


    public static void main(String[] args) {
        ArrayList<String> ips = Util.getIpDisponibles();
        String ip = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ips.toArray(),
                null
        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                9999
        );
        String ipServidor = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la corre el servidor", "IP del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                "127.0.0.1"
        );
        String portServidor = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                8888
        );
        String[] opciones = {"Interfaz Gráfica", "Interfaz Consola"};

        String interfaz = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la interfaz",
                "Tipo de Interfaz",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        IVista vista=null;
        if (interfaz != null) {
            if (interfaz.equals("Interfaz Gráfica")) {
                 vista = new VistaGrafica();
            } else if (interfaz.equals("Interfaz Consola")) {
                 vista = new VistaConsola();
            }
        }
        Controlador controlador = new Controlador(vista);

        Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));


        try {
             c.iniciar(controlador);
            controlador.iniciar();
        } catch (RMIMVCException | RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

}



