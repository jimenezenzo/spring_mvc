package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cita;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.servicios.ServicioCitaDomicilio;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/medico")
public class ControladorMedico {

    ServicioCitaDomicilio servicioCitaDomicilio;

    ServicioMedico servicioMedico;

    @Autowired
    public ControladorMedico(ServicioCitaDomicilio servicioCitaDomicilio, ServicioMedico servicioMedico) {
        this.servicioCitaDomicilio = servicioCitaDomicilio;
        this.servicioMedico = servicioMedico;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHomeMedico()
    {
        return new ModelAndView("medico/citas-del-dia");
    }

    @RequestMapping(value = "/mapa/{id}", method = RequestMethod.POST)
    public ModelAndView mapaMedico(@PathVariable Long id){


        CitaDomicilio citaDomicilio = servicioCitaDomicilio.getCitaById(id);

        Float latitud = citaDomicilio.getLatitud();
        Float longitud = citaDomicilio.getLongitud();

        ModelMap model = new ModelMap();
        model.put("latitud", latitud);
        model.put("longitud", longitud);

        return new ModelAndView("maps/mapaMedico", model);
    }

    @RequestMapping("/citas-del-dia")
    public ModelAndView irAMisCitas(Authentication authentication) {
        ModelMap model = new ModelMap();
        User user = (User) authentication.getPrincipal();

        model.put("citas", servicioMedico.obtenerCitasDomicilio(user.getUsername()));

        return new ModelAndView("medico/citas-del-dia", model);
    }

}
