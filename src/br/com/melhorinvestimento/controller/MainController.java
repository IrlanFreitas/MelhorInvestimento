package br.com.melhorinvestimento.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.model.Recomendacao;

public class MainController {
	
	//usar o controller
	private AplicacaoDAO aplicacaoDAO;
	
	//usar o controller
	private CarteiraDAO carteiraDAO;
	
	private AplicacaoController aplicacaoController;
	
	public MainController(EntityManager em) {
		this.aplicacaoDAO = new AplicacaoDAO(em);
		this.carteiraDAO = new CarteiraDAO(em);
		this.aplicacaoController = new AplicacaoController(em);
	}

	public Recomendacao recomendacao(Double valor, Double riscoMaximo) {
		
		Recomendacao recomendacao = new Recomendacao();
		
		List<Aplicacao> aplicacoes = aplicacaoDAO.encontrarComValorAbaixo(valor);
		
		List<Carteira> carteiras = carteiraDAO.encontrarComValorAbaixo(valor);
		
		List<Aplicacao> aplicacoesCarteiras = aplicacaoController.conversor(carteiras);
		
		aplicacoes.addAll(aplicacoesCarteiras);

		List<Aplicacao> listaFiltrada = aplicacoes.stream().filter( apli -> apli.getEstimativaRisco() < riscoMaximo ).collect(Collectors.toList());
		
		Aplicacao melhorAplicacao = aplicacaoController.verificaMaiorGanho(listaFiltrada, valor);
		
		System.out.println("--------------------- Aplicação Mais Rentável ---------------------");
		System.out.println("De acordo com os critérios apresentados a melhor aplicação é ");
		
		recomendacao.setAplicacao(  melhorAplicacao.getCodigo() + " - " + melhorAplicacao.getDescricao() );
		recomendacao.setRendimento( melhorAplicacao.getEstimativaRendimento() * valor );
		
		System.out.println( recomendacao.getAplicacao() );
		System.out.println(" Ganho estimado ");
		System.out.println( " R$ "+ recomendacao.getRendimento() );
		System.out.println("--------------------- Aplicação Mais Rentável ---------------------");
		
		return recomendacao;
		
	}
	
}
