package com.m2i.formation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.m2i.formation.dao.EntepriseRepository;
import com.m2i.formation.dao.TaxeRepository;
import com.m2i.formation.entities.Entreprise;
import com.m2i.formation.entities.IR;
import com.m2i.formation.entities.TVA;

@SpringBootApplication
public class TaxesApplication  implements CommandLineRunner{
	
	@Autowired
	private EntepriseRepository entepriseRepository ; 
	
	@Autowired
	private TaxeRepository taxeRepository ;
	

	public static void main(String[] args) {
		SpringApplication.run(TaxesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	  Entreprise e1 =entepriseRepository.save(new Entreprise("r1", "r1@gmail.com", "SARL")) ;
	  Entreprise e2 =entepriseRepository.save(new Entreprise("r2", "r2@gmail.com", "SARL")) ;
	  
	  taxeRepository.save(new TVA("tva habitation", new Date(), 900, e1)) ;
	  taxeRepository.save(new TVA("tva voiture", new Date(), 900, e1)) ;
	  taxeRepository.save(new IR("Impot 2016", new Date(), 2200, e2)) ;
	  taxeRepository.save(new TVA("tva habitation", new Date(), 900, e2)) ;
	  
		
	}
}
