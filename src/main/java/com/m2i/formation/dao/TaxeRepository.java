package com.m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.formation.entities.Entreprise;
import com.m2i.formation.entities.Taxe;

public interface TaxeRepository  extends JpaRepository<Taxe, Long>{
	public List<Taxe> findByEntreprise(Entreprise e ) ;

}
