package jogo.Grafico;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Poder {
	
	private BufferedImage imagemPoder;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private CarregadorImagens carregadorImagens;
	
	private static final int LARGURA = 2880;
	private static final int LARGURAINICIAL = 0;
	private static int VELOCIDADE = 15;
	
	public Poder(int x, int y) {
		
		this.x = x;
		this.y = y;
		carregadorImagens = new CarregadorImagens();
		this.isVisivel = true;
	}
	
	public void load(String nomePersonagem) {
		
		if(nomePersonagem == "Zeus") {
			imagemPoder = carregadorImagens.getImagem("PoderZeus");
		} else if (nomePersonagem == "Hades") {
			imagemPoder = carregadorImagens.getImagem("PoderHades");
		} else {
			imagemPoder = carregadorImagens.getImagem("PoderPoseidon");
		}
		
		
		this.altura = imagemPoder.getWidth(null);
		this.largura = imagemPoder.getHeight(null);
	}
	
	public void update(String tipo) {
	    if(tipo == "Pra frente") {
				this.x += VELOCIDADE;
		    }else if(tipo == "Pra tras") {
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
