package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.datos.DatosAltaUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/administrador")
public class ControladorAdmin {

    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorAdmin(ServicioUsuario servicioUsuario){
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHomeMedico()
    {
        return new ModelAndView("home/home-administrador");
    }

    @RequestMapping("/altas-usuario")
    public ModelAndView irAAltaUsuario( @RequestParam(value = "success", required = false) String success){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("datosAltaUsuario", new DatosAltaUsuario());

        if (success != null){
            modelMap.addAttribute("exito", "El alta se creo correctamente");
        }

        return new ModelAndView("administrador/alta-usuario", modelMap);
    }

    @RequestMapping(value = "/registrar-paciente", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(DatosAltaUsuario datosAltaUsuario){
        try {
            this.servicioUsuario.altaUsuario(datosAltaUsuario);
        }
        catch (RuntimeException e){
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("errores", Arrays.asList(e.getMessage()));
            return new ModelAndView("administrador/alta-usuario", modelMap);
        }

        return new ModelAndView("redirect:/administrador/altas-usuario?success");
    }
}
