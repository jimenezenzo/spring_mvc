package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaHistoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioCitaHistoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedico;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
        model.put("datosCitaHistoria", new DatosCitaHistoria());

        return new ModelAndView("medico/formularioObservaciones", model);
    }

    @RequestMapping(value = "/consultorio/{idCita}/accion", method = RequestMethod.POST)
    public ModelAndView cargarObservaciones(@PathVariable Long idCita, DatosCitaHistoria datosCitaHistoria){
        ModelMap modelMap = new ModelMap();
        datosCitaHistoria.setIdCita(idCita);

        try {
            this.servicioCitaHistoria.observarCitaConsultorio(datosCitaHistoria);
        } catch (IOException e) {
            modelMap.put("error", e.getMessage());
        }

        return new ModelAndView("redirect:/cita/consultorio/" + idCita, modelMap);
    }

    @RequestMapping(value = "/consultorio/{idCita}/cancelar", method = RequestMethod.POST)
    public ModelAndView cancelarCita(@PathVariable Long idCita){
        this.servicioCitaHistoria.cancelarCitaPaciente(idCita);

        return new ModelAndView("redirect:/cita/consultorio/" + idCita);
    }

    @RequestMapping("/file/{file}/descarga")
    public void download(@PathVariable String file, HttpServletResponse response) throws IOException {
        this.servicioCitaHistoria.descargarArchivo(file, response);
    }
}
