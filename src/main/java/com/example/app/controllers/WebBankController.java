package com.example.app.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;



import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/Bancos")
public class WebBankController {
	
	@Value("${com.bootcamp.gateway.url}")
	String valor;
	
	
	WebClient webClient;
	@PostConstruct
	 public void init() {
	
			 webClient = WebClient 
			.builder()
			.baseUrl("http://"+valor+"/producto_bancario/api/ProductoBancario")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			.build(); 		 
	}
	
  /* @GetMapping("/ProductoBancario/{dniCliente}")
   public Flux<b> getFamilyList(@PathVariable String dniCliente) 
   { 
		return webClient.get().uri("/dni/"+dniCliente).retrieve().bodyToFlux(CurrentAccount.class); 
   }
  */

}
