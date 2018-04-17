package br.com.melhorinvestimento.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.melhorinvestimento.interfaces.BaseDAO;
import br.com.melhorinvestimento.model.Carteira;
import br.com.melhorinvestimento.model.CarteiraInvestimento;

public class CarteiraInvestimentoDAO implements BaseDAO<CarteiraInvestimento> {

	private EntityManager em;

	public CarteiraInvestimentoDAO(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void inserir(CarteiraInvestimento carteiraInvestimento) {
		this.em.persist(carteiraInvestimento);
	}

	@Override
	public CarteiraInvestimento encontrar(Integer id) {
		return this.em.find(CarteiraInvestimento.class, id);
	}

	public List<CarteiraInvestimento> getTodasCarteirasComAplicacoes(Carteira carteira) {

		// Trocar por um find na list
		String jpql = " SELECT cart FROM CarteiraInvestimento cart " 
				+ " LEFT JOIN FETCH cart.aplicacao "
				+ " WHERE cart.carteira = :pCarteira ";

		TypedQuery<CarteiraInvestimento> query = this.em.createQuery(jpql, CarteiraInvestimento.class);

		query.setParameter("pCarteira", carteira);

		List<CarteiraInvestimento> carteiras = query.getResultList();

		return carteiras;
	}

	@Override
	public void atualizar(CarteiraInvestimento carteiraInvestimento) {
		this.em.persist(carteiraInvestimento);
	}
}
