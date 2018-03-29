package com.m2i.formation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.formation.dao.EntepriseRepository;
import com.m2i.formation.entities.Entreprise;

@RestController
public class TaxeRestController {
	
	@Autowired
	private EntepriseRepository entepriseRepository ;
	
	@RequestMapping(value="/listEntreprises")
	public Page<Entreprise> list(@RequestParam(name="motCle", defaultValue="") String motCle , 
			@RequestParam(name="page", defaultValue="0")int page , 
			@RequestParam(name="size", defaultValue="4")int size){
		
		return entepriseRepository.chercher("%"+motCle+"%", new PageRequest(page, size));
	}

}
