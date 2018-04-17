package br.com.melhorinvestimento.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.melhorinvestimento.interfaces.BaseDAO;
import br.com.melhorinvestimento.model.Carteira;

public class CarteiraDAO implements BaseDAO<Carteira> {

	private EntityManager em;

	public CarteiraDAO(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void inserir(Carteira carteira) {
		em.persist(carteira);
	}
	
	@Override
	public Carteira encontrar(Integer id) {
		return em.find(Carteira.class, id);
	}

	public void atualizar(Carteira carteira) {
		em.merge(carteira);
	}

	public List<Carteira> encontrarComValorAbaixo(Double valor) {
		
		String jpql = " SELECT cart FROM Carteira cart " 
				+ " WHERE cart.valorMinimo < :pValor ";
		
		TypedQuery<Carteira> query = em.createQuery(jpql, Carteira.class);
		
		query.setParameter("pValor", valor);
		
		return query.getResultList();
	}
	
}
