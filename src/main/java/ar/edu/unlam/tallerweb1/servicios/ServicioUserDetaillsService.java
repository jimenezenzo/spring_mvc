package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioUserDetaillsService implements UserDetailsService {

    private ServicioLogin servicioLogin;

    @Autowired
    public ServicioUserDetaillsService(ServicioLogin servicioLogin){
        this.servicioLogin = servicioLogin;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = servicioLogin.consultarUsuarioEmail(username);

        if (usuario != null){

            return org.springframework.security.core.userdetails.
                    User.withUsername(usuario.getName())
                    .password(usuario.getPassword())
                    .roles(usuario.getRol()).build();
        }
        else {
            throw new UsernameNotFoundException("No se encontro usuario");
        }
    }
}
