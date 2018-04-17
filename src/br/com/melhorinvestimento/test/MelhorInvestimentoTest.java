package br.com.melhorinvestimento.test;

import javax.persistence.EntityManager;

import br.com.melhorinvestimento.controller.MainController;
import br.com.melhorinvestimento.util.JPAUtil;

public class MelhorInvestimentoTest {
	
	/*Voc� deve escrever o cen�rio de uma aplica��o, 
	na qual o cliente deve informar qual o valor que deseja investir e o maior valor de risco que est� pronto a assumir (Figura1)
	 
	Em seguida, a aplica��o dever� recuperar todas as aplica��es cujo valor m�nimo de investimento sejam inferiores ao valor que 
	o cliente deseja assumir, verificar dentre estas, quais delas possuem risco estimado menor do que risco m�ximo que o cliente 
	deseja assumir e 
	ent�o, retornar para o cliente qual o c�digo e descri��o da aplica��o que apresente o maior ganho estimado 
	poss�vel (valor que o cliente deseja investir multiplicado pelo rendimento da aplica��o). 
	Este ganho tamb�m dever� ser 
	apresentado como sa�da da aplica��o.*/
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		MainController mainController = new MainController(em);
		
		mainController.recomendacao( 2760.0 , 0.35 );

	}
	
}
