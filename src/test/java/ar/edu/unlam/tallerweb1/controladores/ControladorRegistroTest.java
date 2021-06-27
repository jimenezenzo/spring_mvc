package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class ControladorRegistroTest {

    ServicioUsuario servicioUsuario;
    private ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuario);

    @Test
    public void testIrARegistro(){
        ModelAndView mav = controladorRegistro.irARegistro("");

        assertThat(mav.getViewName()).isEqualTo("auth/register");
        assertThat(mav.getModel().get("paciente")).isNotNull();
    }

}
