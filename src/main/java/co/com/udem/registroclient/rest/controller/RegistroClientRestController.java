package co.com.udem.registroclient.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import co.com.udem.registroclient.domain.AutenticationRequestDTO;
import co.com.udem.registroclient.domain.AutenticationResponseDTO;
import co.com.udem.registroclient.domain.RegistroPropieadadDto;
import co.com.udem.registroclient.entities.UserToken;
import co.com.udem.registroclient.entities.factory.UserTokenFactory;
import co.com.udem.registroclient.repositories.UserTokenRepository;

@RestController
public class RegistroClientRestController {
	@Autowired
    RestTemplate restTemplate;
   
    @Autowired
    UserTokenRepository userTokenRepository;
   
    @Autowired
    UserToken userToken;
    
    @Autowired
    private LoadBalancerClient loadBalancer;
    
	private static final Logger log = LoggerFactory.getLogger(RegistroClientRestController.class);
   
   
    @PutMapping("/autenticar")
    public String autenticar(@RequestBody AutenticationRequestDTO autenticationRequestDTO) {
    	ServiceInstance serviceInstance=loadBalancer.choose("proyectojava");
         
        String baseUrl=serviceInstance.getUri().toString();       
        baseUrl=baseUrl+"/auth/signin";
        ResponseEntity<String> postResponse = restTemplate.postForEntity(baseUrl, autenticationRequestDTO, String.class);
  
        Gson g = new Gson();
        AutenticationResponseDTO autenticationResponseDTO = g.fromJson(postResponse.getBody(), AutenticationResponseDTO.class);
        
        UserToken entity=userTokenRepository.obtenerToken(autenticationResponseDTO.getUsername());
                
        if (userTokenRepository.findById(entity.getId()).isPresent())
        {   entity.setId(entity.getId());
        	entity.setUsername(entity.getUsername());
        	entity.setToken(autenticationResponseDTO.getToken());
            userTokenRepository.save(entity);               
        } else {     
	        userToken.setUsername(autenticationResponseDTO.getUsername());
	        userToken.setToken(autenticationResponseDTO.getToken());
	        userTokenRepository.save(userToken);
        }
        return autenticationResponseDTO.getToken();	
    }

    @GetMapping("/consultarPropiedades/")
    public List<RegistroPropieadadDto> consultarPropiedad(@RequestParam(value = "username") String identificacion) throws JsonProcessingException {
        List<RegistroPropieadadDto> registroPropieadadDto = null;
        ServiceInstance serviceInstance=loadBalancer.choose("proyectojava");
        String baseUrl=serviceInstance.getUri().toString();  
        baseUrl=baseUrl+"/propiedades/PropiedadesUsuario/";
         
        AutenticationResponseDTO autenticationResponseDTO=UserTokenFactory.toModel(userTokenRepository.obtenerToken(identificacion));
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        headers.set("Authorization", "Bearer "+autenticationResponseDTO.getToken());
          
        
        HttpEntity<RegistroPropieadadDto> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl+identificacion, HttpMethod.GET, entity, String.class);
      
        try {
        	registroPropieadadDto = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<RegistroPropieadadDto>>() {});
        } catch (Exception e) {
          
        	log.debug("Error: consultarPropiedad",e); 
        	
        }
       
        return  registroPropieadadDto;
       
    }
}
