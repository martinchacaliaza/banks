package com.example.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.models.Bank;
import com.example.app.service.BankService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/Bancos")
@RestController
public class BankControllers {

	@Autowired
	private BankService bankService;

	@ApiOperation(value = "LISTA TODOS LOS BANCOS", notes="")
	@GetMapping
	public Mono<ResponseEntity<Flux<Bank>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(bankService.findAllBanco())

		);
	}

	@ApiOperation(value = "LISTA BANCO POR ID", notes="")
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Bank>> viewId(@PathVariable String id) {
		return bankService.findByIdBanco(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "LISTA BANCO POR RUC", notes="")
	@GetMapping("/ruc/{ruc}")
	public Mono<ResponseEntity<Bank>> viewId2(@PathVariable String ruc) {
		return bankService.viewRucBanco(ruc)
				.map(p -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());		
	}


	@ApiOperation(value = "ACTUALIZA BANCO POR ID", notes="")
	@PutMapping
	public Mono<Bank> updateClientePersonal(@RequestBody Bank cliente) {
		System.out.println(cliente.toString());
		return bankService.saveBanco(cliente);
	}
	
	@ApiOperation(value = "GUARDA BANCO", notes="")
	@PostMapping
	public Mono<Bank> guardarCliente(@RequestBody Bank bank) {
			return bankService.saveBanco(bank);

	}
	
	
	@ApiOperation(value = "ELIMINA BANCO POR ID", notes="")
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteBanco(@PathVariable String id) {
		return bankService.findByIdBanco(id)
				.flatMap(s -> {
			return bankService.deleteBanco(s).
					then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}

}



