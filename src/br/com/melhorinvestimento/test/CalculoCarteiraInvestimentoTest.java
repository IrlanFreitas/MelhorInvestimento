package br.com.melhorinvestimento.test;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.controller.CarteiraInvestimentoController;
import br.com.melhorinvestimento.dao.CarteiraDAO;
import br.com.melhorinvestimento.dao.CarteiraInvestimentoDAO;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.model.CarteiraInvestimento;
import br.com.melhorinvestimento.util.JPAUtil;

public class CalculoCarteiraInvestimentoTest {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		CarteiraInvestimentoController investimentoController = new CarteiraInvestimentoController(em);
		CarteiraInvestimentoDAO carteiraInvestimentoDAO = new CarteiraInvestimentoDAO(em);

		CarteiraDAO carteiraDAO = new CarteiraDAO(em);

		Carteira carteira = carteiraDAO.encontrar(1);

		List<CarteiraInvestimento> todasCarteiras = carteiraInvestimentoDAO.getTodasCarteirasComAplicacoes(carteira);
		
		investimentoController.atualiazaoRiscoRendimentoCarteira(todasCarteiras, carteira, carteiraDAO);

		/*if (todasCarteiras.isEmpty()) {
			System.out.println("Não há aplicações vinculada a carteira " + carteira.getNome());
		} else {
			todasCarteiras.stream().forEach(System.out::println);
			List<Aplicacao> todasAplicacoes = todasCarteiras.stream().map(cart -> cart.getAplicacao())
					.collect(Collectors.toList());
			todasAplicacoes.stream().forEach(System.out::println);
		}*/

		em.getTransaction().commit();
		em.close();
	}
}
