package ar.unlu.edu.mvc.modelo;

public enum Evento {
    JUEGO_COMENZADO,
    JUGADOR_AGREGADO,
    CAMBIO_TURNO,
    CARTA_TIRADA,
    CARTA_AGREGADA_CARNAVAL,
    CARTA_AGREGADA_AREA,
    FIN_TURNO,
    ULTIMA_RONDA,
    MAZO_SIN_CARTAS, // cuando agregue el mazo a la vista tengo que actualizarlo para mostrar que se quedo sin cartas
    RONDA_DESCARTE,
    FIN_JUEGO,

}
