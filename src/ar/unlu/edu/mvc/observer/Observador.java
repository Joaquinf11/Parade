package ar.unlu.edu.mvc.observer;

import ar.unlu.edu.mvc.modelo.Evento;

public interface Observador {
    public void notificar(Evento evento);
}
