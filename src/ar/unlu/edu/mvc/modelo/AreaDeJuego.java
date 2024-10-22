package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaDeJuego {

    private Map<Color,List<Carta>> cartasPorColor ;


    public AreaDeJuego(){
        this.cartasPorColor= new HashMap<>();
    }

    public void agregarCarta(Carta carta){
        Color color = carta.getColor();
        cartasPorColor.putIfAbsent(color, new ArrayList<>());  // Crea la lista si no existe
        cartasPorColor.get(color).add(carta);
    }



}
