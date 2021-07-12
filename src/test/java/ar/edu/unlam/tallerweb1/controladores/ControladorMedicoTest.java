package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cita;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCitaDomicilio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCitaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedicoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;



import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorMedicoTest {


    private ServicioMedico servicioMedico;
    private RepositorioMedico repositorioMedico;
    private ControladorMedico controladorMedico;
    private ServicioCitaDomicilio servicioCitaDomicilio;
    private Authentication authentication;

    @Before
    public void init(){
        servicioMedico = mock(ServicioMedico.class);
        repositorioMedico = mock(RepositorioMedico.class);
        servicioCitaDomicilio = mock(ServicioCitaDomicilio.class);
        authentication = mock(Authentication.class);
        servicioMedico = new ServicioMedicoImpl(repositorioMedico);
        controladorMedico = new ControladorMedico(servicioCitaDomicilio, servicioMedico);
        User principal = mock(User.class);
        when(principal.getUsername()).thenReturn("delatorre@medico.com");
        when(authentication.getPrincipal()).thenReturn(principal);

    }

    @Test
    public void sePuedenVerEnElMapaLasUbicacionesDeLasCitasDomicilio() {

        Medico medico = givenUnMedicoDadoDeAltaEnElSistema();

        List<CitaDomicilio> listaCitaDomciilio = new ArrayList<>();

        when(servicioMedico.obtenerCitasDomicilio("delatorre@medico.com")).thenReturn(listaCitaDomciilio);

        ModelAndView mav = whenQuiereConsultarTodasLasCitasEnElMapa();

        thenLaConsultaEsExitosa(mav);

    }

    private void thenLaConsultaEsExitosa(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("maps/mapa-todas-las-citas-domicilio");
    }

    private ModelAndView whenQuiereConsultarTodasLasCitasEnElMapa() {
        ModelAndView mav = controladorMedico.verTodasLasCitasEnElMapa(authentication);

        return mav;
    }

    private Medico givenUnMedicoDadoDeAltaEnElSistema() {
        Medico medico = new Medico();

        medico.setEmail("delatorre@medico.com");

        return medico;
    }


}
