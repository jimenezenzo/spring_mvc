package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaConsultio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCita;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/paciente")
public class ControladorPaciente {

    private ServicioCita servicioCita;
    private ServicioPaciente servicioPaciente;

    @Autowired
    public  ControladorPaciente(ServicioCita servicioCita, ServicioPaciente servicioPaciente){
        this.servicioCita = servicioCita;
        this.servicioPaciente = servicioPaciente;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHomePaciente() {
        return new ModelAndView("home/home-paciente");
    }

    @RequestMapping("/citas/index")
    public ModelAndView irAMisCitas(Authentication authentication) {
        ModelMap model = new ModelMap();
        User user = (User) authentication.getPrincipal();
        model.put("citas", servicioPaciente.getCitaConsultorio(user.getUsername()));

        return new ModelAndView("mis-citas/index", model);
    }

    @RequestMapping("/citas/create")
    public ModelAndView irACrearCita(){
        ModelMap model = new ModelMap();
        model.put("datos", new DatosCitaConsultio());
        model.put("especialidades", servicioCita.allEspecialidades());
        model.put("fechaActual", LocalDate.now());

        return new ModelAndView("mis-citas/create", model);
    }

    @RequestMapping(value = "/citas/store", method = RequestMethod.POST)
    public ModelAndView createCita(@Valid DatosCitaConsultio datosCita, BindingResult result, Authentication authentication){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();
        User user = (User) authentication.getPrincipal();
        datosCita.setPaciente(user.getUsername());
        datosCita.setTipoCita(1);

        if (result.hasErrors()){
            result.getFieldErrors().forEach(error -> {
                errores.add(error.getDefaultMessage());
            });

            model.put("errores", errores);
            return new ModelAndView("redirect:/paciente/citas/create", model);
        }

        try {
            servicioCita.create(datosCita);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("mis-citas/create", model);
        }

        return new ModelAndView("redirect:/paciente/citas/index");
    }
}
