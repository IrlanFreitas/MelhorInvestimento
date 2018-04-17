package br.com.melhorinvestimento.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.dao.CarteiraInvestimentoDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.model.CarteiraInvestimento;

public class CarteiraInvestimentoController {
	
	private EntityManager em;
	
	public CarteiraInvestimentoController(EntityManager em) {
		this.em = em;
	}
	
	public void adicionaAplicacao(Carteira carteira, Aplicacao aplicacao, Double fatia) {
			
		CarteiraDAO carteiraDAO = new CarteiraDAO(em);
		CarteiraInvestimentoDAO carteiraInvestimentoDAO = new CarteiraInvestimentoDAO(em);
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO(em);

		if (fatia < 1 || fatia > 100) {
			throw new IllegalArgumentException("O valor da fatia tem estar em 1 e 100.");
		}
		
		if (carteira.getId() == null || carteira.getId() == 0) {

			carteira = carteiraDAO.encontrar(carteira.getId());
			aplicacao = aplicacaoDAO.encontrar(aplicacao.getId());
			
			CarteiraInvestimento carteiraInvestimento = new CarteiraInvestimento();
			carteiraInvestimento.setCarteira(carteira);
			carteiraInvestimento.setAplicacao(aplicacao);
			carteiraInvestimento.setFatiaInvestimento(fatia);
			carteiraInvestimentoDAO.inserir(carteiraInvestimento);
			
		}
		
		if (carteiraDAO.encontrar(carteira.getId()) == null) {
			
			throw new IllegalArgumentException("Carteira não cadastrada.");
			
		} else {
			List<CarteiraInvestimento> todasCarteirasComAplicacoes = carteiraInvestimentoDAO.getTodasCarteirasComAplicacoes(carteira);
			
			verificarFatia(todasCarteirasComAplicacoes, carteiraInvestimentoDAO, fatia);

			adicionarAplicacaoCarteira(carteira, aplicacao, fatia, carteiraInvestimentoDAO);
			
			todasCarteirasComAplicacoes = carteiraInvestimentoDAO.getTodasCarteirasComAplicacoes(carteira);
			
			//Tecnicamente aqui a lista todasCarteirasComAplicacoes deve estar atualizada
			atualiazaoRiscoRendimentoCarteira(todasCarteirasComAplicacoes, carteira, carteiraDAO);
			
		}
		
	}

	private void adicionarAplicacaoCarteira(Carteira carteira, Aplicacao aplicacao, Double fatia, CarteiraInvestimentoDAO carteiraInvestimentoDAO) {
		CarteiraInvestimento carteiraInvestimento = new CarteiraInvestimento();
		carteiraInvestimento.setCarteira(carteira);
		carteiraInvestimento.setAplicacao(aplicacao);
		carteiraInvestimento.setFatiaInvestimento(fatia);
		carteiraInvestimentoDAO.inserir(carteiraInvestimento);	
	}

	private void verificarFatia(List<CarteiraInvestimento> todasCarteirasComAplicacoes, CarteiraInvestimentoDAO carteiraInvestimentoDAO, Double fatia) {
		
		double sum = todasCarteirasComAplicacoes.stream().mapToDouble( CarteiraInvestimento::getFatiaInvestimento ).sum();
		
		if ((fatia + sum) > 100) {
			throw new IllegalArgumentException("Não foi possível adicionar Aplicação, fatia informada maior que 100%.");
		}
		
	}
	
	public void atualiazaoRiscoRendimentoCarteira(List<CarteiraInvestimento> carteirasInvestimento, Carteira carteira, CarteiraDAO carteiraDAO){
		
		double porcetagemRisco = 0.0 ;
		double porcetagemRendimento = 0.0 ;
		double valorMinimo = 0.0 ;
		DecimalFormat format = new DecimalFormat("#.##");
		
		for (int i = 0; i < carteirasInvestimento.size(); i++) {
			
			Aplicacao aplicacao = carteirasInvestimento.get(i).getAplicacao();
			Double fatiaInvestimento = carteirasInvestimento.get(i).getFatiaInvestimento();
			
			porcetagemRisco += aplicacao.getEstimativaRisco() * fatiaInvestimento;
			porcetagemRendimento += aplicacao.getEstimativaRendimento() * fatiaInvestimento;
			valorMinimo += aplicacao.getValorMinimo();
		}
		
		porcetagemRisco = Double.parseDouble( format.format(porcetagemRisco / 100).replaceAll(",", ".") );
		porcetagemRendimento = Double.parseDouble( format.format(porcetagemRendimento / 100).replaceAll(",", ".") );
		
		System.out.println("Porcentagem de Risco: "+ porcetagemRisco);
		System.out.println("Porcentagem de Rendimento: "+ porcetagemRendimento);
		
		carteira.setRendimento(porcetagemRendimento);
		carteira.setRisco(porcetagemRisco);
		carteira.setValorMinimo(valorMinimo);
		
		carteiraDAO.atualizar(carteira);
	}

}
