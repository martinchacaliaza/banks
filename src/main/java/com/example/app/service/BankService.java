package com.example.app.service;


import com.example.app.models.Bank;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {

	Flux<Bank> findAllBanco();
	Mono<Bank> findByIdBanco(String id);
	Mono<Bank> saveBanco(Bank clientePersonal);
	Mono<Void> deleteBanco(Bank cliente);
	Mono<Bank> viewRucBanco(String dni);
	
}
