package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.modelo.Evento;

import java.io.IOException;

public interface Observador {
     void actualizar(Evento evento) ;
}
