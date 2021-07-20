package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioCitaHistoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cita")
public class ControladorCita {

    private ServicioMedico servicioMedico;
    private ServicioCitaHistoria servicioCitaHistoria;

    @Autowired
    public ControladorCita(ServicioMedico servicioMedico, ServicioCitaHistoria servicioCitaHistoria){
        this.servicioMedico = servicioMedico;
        this.servicioCitaHistoria = servicioCitaHistoria;
    }

    @RequestMapping(path = "/consultorio/{idCita}", method = RequestMethod.GET)
    public ModelAndView irACargarObservaciones(@PathVariable Long idCita) {

        ModelMap model = new ModelMap();
        model.put("cita", this.servicioMedico.obtenerCitaConsultorioId(idCita));

        return new ModelAndView("medico/formularioObservaciones", model);
    }

    @RequestMapping(value = "/consultorio/{idCita}/accion", method = RequestMethod.POST)
    public ModelAndView cargarObservaciones(@PathVariable Long idCita, @RequestParam String observacion, @RequestParam String estado) {
        this.servicioCitaHistoria.observarCitaConsultorio(idCita, observacion, estado);

        return new ModelAndView("redirect:/cita/consultorio/" + idCita);
    }
}
