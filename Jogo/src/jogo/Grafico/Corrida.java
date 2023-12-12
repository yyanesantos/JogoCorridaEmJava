package jogo.Grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jogo.Podio;


public class Corrida extends JPanel implements Runnable{
	
	//Configuracoes da tela
		final int originalTileSize = 20;
		final int scale = 2;
		
		public final int tileSize = originalTileSize * scale;
		final int maxScreenCol = 34;
		final int maxScreenRow = 17;
		final int screenWidth = tileSize * maxScreenCol;
		final int screenHeight = tileSize * maxScreenRow;
		
		int FPS = 60;
		public int desvioDeNivelPlayer1 = 0;
		public int desvioDeNivelPlayer2 = 0;
		public int bordaDireita = (int) (0.5 * screenWidth);
		public  int lvlTileWide;
		public  int maxTilesOffSet; 
		
		private KeyHandler keyHPlayer1 = new KeyHandler("Player 1");
		private KeyHandler keyHPlayer2 = new KeyHandler("Player 2");
		private CarregadorImagens carregadorImagens;
		Thread gameThread;
		
		private BufferedImage fundoCorrida;
		private BufferedImage background;
		
		private String campeao;
		private String perdedor;
		
		private Player player1;
		private Player player2;
		private ArrayList<Buraco> buracos;
		private ArrayList<Buraco> buracosSegundaTela;
		private ArrayList<Vantagem> vantagens;
		private ArrayList<Vantagem> vantagensSegundaTela;
		private boolean emJogo;
		private int completouVoltaPlayer1 = 1;
		private int completouVoltaPlayer2 = 1;
		
		private String modoDeJogo;
		private String quemGanhou;
		
		private Podio podio;
		
		

		
		
		
		
	public Corrida(String player1Selecionado) {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		carregadorImagens = new CarregadorImagens();
		addKeyListener(keyHPlayer1);
		
		modoDeJogo = "1-Player";

		fundoCorrida = carregadorImagens.getImagem("FundoCorrida");
		background = carregadorImagens.getImagem("Background");
		
		lvlTileWide = fundoCorrida.getWidth();
		maxTilesOffSet = lvlTileWide - screenWidth;
		
		
		player1 = new Player(player1Selecionado, this, keyHPlayer1, "Player1");
		player1.setY(77);
		player1.setxVida(220);
		player1.setxNitro(220);
		player1.setxPoder(220);
		player1.setxVolta(220);
		player1.setxPlayer(80);
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
		
		modoDeJogo = "2-Player";
	
		fundoCorrida = carregadorImagens.getImagem("FundoCorrida");
		background = carregadorImagens.getImagem("Background");
		
		
		lvlTileWide = fundoCorrida.getWidth();
		maxTilesOffSet = lvlTileWide - screenWidth;
		
		player1 = new Player(player1Selecionado, this, keyHPlayer1, "Player1");
		player1.setY(77);
		player1.setxVida(220);
		player1.setxNitro(220);
		player1.setxPoder(220);
		player1.setxVolta(220);
		player1.setxPlayer(80);
		player2 = new Player(player2Selecionado, this, keyHPlayer2, "Player2");
		player2.setY(177);
		player2.setxVida(1120);
		player2.setxNitro(1120);
		player2.setxPoder(1120);
		player2.setxVolta(1120);
		player2.setxPlayer(980);
		
		setarBuracos();
		setarVantagens();
		emJogo = true;
		
		
	}
	
	public Rectangle getBoundsCenarioAcima() {
		return new Rectangle (0, 0, background.getWidth(null), background.getHeight(null));
	}
	
	public Rectangle getBoundsCenarioAbaixo() {
		return new Rectangle(0, 290, fundoCorrida.getWidth(null), 10);
	}
	
