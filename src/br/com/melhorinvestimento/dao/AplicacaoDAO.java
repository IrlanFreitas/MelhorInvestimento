package br.com.melhorinvestimento.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.melhorinvestimento.interfaces.BaseDAO;
import br.com.melhorinvestimento.model.Aplicacao;

public class AplicacaoDAO implements BaseDAO<Aplicacao> {

	private EntityManager em;

	public AplicacaoDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void inserir(Aplicacao aplicacao) {
		em.persist(aplicacao);
	}

	@Override
	public Aplicacao encontrar(Integer id) {
		return em.find(Aplicacao.class, id);
	}

	@Override
	public void atualizar(Aplicacao aplicacao) {
		em.merge(aplicacao);
	}

	public List<Aplicacao> encontrarComValorAbaixo(Double valor) {

		String jpql = " SELECT apli FROM Aplicacao apli " + " WHERE apli.valorMinimo < :pValor ";

		TypedQuery<Aplicacao> query = em.createQuery(jpql, Aplicacao.class);
		query.setParameter("pValor", valor);

		return query.getResultList();

	}

}
