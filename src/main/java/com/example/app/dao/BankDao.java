package com.example.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.Bank;
import reactor.core.publisher.Mono;


public interface BankDao extends ReactiveMongoRepository<Bank, String> {

	
	@Query("{ 'ruc' : ?0}")
	Mono<Bank> viewRucBanco(String dni);
	
	
}
