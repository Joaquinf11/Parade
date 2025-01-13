package ar.unlu.edu.mvc.persistencia;

import ar.unlu.edu.mvc.interfaces.IJuego;

import java.io.*;

public class RepositorioJuego {

    private final String nombreArchivo;

    public RepositorioJuego(String nombreArchivo){
        this.nombreArchivo=nombreArchivo;
    }

    public void persistir(IJuego juego)throws IOException{
        FileOutputStream out = new FileOutputStream(this.nombreArchivo);
        ObjectOutputStream outputStream= new ObjectOutputStream(out);
        outputStream.writeObject(juego);
        outputStream.close();
    }

    public IJuego recuperar() throws  IOException,ClassNotFoundException{
        try {
            FileInputStream in = new FileInputStream(this.nombreArchivo);
            ObjectInputStream inputStream= new ObjectInputStream(in);
            return (IJuego) inputStream.readObject();
        } catch (EOFException ex){
            return null;
        }catch (FileNotFoundException ex){
            return null;
        }
    }
}
