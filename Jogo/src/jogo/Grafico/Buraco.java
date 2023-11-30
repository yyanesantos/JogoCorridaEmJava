package jogo.Grafico;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Buraco {
	private Image imagemBuraco;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private boolean bateu;
	
    public Buraco(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		bateu = false;
	}
    
    public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\Buraco.png");
		this.imagemBuraco = referencia.getImage();
		
		this.altura = imagemBuraco.getHeight(null);
		this.largura = imagemBuraco.getWidth(null);
		
		
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

	public Image getImagemBuraco() {
		return imagemBuraco;
	}
	
	

}
