package ar.edu.unlam.tallerweb1.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        System.out.println(authentication.getName());

        if (roles.contains("ROLE_Medico")) {
            response.sendRedirect(request.getContextPath() + "/medico/home");
        }
        else if (roles.contains("ROLE_Paciente")){
            response.sendRedirect(request.getContextPath() + "/paciente/home");
        }
        else if (roles.contains("ROLE_Administrador")){
                response.sendRedirect(request.getContextPath() + "/administrador/home");
        }
    }
}