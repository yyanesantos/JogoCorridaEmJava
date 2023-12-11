package jogo.Grafico;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.Timer;
public class Veiculo{
	
	
	protected int x, y;
	protected int xVida, xNitro, xPoder;
	protected double speed;
	protected double aceleracao;
	protected int MaxSpeed;
	protected int MinSpeed;
	protected double MaxAceleracao;
	protected double MinAceleracao;
	protected Timer timerAnimacao;
	protected Timer timerExplosao;
	
	protected BufferedImage imagem;
	protected BufferedImage imagemExplosao;
	protected BufferedImage imagemStatusVida;
	protected BufferedImage imagemStatusPoder;
	protected BufferedImage imagemStatusNitro;
	protected CarregadorImagens carregadorImagens = new CarregadorImagens();
	protected String direction;
	
	
	protected String statusVida;
	protected String statusNitro;
	protected String statusPoder;
	
	protected boolean isVisivel, isNitro, isPoder;
	protected KeyHandler keyH;
	
	
	public BufferedImage getImagemStatusPoder() {
		return imagemStatusPoder;
	}

	public BufferedImage getImagemStatusNitro() {
		return imagemStatusNitro;
	}

	public void setStatusNitro(String statusNitro) {
		this.statusNitro = statusNitro;
		imagemStatusNitro = carregadorImagens.getImagem(statusNitro);	
	}

	public void setStatusPoder(String statusPoder) {
		this.statusPoder = statusPoder;
		imagemStatusPoder = carregadorImagens.getImagem(statusPoder);
	}

	public void setStatusVida (String statusVida) {
		this.statusVida = statusVida;
		imagemStatusVida = carregadorImagens.getImagem(statusVida);
	}
	
	public String getStatusVida() {
		return statusVida;
	}



	public BufferedImage getImagemStatusVida () {
		return this.imagemStatusVida;
	}
	
	public void acelerar () {
		if(keyH.right1Pressed == true) {
			this.speed += this.aceleracao;
		}
	}
	
	public void velocidade() {
		acelerar();
		if(this.speed < this.MinSpeed) {
			this.speed = this.MinSpeed;
		}
		if (this.speed > this.MaxSpeed) {
			this.speed = this.MaxSpeed;
		}
	}
	
	public void frear () {
		if(keyH.left1Pressed == true) {
			this.speed -= 2;
		}
	}
	
	public void desacelerar() {
		if(keyH.left1Pressed == false) {
			this.speed -= 0.09;
		}
	}
	
	public void derrapar () {
		
	}
	
	public void virar () {
		if(this.speed != 0) {
			if(keyH.up1Pressed == true) {
				this.y -= 4;
			}
			else if(keyH.down1Pressed == true) {
				this.y += 4;
			}
		}
	}
	
	public void setxVida(int xVida) {
		this.xVida = xVida;
	}

	public void setxNitro(int xNitro) {
		this.xNitro = xNitro;
	}

	public void setxPoder(int xPoder) {
		this.xPoder = xPoder;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

}
