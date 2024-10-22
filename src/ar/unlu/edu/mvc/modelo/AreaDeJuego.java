package ar.unlu.edu.mvc.modelo;

import java.util.*;

public class AreaDeJuego {

    private Map<Color,List<Carta>> cartas ;
    private List<Carta> cartasBocaAbajo;


    public AreaDeJuego(){
        this.cartas= new HashMap<>();
        this.cartasBocaAbajo= new ArrayList<>();
    }

    public Collection<List<Carta>> getCartas(){
        return this.cartas.values();
    }

    public void agregarCarta(Carta carta){
        Color color = carta.getColor();
        cartas.putIfAbsent(color, new ArrayList<>());  // Crea la lista si no existe
        cartas.get(color).add(carta);
    }

    public int getCantidadDeCartasPorColor(Color color){
        return this.cartas.get(color).size();
    }

    public int getCantidadCartasBocaAbajo(){
        return this.cartasBocaAbajo.size();
    }

    public Collection<List<Carta>> getTodasLasCartas(){
        return this.cartas.values();
    }

    public boolean tiene6colores(){
        return this.cartas.size() == 6;
    }

    public void ponerCartasBocaAbajo(Color color){
        this.cartasBocaAbajo.addAll(this.cartas.get(color));
        this.cartas.remove(color);
    }

    public int sumarValorDeCartas(){
        int total = 0;

        // Primer for-each: recorre el HashMap que contiene las cartas agrupadas por color
        for (Map.Entry<Color, List<Carta>> entry : this.cartas.entrySet()) {
            // Segundo for-each: recorre la lista de cartas de un color específico
            for (Carta carta : entry.getValue()) {
                total -= carta.getValor();  // Suma el valor de cada carta al total
            }
        }

        return total;  // Devuelve la suma de todas las cartas
    }
}
