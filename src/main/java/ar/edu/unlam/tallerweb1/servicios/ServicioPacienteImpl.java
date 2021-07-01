package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioPacienteImpl implements ServicioPaciente {

    private RepositorioCita repositorioCita;
    private RepositorioPaciente repositorioPaciente;

    @Autowired
    public ServicioPacienteImpl(RepositorioCita repositorioCita, RepositorioPaciente repositorioPaciente){
        this.repositorioCita = repositorioCita;
        this.repositorioPaciente = repositorioPaciente;
    }

    @Override
    public List<CitaConsultorio> getCitaConsultorio(String email) {
        Paciente paciente = this.repositorioPaciente.obtenerPacientePorEmail(email);
        return this.repositorioCita.obtenerCitasConsultorioPaciente(paciente);
    }
}
