package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.repositorios.*;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;

import static org.mockito.Mockito.mock;

public class ServicioPacienteTest {

    private ServicioCita servicioPaciente;
    private RepositorioCita repositorioCita;
    private RepositorioMedico repositorioMedico;
    private RepositorioPaciente repositorioPaciente;
    private RepositorioCitaDomicilio repositorioCitaDomicilio;
    private  ServicioCitaDomicilio servicioCitaDomicilio;


    @Before
    public void init() {
        repositorioPaciente = mock(RepositorioPaciente.class);
        repositorioCita = mock(RepositorioCita.class);
        repositorioMedico = mock(RepositorioMedico.class);
        servicioPaciente = new ServicioCitaImpl(repositorioPaciente, repositorioCita, repositorioMedico);

        //repositorioCitaDomicilio = mock(RepositorioCitaDomicilio.class);
        //servicioCitaDomicilio = new ServicioCitaDomicilioImpl(repositorioCitaDomicilio);
    }

    @Test()
    @Transactional
    public void testAPIconsummer()
    {
       /* Float lat = new Float("132.55");
        Float lon = new Float("12.564");
        Medico med = servicioCitaDomicilio.obtenerMedicoProximo(lat, lon);*/
    }

}
