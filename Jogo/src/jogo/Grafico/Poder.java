package jogo.Grafico;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Poder {
	
	private Image imagemPoder;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private String nomePersonagem;
	
	private static final int LARGURA = 1440;
	private static final int LARGURAINICIAL = 0;
	private static int VELOCIDADE = 5;
	
	public Poder(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
	}
	
	public void load() {
		
		if(this.nomePersonagem == "Zeus") {
			ImageIcon referencia = new ImageIcon("res\\PoderZeus.png");
			this.imagemPoder = referencia.getImage();
		} else if (this.nomePersonagem == "Hades") {
			ImageIcon referencia = new ImageIcon("res\\PoderHades.png");
			this.imagemPoder = referencia.getImage();
		} else {
			ImageIcon referencia = new ImageIcon("res\\PoderPoseidon.png");
			this.imagemPoder = referencia.getImage();
		}
		
		
		this.altura = imagemPoder.getWidth(null);
		this.largura = imagemPoder.getHeight(null);
	}
	
	public void update(String tipo) {
		if(tipo == "Pra frente") {
		    this.x += VELOCIDADE;
		} else if(tipo == "Pra tras") {
			this.x -= VELOCIDADE;
		}
		if(this.x > LARGURA || this.x < LARGURAINICIAL) {
			isVisivel = false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int velocidade) {
		VELOCIDADE = velocidade;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Image getImagemPoder() {
		return imagemPoder;
	}
	
	

}
