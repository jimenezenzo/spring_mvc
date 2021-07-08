package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class ServicioCitaTest {

    private RepositorioPaciente repositorioPaciente;
    private RepositorioCita repositorioCita;
    private RepositorioMedico repositorioMedico;
    private ServicioCita servicioCita;

    @Before
    public void init(){
        repositorioPaciente = mock(RepositorioPaciente.class);
        repositorioCita = mock(RepositorioCita.class);
        repositorioMedico = mock(RepositorioMedico.class);
        servicioCita = new ServicioCitaImpl(repositorioPaciente, repositorioCita, repositorioMedico);
    }


}
