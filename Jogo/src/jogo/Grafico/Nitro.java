package jogo.Grafico;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Nitro {
	
	private Image imagemNitro;
	private int x, y;
	private int largura, altura;
	private int tempoDeUtilizacao;
	private boolean isVisivel;
	private boolean ativado;
	private Timer timer;
	
	private static int VELOCIDADE = 2;
	
    public Nitro(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.isVisivel = true;
	}
    
    
    
    public int getTempoDeUtilizacao() {
		return tempoDeUtilizacao;
	}



	public void setTempoDeUtilizacao(int tempoDeUtilizacao) {
		this.tempoDeUtilizacao = tempoDeUtilizacao;
	}



	public void load() {
    	
    	ImageIcon referencia = new ImageIcon("res\\Nitro.png");
    	this.imagemNitro = referencia.getImage();

		this.altura = imagemNitro.getWidth(null);
		this.largura = imagemNitro.getHeight(null);
	}
    
    public void update() {
    	this.timer = new Timer(3000, null);
    	timer.start();
    	this.x += VELOCIDADE;
		if(this.timer.getDelay() > this.tempoDeUtilizacao) {
			timer.stop();
			isVisivel = false;
		}
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

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public Image getImagemNitro() {
		return imagemNitro;
	}

}
