package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TablaTop implements  Serializable {
    private List<Jugador> jugadoresTabla;

    public TablaTop () {
        this.jugadoresTabla= new ArrayList<>();
    }


    public void agregarJugador(Jugador jugador){
        

    }

}
