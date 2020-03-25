package com.trs.rerum.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trs.rerum.api.event.RecursoCriadoEvent;
import com.trs.rerum.api.model.Bovino;
import com.trs.rerum.api.service.BovinoService;

@RestController
@RequestMapping("/bovinos")
public class BovinoResource {
	
	@Autowired
	private BovinoService bovinoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Bovino> listar(){
		return bovinoService.buscar();
	}
	
	@PostMapping
	public ResponseEntity<Bovino> criar(@Valid @RequestBody Bovino bovino, HttpServletResponse response) {
		Bovino criado = bovinoService.salvar(bovino);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criado.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(criado);
	}
	
	@GetMapping("/{codigo}")
	public Bovino buscarCodigo(@PathVariable Long codigo) {
		return bovinoService.buscarCodigo(codigo);
	}
	
	@GetMapping("/brinco/{brinco}")
	public List<Bovino> buscarPlaca(@PathVariable String brinco) {
		return bovinoService.buscarBrinco(brinco);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Bovino> buscarCPF(@PathVariable String nome) {
		return bovinoService.buscarNome(nome);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Bovino> atualizar(@PathVariable Long codigo, @Valid @RequestBody Bovino bovino){
		return ResponseEntity.ok(bovinoService.atualizar(codigo, bovino));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		bovinoService.remover(codigo);
	}
	
}
