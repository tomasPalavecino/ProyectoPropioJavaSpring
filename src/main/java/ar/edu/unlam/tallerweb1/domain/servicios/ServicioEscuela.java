package ar.edu.unlam.tallerweb1.domain.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosEscuelaDTO;
import ar.edu.unlam.tallerweb1.exceptions.escuelaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Escuela;

import java.util.List;

public interface ServicioEscuela {

    void crearEscuela(DatosEscuelaDTO nuevaEscuela) throws escuelaExisteException;

    Escuela getEscuelaId(Long idEscuela);
    List<Escuela> listarTodas();
}
