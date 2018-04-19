package br.com.melhorinvestimento.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.melhorinvestimento.controller.MainController;
import br.com.melhorinvestimento.util.JPAUtil;


public class Recomendacao extends JFrame {


	private static final long serialVersionUID = 1877726073992468597L;
	
	private JPanel contentPane;
	private JTextField textValor;
	private JTextField textRisco;
	private JTextField textMelhorRendimento;
	private JTextField textMelhorAplicacao;
	private MainController mainController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Recomendacao frame = new Recomendacao();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Recomendacao() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuscarAplicacoes = new JButton(" Buscar Aplica\u00E7\u00F5es");
		btnBuscarAplicacoes.setBackground(Color.BLUE);
		btnBuscarAplicacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mainController = new MainController(em);
				Double valor = Double.parseDouble( textValor.getText() );
				Double risco = Double.parseDouble( textRisco.getText() );
				br.com.melhorinvestimento.model.Recomendacao recomendacao = mainController.recomendacao(valor, risco);
				
				String aplicacao = recomendacao.getAplicacao();
				Double rendimento = recomendacao.getRendimento();
				textMelhorAplicacao.setText(aplicacao);
				textMelhorRendimento.setText(rendimento.toString());
			}
		});
		btnBuscarAplicacoes.setBounds(63, 23, 315, 53);
		contentPane.add(btnBuscarAplicacoes);
		
		textValor = new JTextField();
		textValor.setBounds(119, 96, 259, 28);
		contentPane.add(textValor);
		textValor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Valor:");
		lblNewLabel.setBounds(63, 103, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRisco = new JLabel("Risco:");
		lblRisco.setBounds(63, 152, 46, 14);
		contentPane.add(lblRisco);
		
		textRisco = new JTextField();
		textRisco.setColumns(10);
		textRisco.setBounds(119, 145, 259, 28);
		contentPane.add(textRisco);
		
		JLabel label = new JLabel("0");
		label.setBounds(119, 184, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("1");
		label_1.setBounds(372, 184, 15, 14);
		contentPane.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel("------------------------------------------------------------------------------");
		lblNewLabel_1.setBounds(63, 235, 315, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("------------------------------------------------------------------------------");
		label_2.setBounds(63, 423, 315, 14);
		contentPane.add(label_2);
		
		JLabel lblNewLabel_2 = new JLabel("Aplica\u00E7\u00E3o mais rent\u00E1vel");
		lblNewLabel_2.setBounds(155, 260, 125, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblDeAcordoCom = new JLabel("De acordo com os crit\u00E9rios apresentados a melhor aplica\u00E7\u00E3o \u00E9");
		lblDeAcordoCom.setBounds(63, 285, 315, 28);
		contentPane.add(lblDeAcordoCom);
		
		JLabel lblGanhoEstimado = new JLabel("Ganho estimado");
		lblGanhoEstimado.setBounds(165, 368, 95, 14);
		contentPane.add(lblGanhoEstimado);
		
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(129, 398, 22, 14);
		contentPane.add(lblR);
		
		textMelhorRendimento = new JTextField();
		textMelhorRendimento.setEnabled(false);
		textMelhorRendimento.setBounds(155, 393, 106, 20);
		contentPane.add(textMelhorRendimento);
		textMelhorRendimento.setColumns(10);
		
		textMelhorAplicacao = new JTextField();
		textMelhorAplicacao.setEnabled(false);
		textMelhorAplicacao.setBounds(63, 324, 315, 20);
		contentPane.add(textMelhorAplicacao);
		textMelhorAplicacao.setColumns(10);
		

	}
}
