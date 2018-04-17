package br.com.melhorinvestimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aplicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String codigo;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false, name = "estimativa_risco")
	private Double estimativaRisco;
	
	@Column(nullable = false, name = "estimativa_rendimento")
	private Double estimativaRendimento;
	
	@Column(nullable = false)
	private Double valorMinimo;
<<<<<<< HEAD
	
	public Aplicacao() {}
	
	public Aplicacao(String codigo, String descricao, Double estimativaRisco, Double estimativaRendimento, Double valorMinimo) {
=======
	
//	@OneToMany(mappedBy = "aplicacao")
//	private List<CarteiraInvestimento> carteirasInvestimento;
	
	public Aplicacao() {}
	
	public Aplicacao(String codigo, String descricao, Double estimativaRisco, Double estimativaRendimento, Double valorMinimo) throws IllegalAccessException {
>>>>>>> 86c3752092958a189b80c81675e1def28951bc56
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.estimativaRisco = estimativaRisco;
		this.estimativaRendimento = estimativaRendimento;
<<<<<<< HEAD
=======
		if (valorMinimo < 500.0) {
			throw new IllegalAccessException("Valor da aplicação deve ser maior que 500.0");
		}
>>>>>>> 86c3752092958a189b80c81675e1def28951bc56
		this.valorMinimo = valorMinimo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getEstimativaRisco() {
		return estimativaRisco;
	}

	public void setEstimativaRisco(Double estimativaRisco) {
		this.estimativaRisco = estimativaRisco;
	}

	public Double getEstimativaRendimento() {
		return estimativaRendimento;
	}

	public void setEstimativaRendimento(Double estimativaRendimento) {
		this.estimativaRendimento = estimativaRendimento;
	}

	public Double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(Double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	@Override
	public String toString() {
		return "Aplicacao [id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", estimativaRisco="
				+ estimativaRisco + ", estimativaRendimento=" + estimativaRendimento + ", valorMinimo=" + valorMinimo
				+ "]";
	}
	
}
