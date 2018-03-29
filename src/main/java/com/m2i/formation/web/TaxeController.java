package com.m2i.formation.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.m2i.formation.dao.EntepriseRepository;
import com.m2i.formation.dao.TaxeRepository;
import com.m2i.formation.entities.Entreprise;
import com.m2i.formation.entities.Taxe;

@Controller

public class TaxeController {
	@Autowired
	private EntepriseRepository entepriseRepository;
	
	@Autowired
	private TaxeRepository taxeRepository ;

	@RequestMapping(value = "/entreprises", method = RequestMethod.GET)
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "4") int s, @RequestParam(name = "motCle", defaultValue = "")String motCle) {

		Page<Entreprise> ets = entepriseRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		int[] pages = new int[ets.getTotalPages()];
		model.addAttribute("listEntreprises", ets.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		

		return "entreprises";
	}
	
	
	@RequestMapping(value="/formEntreprise")
	public String formEntreprise(Model model) {
		model.addAttribute("entreprise", new Entreprise());
		
		return "formEntreprise" ;
	}
	
	
	
	@RequestMapping(value="/saveEntreprise")
	public String saveEntreprise(Model model , @Valid Entreprise e, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "formEntreprise";   // s'il ya des erreurs il retourne vers la formulaire
		}
		entepriseRepository.save(e) ;
		
		return "redirect:/entreprises" ;
	}
	
	
	

	@RequestMapping(value="/taxes")
	public String taxes(Model model, @RequestParam(name="code" , defaultValue="-1") Long code ) {
		model.addAttribute("entreprises", entepriseRepository.findAll());
		if(code==-1) 
		{
			model.addAttribute("taxes", new ArrayList<Taxe>()) ; 
		}
		else {
		Entreprise e = new Entreprise() ; 
		e.setCode(code);
	
		model.addAttribute("taxes", taxeRepository.findByEntreprise(e)) ;
		
		}
		
		return "taxes" ;
	}
	

	
	
	

}
