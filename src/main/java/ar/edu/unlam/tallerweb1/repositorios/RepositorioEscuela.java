package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;

import java.util.List;

public interface RepositorioEscuela {

    Escuela buscarEscuela(String nombre, String direccion, String localidad);

    void guardar(Escuela escuela);

    Escuela buscarEscuelaPorId(Long idEscuela);

    List<Escuela> getEscuelas();
}
