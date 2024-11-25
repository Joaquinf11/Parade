package ar.unlu.edu.mvc.modelo;

import java.io.Serializable;

public enum Evento implements Serializable {
    JUEGO_COMENZADO,
    JUGADOR_AGREGADO,
    CAMBIO_TURNO,
    CARTA_TIRADA,
    CARTA_AGREGADA_CARNAVAL,
    CARTA_AGREGADA_AREA,
    FIN_TURNO,
    ULTIMA_RONDA,
    MAZO_SIN_CARTAS,
    RONDA_DESCARTE,
    CARTA_DESCARTADA,
    FIN_JUEGO,

}
