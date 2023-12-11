package jogo;
import jogo.Grafico.Corrida;
import jogo.Grafico.KeyHandler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Jogo extends JFrame implements ActionListener{
	
	private MenuPrincipal menuPrincipal;
	private MenuPrincipalDefinitivo menuPrincipalDefinitivo;
    private boolean jogoIniciado;
    public String situacaoJogo;
    private Timer timer;
    private int teste = 0;
	
	public Jogo() {
		
		/*timer = new Timer(5, this);
		this.situacaoJogo = "Nao Iniciado";
		menuPrincipalDefinitivo = new MenuPrincipalDefinitivo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,400);
		setLocationRelativeTo(null);
		setResizable(false);
		add(menuPrincipalDefinitivo);
		setVisible(true);*/
		
		/*menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menuPrincipal.setSize(720, 500);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setResizable(false);
		menuPrincipal.setVisible(true);*/
		jogoIniciado();
		//timer.start();
	}
	
	public void testeSeOJogoFoiIniciado() {
		//teste++;
		if(teste == 200) {
			situacaoJogo = "Iniciado";
		}
		if(menuPrincipalDefinitivo.getSituacaoJogo() == "Iniciado") {
		    jogoIniciado();
		    timer.stop();
		} else if(menuPrincipalDefinitivo.getSituacaoJogo() == "Fechar") {
			setVisible(false);
		}
		
	}
	
	public void jogoIniciado () {
		JFrame telaDaCorrida = new JFrame("teste");
		telaDaCorrida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		telaDaCorrida.setResizable(false);
		Corrida novaCorrida = new Corrida("Zeus", "Poseidon");
		novaCorrida.setBackground(Color.WHITE);
		telaDaCorrida.add(novaCorrida);
		telaDaCorrida.pack();
		telaDaCorrida.setVisible(true);
		novaCorrida.iniciarCorrida();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		testeSeOJogoFoiIniciado();
		/*boolean iniciouJogo = menuPrincipal.testeSeOJogoFoiIniciado();
		if(iniciouJogo == true) {
			jogoIniciado();
			timer.stop();
		}*/
		
	}
	
	
}
