package br.com.melhorinvestimento.util;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.dao.CarteiraInvestimentoDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.model.CarteiraInvestimento;

public class PopularDados {

	public static void main(String[] args) {

		EntityManager em = null ;

		try {

			Aplicacao petr4 = new Aplicacao("PETR4", "Ações da Petrobrás", 0.487, 0.9, 630.0);
			Aplicacao bbse3 = new Aplicacao("BBSE3", "BB Seguridade", 0.357, 0.98, 580.0);
			Aplicacao cdb = new Aplicacao("CDB", "Certificado de Depósito Bancário", 0.153, 0.63, 500.0);
			Aplicacao poup = new Aplicacao("POUP", "Poupança", 0.001, 0.05, 500.0);
			Aplicacao vale5 = new Aplicacao("VALE5", "Vale", 0.698, 1.7, 540.0);
			Aplicacao goog = new Aplicacao("GOOG34/35", "Google Inc.", 0.2, 0.5, 700.0);
			Aplicacao apple = new Aplicacao("AAPL", "Apple Inc.", 0.1, 0.4, 750.0);
			Aplicacao micro = new Aplicacao("MSFT", "Microsoft", 0.252, 0.8, 550.0);

			Carteira clp = new Carteira("CLP", "Carteira LP");
			Carteira cslr = new Carteira("CSLR", "Carteira Estável de Baixo Risco");

			em = new JPAUtil().getEntityManager();

			AplicacaoDAO aplicacaDAO = new AplicacaoDAO(em);

			em.getTransaction().begin();

			aplicacaDAO.inserir(petr4);
			aplicacaDAO.inserir(bbse3);
			aplicacaDAO.inserir(cdb);
			aplicacaDAO.inserir(poup);
			aplicacaDAO.inserir(vale5);

			aplicacaDAO.inserir(goog);
			aplicacaDAO.inserir(apple);
			aplicacaDAO.inserir(micro);

			CarteiraDAO carteiraDAO = new CarteiraDAO(em);

			carteiraDAO.inserir(clp);
			carteiraDAO.inserir(cslr);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
		// CarteiraInvestimentoDAO carteiraInvestimentoDAO = new
		// CarteiraInvestimentoDAO(em);
		//
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, clp, petr4, 20.0);
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, clp, bbse3, 40.0);
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, clp, cdb, 15.0);
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, clp, poup, 15.0);
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, clp, vale5, 10.0);
		//
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, cslr, goog, 50.0);
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, cslr, apple,
		// 25.0);
		// gerarCarteiraInvestimento(carteiraInvestimentoDAO, cslr, micro,
		// 25.0);

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
