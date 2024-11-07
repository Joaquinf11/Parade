package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.modelo.Evento;

public interface Observador {
    public void notificar(Evento evento);
}
