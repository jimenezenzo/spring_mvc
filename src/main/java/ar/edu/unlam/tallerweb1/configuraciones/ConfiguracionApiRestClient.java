package ar.edu.unlam.tallerweb1.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracionApiRestClient {

    @Bean
    public RestTemplate restTemplate() {

        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(6000);
        factory.setReadTimeout(6000);
        return new RestTemplate(factory);
    }

}
