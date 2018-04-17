package br.com.melhorinvestimento.test;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.controller.AplicacaoController;
import br.com.melhorinvestimento.model.Aplicacao;
import br.com.melhorinvestimento.util.JPAUtil;

public class AplicacaoControllerTest {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		AplicacaoController controller = new AplicacaoController(em);
			
		try {
		
			Aplicacao trell = new Aplicacao("TRELL", "Trello", 4.0 , 0.9, 679.0 );
			em.getTransaction().begin();

			controller.inserir(trell);
			
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
