package br.com.melhorinvestimento.interfaces;

public interface BaseDAO<T> {
	
	public void inserir(T t);
	
	public void atualizar(T t);
	
	public T encontrar(Integer t);
	
}
