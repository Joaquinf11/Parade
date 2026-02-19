package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;

public enum Evento implements Serializable {
    JUGADOR_AGREGADO,
    JUEGO_COMENZADO,
    CAMBIO_TURNO,
    CARTA_TIRADA,
    CARTA_AGREGADA_CARNAVAL,
    CARTA_AGREGADA_AREA,
    FIN_TURNO,
    ULTIMA_RONDA,
    MAZO_SIN_CARTAS,
    RONDA_DESCARTE,
    CARTA_DESCARTADA,
    JUGADOR_AGREGADO_TABLA,
    ABANDONO_JUGADOR,
    FIN_JUEGO
}
