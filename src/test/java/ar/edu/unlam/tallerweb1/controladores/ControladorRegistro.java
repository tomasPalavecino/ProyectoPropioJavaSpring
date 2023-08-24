package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistro {

    public ModelAndView crearUsuario(String email){
        ModelMap model = new ModelMap();
        String mensaje = "";
        String view = "";
        if(esEmailValido(email)){
            if(email != "tomy@circo.com"){
                mensaje = "El email o contraseña es incorrecta";
                view = "Registro-usuario";
            }else{
                mensaje = "El email es correcto";
                view = "login";
            }
        }else{
            mensaje = "El usuario no existe o el formato es incorrecto";
            view = "Registro-usuario";
        }
        model.put("mensaje", mensaje);
        return new ModelAndView(view, model);
    }

    private Boolean esEmailValido(String email) {
        return email.contains("@") && email.endsWith(".com");
    }

}
