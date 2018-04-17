package br.com.melhorinvestimento.util;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.dao.CarteiraInvestimentoDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.model.CarteiraInvestimento;

public class AdicionandoCarteiraInvestimento {

	public static void main(String[] args) {

		gerarCarteiraInvestimentoCSLR();

	}

	private static void gerarCarteiraInvestimentoCSLR() {
		Aplicacao goog = null, apple = null, micro = null;

<<<<<<< HEAD
		goog = new Aplicacao("GOOG34/35", "Google Inc.", 0.2, 0.5, 500.0);
		apple = new Aplicacao("AAPL", "Apple Inc.", 0.1, 0.4, 750.0);
		micro = new Aplicacao("MSFT", "Microsoft", 0.252, 0.8, 450.0);
=======
		try {
			goog = new Aplicacao("GOOG34/35", "Google Inc.", 0.2, 0.5, 500.0);
			apple = new Aplicacao("AAPL", "Apple Inc.", 0.1, 0.4, 750.0);
			micro = new Aplicacao("MSFT", "Microsoft", 0.252, 0.8, 450.0);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
>>>>>>> 86c3752092958a189b80c81675e1def28951bc56

		Carteira cslr = new Carteira("CSLR", "Carteira Estável de Baixo Risco ");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		AplicacaoDAO aplicacaDAO = new AplicacaoDAO(em);

		aplicacaDAO.inserir(goog);
		aplicacaDAO.inserir(apple);
		aplicacaDAO.inserir(micro);

		CarteiraDAO carteiraDAO = new CarteiraDAO(em);

		carteiraDAO.inserir(cslr);

		CarteiraInvestimentoDAO carteiraInvestimentoDAO = new CarteiraInvestimentoDAO(em);

		gerarCarteiraInvestimento(carteiraInvestimentoDAO, cslr, goog, 50.0);
		gerarCarteiraInvestimento(carteiraInvestimentoDAO, cslr, apple, 25.0);
		gerarCarteiraInvestimento(carteiraInvestimentoDAO, cslr, micro, 25.0);

		em.getTransaction().commit();
		em.close();
	}

	public static void gerarCarteiraInvestimento(CarteiraInvestimentoDAO carteiraInvestimentoDAO, Carteira carteira,
			Aplicacao aplicacao, Double fatia) {
		CarteiraInvestimento carteiraInvestimento = new CarteiraInvestimento();

		carteiraInvestimento.setCarteira(carteira);
		carteiraInvestimento.setAplicacao(aplicacao);
		carteiraInvestimento.setFatiaInvestimento(fatia);
		carteiraInvestimentoDAO.inserir(carteiraInvestimento);
	}

}