	public void colidir(Player player) {
		player.getMonitorInvencibilidade().startTimer();
		if(player.getStatusVida() == "3Vidas") {
			player.piscar();
			player.setStatusVida("2Vidas");
		} else if(player.getStatusVida() == "2Vidas") {
			player.piscar();
			player.setStatusVida("1Vida");
		} else {
			player.explodir();
			player.setStatusVida("3Vidas");
		} 
	}
	
	
	public void checarColisoes() {
		Rectangle formaPlayer1 = player1.getBounds();
		Rectangle formaPistaAcima = getBoundsCenarioAcima();
		Rectangle formaPistaAbaixo = getBoundsCenarioAbaixo();
		Rectangle formaBuracos;
		Rectangle formaVantagens;
		Rectangle formaPoderesPraFrente1;
		Rectangle formaPoderesPraFrente2;
		Rectangle formaPoderesPraTras1;
		Rectangle formaPoderesPraTras2;
		
		if(formaPlayer1.intersects(formaPistaAcima) && player1.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
			player1.derrapar("Pra baixo");
			colidir(player1);
		} else if(formaPlayer1.intersects(formaPistaAcima)) {
			player1.derrapar("Pra baixo");
		}
		
		if(player2 != null) {
			if(player2.getBounds().intersects(formaPistaAcima) && player2.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
				colidir(player2);
				player1.derrapar("Pra baixo");
			} else if(player2.getBounds().intersects(formaPistaAcima)) {
				player2.derrapar("Pra baixo");
			}
			
			if(player2.getBounds().intersects(formaPistaAbaixo) && player2.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
				colidir(player2);
				player2.derrapar("Pra cima");
			} else if(player2.getBounds().intersects(formaPistaAbaixo)) {
				player2.derrapar("Pra cima");
			}
			
			if(formaPlayer1.intersects(player2.getBounds())) {
				if(player1.getY() > player2.getY()) {
					player1.derrapar("Pra baixo"); 
					player2.derrapar("Pra cima");
				} else {
					player1.derrapar("Pra cima");
					player2.derrapar("Pra baixo");
				}
				if(player1.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
					colidir(player1);
				}
				    if(player2.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
					colidir(player2);
				}
			}
			
			for(int u = 0; u < player1.getPoderesPraFrente().size(); u++) {
				Poder poderFrenteTemp = player1.getPoderesPraFrente().get(u);
				formaPoderesPraFrente1 = poderFrenteTemp.getBounds();
				if(player2.getBounds().intersects(formaPoderesPraFrente1) && player2.getMonitorInvencibilidade().getTimerStatus() == "OFF"){
					if(player1.getY() > player2.getY()) { 
						player2.derrapar("Pra cima");
					} else {
						player2.derrapar("Pra baixo");
					}
					colidir(player2);
					
				}
			}
			
			for(int u = 0; u < player1.getPoderesPraTras().size(); u++) {
				Poder poderFrenteTemp = player1.getPoderesPraTras().get(u);
				formaPoderesPraTras1 = poderFrenteTemp.getBounds();
				if(player2.getBounds().intersects(formaPoderesPraTras1) && player2.getMonitorInvencibilidade().getTimerStatus() == "OFF"){
					if(player1.getY() > player2.getY()) {
						player2.derrapar("Pra cima");
					} else {
						player2.derrapar("Pra baixo");
					}
					colidir(player2);
					
				}
			}
			
			for(int u = 0; u < player2.getPoderesPraFrente().size(); u++) {
				Poder poderFrenteTemp = player2.getPoderesPraFrente().get(u);
				formaPoderesPraFrente2 = poderFrenteTemp.getBounds();
				if(player1.getBounds().intersects(formaPoderesPraFrente2) && player1.getMonitorInvencibilidade().getTimerStatus() == "OFF"){
					if(player1.getY() > player2.getY()) {
						player1.derrapar("Pra baixo"); 
					} else {
						player1.derrapar("Pra cima");
					}
					colidir(player1);
					
				}
			}
			
			for(int u = 0; u < player2.getPoderesPraTras().size(); u++) {
				Poder poderFrenteTemp = player2.getPoderesPraTras().get(u);
				formaPoderesPraTras2 = poderFrenteTemp.getBounds();
				if(player1.getBounds().intersects(formaPoderesPraTras2) && player1.getMonitorInvencibilidade().getTimerStatus() == "OFF"){
					if(player1.getY() > player2.getY()) {
						player1.derrapar("Pra baixo"); 
					} else {
						player1.derrapar("Pra cima");
					}
					colidir(player1);
					
				}
			}
		}
		
		
		if(formaPlayer1.intersects(formaPistaAbaixo) && player1.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
			colidir(player1);
		} else if(formaPlayer1.intersects(formaPistaAbaixo)) {
			player1.derrapar("Pra cima");
		}
		
		for(int i = 0; i < buracos.size(); i++) {
			Buraco buracoTemp = buracos.get(i);
			formaBuracos = buracoTemp.getBounds();
			if(formaPlayer1.intersects(formaBuracos) && player1.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
				colidir(player1);
			} else if (player2 != null) {
				if(player2.getBounds().intersects(formaBuracos) && player2.getMonitorInvencibilidade().getTimerStatus() == "OFF") {
					colidir(player2);
				}
			}
			
		}
		
		for(int o = 0; o < vantagens.size(); o++) {
			Vantagem vantagemTemp = vantagens.get(o);
			formaVantagens = vantagemTemp.getBounds();
			if(formaPlayer1.intersects(formaVantagens) && player1.isNitro() == false && player1.isPoder() == false) {
				vantagemTemp.setVisivel(false);
				if(vantagensSegundaTela != null) {
					vantagensSegundaTela.get(o).setVisivel(false);
				}
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
					if(vantagensSegundaTela != null) {
						vantagensSegundaTela.get(o).setVisivel(false);
					}
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
	
	public void irParaPodio () {

		JFrame janelaPodio = new JFrame("Podio!");
		janelaPodio.setSize(800,500);
		janelaPodio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		janelaPodio.setLocationRelativeTo(null);
		janelaPodio.setResizable(false);
		janelaPodio.setBackground(Color.WHITE);
		if(modoDeJogo == "1-Player") {
			podio = new Podio(campeao, 240, 200);
			janelaPodio.add(podio);
			podio.setDoubleBuffered(true);
			podio.setVisible(true);
			podio.iniciarCerimonia();
		} else if(modoDeJogo == "2-Player") {
			podio = new Podio(campeao, perdedor, 240, 200, quemGanhou);
			janelaPodio.add(podio);
			podio.setDoubleBuffered(true);
			podio.setVisible(true);
			podio.iniciarCerimonia();
		}
		janelaPodio.setVisible(true);
		
	}
	
	public void setarVantagens () {
		int coordenadas [] = new int [8];
		vantagens = new ArrayList<Vantagem>();
		if(player2 != null) {
			vantagensSegundaTela = new ArrayList<Vantagem>();
		}
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
			    int y = 95;
			    vantagens.add(new Vantagem(x, y));
			    if(vantagensSegundaTela != null) {
			    	vantagensSegundaTela.add(new Vantagem(x, y + 300 + 100));
			    }
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
			    int y = 95;
			    vantagens.add(new Vantagem(x, y));		
			    if(vantagensSegundaTela != null) {
			    	vantagensSegundaTela.add(new Vantagem(x, y + 300 + 100));
			    }
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
	    int y = 215;
	    vantagens.add(new Vantagem(x, y));
	    if(vantagensSegundaTela != null) {
	    	vantagensSegundaTela.add(new Vantagem(x, y + 300 + 100));
	    }
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
		    int y = 215;
		    vantagens.add(new Vantagem(x, y));	
		    if(vantagensSegundaTela != null) {
		    	vantagensSegundaTela.add(new Vantagem(x, y + 300 + 100));
		    }
		    vantagens.get(qtdVantagens).setTipoVantagem("Poder");
		}
			}
		
		
	}
	
	public void checkCloseToBorder() {
		int X1 = (int) player1.getX();
		int diff2 = 0;
		if(player1.getX() == 0) {
			desvioDeNivelPlayer1 = 0;
		}
		int diff = X1 - desvioDeNivelPlayer1;
		if(player2 != null) {
			if(player2.getX() == 0) {
				desvioDeNivelPlayer2 = 0;
			}
			diff2 = (int)player2.getX() - desvioDeNivelPlayer2;
		}
		
		if(desvioDeNivelPlayer1 <= 1515) {
			if(diff > bordaDireita) {
				desvioDeNivelPlayer1 += diff - bordaDireita;
			}
			else if(desvioDeNivelPlayer1 < 0) {
				desvioDeNivelPlayer1 = 0;
			}
		} 
		if(desvioDeNivelPlayer2 <= 1515 && player2 != null) {
			if(diff2 > bordaDireita) {
				desvioDeNivelPlayer2 += diff2 - bordaDireita;
			}
			else if(desvioDeNivelPlayer2 < 0) {
				desvioDeNivelPlayer2 = 0;
			}
		}
	}
	
	public void setarBuracos () {
		int coordenadas [] = new int [5];
		buracos = new ArrayList<Buraco>();
		if(player2 != null) {
			buracosSegundaTela = new ArrayList<Buraco>();
		}
		int max = 4;
        int min = 1;
        int range = max - min + 1;
        List<Integer> xEscolhidosy1 = new ArrayList<Integer>();
        List<Integer> xEscolhidosy2 = new ArrayList<Integer>();
		
		for(int qtdBuracos = 0; qtdBuracos < (coordenadas.length - 2) ; qtdBuracos++ ) {
			boolean novoNumero = false;
			boolean repetiu;
			int x = -1;
			while(!novoNumero) {
				repetiu = false;
	    		x = (int) ((Math.random() * range) + min)* 400;
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
			int y = 85;
			buracos.add(new Buraco(x, y));
			if(buracosSegundaTela != null) {
				int y2 = 85 +300 + 100;
				buracosSegundaTela.add(new Buraco(x,y2));
			}
		}
		
		for(int qtdBuracos = 3; qtdBuracos < (coordenadas.length) ; qtdBuracos++ ) {
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
			int y = 220;
			buracos.add(new Buraco(x, y));
			if(buracosSegundaTela != null) {
				int y2 = 220 + 300 + 100;
				buracosSegundaTela.add(new Buraco(x,y2));
			}
		}
		
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
			if(player2 != null) {
				for(int a = 0; a < vantagensSegundaTela.size(); a++) {
					Vantagem v = vantagensSegundaTela.get(a);
					if(v.isVisivel()) {
						v.update();
					} else {
						vantagensSegundaTela.remove(a);
					}
					
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
		if(player2 != null) {
			player2.update(vantagens);
		}
		
		checkCloseToBorder();
		player1.checarVoltas(player1.getX());
		if(player2 != null) {
			player2.checarVoltas(player2.getX());
			if(player1.getNumeroVoltas() > completouVoltaPlayer1 && player2.getNumeroVoltas() > completouVoltaPlayer2) {
				setarBuracos();
				setarVantagens();
				completouVoltaPlayer1++;
				completouVoltaPlayer2++;
			}
		}
		
		if(player1.getNumeroVoltas() > completouVoltaPlayer1 && modoDeJogo == "1-Player") {
			setarBuracos();
			setarVantagens();
			completouVoltaPlayer1++;
		}
		
		if(player1.getNumeroVoltas() > 5) {
			campeao = player1.getNomePersonagem();
			quemGanhou = "Player1";
			if(player2 != null) {
				perdedor = player2.getNomePersonagem();
			}
			emJogo = false;
		} else if(player2 != null && player2.getNumeroVoltas() > 5) {
			campeao = player2.getNomePersonagem();
			quemGanhou = "Player2";
			perdedor = player1.getNomePersonagem();
			emJogo = false;
		}
	}
	
    public void paintComponent(Graphics g) {
		
		if(emJogo == true) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			if(player1.getX() <= 100) 
			{
				g2.drawImage(background, 0, 0, null);
				g2.drawImage(fundoCorrida, 0, 50, null);
			} else {
			g2.drawImage(background, -desvioDeNivelPlayer1, 0, null);
			g2.drawImage(fundoCorrida, -desvioDeNivelPlayer1, 50, null);
			}
			
			if(player2 != null ) {
				
				g2.drawImage(background, -desvioDeNivelPlayer2, 350 +25, null);
				g2.drawImage(fundoCorrida, -desvioDeNivelPlayer2, 425, null);
				player1.draw(g2, desvioDeNivelPlayer2, "1-Player");
				player2.draw(g2, desvioDeNivelPlayer2, "2-Player");
				player2.draw(g2, desvioDeNivelPlayer1);
				List<Poder> poderesFrentePlayer2 = player2.getPoderesPraFrente();
				for(int i = 0; i < poderesFrentePlayer2.size(); i++) {
				    Poder p = poderesFrentePlayer2.get(i);
				    p.load(player2.getNomePersonagem());
				    g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer1, p.getY() , this.tileSize, this.tileSize, this);
				    g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer2, p.getY() + 375 - desvioDeNivelPlayer1, this.tileSize, this.tileSize, this);
				}
				List<Poder> poderesTrasPlayer2 = player2.getPoderesPraTras();
				for(int o = 0; o < poderesTrasPlayer2.size(); o++) {
				    Poder p = poderesTrasPlayer2.get(o);
				    p.load(player2.getNomePersonagem());
				    g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer1, p.getY() , this.tileSize, this.tileSize, this);
				    g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer2, p.getY()+ 375 - desvioDeNivelPlayer1, this.tileSize, this.tileSize, this);
				}
				for(int u = 0; u < buracosSegundaTela.size(); u++ ) {
					Buraco b = buracosSegundaTela.get(u);
					b.load();
					g2.drawImage(b.getImagemBuraco(), b.getX() - desvioDeNivelPlayer2, b.getY() - 25, b.getLargura(), b.getAltura(), this);
				}
				for(int a = 0; a < vantagensSegundaTela.size(); a++ ) {
					Vantagem v = vantagensSegundaTela.get(a);
					v.load();
					g2.drawImage(v.getImagemVantagem(), v.getX() - desvioDeNivelPlayer2, v.getY() - 25, v.getLargura(), v.getAltura(), this);
				}
			}
			
			
			List<Poder> poderesFrentePlayer1 = player1.getPoderesPraFrente();
			for(int i = 0; i < poderesFrentePlayer1.size(); i++) {
			    Poder p = poderesFrentePlayer1.get(i);
			    p.load(player1.getNomePersonagem());
			    g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer1, p.getY(), this.tileSize, this.tileSize, this);
			    if(player2 != null) {
			    	g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer2, p.getY() + 375 - desvioDeNivelPlayer2, this.tileSize, this.tileSize, this);
			    }
			    
			}
			List<Poder> poderesTrasPlayer1 = player1.getPoderesPraTras();
			for(int o = 0; o < poderesTrasPlayer1.size(); o++) {
			    Poder p = poderesTrasPlayer1.get(o);
			    p.load(player1.getNomePersonagem());
			    g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer1, p.getY(), this.tileSize, this.tileSize, this);
			    if(player2 != null) {
			    	g2.drawImage(p.getImagemPoder(), p.getX() - desvioDeNivelPlayer2, p.getY() + 375 - desvioDeNivelPlayer2, this.tileSize, this.tileSize, this);
			    }
			    
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
			player1.draw(g2, desvioDeNivelPlayer1);
			
			g2.dispose();
		} else {
			gameThread = null;
			irParaPodio();
		}
	}

}
