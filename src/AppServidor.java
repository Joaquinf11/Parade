import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.servidor.Servidor;
import ar.unlu.edu.mvc.interfaces.IJuego;
import ar.unlu.edu.mvc.modelo.Juego;
import ar.unlu.edu.mvc.persistencia.RepositorioJuego;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppServidor {
//    public static void main(String[] args) {
//        RepositorioJuego repo = new RepositorioJuego("juego.dat");
//        try {
//            IJuego juego = repo.recuperar();
//            if(juego == null){
//                juego = new Juego();
//            }
//            IJuego finalJuego = juego;
//            juego.agregarObservador(new IObservadorRemoto() {
//                @Override
//                public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {
//                    try {
//                        repo.persistir(finalJuego);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//            ar.edu.unlu.rmimvc.servidor.Servidor servidor = new ar.edu.unlu.rmimvc.servidor.Servidor("127.0.0.1", 20000);
//            servidor.iniciar(juego);
//        } catch (RemoteException e) {
//            throw new RuntimeException(e);
//        } catch (RMIMVCException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    public static void main(String[] args) {
        ArrayList<String> ips = Util.getIpDisponibles();
        String ip = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ips.toArray(),
                null
        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                8888
        );
        IJuego juego = new Juego();
        Servidor servidor = new Servidor(ip, Integer.parseInt(port));
        try {
            servidor.iniciar(juego);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RMIMVCException e) {
            e.printStackTrace();
        }
    }


}
