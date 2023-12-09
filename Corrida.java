package jogo.Grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Corrida extends JPanel implements Runnable{
	
	//Configuracoes da tela
		final int originalTileSize = 16;
		final int scale = 5;
		
		public final int tileSize = originalTileSize * scale;
		final int maxScreenCol = 23;
		final int maxScreenRow = 12;
		final int screenWidth = tileSize * maxScreenCol;
		final int screenHeight = tileSize * maxScreenRow;
		
		int FPS = 60;
		
		public final int maxWorldCol = 30;
		public final int worldWidth = tileSize * maxWorldCol;
		public int desvioDeNivelPlayer1 = 0;
		public int desvioDeNivelPlayer2 = 0;
		public int bordaDireita = (int) (0.5 * screenWidth);
		public  int lvlTileWide;
		public  int maxTilesOffSet; 
		//public  int maxDesvioDeNivel; 
		
		private KeyHandler keyHPlayer1 = new KeyHandler("Player 1");
		private KeyHandler keyHPlayer2 = new KeyHandler("Player 2");
		private CarregadorImagens carregadorImagens;
		Thread gameThread;
		
		private BufferedImage fundoCorrida;
		private BufferedImage background;
		private Player player1;
		private Player player2;
		private ArrayList<Buraco> buracos;
		private ArrayList<Buraco> buracosSegundaTela;
		private ArrayList<Vantagem> vantagens;
		private ArrayList<Vantagem> vantagensSegundaTela;
		private boolean emJogo;
		

		
		
		
		
	public Corrida(String playerSelecionado) {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		carregadorImagens = new CarregadorImagens();
		addKeyListener(keyHPlayer1);
		addKeyListener(keyHPlayer2);

		fundoCorrida = carregadorImagens.getImagem("FundoCorrida");
		
		lvlTileWide = fundoCorrida.getWidth();
		maxTilesOffSet = lvlTileWide - screenWidth;
		//maxDesvioDeNivel = maxTilesOffSet * tileSize;
		
		
		player1 = new Player(playerSelecionado, this, keyHPlayer1);
		player1.setY(110);
		player1.setxVida(0);
		player1.setxNitro(0);
		player1.setxPoder(0);
		setarBuracos();
		setarVantagens();
		emJogo = true;
		
	}
	
	public Corrida(String player1Selecionado, String player2Selecionado) {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		carregadorImagens = new CarregadorImagens();
		addKeyListener(keyHPlayer1);
		addKeyListener(keyHPlayer2);
	
		fundoCorrida = carregadorImagens.getImagem("FundoCorrida");
		background = carregadorImagens.getImagem("Background");
		
		
		lvlTileWide = fundoCorrida.getWidth();
		maxTilesOffSet = lvlTileWide - screenWidth;
		//maxDesvioDeNivel = maxTilesOffSet * tileSize;
		
		player1 = new Player(player1Selecionado, this, keyHPlayer1);
		player1.setY(152);
		player1.setxVida(0);
		player1.setxNitro(0);
		player1.setxPoder(0);
		player2 = new Player(player2Selecionado, this, keyHPlayer2);
		player2.setY(252);
		player2.setxVida(668);
		player2.setxNitro(668);
		player2.setxPoder(668);
		
		setarBuracos();
		setarVantagens();
		emJogo = true;
		
		
	}
	
	public Rectangle getBoundsCenarioAcima() {
		return new Rectangle (0, 0, background.getWidth(null), background.getHeight(null));
	}
	
	public Rectangle getBoundsCenarioAbaixo() {
		return new Rectangle(0, 375, fundoCorrida.getWidth(null), 10);
	}
	
	
	public void checarColisoes() {
		Rectangle formaPlayer1 = player1.getBounds();
		Rectangle formaPistaAcima = getBoundsCenarioAcima();
		Rectangle formaPistaAbaixo = getBoundsCenarioAbaixo();
		Rectangle formaBuracos;
		Rectangle formaVantagens;
		Rectangle formaPoderes;
		
		if(formaPlayer1.intersects(formaPistaAcima) && player1.getMonitor().getTimerInvencibilidadeStatus() == "OFF") {
			player1.getMonitor().startTimerInvencibilidade();
			if(player1.getStatusVida() == "3Vidas") {
				player1.piscar();
				player1.setY(player1.getY() +20);
				player1.setStatusVida("2Vidas");
			} else if(player1.getStatusVida() == "2Vidas") {
				player1.piscar();
				player1.setY(player1.getY() +20);
				player1.setStatusVida("1Vida");
			} else {
				player1.explodir();
				player1.setStatusVida("3Vidas");
			} 
			
		} else if(formaPlayer1.intersects(formaPistaAcima)) {
			player1.derrapar("Pra baixo");
		}
		
		if(formaPlayer1.intersects(formaPistaAbaixo) && player1.getMonitor().getTimerInvencibilidadeStatus() == "OFF") {
			player1.getMonitor().startTimerInvencibilidade();
			if(player1.getStatusVida() == "3Vidas") {
				player1.piscar();
				player1.setY(player1.getY() -20);
				player1.setStatusVida("2Vidas");
			} else if(player1.getStatusVida() == "2Vidas") {
				player1.piscar();
				player1.setY(player1.getY() -20);
				player1.setStatusVida("1Vida");
			} else {
				player1.explodir();
				player1.setStatusVida("3Vidas");
			} 
		} else if(formaPlayer1.intersects(formaPistaAbaixo)) {
			player1.derrapar("Pra cima");
		}
		
		for(int i = 0; i < buracos.size(); i++) {
			Buraco buracoTemp = buracos.get(i);
			formaBuracos = buracoTemp.getBounds();
			if(formaPlayer1.intersects(formaBuracos) && player1.getMonitor().getTimerInvencibilidadeStatus() == "OFF") {
				player1.getMonitor().startTimerInvencibilidade();
				if(player1.getStatusVida() == "3Vidas") {
					player1.piscar();
					player1.setStatusVida("2Vidas");
				} else if(player1.getStatusVida() == "2Vidas") {
					player1.piscar();
					player1.setStatusVida("1Vida");
				} else {
					player1.explodir();
					player1.setStatusVida("3Vidas");
				}
			} else if (player2 != null) {
				if(player2.getBounds().intersects(formaBuracos) && player2.getMonitor().getTimerInvencibilidadeStatus() == "OFF") {
					player2.getMonitor().startTimerInvencibilidade();
					if(player2.getStatusVida() == "3Vidas") {
						player2.piscar();
						player2.setStatusVida("2Vidas");
					} else if(player2.getStatusVida() == "2Vidas") {
						player2.piscar();
						player2.setStatusVida("1Vida");
					} else {
						player2.explodir();
						player2.setStatusVida("3Vidas");
					}
				}
			}
			
		}
		
		for(int o = 0; o < vantagens.size(); o++) {
			Vantagem vantagemTemp = vantagens.get(o);
			formaVantagens = vantagemTemp.getBounds();
			if(formaPlayer1.intersects(formaVantagens) && player1.isNitro() == false && player1.isPoder() == false) {
				vantagemTemp.setVisivel(false);
				if(vantagemTemp.getTipoVantagem() == "Nitro") {
				    player1.setNitro(true);
				    player1.setStatusNitro("NitroON");
				} else if(vantagemTemp.getTipoVantagem() == "Poder") {
					player1.setPoder(true);
					player1.setStatusPoder("PoderON");
				}
			} else if( player2 != null) {
				if(player2.getBounds().intersects(formaVantagens) && player2.isNitro() == false && player2.isPoder() == false) {
					vantagemTemp.setVisivel(false);
					if(vantagemTemp.getTipoVantagem() == "Nitro") {
					    player2.setNitro(true);
					    player2.setStatusNitro("NitroON");
					} else if(vantagemTemp.getTipoVantagem() == "Poder") {
						player2.setPoder(true);
						player2.setStatusPoder("PoderON");
					}
				}
				
			}
		}
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
		Random random = new Random();
		int max = 7;
        int min = 1;
        int range = max - min + 1;
        List<Integer> xEscolhidosy1 = new ArrayList<Integer>();
        List<Integer> xEscolhidosy2 = new ArrayList<Integer>();
		
		for(int qtdVantagens = 0; qtdVantagens < coordenadas.length - 4 ; qtdVantagens++ ) {
			int tipoVantagem = random.nextInt(2);
			
			int x = -1;
			if(tipoVantagem == 0) {
				boolean novoNumero = false;
				boolean repetiu;
				    	while(!novoNumero) {
				    		repetiu = false;
				    		x = (int) ((Math.random() * range) + min)* 300;
				    		for (int i = 0; i < xEscolhidosy1.size(); i++) {
				    	        if (x == xEscolhidosy1.get(i)) {
				    	            novoNumero = false;
				    	            repetiu = true;
				    	            break;
				    	        }
				    	        
				    	    }
				    		if(repetiu == false) {
				    	        novoNumero = true;
				    	        }
				    	}
				    xEscolhidosy1.add(x);
			    int y = 175;
			    vantagens.add(new Vantagem(x, y));		
			    vantagens.get(qtdVantagens).setTipoVantagem("Nitro");
			}else if(tipoVantagem == 1) {
				boolean novoNumero = false;
				boolean repetiu;
			    	while(!novoNumero) {
			    		repetiu = false;
			    		x = (int) ((Math.random() * range) + min)* 300;
			    		for (int i = 0; i < xEscolhidosy1.size(); i++) {
			    	        if (x == xEscolhidosy1.get(i)) {
			    	            novoNumero = false;
			    	            repetiu = true;
			    	            break;
			    	        } 
			    	        
			    	    }
			    		if(repetiu == false) {
			    	        novoNumero = true;
			    	        }
			    	}
			    	xEscolhidosy1.add(x);
			    int y = 175;
			    vantagens.add(new Vantagem(x, y));		
			    vantagens.get(qtdVantagens).setTipoVantagem("Poder");
			}
		}
		for(int qtdVantagens = 4; qtdVantagens < (coordenadas.length) ; qtdVantagens++ ) {
			int tipoVantagem = random.nextInt(2);
			int x = -1;
			if(tipoVantagem == 0) {
				boolean novoNumero = false;
				boolean repetiu;
				while(!novoNumero) {
					repetiu = false;
		    		x = (int) ((Math.random() * range) + min)* 300;
		    		for (int i = 0; i < xEscolhidosy2.size(); i++) {
		    	        if (x == xEscolhidosy2.get(i)) {
		    	            novoNumero = false;
		    	            repetiu = true;
		    	            break;
		    	        }
		    	    }
		    		if(repetiu == false) {
		    	        novoNumero = true;
		    	    }
		    	}
		    xEscolhidosy2.add(x);
	    int y = 285;
	    vantagens.add(new Vantagem(x, y));		
	    vantagens.get(qtdVantagens).setTipoVantagem("Nitro");
			} else if(tipoVantagem == 1) {
				boolean novoNumero = false;
				boolean repetiu;
				while(!novoNumero) {
					repetiu = false;
		    		x = (int) ((Math.random() * range) + min)* 300;
		    		for (int i = 0; i < xEscolhidosy2.size(); i++) {
		    	        if (x == xEscolhidosy2.get(i)) {
		    	            novoNumero = false;
		    	            repetiu = true;
		    	            break;
		    	        }
		    	    }
		    		if(repetiu == false) {
		    	        novoNumero = true;
		    	        }
				}
		    	xEscolhidosy2.add(x);
		    int y = 285;
		    vantagens.add(new Vantagem(x, y));		
		    vantagens.get(qtdVantagens).setTipoVantagem("Poder");
		}
			}
		
		
	}
	
	public void checkCloseToBorder() {
		int X1 = (int) player1.getX();
		int X2 = (int) player2.getX();
		int diff = X1 - desvioDeNivelPlayer1;
		int diff2 = X2 - desvioDeNivelPlayer2;
		if(desvioDeNivelPlayer1 <= 2106) {
			if(diff > bordaDireita) {
				desvioDeNivelPlayer1 += diff - bordaDireita;
			}
			else if(desvioDeNivelPlayer1 < 0) {
				desvioDeNivelPlayer1 = 0;
			}
		} else if(desvioDeNivelPlayer2 <= 2106 && player2 != null) {
			if(diff2 > bordaDireita) {
				desvioDeNivelPlayer2 += diff2 - bordaDireita;
			}
			else if(desvioDeNivelPlayer2 < 0) {
				desvioDeNivelPlayer2 = 0;
			}
		}
	}
	
	public void setarBuracos () {
		int coordenadas [] = new int [8];
		buracos = new ArrayList<Buraco>();
		if(player2 != null) {
			buracosSegundaTela = new ArrayList<Buraco>();
		}
		int max = 4;
        int min = 1;
        int range = max - min + 1;
        List<Integer> xEscolhidosy1 = new ArrayList<Integer>();
        List<Integer> xEscolhidosy2 = new ArrayList<Integer>();
		
		for(int qtdBuracos = 0; qtdBuracos < (coordenadas.length - 4) ; qtdBuracos++ ) {
			boolean novoNumero = false;
			boolean repetiu;
			int x = -1;
			while(!novoNumero) {
				repetiu = false;
	    		x = (int) ((Math.random() * range) + min)* 600;
	    		for (int i = 0; i < xEscolhidosy1.size(); i++) {
	    	        if (x == xEscolhidosy1.get(i)) {
	    	            novoNumero = false;
	    	            repetiu = true;
	    	            break;
	    	        }
	    	    }
	    		if(repetiu == false) {
	    	        novoNumero = true;
	    	        }
			}
	    	xEscolhidosy1.add(x);
			int y = 160;
			buracos.add(new Buraco(x, y));
			if(buracosSegundaTela != null) {
				int y2 = 160+375+100;
				buracosSegundaTela.add(new Buraco(x,y2));
			}
		}
		
		for(int qtdBuracos = 4; qtdBuracos < (coordenadas.length) ; qtdBuracos++ ) {
			boolean novoNumero = false;
			boolean repetiu;
			int x = -1;
			while(!novoNumero) {
				repetiu = false;
	    		x = (int) ((Math.random() * range - 1) + min + 1)* 700;
	    		for (int i = 0; i < xEscolhidosy2.size(); i++) {
	    	        if (x == xEscolhidosy2.get(i)) {
	    	            novoNumero = false;
	    	            repetiu = true;
	    	            break;
	    	        }
	    	    }
	    		if(repetiu == false) {
	    	        novoNumero = true;
	    	        }
			}
	    	xEscolhidosy2.add(x);
			int y = 270;
			buracos.add(new Buraco(x, y));
			if(buracosSegundaTela != null) {
				int y2 = 270 + 375+100;
				buracosSegundaTela.add(new Buraco(x,y2));
			}
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
			
			List<Poder> poderesFrentePlayer1 = player1.getPoderesPraFrente();
			for(int i = 0; i < poderesFrentePlayer1.size(); i++) {
				Poder p = poderesFrentePlayer1.get(i);
				if(p.isVisivel()) {
					p.update("Pra frente");
				} else {
					poderesFrentePlayer1.remove(i);
				}
			}
			List<Poder> poderesAtrasPlayer1 = player1.getPoderesPraTras();
			for(int o = 0; o < poderesAtrasPlayer1.size(); o++) {
				Poder p = poderesAtrasPlayer1.get(o);
				if(p.isVisivel()) {
					p.update("Pra tras");
				} else {
					poderesAtrasPlayer1.remove(o);
				}
			}
			if(player2 != null) {
			
			List<Poder> poderesFrentePlayer2 = player2.getPoderesPraFrente();
			for(int i = 0; i < poderesFrentePlayer2.size(); i++) {
				Poder p = poderesFrentePlayer2.get(i);
				if(p.isVisivel()) {
					p.update("Pra frente");
				} else {
					poderesFrentePlayer2.remove(i);
				}
			}
			List<Poder> poderesAtrasPlayer2 = player2.getPoderesPraTras();
			for(int o = 0; o < poderesAtrasPlayer2.size(); o++) {
				Poder p = poderesAtrasPlayer2.get(o);
				if(p.isVisivel()) {
					p.update("Pra tras");
				} else {
					poderesAtrasPlayer2.remove(o);
				}
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
		player1.update(vantagens);
		player2.update(vantagens);
		checkCloseToBorder();
	}
	
    public void paintComponent(Graphics g) {
		
		if(emJogo == true) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(background, -desvioDeNivelPlayer1, 0, null);
			g2.drawImage(fundoCorrida, -desvioDeNivelPlayer1, 125, null);
			player1.draw(g2, desvioDeNivelPlayer1);
			
			List<Poder> poderesFrentePlayer1 = player1.getPoderesPraFrente();
			for(int i = 0; i < poderesFrentePlayer1.size(); i++) {
			    Poder p = poderesFrentePlayer1.get(i);
			    p.load();
			    g2.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this.tileSize, this.tileSize, this);
			}
			List<Poder> poderesTrasPlayer1 = player1.getPoderesPraTras();
			for(int o = 0; o < poderesTrasPlayer1.size(); o++) {
			    Poder p = poderesTrasPlayer1.get(o);
			    p.load();
			    g2.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this.tileSize, this.tileSize, this);
			}
			if(player2 != null ) {
				player2.draw(g2, desvioDeNivelPlayer1);
				g2.drawImage(background, -desvioDeNivelPlayer2, 375 +100, null);
				g2.drawImage(fundoCorrida, -desvioDeNivelPlayer2, 500 +100, null);
				player1.draw(g2, desvioDeNivelPlayer2, "1-Player");
				player2.draw(g2, desvioDeNivelPlayer2, "2-Player");
				List<Poder> poderesFrentePlayer2 = player2.getPoderesPraFrente();
				for(int i = 0; i < poderesFrentePlayer2.size(); i++) {
				    Poder p = poderesFrentePlayer2.get(i);
				    p.load();
				    g2.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this.tileSize, this.tileSize, this);
				}
				List<Poder> poderesTrasPlayer2 = player2.getPoderesPraTras();
				for(int o = 0; o < poderesTrasPlayer2.size(); o++) {
				    Poder p = poderesTrasPlayer2.get(o);
				    p.load();
				    g2.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this.tileSize, this.tileSize, this);
				}
				for(int u = 0; u < buracos.size(); u++ ) {
					Buraco b = buracosSegundaTela.get(u);
					b.load();
					g2.drawImage(b.getImagemBuraco(), b.getX() - desvioDeNivelPlayer1, b.getY(), b.getLargura(), b.getAltura(), this);
				}
				/*for(int a = 0; a < vantagens.size(); a++ ) {
					Vantagem v = vantagens.get(a);
					v.load();
					g2.drawImage(v.getImagemVantagem(), v.getX() - desvioDeNivelPlayer1, v.getY(), v.getLargura(), v.getAltura(), this);
				}*/
			}
			
			for(int u = 0; u < buracos.size(); u++ ) {
				Buraco b = buracos.get(u);
				b.load();
				g2.drawImage(b.getImagemBuraco(), b.getX() - desvioDeNivelPlayer1, b.getY(), b.getLargura(), b.getAltura(), this);
			}
			
			for(int a = 0; a < vantagens.size(); a++ ) {
				Vantagem v = vantagens.get(a);
				v.load();
				g2.drawImage(v.getImagemVantagem(), v.getX() - desvioDeNivelPlayer1, v.getY(), v.getLargura(), v.getAltura(), this);
			}
			
			
			g2.dispose();
		} else {
			irParaPodio();
		}
	}

}
