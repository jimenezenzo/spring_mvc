package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Persona;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosAltaUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class ServicioUsuarioTest {

    RepositorioUsuario repositorioUsuario;

    ServicioUsuario servicioUsuario;

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuario.class);

        servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario);
    }


    @Test()
    public void sePuedeRegistrarUnPacienteCorrectamente() throws ParseException {


        DatosAltaUsuario datosAltaUsuario = givenDatosDePacienteCorrectos();

        Persona persona = givenDatosDePersonaCorrectos(datosAltaUsuario);

        Paciente paciente = givenUnPacienteCorrecto(datosAltaUsuario, persona);

        whenLaQuieroRegistrar(paciente);

        verify(repositorioUsuario, times(1)).registrarAltaUsuario(paciente);

    }

    private void whenLaQuieroRegistrar(Paciente paciente) {
        repositorioUsuario.registrarAltaUsuario(paciente);

    }

    private Paciente givenUnPacienteCorrecto(DatosAltaUsuario datosAltaUsuario, Persona persona) {
        Paciente paciente = new Paciente();
        paciente.setPersona(persona);
        paciente.setEmail(datosAltaUsuario.getEmail());
        paciente.setRol(datosAltaUsuario.getRol());
        paciente.setNumeroAfiliado(datosAltaUsuario.getNumeroAfiliado());

        return paciente;
    }


    private DatosAltaUsuario givenDatosDePacienteCorrectos() {

        DatosAltaUsuario datosAltaUsuario = new DatosAltaUsuario();
        datosAltaUsuario.setTipoDocumento("DNI");
        datosAltaUsuario.setNumeroDocumento("31231232");
        datosAltaUsuario.setNombre("juan");
        datosAltaUsuario.setApellido("perez");
        datosAltaUsuario.setSexo("Masculino");
        datosAltaUsuario.setFechaNacimiento("2021-06-05");

        return datosAltaUsuario;
    }

    private Persona givenDatosDePersonaCorrectos(DatosAltaUsuario datosAltaUsuario) {
        Persona persona = new Persona();
        persona.setTipoDocumento(datosAltaUsuario.getTipoDocumento());
        persona.setNumeroDocumento(datosAltaUsuario.getNumeroDocumento());
        persona.setNombre(datosAltaUsuario.getNombre());
        persona.setApellido(datosAltaUsuario.getApellido());
        persona.setSexo(datosAltaUsuario.getSexo());
        persona.setFechaNacimiento(LocalDate.parse(datosAltaUsuario.getFechaNacimiento()));
        return persona;
    }




}
