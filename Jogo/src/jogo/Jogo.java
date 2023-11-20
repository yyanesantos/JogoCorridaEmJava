package jogo;
import jogo.Grafico.Corrida;
import jogo.Grafico.KeyHandler;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.border.Border;

public class Jogo{
	
	private MenuPrincipal menuPrincipal;
    //private KeyHandler keyH = new KeyHandler();
    private boolean jogoIniciado;
	
	public Jogo() {
		
		/*menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menuPrincipal.setSize(720, 500);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setResizable(false);
		menuPrincipal.setVisible(true);
		do {
			jogoIniciado = menuPrincipal.testeSeOJogoFoiIniciado();
			System.out.println("saude");
			if(jogoIniciado == true) {
				menuPrincipal.dispose();
			}
		} while(jogoIniciado == false);
		System.out.println("praga");*/
		jogoIniciado();
		
		
		
	}
	
	public void jogoIniciado () {
		JFrame telaDaCorrida = new JFrame("teste");
		telaDaCorrida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		telaDaCorrida.setResizable(false);
		telaDaCorrida.setLocationRelativeTo(null);
		Corrida novaCorrida = new Corrida("Zeus");
		telaDaCorrida.add(novaCorrida);
		telaDaCorrida.pack();
		telaDaCorrida.setVisible(true);
		novaCorrida.iniciarCorrida();
		
	}
	
	
}
