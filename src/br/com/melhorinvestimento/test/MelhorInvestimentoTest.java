package br.com.melhorinvestimento.test;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.controller.MainController;
import br.com.melhorinvestimento.util.JPAUtil;

public class MelhorInvestimentoTest {
	
	/*Você deve escrever o cenário de uma aplicação, 
	na qual o cliente deve informar qual o valor que deseja investir e o maior valor de risco que está pronto a assumir (Figura1)
	 
	Em seguida, a aplicação deverá recuperar todas as aplicações cujo valor mínimo de investimento sejam inferiores ao valor que 
	o cliente deseja assumir, verificar dentre estas, quais delas possuem risco estimado menor do que risco máximo que o cliente 
	deseja assumir e 
	então, retornar para o cliente qual o código e descrição da aplicação que apresente o maior ganho estimado 
	possível (valor que o cliente deseja investir multiplicado pelo rendimento da aplicação). 
	Este ganho também deverá ser 
	apresentado como saída da aplicação.*/
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		MainController mainController = new MainController(em);
		
		mainController.recomendacao( 2760.0 , 0.35 );

	}
	
}
