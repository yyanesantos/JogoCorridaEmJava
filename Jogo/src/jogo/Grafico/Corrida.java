package jogo.Grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Corrida extends JPanel implements Runnable{
	
	//Configurações da tela
		final int originalTileSize = 16;
		final int scale = 3;
		
		public final int tileSize = originalTileSize * scale;
		final int maxScreenCol = 16;
		final int maxScreenRow = 12;
		final int screenWidth = tileSize * maxScreenCol;
		final int screenHeight = tileSize * maxScreenRow;
		
		int FPS = 60;
		
		private KeyHandler keyH = new KeyHandler();
		Thread gameThread;
		
		private Image fundoCorrida;
		private Player1 player1;
		private Timer timer;
		private List<Buraco> buracos;
		private List<Vantagem> vantagens;
		private boolean emJogo;
		//private Player2 player2;
		
		
		
		
	public Corrida(String player1Selecionado) {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		addKeyListener(keyH);
		
		ImageIcon pistaDeCorrida = new ImageIcon("res\\FundoCorrida.png");
		fundoCorrida = pistaDeCorrida.getImage();
		
		player1 = new Player1(player1Selecionado, this, keyH);
		setarBuracos();
		setarVantagens();
		emJogo = true;
		//player1 = new Player1(player1Selecionado);
		
	}
	
	/*public Corrida(String player1Selecionado, String player2Selecionado) {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.red);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		//this.addKeyListener(keyH);
		
		
	}*/
	
	public Rectangle getBounds() {
		return new Rectangle (0, 0, fundoCorrida.getWidth(null), fundoCorrida.getHeight(null));
	}
	
	public void checarColisoes() {
		Rectangle formaPlayer1 = player1.getBounds();
		Rectangle formaPista = this.getBounds();
		Rectangle formaBuracos;
		Rectangle formaVantagens;
		Rectangle formaPoderes;
		
		for(int i = 0; i < buracos.size(); i++) {
			Buraco buracoTemp = buracos.get(i);
			formaBuracos = buracoTemp.getBounds();
			if(formaPlayer1.intersects(formaBuracos)) {
				buracoTemp.setBateu(false);
				player1.setVisivel(false);
			}
		}
		
		for(int o = 0; o < vantagens.size(); o++) {
			Vantagem vantagemTemp = vantagens.get(o);
			formaVantagens = vantagemTemp.getBounds();
			if(formaPlayer1.intersects(formaVantagens)) {
				vantagemTemp.setBateu(false);
				vantagemTemp.setVisivel(false);
			}
		}
		
		/*List<Poder> poderesPraFrente = player1.getPoderesPraFrente(); 
		for(int j = 0; j < poderesPraFrente.size(); j++) {
			Poder poderFrenteTemp = poderesPraFrente.get(j);
			for(int k = 0; k < 2; k++) {
				
			}

		}*/
	}
	
	public void iniciarCorrida () {
			gameThread = new Thread(this);
		    gameThread.start();		
	}
	
	public void baterEmPlayer () {
		
	}
	
	public void baterNaPista () {
		
	}
	
	public void pegarVantagem () {
		
	}
	
	public void cruzarLinhaDeChegada () {
		
	}
	
	public void irParaPodio () {
		
	}
	
	public void setarVantagens () {
		int coordenadas [] = new int [8];
		vantagens = new ArrayList<Vantagem>();
		int max = 7;
        int min = 1;
        int range = max - min + 1;
		
		for(int qtdVantagens = 0; qtdVantagens < (coordenadas.length - 4) ; qtdVantagens++ ) {
			int x = (int) ((Math.random() * range) + min)* 300;
			int y = 120;
			vantagens.add(new Vantagem(x, y));			
		}
		
		for(int qtdVantagens = 5; qtdVantagens < (coordenadas.length) ; qtdVantagens++ ) {
			int x = (int) ((Math.random() * range) + min)* 300;
			int y = 200;
			vantagens.add(new Vantagem(x, y));
		}
		
		
		
	}
	
	public void setarBuracos () {
		int coordenadas [] = new int [10];
		buracos = new ArrayList<Buraco>();
		int max = 7;
        int min = 1;
        int range = max - min + 1;
		
		for(int qtdBuracos = 0; qtdBuracos < (coordenadas.length - 5) ; qtdBuracos++ ) {
			int x = (int) ((Math.random() * range) + min)* 200;
			int y = 120;
			buracos.add(new Buraco(x, y));			
		}
		
		for(int qtdBuracos = 5; qtdBuracos < (coordenadas.length) ; qtdBuracos++ ) {
			int x = (int) ((Math.random() * range) + min)* 200;
			int y = 200;
			buracos.add(new Buraco(x, y));
		}
		
	}
	
	public void setarPlayers () {
		
	}
	
	public void setarLinhaDeChegada () {
		
	}
	
	public void setarInicio () {
		
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		while(gameThread != null) {
			update();
			
			List<Poder> poderesFrente = player1.getPoderesPraFrente();
			for(int i = 0; i < poderesFrente.size(); i++) {
				Poder p = poderesFrente.get(i);
				if(p.isVisivel()) {
					p.update("Pra frente");
				} else {
					poderesFrente.remove(i);
				}
			}
			List<Poder> poderesAtras = player1.getPoderesPraTras();
			for(int o = 0; o < poderesAtras.size(); o++) {
				Poder p = poderesAtras.get(o);
				if(p.isVisivel()) {
					p.update("Pra tras");
				} else {
					poderesAtras.remove(o);
				}
			}
			
			for(int u = 0; u < buracos.size(); u++) {
				Buraco b = buracos.get(u);
				if(b.isVisivel()) {
					b.update();
				} else {
					buracos.remove(u);
				}
				
			}
			
			for(int a = 0; a < vantagens.size(); a++) {
				Vantagem v = vantagens.get(a);
				if(v.isVisivel()) {
					v.update();
				} else {
					vantagens.remove(a);
				}
				
			}
			
			checarColisoes();
			repaint();
			
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
	
    public void paintComponent(Graphics g) {
		
		if(emJogo == true) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(fundoCorrida, 0, 0, null);
			player1.draw(g2);
			List<Poder> poderesFrente = player1.getPoderesPraFrente();
			for(int i = 0; i < poderesFrente.size(); i++) {
			    Poder p = poderesFrente.get(i);
			    p.load();
			    g2.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this.tileSize, this.tileSize, this);
			}
			List<Poder> poderesTras = player1.getPoderesPraTras();
			for(int o = 0; o < poderesTras.size(); o++) {
			    Poder p = poderesTras.get(o);
			    p.load();
			    g2.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this.tileSize, this.tileSize, this);
			}
			
			for(int u = 0; u < buracos.size(); u++ ) {
				Buraco b = buracos.get(u);
				b.load();
				g2.drawImage(b.getImagemBuraco(), b.getX(), b.getY(), this.tileSize, this.tileSize, this);
			}
			
			for(int a = 0; a < vantagens.size(); a++ ) {
				Vantagem v = vantagens.get(a);
				v.load();
				g2.drawImage(v.getImagemVantagem(), v.getX(), v.getY(), this.tileSize - 15, this.tileSize - 15, this);
			}
			
			g2.dispose();
		} else {
			irParaPodio();
		}
		
		
		/*List<Nitro> nitros = player1.getNitros();
		for(int i = 0; i < nitros.size(); i++) {
			Nitro n = nitros.get(i);
		    n.load();
		    graficos.drawImage(n.getImagemNitro(), n.getX(), n.getY(), this);
		}*/
		//g.dispose(); 
	}
}
