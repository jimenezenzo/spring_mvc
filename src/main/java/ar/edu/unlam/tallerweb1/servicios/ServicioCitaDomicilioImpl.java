package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.configuraciones.SortByDateTime;
import ar.edu.unlam.tallerweb1.excepciones.CrearCitaError;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaDomicilio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaDomicilio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ServicioCitaDomicilioImpl implements ServicioCitaDomicilio{


    private RestTemplate restTemplate;
    private RepositorioCitaDomicilio repositorioCitaDomicilio;
    private RepositorioPaciente repositorioPaciente;
    private RepositorioMedico repositorioMedico;
    private ServicioMedico servicioMedico;

    @Autowired
    public ServicioCitaDomicilioImpl(RepositorioCitaDomicilio repositorioCitaDomicilio, RestTemplate restTemplate,
                                     RepositorioPaciente repositorioPaciente, RepositorioMedico repositorioMedico,
                                     ServicioMedico servicioMedico) {
        this.repositorioCitaDomicilio = repositorioCitaDomicilio;
        this.restTemplate = restTemplate;
        this.repositorioPaciente = repositorioPaciente;
        this.repositorioMedico = repositorioMedico;
        this.servicioMedico = servicioMedico;
    }

    @Override
    public CitaDomicilio getCitaById(Long id) {
        return repositorioCitaDomicilio.getCitaById(id);
    }

    @Override
    public Medico obtenerMedicoProximo(Float latitud, Float longitud){

        final String uri = "http://dev.virtualearth.net/REST/V1/Routes/Driving?wp.0=" + latitud + "," + longitud + "&wp.1=-34.644082,-58.656933&avoid=minimizeTolls&ra=routeSummariesOnly&key=AtLKQPChPF7fjzgLfxwMc7ybQlYp35ziJznMf0EFCAHpsrhabrTjF6vNh4bib3zo";
        String result = restTemplate.getForObject(uri, String.class);

        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(result);
            JSONObject objeto = (JSONObject)obj;

            JSONArray resourceSets = (JSONArray) objeto.get("resourceSets");
            objeto = (JSONObject) resourceSets.get(0);
            JSONArray resources = (JSONArray) objeto.get("resources");
            objeto = (JSONObject) resources.get(0);
            Long travelDurationTraffic = (Long) objeto.get("travelDurationTraffic");
            Double travelDistance = (Double) objeto.get("travelDistance");


        }catch(ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return null;
    }

    @Override
    public void createCitaDomicilio(DatosCitaDomicilio datosCita) throws CrearCitaError {
        CitaHistoria citaHistoria = new CitaHistoria();
        citaHistoria.setEstado(EstadoCita.CREADO);
        citaHistoria.setObservacion("Creado");
        citaHistoria.setFechaRegistro(LocalDateTime.now());

        CitaDomicilio citaDomicilio =  new CitaDomicilio();
        citaDomicilio.setFechaRegistro(LocalDateTime.now());
        citaDomicilio.setPaciente(this.repositorioPaciente.obtenerPacientePorEmail(datosCita.getEmailPaciente()));

        // Se determina el mejor médico para asistir al domicilio según coordenadas
        DatosCitaDomicilio data = obtenerMenosOcupado(datosCita.getLatitud(), datosCita.getLongitud());
        if (data.getMedico() == null || data.getMedico().getId() == null)
            throw new CrearCitaError("No hay médico de guardia disponible");

        citaDomicilio.setMedico(data.getMedico());

        citaDomicilio.agregarHistoria(citaHistoria);

        citaDomicilio.setLatitud(datosCita.getLatitud());
        citaDomicilio.setLongitud(datosCita.getLongitud());
        citaDomicilio.setSintomas(datosCita.getSintomas());

        this.repositorioCitaDomicilio.registrarCitaDomicilio(citaDomicilio);
    }

    //Obtiene el médico de guardia con menos citas pendientes
    @Override
    public DatosCitaDomicilio obtenerMenosOcupado(Float lat_paciente, Float lon_paciente) {
        Agenda agendaHoy;
        List<CitaDomicilio> citas;
        Medico mejorOpcion = new Medico();
        DatosCitaDomicilio data = new DatosCitaDomicilio();
        Long demoraTotal = 0L;
        Long mejorTiempo = 0L;

        // Obtengo todos los medicos
        List<Medico> todos = repositorioMedico.obtenerTodosLosMedicos();
        LocalTime horaActual = LocalDateTime.now().toLocalTime();

        //Por cada médico
        for (Medico medico: todos){
            // Obtengo su agenda para hoy
            agendaHoy = servicioMedico.getAgendaHoy(medico.getEmail());

            // Si está de guardia
            if (agendaHoy.getGuardia() && agendaHoy.getActivo()) {

                // Y la hora actual está dentro de su rango de guardia
                if (agendaHoy.getHoraDesde().isBefore(horaActual) && agendaHoy.getHoraHasta().isAfter(horaActual)){

                    // Obtengo todas las citas del médico
                    citas = servicioMedico.obtenerCitasDomicilio(medico.getEmail());
                    for (Cita cita : citas){
                        for (CitaHistoria historia : cita.getCitaHistoriaList()){
                            // Y descarto las citas CANCELADAS o FINALIZADAS
                            if (historia.getEstado() != EstadoCita.CREADO){
                                citas.remove(cita);
                            }
                        }
                    }

                    //Ordeno citas por fecha/hora de registro
                    citas.sort(new SortByDateTime());

                    //Recorro las citas en orden cronológico y calculo el timepo total de viaje entre citas
                    for (int i = 0; i < (citas.size()-1); i++){
                        demoraTotal += tiempoDeViajeEntreCitas(citas.get(i).getLatitud(),
                                                               citas.get(i).getLongitud(),
                                                               citas.get(i+1).getLatitud(),
                                                               citas.get(i+1).getLongitud());
                    }
                    // Sumo tiempo de viaje a la ubicación del paciente
                    demoraTotal += tiempoDeViajeEntreCitas(citas.get(citas.size()-1).getLatitud(),
                                                           citas.get(citas.size()-1).getLongitud(),
                                                           lat_paciente, lon_paciente);

                    // Si este es el mejor tiempo hasta ahora, lo tomo para devolverlo
                    if (demoraTotal < mejorTiempo || mejorTiempo == 0L){
                        mejorTiempo = demoraTotal;
                        mejorOpcion = medico;
                        demoraTotal = 0L; //Limpio variable para próximo médico
                    }
                }
            }
        }

        data.setMedico(mejorOpcion);
        data.setDemora(mejorTiempo);

        return data;
    }

    private Long tiempoDeViajeEntreCitas(Float lat_ori, Float lon_ori, Float lat_des, Float lon_des ){
        Long travelDurationTraffic = 0L;
        final String uri = "http://dev.virtualearth.net/REST/V1/Routes/Driving?wp.0=" + lat_ori + "," + lon_ori +
                            "&wp.1=" + lat_des + "," + lon_des +
                            "&avoid=minimizeTolls&ra=routeSummariesOnly&key=AtLKQPChPF7fjzgLfxwMc7ybQlYp35ziJznMf0EFCAHpsrhabrTjF6vNh4bib3zo";
        String result = restTemplate.getForObject(uri, String.class);

        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(result);
            JSONObject objeto = (JSONObject)obj;
            JSONArray resourceSets = (JSONArray) objeto.get("resourceSets");
            objeto = (JSONObject) resourceSets.get(0);
            JSONArray resources = (JSONArray) objeto.get("resources");
            objeto = (JSONObject) resources.get(0);
            travelDurationTraffic = (Long) objeto.get("travelDurationTraffic");
        }catch(ParseException pe) {
            return 0L;
        }

        return travelDurationTraffic;
    }


}
