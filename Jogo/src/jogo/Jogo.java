package jogo;
import jogo.Gráfico.Fase;
import jogo.Gráfico.KeyHandler;
import jogo.Gráfico.Player1;
import jogo.MenuPrincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JFrame implements Runnable{
	
	private MenuPrincipal menuPrincipal;
    private KeyHandler keyH = new KeyHandler();
	
    Player1 player1 = new Player1(keyH);
	int carroX = 100;
	int carroY = 100;
	int carroSpeed = 0;
	int carroAceleracao = 1;
	
	int FPS = 60;
	
	Thread gameThread;
	
	public Jogo() {
		
		menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menuPrincipal.setSize(720, 500);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setResizable(false);
		menuPrincipal.setVisible(true);
		
		/*super("teste");
		add(new Fase());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //definir padrão de fechamento da janela
		//JFrame.Exit_ON_CLOSE retorna um inteiro que atribuirá um X para a janela
		setSize(720, 500); //setar o tamanho da janela
		setLocationRelativeTo(null); //setar a janela no meio da tela
		setResizable(false);
		setVisible(true); //definir que a janela será visível*/
	}
	
	//public static void main (String[] args) {
	public void startGame () {
		
		//new Jogo();
//		menuPrincipal = new MenuPrincipal();
//		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		menuPrincipal.setSize(720, 500);
//		menuPrincipal.setLocationRelativeTo(null);
//		menuPrincipal.setResizable(false);
//		menuPrincipal.setVisible(true);
		for(;;) {
			boolean estaNoMenu;
			System.out.println("a");
			estaNoMenu = !menuPrincipal.testeSeOJogoFoiIniciado(); 
			if(estaNoMenu == false) {
				menuPrincipal.dispose();
				startGameThread();
				break;
			}
		}
		
		/*MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menuPrincipal.setSize(720, 500);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setResizable(false);
		menuPrincipal.setVisible(true);
		for(;;) {
			boolean estaNoMenu;
			System.out.println("a");
			estaNoMenu = !menuPrincipal.testeSeOJogoFoiIniciado(); 
			if(estaNoMenu == false) {
				break;
			}
		}
		Thread gameThread = new Thread(this);
		System.out.println(menuPrincipal.isIniciouJogo());
		if(menuPrincipal.isIniciouJogo()) {
			System.out.println("a");
			JFrame teste = new JFrame();
			teste.setSize(720, 500); //setar o tamanho da janela
			teste.setLocationRelativeTo(null); //setar a janela no meio da tela
			teste.setResizable(false);
			teste.setVisible(true); //definir que a janela será visível
		}
	}*/
	
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		setSize(720, 500); 
		setLocationRelativeTo(null); 
		setResizable(false);
		add(new Fase(keyH));
		repaint();
		setVisible(true); 
		gameThread.start();
	}

	@Override
	public void run() {
		
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime<0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		player1.update();
	}
	
	/*public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player1.draw(g2);
		g2.dispose();
	}*/
	
}
