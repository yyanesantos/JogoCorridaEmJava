package jogo;
import jogo.Grafico.Corrida;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Jogo extends JFrame implements ActionListener{
	
	private MenuPrincipal menuPrincipal;
    public String situacaoJogo;
    private Timer timer;
    
    private Corrida novaCorrida;
    
	public Jogo() {
		
		timer = new Timer(5, this);
		
		menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menuPrincipal.setSize(720, 400);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setResizable(false);
		menuPrincipal.setVisible(true);
		timer.start();
	}
	
	public void jogoIniciado () {
		JFrame telaDaCorrida = new JFrame("Corrida dos Deuses!");
		telaDaCorrida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		telaDaCorrida.setResizable(false);
		if(menuPrincipal.getModoDeJogo() == "1-Player" ) {
			novaCorrida = new Corrida(menuPrincipal.getPersonagem1Player());
		} else if(menuPrincipal.getModoDeJogo() == "2-Player") {
			novaCorrida = new Corrida(menuPrincipal.getPersonagem1Player(), menuPrincipal.getPersonagem2Player());
		}
		novaCorrida.setBackground(Color.WHITE);
		telaDaCorrida.add(novaCorrida);
		telaDaCorrida.pack();
		telaDaCorrida.setVisible(true);
		novaCorrida.iniciarCorrida();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean iniciouJogo = menuPrincipal.testeSeOJogoFoiIniciado();
		if(iniciouJogo == true) {
			jogoIniciado();
			timer.stop();
		}
		
	}
	
	
}
