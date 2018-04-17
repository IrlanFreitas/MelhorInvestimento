package br.com.melhorinvestimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carteira_investimento")
public class CarteiraInvestimento { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
//	@JoinColumn(unique = true) 
	private Carteira carteira;
	
	@OneToOne
//	@JoinColumn(unique = true)
	private Aplicacao aplicacao;
	
	@Column(name = "fatia_investimento")
	private Double fatiaInvestimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Double getFatiaInvestimento() {
		return fatiaInvestimento;
	}

	public void setFatiaInvestimento(Double fatiaInvestimento) {
		this.fatiaInvestimento = fatiaInvestimento;
	}

	@Override
	public String toString() {
		return "CarteiraInvestimento [id=" + id + ", carteira=" + carteira + ", aplicacao=" + aplicacao
				+ ", fatiaInvestimento=" + fatiaInvestimento + "]";
	}
	
	

}
