package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class RepositorioPacienteTest extends SpringTest {

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
    public void testQueUnPacientePuedaCrearUnaCitaProgramada(){
        Paciente paciente = givenUnPaciente();
        Medico medico = givenUnMedico();
        CitaConsultorio citaConsultorio = whenElPacienteCreaLacitaProgramada(paciente, medico);
        thenLaCitaProgramadaSeCreaConExito(citaConsultorio, paciente, medico);
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

    private CitaConsultorio whenElPacienteCreaLacitaProgramada(Paciente paciente, Medico medico) {
        CitaConsultorio citaConsultorio = new CitaConsultorio();
        citaConsultorio.setPaciente(paciente);
        citaConsultorio.setMedico(medico);
        citaConsultorio.setEspecialidad(medico.getEspecialidades().get(0));
        citaConsultorio.setFecha(LocalDate.parse("2021-06-30"));
        citaConsultorio.setHora(LocalTime.parse("08:00"));

        this.repositorioPaciente.registrarCita(citaConsultorio);

        return citaConsultorio;
    }

    private void thenLaCitaProgramadaSeCreaConExito(CitaConsultorio citaConsultorio, Paciente paciente, Medico medico) {
        List<Cita> citaList = this.repositorioPaciente.obtenerTodasLasCitas(paciente.getEmail());
        assertThat(citaList).isNotEmpty();
        assertThat(citaList.get(0)).isEqualTo(citaConsultorio);
        assertThat(citaList.get(0).getPaciente()).isEqualTo(paciente);
        assertThat(citaList.get(0).getMedico()).isEqualTo(medico);
    }
}
