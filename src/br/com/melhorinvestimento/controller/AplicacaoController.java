package br.com.melhorinvestimento.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;

public class AplicacaoController {
	
	private AplicacaoDAO dao;
	
	public AplicacaoController(EntityManager em) {
		dao = new AplicacaoDAO(em);
	}
	
	public void inserir(Aplicacao aplicacao){
		
		if (aplicacao.getEstimativaRisco() < 0 || aplicacao.getEstimativaRisco() > 1 ) {
			throw new IllegalArgumentException("Estimativa de risco deve ser > 0 e < 1.");
		}
		if(aplicacao.getValorMinimo() < 500.0){
			throw new IllegalArgumentException("Valor mínimo deve ser maior que 500 .");
		}
		
		if(aplicacao.getValorMinimo() < 500.0){
			throw new IllegalArgumentException("Valor mínimo deve ser maior que 500 .");
		}
		
		dao.inserir(aplicacao);
	}
	
	//POG 
	public List<Aplicacao> conversor( List<Carteira> carteiras ){
		
		if (carteiras == null) {
			throw new IllegalArgumentException("Não há carteiras para converter.");
		}
		
		List<Aplicacao> aplicacoes = new ArrayList<>();
		
		for (int i = 0; i < carteiras.size() ; i++) {
	
			aplicacoes.add(  new Aplicacao(carteiras.get(i).getCodigo(), 
					carteiras.get(i).getNome(), 
					carteiras.get(i).getRisco(), 
					carteiras.get(i).getRendimento(), 
					carteiras.get(i).getValorMinimo()) );
			
		}
		
		return aplicacoes;			
	}

	public Aplicacao verificaMaiorGanho(List<Aplicacao> aplicacoes, Double valor) {
		
		double valorGanho = 0.0;
		int index = 0; 
		
		for (int i = 0; i < aplicacoes.size(); i++) {
			
			double rendimento = aplicacoes.get(i).getEstimativaRendimento() * valor;
			
			if (rendimento > valorGanho) {
				valorGanho = rendimento;
				index = i;
			}
		}
		
		return aplicacoes.get(index);
	}
	
}
