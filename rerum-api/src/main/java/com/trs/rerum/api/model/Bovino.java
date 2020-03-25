package com.trs.rerum.api.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bovino")
public class Bovino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(max = 8)
	private String brinco;
	
	@NotNull
	@Size(max = 20)
	private String nome;
	
	@Size(max = 15)
	private String situacao;
	
	@Size(max = 1)
	private String sexo;
	
	@Size(max = 8)
	private String brincoMae;
	
	@Size(max = 8)
	private String brincoPai;
	
	@Size(max = 15)
	private String raca;
	
	@Column(name="nascimento")
	private Date nascimento;
	
	private Date prenhes;
	
	@Transient
	private Date parto;
	
	private Date ultimoParto;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getBrinco() {
		return brinco;
	}

	public void setBrinco(String brinco) {
		this.brinco = brinco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getBrincoMae() {
		return brincoMae;
	}

	public void setBrincoMae(String brincoMae) {
		this.brincoMae = brincoMae;
	}

	public String getBrincoPai() {
		return brincoPai;
	}

	public void setBrincoPai(String brincoPai) {
		this.brincoPai = brincoPai;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Date getPrenhes() {
		return prenhes;
	}

	//Ao alterar a data da prenhes, automaticamente a data do parto é alterada
	public void setPrenhes(Date prenhes) {
		this.prenhes = prenhes;
		if(prenhes == null) return;
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(prenhes.getTime());
		cal.add(Calendar.MONTH, 9);
		
		this.parto = new Date(cal.getTimeInMillis());
	}

	public Date getUltimoParto() {
		return ultimoParto;
	}

	public void setUltimoParto(Date ultimoParto) {
		this.ultimoParto = ultimoParto;
	}

	//Nada é feito nesse método, @see setPrenhes
	public void setParto(Date parto) {
		
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getParto() {
		if(prenhes == null) return null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(prenhes.getTime());
		cal.add(Calendar.MONTH, 9);
		
		this.parto = new Date(cal.getTimeInMillis());
		
		return parto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bovino other = (Bovino) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
