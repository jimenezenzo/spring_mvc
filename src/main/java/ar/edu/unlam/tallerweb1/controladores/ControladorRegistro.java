package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorRegistro(ServicioUsuario servicioUsuario){
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping("/registro")
    public ModelAndView irARegistro(@RequestParam(value = "message", required = false) String message){
        ModelMap model = new ModelMap();
        if (message != null){
            model.put("message", message);
        }
        model.put("paciente", new Paciente());

        return new ModelAndView("auth/register", model);
    }

    @RequestMapping("/registro/store")
    public ModelAndView store(Paciente paciente) throws Throwable {
        ModelMap model = new ModelMap();
        try {
            servicioUsuario.registrarPaciente(paciente);
        }
        catch (RuntimeException e){
            model.put("error", "Hubo un error al registrar");
            return new ModelAndView("auth/register", model);
        }

        model.put("message", "Usuario creado correctamente");
        return new ModelAndView("redirect:/registro", model);
    }
}
