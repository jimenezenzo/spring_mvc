package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.CrearCitaError;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaDomicilio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaDomicilio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ServicioCitaTest {

    private RepositorioCitaDomicilio repositorioCitaDomicilio;
    private RestTemplate restTemplate;
    private RepositorioPaciente repositorioPaciente;
    private RepositorioMedico repositorioMedico;
    private ServicioMedico servicioMedico;

    private ServicioCitaDomicilio servicioCitaDomicilio;

    @Before
    public void init(){
        repositorioPaciente = mock(RepositorioPaciente.class);
        repositorioMedico = mock(RepositorioMedico.class);
        repositorioCitaDomicilio = mock(RepositorioCitaDomicilio.class);
        restTemplate = mock(RestTemplate.class);
        servicioMedico = mock(ServicioMedico.class);

        servicioCitaDomicilio = new ServicioCitaDomicilioImpl(repositorioCitaDomicilio, restTemplate,
                                                              repositorioPaciente, repositorioMedico, servicioMedico);

    }

    @Test
    public void noSePuedeCrearCitaSinMedicoDeGuardia(){
        DatosCitaDomicilio datosCitaDomicilio = givenDatosCitaDomicilio();
        whenSeCreaLaCitathenSeDisparaExcepcion(datosCitaDomicilio);
    }

    private DatosCitaDomicilio givenDatosCitaDomicilio() {
        DatosCitaDomicilio datosCitaDomicilio = new DatosCitaDomicilio();

        datosCitaDomicilio.setLatitud(-34.637900F);
        datosCitaDomicilio.setLongitud(-58.691900F);
        datosCitaDomicilio.setSintomas("Me duelen los bolsillos");
        datosCitaDomicilio.setEmailPaciente("juan.topo@simpsons.com");

        return datosCitaDomicilio;
    }

    private void whenSeCreaLaCitathenSeDisparaExcepcion(DatosCitaDomicilio datosCitaDomicilio) {
        Paciente paciente = new Paciente();
        paciente.setEmail(datosCitaDomicilio.getEmailPaciente());

        doReturn(paciente).when(repositorioPaciente).obtenerPacientePorEmail(datosCitaDomicilio.getEmailPaciente());
        doReturn(Collections.emptyList()).when(repositorioMedico).obtenerTodosLosMedicos();

        assertThatThrownBy(() -> {
            servicioCitaDomicilio.createCitaDomicilio(datosCitaDomicilio);
        }).isInstanceOf(CrearCitaError.class)
                .hasMessageContaining("No hay médico de guardia disponible");
    }



}
