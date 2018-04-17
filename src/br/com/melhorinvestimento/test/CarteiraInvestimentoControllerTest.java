package br.com.melhorinvestimento.test;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.controller.CarteiraInvestimentoController;
import br.com.melhorinvestimento.dao.AplicacaoDAO;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.util.JPAUtil;

public class CarteiraInvestimentoControllerTest {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		CarteiraInvestimentoController investimentoController = new CarteiraInvestimentoController(em);
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO(em);
		CarteiraDAO carteiraDAO = new CarteiraDAO(em);
		
		Carteira clp = carteiraDAO.encontrar(1);
		
		Aplicacao petr4 = aplicacaoDAO.encontrar(1);
		Aplicacao bbse3 = aplicacaoDAO.encontrar(2);
		Aplicacao cdb = aplicacaoDAO.encontrar(3);
		Aplicacao poup = aplicacaoDAO.encontrar(4);
		Aplicacao vale5 = aplicacaoDAO.encontrar(5);
		
		investimentoController.adicionaAplicacao(clp, petr4, 20.0);
		investimentoController.adicionaAplicacao(clp, bbse3, 40.0);
		investimentoController.adicionaAplicacao(clp, cdb, 15.0);
		investimentoController.adicionaAplicacao(clp, poup, 15.0);
		investimentoController.adicionaAplicacao(clp, vale5, 10.0);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
