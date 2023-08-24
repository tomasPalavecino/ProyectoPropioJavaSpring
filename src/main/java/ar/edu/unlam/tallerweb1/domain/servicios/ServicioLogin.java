package ar.edu.unlam.tallerweb1.domain.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosUsuarioDTO;
import ar.edu.unlam.tallerweb1.exceptions.formNullException;
import ar.edu.unlam.tallerweb1.exceptions.usuarioExisteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario getUsuarioId(Long idUsuario);

	Usuario getUsuario(String email, String password);

	void guardarUsuario(DatosUsuarioDTO nuevoUsuario) throws usuarioExisteException, formNullException;
}
