package br.com.melhorinvestimento.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	 private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MelhorInvestimentoMySQL");
	 
	 public EntityManager getEntityManager() {
		return emf.createEntityManager();
	 }

}
