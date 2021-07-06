package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.CrearCitaError;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import ar.edu.unlam.tallerweb1.modelo.EstadoCita;
import ar.edu.unlam.tallerweb1.modelo.Medico;
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
import java.util.List;

@Service
@Transactional
public class ServicioCitaDomicilioImpl implements ServicioCitaDomicilio{


    private RestTemplate restTemplate;
    private RepositorioCitaDomicilio repositorioCitaDomicilio;
    private RepositorioPaciente repositorioPaciente;
    private RepositorioMedico repositorioMedico;

    @Autowired
    public ServicioCitaDomicilioImpl(RepositorioCitaDomicilio repositorioCitaDomicilio, RestTemplate restTemplate,
                                     RepositorioPaciente repositorioPaciente, RepositorioMedico repositorioMedico) {
        this.repositorioCitaDomicilio = repositorioCitaDomicilio;
        this.restTemplate = restTemplate;
        this.repositorioPaciente = repositorioPaciente;
        this.repositorioMedico = repositorioMedico;
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
        //Medico med = obtenerMedicoProximo(datosCita.getLatitud(), datosCita.getLongitud());
        Medico med = obtenerMenosOcupado();
        citaDomicilio.setMedico(med);

        citaDomicilio.agregarHistoria(citaHistoria);

        citaDomicilio.setLatitud(datosCita.getLatitud());
        citaDomicilio.setLongitud(datosCita.getLongitud());
        citaDomicilio.setSintomas(datosCita.getSintomas());

        this.repositorioCitaDomicilio.registrarCitaDomicilio(citaDomicilio);
    }

    //Obtiene el médico de guardia con menos citas pendientes
    private Medico obtenerMenosOcupado() {
        List<Medico> medicos = repositorioMedico.obtenerTodosLosMedicos();

        return medicos.get(0);
    }


}
