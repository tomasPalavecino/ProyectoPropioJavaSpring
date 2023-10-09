package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.domain.servicios.ServicioEscuela;
import ar.edu.unlam.tallerweb1.exceptions.escuelaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorEscuela {

    private ServicioEscuela servicioEscuela;

    @Autowired
    public ControladorEscuela(ServicioEscuela servicioEscuela){
        this.servicioEscuela = servicioEscuela;
    }

    @RequestMapping( "/crearEscuela")
    public ModelAndView CrearEsuelaView(){
        ModelMap modelo = new ModelMap();
        modelo.put("datosEscuela", new DatosEscuelaDTO());
        return new ModelAndView("crearEscuelaForm", modelo);
    }

    @RequestMapping(path= "/crearEscuelaa", method = RequestMethod.POST)
    public ModelAndView crearEscuela(@ModelAttribute("datosEscuela") DatosEscuelaDTO datosEscuelaDTO, HttpServletRequest request){
        DatosEscuelaDTO nuevaEscuela = datosEscuelaDTO;
        ModelMap modelo = new ModelMap();
        try{
            this.servicioEscuela.crearEscuela(nuevaEscuela);
            return irAHome(request);
        } catch (escuelaExisteException e){
            modelo.put("mensajeError", e.getMessage());
            modelo.put("datosEscuela", new DatosEscuelaDTO());
            return new ModelAndView("crearEscuelaForm", modelo);
        }
    }

    private ModelAndView irAHome(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        modelo.put("listaEscuelas", this.servicioEscuela.listarTodas());
        return new ModelAndView("home", modelo);
    }


}
