package ar.edu.unlam.tallerweb1.domain.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosUsuarioDTO;
import ar.edu.unlam.tallerweb1.exceptions.formNullException;
import ar.edu.unlam.tallerweb1.exceptions.usuarioExisteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario repoUsuario;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario repoUsuario){
		this.repoUsuario = repoUsuario;
	}

	@Override
	public void guardarUsuario(DatosUsuarioDTO nuevoUsuario) throws usuarioExisteException, formNullException {
		try {
			this.formNotNull(nuevoUsuario);
			this.validarNuevoUsuario(nuevoUsuario);
			Usuario usuario = new Usuario();
			usuario.setEmail(nuevoUsuario.getEmail());
			usuario.setName(nuevoUsuario.getNombre());
			usuario.setPassword(nuevoUsuario.getPassword());
			repoUsuario.guardar(usuario);
		} catch (usuarioExisteException | formNullException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public Usuario getUsuarioId(Long idUsuario) {
		return repoUsuario.buscarUsuarioPorId(idUsuario);
	}

	@Override
	public Usuario getUsuario (String email, String password) {
		return repoUsuario.buscarUsuario(email, password);
	}

	private void validarNuevoUsuario(DatosUsuarioDTO nuevoUsuario) throws usuarioExisteException {
		if (repoUsuario.buscar(nuevoUsuario.getEmail()) != null){
			throw new usuarioExisteException("El usuario ya existe");
		}
	}

	public void formNotNull(DatosUsuarioDTO usuarioDatosForm) throws formNullException{
		if (usuarioDatosForm.getEmail().isEmpty() ||
			usuarioDatosForm.getNombre().isEmpty() ||
			usuarioDatosForm.getPassword().isEmpty()){
			throw new formNullException("Debe completar el formulario");
		}
	}

}
