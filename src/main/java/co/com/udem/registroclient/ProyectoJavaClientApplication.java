package co.com.udem.registroclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import co.com.udem.registroclient.entities.UserToken;

@SpringBootApplication
@EnableEurekaClient
public class ProyectoJavaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoJavaClientApplication.class, args);
	}
	
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
   
    @Bean
    UserToken userToken() {
        return new UserToken();
    }

}
