package jogo.Grafico;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Vantagem {
	
	private BufferedImage imagemVantagem;
	private CarregadorImagens carregadorImagens;
	private int x, y;
	private int largura, altura;
	private String tipoVantagem;
	private boolean isVisivel;
	private boolean bateu;
	
    public Vantagem(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		carregadorImagens = new CarregadorImagens();
	}
    
    public void load() {
		
		this.imagemVantagem = carregadorImagens.getImagem("Vantagem");
		
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

	public BufferedImage getImagemVantagem() {
		return imagemVantagem;
	}
	
	
    
    

}
