package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ControladorRegistroTest {

    private ControladorRegistro controladorRegistro = new ControladorRegistro();

    @Test
    public void testIrARegistro(){
        ModelAndView mav = controladorRegistro.irARegistro("");

        assertThat(mav.getViewName()).isEqualTo("auth/register");
        assertThat(mav.getModel().get("usuario")).isNotNull();
    }

    @Test
    public void testQueSePuedaRegistrarUnUsuario(){
        /*Usuario usuario = new Usuario();
        usuario.setName("test");
        usuario.setEmail("usuario@test.com");
        usuario.setPassword("password");

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", usuario.getEmail());

        BindingResult bindingResult = new MapBindingResult(map, "usuario");

        ModelAndView mav = controladorRegistro.store(usuario, bindingResult);

        assertThat(mav.getViewName()).isEqualTo("login");*/
    }
}
