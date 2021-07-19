package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaDomicilio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioPacienteImpl implements ServicioPaciente {

    private RepositorioCita repositorioCita;
    private RepositorioPaciente repositorioPaciente;
    private ServicioCitaDomicilio servicioCitaDomicilio;

    @Autowired
    public ServicioPacienteImpl(RepositorioCita repositorioCita, RepositorioPaciente repositorioPaciente,
                                                                ServicioCitaDomicilio servicioCitaDomicilio){
        this.repositorioCita = repositorioCita;
        this.repositorioPaciente = repositorioPaciente;
        this.servicioCitaDomicilio = servicioCitaDomicilio;
    }

    @Override
    public List<CitaConsultorio> getCitaConsultorio(String email) {
        Paciente paciente = this.repositorioPaciente.obtenerPacientePorEmail(email);
        return this.repositorioCita.obtenerCitasConsultorioPaciente(paciente);
    }

    @Override
    public List<DatosCitaDomicilio> getCitasDomicilioPend(String email){
        Paciente paciente = this.repositorioPaciente.obtenerPacientePorEmail(email);
        List<CitaDomicilio> citas = this.repositorioCita.obtenerCitasDomicilioPaciente(paciente);
        List<DatosCitaDomicilio> citasReturn = new ArrayList<DatosCitaDomicilio>();
        DatosCitaDomicilio datos = new DatosCitaDomicilio();

        for (CitaDomicilio cita : citas){
            if(cita.getUltimaHistoria().getObservacion().equals("Creado"))
                datos = cita.toDatosCitaDomicilio();
                datos.setDemora(servicioCitaDomicilio.obtenerDemora(cita.getId()));
                citasReturn.add(datos);
        }

        return citasReturn;
    }
}
