package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

public class controladorRegistroTest {

    ControladorRegistro controladorRegistro = new ControladorRegistro();
    ModelAndView mav = new ModelAndView();

    @Test
    public void siElEmailNoTieneFormatoCorrectoElRegistroFalla(){
        String email = "tototo@.com";
        givenNoExisteUsuario();
        mav = whenCreoUsuarioConEmail(email);
        thenElRegistroFalla(mav);
    }

    @Test
    public void siElEmailTieneFormatoCorrectoElRegistroEsExitoso(){
        String email = "tomy@circo.com";
        givenNoExisteUsuario();
        mav = whenCreoUsuarioConEmail(email);
        thenElRegistroEsExitoso(mav);
    }

    private void givenNoExisteUsuario() {

    }

    private ModelAndView whenCreoUsuarioConEmail(String email) {
        mav = controladorRegistro.crearUsuario(email);
        return mav;
    }

    private void thenElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getModel().get("mensaje")).isEqualTo("El email o contraseña es incorrecta");
        assertThat(mav.getViewName()).isEqualTo("Registro-usuario");
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getModel().get("mensaje")).isEqualTo("El email es correcto");
        assertThat(mav.getViewName()).isEqualTo("login");
    }

}
