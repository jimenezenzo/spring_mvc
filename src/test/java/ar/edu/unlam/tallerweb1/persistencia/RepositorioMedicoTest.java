package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class RepositorioMedicoTest extends SpringTest {

    private RepositorioPaciente repositorioPaciente;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioMedico repositorioMedico;

    @Before
    public void init(){
        this.repositorioPaciente = new RepositorioPacienteImpl(session().getSessionFactory());
        this.repositorioUsuario = new RepositorioUsuarioImpl(session().getSessionFactory());
        this.repositorioMedico = new RepositorioMedicoImpl(session().getSessionFactory());
    }

    @Test
    @Transactional
    @Rollback
    public void testQueUnMedicoPuedaVerSusCitasProgramadasDelDia(){
        Medico medico = givenUnMedico();
        Paciente paciente = givenUnPaciente();
        whenCreoLasCitasConsultorio(medico, paciente);
        thenElMedicoPuedeVerSusCitasProgramadas(medico);
    }

    @Test
    @Transactional
    @Rollback
    public void testQueUnMedicoPuedaVerSusCitasADomicilioDelDia(){
        Medico medico = givenUnMedico();
        Paciente paciente = givenUnPaciente();
        whenCreoLasCitasADomicilio(medico, paciente);
        thenElMedicoPuedeVerSusCitasADomicilio(medico);
    }

    private Medico givenUnMedico() {
        Persona persona = new Persona();
        persona.setNombre("Roman");
        persona.setApellido("Riquelme");
        persona.setTipoDocumento("Dni");
        persona.setNumeroDocumento("40258513");
        persona.setFechaNacimiento(LocalDate.parse("1997-04-19"));
        persona.setSexo("Masculino");

        Especialidad especialidad = new Especialidad();
        especialidad.setDescripcion("Clinico");
        this.repositorioMedico.registrarEspecialidad(especialidad);

        Consultorio consultorio = new Consultorio();
        consultorio.setDomicilio("Av siempre viva 123");
        consultorio.setLatitud(-30.4564f);
        consultorio.setLongitud(-25.34536f);
        this.repositorioMedico.registrarConsultorio(consultorio);

        Medico medico = new Medico();
        medico.setMatricula("9999");
        medico.setRol("Medico");
        medico.setEmail("juan-roman@hotmail.com");
        medico.setEspecialidades(Arrays.asList(especialidad));
        medico.setConsultorio(consultorio);

        this.repositorioUsuario.registrarAltaUsuario(medico);

        return medico;
    }

    private Paciente givenUnPaciente() {
        Persona persona = new Persona();
        persona.setNombre("Carlos");
        persona.setApellido("Tevez");
        persona.setTipoDocumento("Dni");
        persona.setNumeroDocumento("40258513");
        persona.setFechaNacimiento(LocalDate.parse("1997-04-18"));
        persona.setSexo("Masculino");

        Paciente paciente = new Paciente();
        paciente.setPersona(persona);
        paciente.setRol("Paciente");
        paciente.setEmail("carlos-tevez@hotmail.com");
        paciente.setNumeroAfiliado("123456789");
        paciente.setPassword("bla");

        this.repositorioUsuario.registrarAltaUsuario(paciente);

        return paciente;
    }

    private void whenCreoLasCitasConsultorio(Medico medico, Paciente paciente) {
        CitaConsultorio citaConsultorio = new CitaConsultorio();
        citaConsultorio.setEspecialidad(medico.getEspecialidades().get(0));
        citaConsultorio.setFecha(LocalDate.parse("2021-07-06"));
        citaConsultorio.setHora(LocalTime.parse("12:00"));
        citaConsultorio.setFechaRegistro(LocalDateTime.now());
        citaConsultorio.setMedico(medico);
        citaConsultorio.setPaciente(paciente);
        this.repositorioPaciente.registrarCita(citaConsultorio);

        CitaConsultorio citaConsultorio2 = new CitaConsultorio();
        citaConsultorio.setEspecialidad(medico.getEspecialidades().get(0));
        citaConsultorio.setFecha(LocalDate.parse("2021-07-06"));
        citaConsultorio.setHora(LocalTime.parse("12:40"));
        citaConsultorio.setFechaRegistro(LocalDateTime.now());
        citaConsultorio.setMedico(medico);
        citaConsultorio.setPaciente(paciente);
        this.repositorioPaciente.registrarCita(citaConsultorio2);
    }

    private void whenCreoLasCitasADomicilio(Medico medico, Paciente paciente) {
        CitaDomicilio citaDomicilio = new CitaDomicilio();
        citaDomicilio.setLatitud(-34.676f);
        citaDomicilio.setLongitud(-23.687f);
        citaDomicilio.setFechaRegistro(LocalDateTime.now());
        citaDomicilio.setPaciente(paciente);
        citaDomicilio.setMedico(medico);
        citaDomicilio.setSintomas("bla bla");
        this.repositorioPaciente.registrarCita(citaDomicilio);

        CitaDomicilio citaDomicilio2 = new CitaDomicilio();
        citaDomicilio.setLatitud(-34.676f);
        citaDomicilio.setLongitud(-23.687f);
        citaDomicilio.setFechaRegistro(LocalDateTime.now());
        citaDomicilio.setPaciente(paciente);
        citaDomicilio.setMedico(medico);
        citaDomicilio.setSintomas("bla bla2");
        this.repositorioPaciente.registrarCita(citaDomicilio2);
    }

    private void thenElMedicoPuedeVerSusCitasProgramadas(Medico medico) {
        List citas = this.repositorioMedico.obtenerCitasConsultorioPorFecha(medico, LocalDate.parse("2021-07-06"));
        assertThat(citas).isNotEmpty();
        //assertThat(citas.size()).isEqualTo(2);
    }

    private void thenElMedicoPuedeVerSusCitasADomicilio(Medico medico) {
        List citas = this.repositorioMedico.obtenerCitasDomicilioPorFecha(medico, LocalDateTime.now());
        assertThat(citas).isNotEmpty();
    }
}
