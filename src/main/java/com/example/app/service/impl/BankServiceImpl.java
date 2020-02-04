package com.example.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.Bank;
import com.example.app.repository.BankDao;
import com.example.app.service.BankService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	public BankDao bankDao;
	
	@Override
	public Flux<Bank> findAllBanco()
	{
	return bankDao.findAll();
	
	}
	@Override
	public Mono<Bank> findByIdBanco(String id)
	{
	return bankDao.findById(id);
	
	}
	
	@Override
	public Mono<Bank> viewRucBanco(String ruc)
	{
	return bankDao.findByRuc(ruc);
	
	}
	
	@Override
	public Mono<Bank> saveBanco(Bank bank)
	{
	return bankDao.save(bank);
	}
	
	@Override
	public Mono<Void> deleteBanco(Bank bank) {
		return bankDao.delete(bank);
	}
	
}
