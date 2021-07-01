package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.servicios.ServicioCita;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorApi {
    private ServicioCita servicioCita;
    private ServicioMedico servicioMedico;

    @Autowired
    public  ControladorApi(ServicioCita servicioCita, ServicioMedico servicioMedico){
        this.servicioCita = servicioCita;
        this.servicioMedico = servicioMedico;
    }

    @RequestMapping("/medicos")
    public ResponseEntity<List<Medico>> getMedicos(){

        return new ResponseEntity<>(servicioMedico.obtenerMedicosTodos(), HttpStatus.OK);
    }

    @RequestMapping("medicos/{idEspecialidad}")
    public ResponseEntity<List<Medico>> getMedicosByEspecialidad(@PathVariable Long idEspecialidad){

        return new ResponseEntity<>(servicioCita.medicosByEspecialidad(idEspecialidad), HttpStatus.OK);
    }

    @RequestMapping("horarios/{medico}/{fecha}")
    public List<String> horariosMedico(@PathVariable Long medico, @PathVariable String fecha){

        return this.servicioMedico.getHorariosDia(medico, fecha);
    }
}
