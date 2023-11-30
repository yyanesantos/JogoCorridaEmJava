package jogo.Grafico;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Vantagem {
	
	private Image imagemVantagem;
	private int x, y;
	private int largura, altura;
	private String tipoVantagem;
	private boolean isVisivel;
	private boolean bateu;
	
    public Vantagem(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
	}
    
    public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\Vantagem.png");
		this.imagemVantagem = referencia.getImage();
		
		this.altura = 33;
		this.largura = 33;
		
		
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
    
	public String getTipoVantagem() {
		return tipoVantagem;
	}

	public void setTipoVantagem(String tipoVantagem) {
		this.tipoVantagem = tipoVantagem;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagemVantagem() {
		return imagemVantagem;
	}
	
	
    
    

}
