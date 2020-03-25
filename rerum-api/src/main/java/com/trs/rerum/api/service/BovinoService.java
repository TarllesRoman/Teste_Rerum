package com.trs.rerum.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.trs.rerum.api.model.Bovino;
import com.trs.rerum.api.repository.BovinoRepository;
import com.trs.rerum.api.service.exception.BrincoExistenteException;
import com.trs.rerum.api.service.exception.MaeInexistenteException;

@Service
public class BovinoService {
	
	@Autowired
	private BovinoRepository bovinoRepository;

	public Bovino salvar(Bovino bovino) {
		List<Bovino> brinco = bovinoRepository.findByBrinco(bovino.getBrinco());
		if(brinco != null)
			throw new BrincoExistenteException();
		
		if( bovino.getBrincoMae() != null && !bovino.getBrincoMae().isEmpty()) {
			Bovino mae = bovinoRepository.findByBrinco(bovino.getBrincoMae()).get(0);
			if(mae == null) throw new MaeInexistenteException();
			else {
				if ( !mae.getSexo().equals("F") )
					throw new MaeInexistenteException();
			}
		}
		
		if( bovino.getBrincoPai() != null && !bovino.getBrincoPai().isEmpty()) {
			Bovino pai = bovinoRepository.findByBrinco(bovino.getBrincoPai()).get(0);
			if(pai == null) throw new MaeInexistenteException();
			else {
				if ( !pai.getSexo().equals("M") )
					throw new MaeInexistenteException();
			}	
		}
			
		
		
		return bovinoRepository.save(bovino);
	}
	
	public Bovino atualizar(Long codigo, Bovino bovino) {
		Bovino bo = buscarCodigo(codigo);
		if(bo == null) return null;
		
		if(!bovino.getBrincoMae().isEmpty()) {
			Bovino mae = bovinoRepository.findByBrinco(bovino.getBrincoMae()).get(0);
			if(mae == null) throw new MaeInexistenteException();
			else {
				if ( !mae.getSexo().equals("F") )
					throw new MaeInexistenteException();
			}
		}
		
		if(!bovino.getBrincoPai().isEmpty()) {
			Bovino pai = bovinoRepository.findByBrinco(bovino.getBrincoPai()).get(0);
			if(pai == null) throw new MaeInexistenteException();
			else {
				if ( !pai.getSexo().equals("M") )
					throw new MaeInexistenteException();
			}	
		}	
		
		
		return bovinoRepository.save(bovino);
	}
	
	public List<Bovino>	buscar(){
		List<Bovino> bovinos = null;
		
		try {
			bovinos = bovinoRepository.findAll();
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return bovinos;
	}
	
	public List<Bovino>	buscarNome(String nome){
		List<Bovino> bovinos = null;
		
		try {
			bovinos = bovinoRepository.findByNome(nome);
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return bovinos;
	}
	
	public List<Bovino> buscarBrinco(String brinco){
		List<Bovino> bovino = null;
		
		try {
			bovino = bovinoRepository.findByBrinco(brinco);
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return bovino;
	}
	
	public void remover(Long codigo) {
		bovinoRepository.deleteById(codigo);
	}
	
	public Bovino buscarCodigo(Long codigo) {
		Bovino bo = null;
		
		try {
			bo = bovinoRepository.findById(codigo).get();
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return bo;
	}
	
}
