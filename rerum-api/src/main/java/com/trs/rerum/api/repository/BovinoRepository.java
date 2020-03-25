package com.trs.rerum.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trs.rerum.api.model.Bovino;

public interface BovinoRepository extends JpaRepository<Bovino, Long>{
	
	@Query("SELECT b FROM Bovino b WHERE b.nome LIKE %?1%")
	List<Bovino> findByNome(String nome);
	
	@Query("SELECT b FROM Bovino b WHERE b.brinco LIKE %?1%")
	List<Bovino> findByBrinco(String brinco);
}
