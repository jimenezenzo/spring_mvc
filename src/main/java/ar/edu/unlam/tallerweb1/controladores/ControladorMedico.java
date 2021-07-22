package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.configuraciones.SortByDateTime;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioCitaDomicilio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCitaHistoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/medico")
public class ControladorMedico {

    ServicioCitaDomicilio servicioCitaDomicilio;
    ServicioMedico servicioMedico;
    ServicioCitaHistoria servicioCitaHistoria;

    @Autowired
    public ControladorMedico(ServicioCitaDomicilio servicioCitaDomicilio, ServicioMedico servicioMedico, ServicioCitaHistoria servicioCitaHistoria) {
        this.servicioCitaDomicilio = servicioCitaDomicilio;
        this.servicioMedico = servicioMedico;
        this.servicioCitaHistoria = servicioCitaHistoria;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHomeMedico(Authentication authentication) {
        ModelMap modelMap = new ModelMap();
        User user = (User) authentication.getPrincipal();

        modelMap.put("modoGuardia", this.servicioMedico.getGuardia(user.getUsername()));
        modelMap.put("citasDelDia", true);
        modelMap.put("citas", servicioMedico.obtenerCitasDelDia(user.getUsername()));

        if (this.servicioMedico.getGuardia(user.getUsername())) {

            return new ModelAndView("medico/citas-domicilio", modelMap);
        } else {

            return new ModelAndView("medico/citas-consultorio", modelMap);
        }
    }


    @RequestMapping(path = "/todas-las-citas-mapa", method = RequestMethod.GET)
    public ModelAndView verTodasLasCitasEnElMapa(Authentication authentication) {

        ModelMap model = new ModelMap();

        User user = (User) authentication.getPrincipal();

        model.put("citas", servicioMedico.obtenerCitasDomicilio(user.getUsername()));

        return new ModelAndView("maps/mapa-todas-las-citas-domicilio", model);
    }

    @RequestMapping(value = "/mapa/{id}", method = RequestMethod.GET)
    public ModelAndView mapaMedico(@PathVariable Long id) {

        CitaDomicilio citaDomicilio = servicioCitaDomicilio.getCitaById(id);

        Float latitud = citaDomicilio.getLatitud();
        Float longitud = citaDomicilio.getLongitud();

        ModelMap model = new ModelMap();
        model.put("latitud", latitud);
        model.put("longitud", longitud);

        return new ModelAndView("maps/mapa-citas-individuales", model);
    }


    @RequestMapping("/mapa-citas-domicilio-todas")
    public ModelAndView mapaMedicoTodas(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ModelMap model = new ModelMap();
        List<CitaDomicilio> listaCitaDomicilio = servicioMedico.obtenerCitasDomicilio(user.getUsername());
        List<CitaDomicilio> listaFiltrada = citasDomicilioPendientes(listaCitaDomicilio);
        model.put("citas", listaFiltrada);
        model.put("cantidad", listaFiltrada.size());
        return new ModelAndView("maps/mapa-citas-domicilio-todas", model);
    }

    @RequestMapping("/citas-domicilio")
    public ModelAndView irAMisCitasDomicilio(Authentication authentication) {
        ModelMap model = new ModelMap();
        User user = (User) authentication.getPrincipal();
        List<CitaDomicilio> listaCitaDomicilio = servicioMedico.obtenerCitasDomicilio(user.getUsername());
        /*List<CitaDomicilio> listaFiltrada = citasDomicilioFinalizadas(listaCitaDomicilio);*/

        model.put("citas", listaCitaDomicilio);
        model.put("citasDelDia", false);
        return new ModelAndView("medico/citas-domicilio", model);
    }



    @RequestMapping("/citas-consultorio")
    public ModelAndView irAMisCitasConsultorio(Authentication authentication) {
        ModelMap model = new ModelMap();
        User user = (User) authentication.getPrincipal();
        List<CitaConsultorio> listaCitaConsultorio = servicioMedico.obtenerCitasConsultorio(user.getUsername());
        /*List<CitaConsultorio> listaFiltrada = citasConsultorioFinalizadas(listaCitaConsultorio);*/

        model.put("citas", listaCitaConsultorio);
        model.put("citasDelDia", false);
        return new ModelAndView("medico/citas-consultorio", model);
    }


    @RequestMapping("/mi-agenda")
    public ModelAndView irAMiAgenda(Authentication authentication, @RequestParam(value = "success", required = false) String success) {
        User user = (User) authentication.getPrincipal();
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("agendas", this.servicioMedico.getAgenda(user.getUsername()));
        modelMap.addAttribute("objAgenda", new Agenda());

        if (success != null) {
            modelMap.addAttribute("estado", "Se actualizo correctamente el dia en la agenda");
        }

        return new ModelAndView("medico/mi-agenda", modelMap);
    }

    @RequestMapping(value = "/actualizar-agenda", method = RequestMethod.POST)
    public ModelAndView actualizarAgenda(Agenda agenda, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.servicioMedico.actualizarAgenda(agenda, user.getUsername());
        return new ModelAndView("redirect:/medico/mi-agenda?success");
    }

    private List<CitaDomicilio> citasDomicilioPendientes(List<CitaDomicilio> listaCitaDomicilio) {
        List<CitaDomicilio> listaFiltrada = new ArrayList<>();

        for (CitaDomicilio citaDomicilioi : listaCitaDomicilio) {
            /*for (CitaHistoria citaHistoriai : citaDomicilioi.getCitaHistoriaList()) {
                if (citaHistoriai.getObservacion().equals("Creado")) {
                    listaFiltrada.add(citaDomicilioi);
                }
            }*/
            if (citaDomicilioi.getUltimaHistoria().getEstado() == EstadoCita.PENDIENTE)
                listaFiltrada.add(citaDomicilioi);
        }

        listaFiltrada.sort(new SortByDateTime());

        return listaFiltrada;
    }

    private List<CitaDomicilio> citasDomicilioFinalizadas(List<CitaDomicilio> listaCitaDomicilio) {
        List<CitaDomicilio> listaFiltrada = new ArrayList<>();

        for (CitaDomicilio citaDomicilioi : listaCitaDomicilio) {
            for (CitaHistoria citaHistoriai : citaDomicilioi.getCitaHistoriaList()) {
                if (!citaHistoriai.getObservacion().equals("Creado")) {
                    listaFiltrada.add(citaDomicilioi);
                }
            }
            if (citaDomicilioi.getUltimaHistoria().getEstado() != EstadoCita.PENDIENTE)
                listaFiltrada.add(citaDomicilioi);
        }
        return listaFiltrada;
    }

    private List<CitaConsultorio> citasConsultorioPendientes(List<CitaConsultorio> listaCitaConsultorio) {
        List<CitaConsultorio> listaFiltrada = new ArrayList<>();
        for (CitaConsultorio citaConsultorioi : listaCitaConsultorio) {
            for (CitaHistoria citaHistoriai : citaConsultorioi.getCitaHistoriaList()) {
                if (citaHistoriai.getObservacion().equals("Creado")) {
                    listaFiltrada.add(citaConsultorioi);
                }
            }
        }
        return listaFiltrada;
    }

    private List<CitaConsultorio> citasConsultorioFinalizadas(List<CitaConsultorio> listaCitaConsultorio) {
        List<CitaConsultorio> listaFiltrada = new ArrayList<>();
        for (CitaConsultorio citaConsultorioi : listaCitaConsultorio) {
            for (CitaHistoria citaHistoriai : citaConsultorioi.getCitaHistoriaList()) {
                if (!citaHistoriai.getObservacion().equals("Creado")) {
                    listaFiltrada.add(citaConsultorioi);
                }
            }
        }
        return listaFiltrada;
    }
}
