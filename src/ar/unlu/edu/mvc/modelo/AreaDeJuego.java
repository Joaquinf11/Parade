package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;
import java.util.*;

public class AreaDeJuego implements Serializable {

    private final Map<Color, List<Carta>> cartas;
    private final Map<Color, Integer> cartasBocaAbajo;


    public AreaDeJuego() {
        this.cartas = new HashMap<>();
        this.cartasBocaAbajo = new HashMap<>();
    }


    public void agregarCarta(Carta carta) {
        Color color = carta.getColor();
        cartas.putIfAbsent(color, new ArrayList<>());  // Crea la lista si no existe
        cartas.get(color).add(carta);
    }

    private void agregarCartaBocaAbajo(Color color, int cantidad) {
        if (cartasBocaAbajo.containsKey(color)) {
            int cantidadVieja = cartasBocaAbajo.get(color);
            cartasBocaAbajo.replace(color, cantidad + cantidadVieja);
        } else {
            cartasBocaAbajo.put(color, cantidad);
        }
    }

    public int getCantidadDeCartasPorColor(Color color) {
        if (this.cartas.containsKey(color)) {
            List<Carta> cartas = this.cartas.get(color);
            if (cartas == null) {
                return 0;
            }
            return cartas.size();
        } else {
            return 0;
        }
    }

    public Collection<List<Carta>> getTodasLasCartas() {
        return this.cartas.values();
    }

    public int getCantidadDeCartasTotales() {
        return getCantidadCartasBocaAbajo() + getCantidadCartasBocaArriba();
    }

    public int getCantidadCartasBocaArriba() {
        int total = 0;

        for (Map.Entry<Color, List<Carta>> entry : this.cartas.entrySet()) {
            List<Carta> cartas = entry.getValue();
            total += cartas.size(); // Sumar la cantidad de cartas en cada lista
        }
        return total;
    }

    public int getCantidadCartasBocaAbajo() {
        int total = 0;

        for (Map.Entry<Color, Integer> entry : this.cartasBocaAbajo.entrySet()) {
            int cantidad = entry.getValue();
            total += cantidad;
        }
        return total;
    }


    public boolean tiene6colores() {
        return this.cartas.size() == 6;
    }

    public void ponerCartasBocaAbajo(Color color) {
        if (this.cartas.containsKey(color)) {
            int cantidad = this.cartas.get(color).size();
            this.agregarCartaBocaAbajo(color, cantidad);
            this.cartas.remove(color);
        }
    }

    public int sumarValorDeCartas() {
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

    public int calcularPuntos() {
        return (getCantidadCartasBocaAbajo() * -1) + sumarValorDeCartas();
    }

    public List<String> listarCartasDadasVuelta(){
        List<String> resultado= new ArrayList<>();
        for (Map.Entry<Color, Integer> entry : this.cartasBocaAbajo.entrySet()) {
            Color color= entry.getKey();
            int cantidad= entry.getValue();
            resultado.add(color.toString() + "," + cantidad);
        }
        return resultado;
    }
}
