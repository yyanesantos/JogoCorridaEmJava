package jogo.Grafico;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Buraco {
	private BufferedImage imagemBuraco;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private boolean bateu;
	private CarregadorImagens carregadorImagens;
	
    public Buraco(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		bateu = false;
		carregadorImagens = new CarregadorImagens();
		
	}
    
    public void load() {
		
		this.imagemBuraco = carregadorImagens.getImagem("Buraco");
		
		this.altura = imagemBuraco.getHeight(null) + 20;
		this.largura = imagemBuraco.getWidth(null) + 20;
		
		
	}
    
    public void update() {
		this.x += 0;
		if(bateu == true) {
			isVisivel = false;
		}
	}
    
    public Rectangle getBounds() {
    	return new Rectangle(x, y, largura, altura);
    }
    
	public boolean isBateu() {
		return bateu;
	}

	public void setBateu(boolean bateu) {
		this.bateu = bateu;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	
	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImagemBuraco() {
		return imagemBuraco;
	}
	
	

}
