package ar.edu.unlam.tallerweb1.domain.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosEscuelaDTO;
import ar.edu.unlam.tallerweb1.exceptions.escuelaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioEscuela")
@Transactional
public class ServicioEscuelaImpl implements ServicioEscuela{

    private RepositorioEscuela repoEscuela;

    @Autowired
    public ServicioEscuelaImpl(RepositorioEscuela repoEscuela){
        this.repoEscuela = repoEscuela;
    }

    @Override
    public void crearEscuela(DatosEscuelaDTO nuevaEscuela) throws escuelaExisteException{
        try{
            this.validarNuevaEscuela(nuevaEscuela);
            Escuela escuela = new Escuela();
            escuela.setNombre(nuevaEscuela.getNombre());
            escuela.setDireccion(nuevaEscuela.getDireccion());
            escuela.setLocalidad(nuevaEscuela.getLocalidad());
            repoEscuela.guardar(escuela);
        }catch(escuelaExisteException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public Escuela getEscuelaId(Long idEscuela) {
        return repoEscuela.buscarEscuelaPorId(idEscuela);
    }

    private void validarNuevaEscuela(DatosEscuelaDTO nuevaEscuela) throws escuelaExisteException {
        if(repoEscuela.buscarEscuela(nuevaEscuela.getNombre(),
                                    nuevaEscuela.getDireccion(),
                                    nuevaEscuela.getLocalidad()) != null){
            throw new escuelaExisteException("La escuela ya existe");
        }
    }
}
