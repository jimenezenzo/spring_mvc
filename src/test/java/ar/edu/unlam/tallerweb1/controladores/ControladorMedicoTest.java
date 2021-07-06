package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cita;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ControladorMedicoTest {

    /*@Test
    public void sePuedenVerEnElMapaLasUbicacionesDeLasCitasDomicilio() {

        Usuario usuario = givenunUnaCitaConUbicacionCorrecta();

        when(repositorioCita.userById(3L)).thenReturn(usuario);

        ModelAndView mav = controladorMedico.mapaMedico("id=3");

        assertThat(mav.getViewName()).isEqualTo("maps/mapaMedico");

    }

    private Usuario givenunUnaCitaConUbicacionCorrecta() {
        CitaDomicilio citaDomicilio = new CitaDomicilio();
        citaDomicilio.setLatitud(-34.67818784107572F);
        citaDomicilio.setLongitud(-58.56242023782406F);

        usuario.setId(3L);
        usuario.setUbicacion(ubicacion);
        return usuario;
    }*/


}
