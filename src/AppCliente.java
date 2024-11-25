import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.vista.vistagrafica.paneles.VistaGrafica;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppCliente {
//    public static void main(String[] args) {
//        // Vista
//        IVista vista = new VistaGrafica();
//        // Controlador
//        Controlador controlador = new Controlador(vista);
//        ar.edu.unlu.rmimvc.cliente.Cliente cliente = new ar.edu.unlu.rmimvc.cliente.Cliente(
//                "127.0.0.1",
//                Integer.parseInt(args[0]),
//                "127.0.0.1",
//                20000
//        );
//        Runtime.getRuntime().addShutdownHook(new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    controlador.getJuego().removerObservador(controlador);
//                } catch (RemoteException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        try {
//            cliente.iniciar(controlador);
//            controlador.iniciar();
//        } catch (RMIMVCException e) {
//            throw new RuntimeException(e);
//        } catch (RemoteException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
                null
        );
        String portServidor = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                8888
        );
        IVista vista = new VistaGrafica();
        Controlador controlador = new Controlador(vista);
        Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));


        try {
             c.iniciar(controlador);
            controlador.iniciar();
        } catch (RMIMVCException ex) {
            throw new RuntimeException(ex);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

}



