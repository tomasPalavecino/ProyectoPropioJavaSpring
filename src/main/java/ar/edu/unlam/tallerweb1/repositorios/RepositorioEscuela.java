package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;

public interface RepositorioEscuela {

    Escuela buscarEscuela(String nombre, String direccion, String localidad);

    void guardar(Escuela escuela);

    Escuela buscarEscuelaPorId(Long idEscuela);
}
