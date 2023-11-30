package jogo.Grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Corrida extends JPanel implements Runnable, ActionListener{
	
	//Configurações da tela
		final int originalTileSize = 16;
		final int scale = 3;
		
		public final int tileSize = originalTileSize * scale;
		final int maxScreenCol = 16;
		final int maxScreenRow = 12;
		final int screenWidth = tileSize * maxScreenCol;
		final int screenHeight = tileSize * maxScreenRow;
		
		int FPS = 60;
		
		private KeyHandler keyHPlayer1 = new KeyHandler("Player 1");
		private KeyHandler keyHPlayer2 = new KeyHandler("Player 2");
		Thread gameThread;
		
		private Image fundoCorrida;
		private Player player1;
		private Player player2;
		private ArrayList<Buraco> buracos;
		private ArrayList<Vantagem> vantagens;
		private boolean emJogo;
		//private Player2 player2;
		
		
		
		
	public Corrida(String playerSelecionado) {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setSize(360, 1440);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		addKeyListener(keyHPlayer1);
		addKeyListener(keyHPlayer2);
		
		ImageIcon pistaDeCorrida = new ImageIcon("res\\FundoCorrida.png");
		
		fundoCorrida = pistaDeCorrida.getImage();
		
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
		this.setSize(360, 1440);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		addKeyListener(keyHPlayer1);
		addKeyListener(keyHPlayer2);
		
		ImageIcon pistaDeCorrida = new ImageIcon("res\\FundoCorrida.png");
		fundoCorrida = pistaDeCorrida.getImage();
		player1 = new Player(player1Selecionado, this, keyHPlayer1);
		player1.setY(110);
		player1.setxVida(0);
		player1.setxNitro(0);
		player1.setxPoder(0);
		player2 = new Player(player2Selecionado, this, keyHPlayer2);
		player2.setY(160);
		player2.setxVida(668);
		player2.setxNitro(668);
		player2.setxPoder(668);
		
		setarBuracos();
		setarVantagens();
		emJogo = true;
		
		
	}
	
	public Rectangle getBoundsCenarioAcima() {
		return new Rectangle (0, 0, fundoCorrida.getWidth(null), fundoCorrida.getHeight(null) - 263);
	}
	
	public Rectangle getBoundsCenarioAbaixo() {
		return new Rectangle(0, 257, fundoCorrida.getWidth(null), fundoCorrida.getHeight(null) - 257);
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
			if(player1.getStatusVida() == "3 Vidas") {
				player1.piscar();
				player1.setY(player1.getY() +20);
				player1.setStatusVida("2 Vidas");
			} else if(player1.getStatusVida() == "2 Vidas") {
				player1.piscar();
				player1.setY(player1.getY() +20);
				player1.setStatusVida("1 Vida");
			} else {
				player1.explodir();
				player1.setStatusVida("3 Vidas");
			} 
			
		} if(formaPlayer1.intersects(formaPistaAcima)) {
			player1.derrapar();
		}
		
		for(int i = 0; i < buracos.size(); i++) {
			Buraco buracoTemp = buracos.get(i);
			formaBuracos = buracoTemp.getBounds();
			if(formaPlayer1.intersects(formaBuracos) && player1.getMonitor().getTimerInvencibilidadeStatus() == "OFF") {
				player1.getMonitor().startTimerInvencibilidade();
				if(player1.getStatusVida() == "3 Vidas") {
					player1.piscar();
					player1.setStatusVida("2 Vidas");
				} else if(player1.getStatusVida() == "2 Vidas") {
					player1.piscar();
					player1.setStatusVida("1 Vida");
				} else {
					player1.explodir();
					player1.setStatusVida("3 Vidas");
				}
			} else if (player2 != null) {
				if(player2.getBounds().intersects(formaBuracos) && player2.getMonitor().getTimerInvencibilidadeStatus() == "OFF") {
					player2.getMonitor().startTimerInvencibilidade();
					if(player2.getStatusVida() == "3 Vidas") {
						player2.piscar();
						player2.setStatusVida("2 Vidas");
					} else if(player2.getStatusVida() == "2 Vidas") {
						player2.piscar();
						player2.setStatusVida("1 Vida");
					} else {
						player2.explodir();
						player2.setStatusVida("3 Vidas");
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
				    player1.setStatusNitro("ON");
				} else if(vantagemTemp.getTipoVantagem() == "Poder") {
					player1.setPoder(true);
					player1.setStatusPoder("ON");
				}
			} else if( player2 != null) {
				if(player2.getBounds().intersects(formaVantagens) && player2.isNitro() == false && player2.isPoder() == false) {
					vantagemTemp.setVisivel(false);
					if(vantagemTemp.getTipoVantagem() == "Nitro") {
					    player2.setNitro(true);
					    player2.setStatusNitro("ON");
					} else if(vantagemTemp.getTipoVantagem() == "Poder") {
						player2.setPoder(true);
						player2.setStatusPoder("ON");
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
			    int y = 120;
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
			    int y = 120;
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
	    int y = 200;
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
		    int y = 200;
		    vantagens.add(new Vantagem(x, y));		
		    vantagens.get(qtdVantagens).setTipoVantagem("Poder");
		}
			}
		
		
	}
	
	public void setarBuracos () {
		int coordenadas [] = new int [10];
		buracos = new ArrayList<Buraco>();
		int max = 7;
        int min = 1;
        int range = max - min + 1;
        List<Integer> xEscolhidosy1 = new ArrayList<Integer>();
        List<Integer> xEscolhidosy2 = new ArrayList<Integer>();
		
		for(int qtdBuracos = 0; qtdBuracos < (coordenadas.length - 5) ; qtdBuracos++ ) {
			boolean novoNumero = false;
			boolean repetiu;
			int x = -1;
			while(!novoNumero) {
				repetiu = false;
	    		x = (int) ((Math.random() * range) + min)* 200;
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
			int y = 120;
			buracos.add(new Buraco(x, y));			
		}
		
		for(int qtdBuracos = 5; qtdBuracos < (coordenadas.length) ; qtdBuracos++ ) {
			boolean novoNumero = false;
			boolean repetiu;
			int x = -1;
			while(!novoNumero) {
				repetiu = false;
	    		x = (int) ((Math.random() * range) + min)* 200;
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
	}
	
    public void paintComponent(Graphics g) {
		
		if(emJogo == true) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(fundoCorrida, 0, 0, null);
			player1.draw(g2);
			
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
				player2.draw(g2);
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
			}
			
			for(int u = 0; u < buracos.size(); u++ ) {
				Buraco b = buracos.get(u);
				b.load();
				g2.drawImage(b.getImagemBuraco(), b.getX(), b.getY(), b.getLargura(), b.getAltura(), this);
			}
			
			for(int a = 0; a < vantagens.size(); a++ ) {
				Vantagem v = vantagens.get(a);
				v.load();
				g2.drawImage(v.getImagemVantagem(), v.getX(), v.getY(), this.tileSize - 13, this.tileSize - 13, this);
			}
			g2.dispose();
		} else {
			irParaPodio();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
