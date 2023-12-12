package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import jogo.Grafico.CarregadorImagens;
import jogo.Grafico.Personagem;

public class Podio extends JPanel implements ActionListener{
	
	private Personagem campeao;
	private Personagem perdedor;
	
	private BufferedImage podio;
	private BufferedImage trofeu;
	private BufferedImage mensagemCongratulatoria;
	private CarregadorImagens carregadorImagens;
	
	private Timer timer;
	
	private int x, y;
	
	private String modoDeJogo;
	
	public Podio (String ganhador, int x, int y)  {
		this.x = x;
		this.y = y;
		campeao = new Personagem(ganhador);
		
		timer = new Timer(100, this);
		carregadorImagens = new CarregadorImagens();
		mensagemCongratulatoria = carregadorImagens.getImagem("ParabensPlayer1");
		podio = carregadorImagens.getImagem("Podio");
		trofeu = carregadorImagens.getImagem("Trofeu");
		
		modoDeJogo = "1-Player";
		
	}
	
	public Podio (String ganhador, String segundo, int x, int y, String player) {
		this.x = x;
		this.y = y;
		campeao = new Personagem(ganhador);
		perdedor = new Personagem(segundo);
		
		timer = new Timer(100, this);
		carregadorImagens = new CarregadorImagens();
		podio = carregadorImagens.getImagem("Podio");
		mensagemCongratulatoria = carregadorImagens.getImagem("Parabens" + player);
		trofeu = carregadorImagens.getImagem("Trofeu");
		
		
		modoDeJogo = "2-Player";
	}
	
	public void iniciarCerimonia() {
		timer.start();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 800, 500);
		g2.drawImage(mensagemCongratulatoria, 20, -20, 700, 200, null);
		g2.drawImage(podio, this.x, this.y + 100, null);
		campeao.reacao(g2, "Feliz", this.x + 180, this.y + 45);
		g2.drawImage(trofeu, this.x + 180, this.y - 40, null);
		if(modoDeJogo == "2-Player") {
			perdedor.reacao(g2, "Triste", x + 50, y + 105);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
	
	

}
