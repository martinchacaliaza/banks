package com.example.app;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.example.app.models.Bank;
import com.example.app.service.BankService;

import reactor.core.publisher.Mono;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@Autowired
	private WebTestClient client; 
	
	@Autowired
	private BankService service;
	
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void listBank() {
		client.get().uri("/api/Bancos/")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBodyList(Bank.class).consumeWith(response -> {
			List<Bank> cli = response.getResponseBody();
			
			cli.forEach(p -> {
				System.out.println(p.getCodigo_banco());
			});
			
			Assertions.assertThat(cli.size()>0).isTrue();
		});;
	}
	
	@Test
	public void findByIdBank() {
		Bank banc = service.findByIdBanco("5e2b167da2cabf58a5688662").block();
		client.get().uri("/api/Bancos/{id}", Collections.singletonMap("id", banc.getId()))
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
	}
	

	@Test
	void saveBank() {	
		Bank bank = new Bank();
		bank.setCodigo_banco("987");
		bank.setRuc("213123121231");
		bank.setRazon_social("Interbank");
		bank.setDireccion("Jr de la Union 103");
		bank.setTelefono("1111111");

		client.post()
		.uri("api/Bancos")
		.body(Mono.just(bank), Bank.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Bank.class)
		.consumeWith(response -> {
			Bank b = response.getResponseBody();
			System.out.println("[Banco REGISTRADO] " + bank);
			Assertions.assertThat(b.getCodigo_banco()).isNotEmpty().isEqualTo("987");
			Assertions.assertThat(b.getRuc()).isNotEmpty().isEqualTo("213123121231");
			Assertions.assertThat(b.getRazon_social()).isNotEmpty().isEqualTo("Interbank");
			Assertions.assertThat(b.getDireccion()).isNotEmpty().isEqualTo("Jr de la Union 103");
			Assertions.assertThat(b.getTelefono()).isNotEmpty().isEqualTo("1111111");
		});
	}
	
	@Test
	void updateBank() {
		Bank bank= service.viewRucBanco("213123121231").block();
		bank.setCodigo_banco("987");
		bank.setRuc("2131231212315");
		bank.setRazon_social("Interbank");
		bank.setDireccion("Jr de la Union 103");
		bank.setTelefono("1111111");
		
		client.put()
		.uri("api/Bancos")
		.body(Mono.just(bank), Bank.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Bank.class)
		.consumeWith(response -> {
			Bank b = response.getResponseBody();
			System.out.println("[Banco REGISTRADO] " + bank);
			Assertions.assertThat(b.getCodigo_banco()).isNotEmpty().isEqualTo("987");
			Assertions.assertThat(b.getRuc()).isNotEmpty().isEqualTo("2131231212315");
			Assertions.assertThat(b.getRazon_social()).isNotEmpty().isEqualTo("Interbank");
			Assertions.assertThat(b.getDireccion()).isNotEmpty().isEqualTo("Jr de la Union 103");
			Assertions.assertThat(b.getTelefono()).isNotEmpty().isEqualTo("1111111");
		});
	}

	
	
	@Test
	void deleteBank() {
		Bank bank= service.viewRucBanco("213123121231").block();	
		client.delete()
		.uri("api/Bancos" + "/{id}", Collections.singletonMap("id", bank.getId()))
		.exchange()
		.expectStatus().isNoContent()
		.expectBody()
		.isEmpty();
	}
	
}
