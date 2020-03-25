package com.trs.rerum.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trs.rerum.api.service.exception.BrincoExistenteException;
import com.trs.rerum.api.service.exception.MaeInexistenteException;
import com.trs.rerum.api.service.exception.PaiInexistenteException;

@ControllerAdvice
public class RerumExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String causa = ex.getCause() != null? ex.getCause().toString() : ex.toString();
		
		return handleExceptionInternal(ex, Arrays.asList(new Erro(causa,"Mensagem inválida")), headers, status, request);
	}
	
	@ExceptionHandler({BrincoExistenteException.class})
	protected ResponseEntity<Object> handleBrincoExistenteException(BrincoExistenteException ex, WebRequest request) {
		String causa = ExceptionUtils.getRootCause(ex).toString();
		
		return handleExceptionInternal(ex, Arrays.asList(new Erro(causa,"Esse brinco já está cadastrado!")), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({MaeInexistenteException.class})
	protected ResponseEntity<Object> handleMaeInexistenteException(MaeInexistenteException ex, WebRequest request) {
		String causa = ExceptionUtils.getRootCause(ex).toString();
		
		return handleExceptionInternal(ex, Arrays.asList(new Erro(causa,"Não existe um bovino do sexo F com esse brinco!")), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({PaiInexistenteException.class})
	protected ResponseEntity<Object> handlePaiInexistenteException(PaiInexistenteException ex, WebRequest request) {
		String causa = ExceptionUtils.getRootCause(ex).toString();
		
		return handleExceptionInternal(ex, Arrays.asList(new Erro(causa,"Não existe um bovino do sexo M com esse brinco!")), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		String causa = ExceptionUtils.getRootCause(ex).getMessage();
		
		return handleExceptionInternal(ex, Arrays.asList(new Erro(causa,"Operacao não permitida")), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleEmptyResultDataAccessException() {
		
	}
	
	private List<Erro> criarListaErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		String mensagemUsuario;
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(new Erro(fieldError.toString(), mensagemUsuario));
		}
		
			
		return erros;
	}
	
	public static class Erro{
		private String causa;
		private String mensagemUsuario;
		
		public Erro(String causa, String mensagemUsuario) {
			this.causa = causa;
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getCausa() {
			return causa;
		}

		public void setCausa(String causa) {
			this.causa = causa;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}
		
	}//class Erro
}
