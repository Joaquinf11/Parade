package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TablaTop implements  Serializable {
    private final List<Jugador> jugadoresTabla;

    public TablaTop () {
        this.jugadoresTabla= new ArrayList<>();
    }


    public void agregarJugador(Jugador jugador){
        if(jugadoresTabla.size() < 5 && !jugadoresTabla.contains(jugador)){
            jugadoresTabla.add(jugador);
        }
        else{
            Jugador jugadorConMenosVictorias = getJugadorConMenosVictorias();
            if (jugador.getVictorias() > jugadorConMenosVictorias.getVictorias()){
                jugadoresTabla.remove(jugadorConMenosVictorias);
                jugadoresTabla.add(jugador);
            }
        }
        jugadoresTabla.sort(Comparator.comparingInt(Jugador::getVictorias).reversed());

    }

    public Jugador getJugadorConMenosVictorias(){
        return  this.jugadoresTabla.getLast();
    }

}
