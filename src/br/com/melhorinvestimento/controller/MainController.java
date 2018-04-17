package br.com.melhorinvestimento.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;

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

	public void recomendacao(Double valor, Double riscoMaximo) {
		
		List<Aplicacao> aplicacoes = aplicacaoDAO.encontrarComValorAbaixo(valor);
		
		List<Carteira> carteiras = carteiraDAO.encontrarComValorAbaixo(valor);
		
		List<Aplicacao> aplicacoesCarteiras = aplicacaoController.conversor(carteiras);
		
		aplicacoes.addAll(aplicacoesCarteiras);

		List<Aplicacao> listaFiltrada = aplicacoes.stream().filter( apli -> apli.getEstimativaRisco() < riscoMaximo ).collect(Collectors.toList());
		
		Aplicacao melhorAplicacao = aplicacaoController.verificaMaiorGanho(listaFiltrada, valor);
		
		System.out.println("--------------------- Aplicação Mais Rentável ---------------------");
		System.out.println("De acordo com os critérios apresentados a melhor aplicação é ");
		System.out.println( melhorAplicacao.getCodigo() + " - " + melhorAplicacao.getDescricao());
		System.out.println(" Ganho estimado ");
		System.out.println( " R$ "+ melhorAplicacao.getEstimativaRendimento() * valor );
		System.out.println("--------------------- Aplicação Mais Rentável ---------------------");
		
	}
	
}
