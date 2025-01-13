package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TablaTop implements Serializable {
    private List<Jugador> jugadores;


    public TablaTop (){
        this.jugadores= new ArrayList<>();
    }


    public void actualizarTabla(){
        List<Jugador> jugadoresJuego=this.juego.getJugadores();
        if (jugadores.isEmpty()){

        }
    }

}
