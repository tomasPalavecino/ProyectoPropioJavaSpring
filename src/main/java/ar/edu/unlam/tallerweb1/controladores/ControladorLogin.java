package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.formNullException;
import ar.edu.unlam.tallerweb1.exceptions.usuarioExisteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.domain.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	private final ServicioLogin servicioLogin;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin){
		this.servicioLogin = servicioLogin;
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto con key 'datosLogin' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		modelo.put("datosLogin", new DatosLoginDTO());
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El metodo recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLoginDTO datosLogin, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a traves de la URL correspondiente a esta
		//Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
		Usuario usuarioBuscado = servicioLogin.getUsuario(datosLogin.getEmail(), datosLogin.getPassword());
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			return irAHome(request);
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			modelo.put("error", "Usuario o clave incorrecta");
			return new ModelAndView("login", modelo);
		}
	}

	@RequestMapping(path = "/registrarme")
	public ModelAndView registrarView() {
		ModelMap modelo = new ModelMap();
		modelo.put("datosUsuario", new DatosUsuarioDTO());
		return new ModelAndView("registro-usuario", modelo);
	}

	@RequestMapping(path = "/registrarUsuario", method = RequestMethod.POST)
	public ModelAndView registrarUsuario(@ModelAttribute("datosUsuario") DatosUsuarioDTO datosUsuarioDTO, HttpServletRequest request){
		DatosUsuarioDTO nuevoUsuario = datosUsuarioDTO;
		ModelMap modelo = new ModelMap();
			try{
				this.servicioLogin.guardarUsuario(nuevoUsuario);
				return new ModelAndView("redirect:/home");
			}
			catch (usuarioExisteException | formNullException e){
				modelo.put("mensajeDeError", e.getMessage());
				modelo.put("datosUsuario", new DatosUsuarioDTO());
				return new ModelAndView("registro-usuario", modelo);
			}
	}

	public ModelAndView irAHome(HttpServletRequest request){
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Usuario usuario = servicioLogin.getUsuarioId(idUsuario);
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", usuario);
		return new ModelAndView("home", modelo);
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
}
