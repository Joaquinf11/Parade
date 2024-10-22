package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaDeJuego {

    private Map<Color,List<Carta>> cartas ;


    public AreaDeJuego(){
        this.cartas= new HashMap<>();
    }

    public void agregarCarta(Carta carta){
        Color color = carta.getColor();
        cartas.putIfAbsent(color, new ArrayList<>());  // Crea la lista si no existe
        cartas.get(color).add(carta);
    }

    public boolean tiene6colores(){
        return this.cartas.size() == 6;
    }

    public void eliminarCartas(Color color){
        this.cartas.remove(color);
    }
}
