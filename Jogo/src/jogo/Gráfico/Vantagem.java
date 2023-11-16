package jogo.Gráfico;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Vantagem {
	
	private Image imagemVantagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	
    public Vantagem(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
	}
    
    public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\Vantagem.png");
		this.imagemVantagem = referencia.getImage();
		
		this.altura = imagemVantagem.getWidth(null);
		this.largura = imagemVantagem.getHeight(null);
		
		
	}
    
    public void update(int x, int y) {
		this.x = x;
		/*if(this.x > LARGURA) {
			isVisivel = false;
		}*/
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
    
    

}
