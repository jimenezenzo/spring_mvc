package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView irAHomeMedico(Authentication authentication)
    {
        ModelMap modelMap = new ModelMap();
        User user = (User) authentication.getPrincipal();
        modelMap.put("citas", this.servicioMedico.obtenerCitasDelDia(user.getUsername()));
        modelMap.put("modoGuardia", this.servicioMedico.getGuardia(user.getUsername()));

        return new ModelAndView("home/home-medico", modelMap);
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

    @RequestMapping("/citas-consultorio")
    public ModelAndView irAMisCitasConsultorio(Authentication authentication) {
        ModelMap model = new ModelMap();
        User user = (User) authentication.getPrincipal();

        model.put("citas", servicioMedico.obtenerCitasConsultorio(user.getUsername()));

        return new ModelAndView("medico/citas-consultorio", model);
    }

    @RequestMapping("/citas-domicilio")
    public ModelAndView irAMisCitasDomicilio(Authentication authentication) {
        ModelMap model = new ModelMap();
        User user = (User) authentication.getPrincipal();

        model.put("citas", servicioMedico.obtenerCitasDomicilio(user.getUsername()));

        return new ModelAndView("medico/citas-domicilio", model);
    }

    @RequestMapping("/mi-agenda")
    public ModelAndView irAMiAgenda(Authentication authentication, @RequestParam(value = "success", required = false) String success){
        User user = (User) authentication.getPrincipal();
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("agendas", this.servicioMedico.getAgenda(user.getUsername()));
        modelMap.addAttribute("objAgenda", new Agenda());

        if (success != null){
            modelMap.addAttribute("estado", "Se actualizo correctamente el dia en la agenda");
        }

        return new ModelAndView("medico/mi-agenda", modelMap);
    }

    @RequestMapping(value = "/actualizar-agenda", method = RequestMethod.POST)
    public ModelAndView actualizarAgenda(Agenda agenda, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        this.servicioMedico.actualizarAgenda(agenda, user.getUsername());

        return new ModelAndView("redirect:/medico/mi-agenda?success");
    }
}
