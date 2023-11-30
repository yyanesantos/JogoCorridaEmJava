package jogo.Grafico;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	protected Image imagem;
	protected Image imagemExplosao;
	protected Image imagemStatusVida;
	protected Image imagemStatusPoder;
	protected Image imagemStatusNitro;
	protected String direction;
	
	
	protected String statusVida;
	protected String statusNitro;
	protected String statusPoder;
	
	protected boolean isVisivel, isNitro, isPoder;
	protected KeyHandler keyH;
	
	
	public Image getImagemStatusPoder() {
		return imagemStatusPoder;
	}

	public Image getImagemStatusNitro() {
		return imagemStatusNitro;
	}

	public void setStatusNitro(String statusNitro) {
		this.statusNitro = statusNitro;
		if(statusNitro == "ON") {
			ImageIcon referencia = new ImageIcon("res\\NitroON.png");
		    imagemStatusNitro = referencia.getImage();
		} else {
			ImageIcon referencia = new ImageIcon("res\\NitroOFF.png");
		    imagemStatusNitro = referencia.getImage();
		}
		
	}

	public void setStatusPoder(String statusPoder) {
		this.statusPoder = statusPoder;
		if(statusPoder == "ON") {
			ImageIcon referencia = new ImageIcon("res\\PoderON.png");
		    imagemStatusPoder = referencia.getImage();
		} else {
			ImageIcon referencia = new ImageIcon("res\\PoderOFF.png");
		    imagemStatusPoder = referencia.getImage();
		}
	}

	public void setStatusVida (String statusVida) {
		this.statusVida = statusVida;
		if(statusVida == "3 Vidas") {
		    ImageIcon referencia = new ImageIcon("res\\3Vidas.png");
		    imagemStatusVida = referencia.getImage();
		} else if(statusVida == "2 Vidas") {
			ImageIcon referencia = new ImageIcon("res\\2Vidas.png");
			imagemStatusVida = referencia.getImage();
		} else {
			ImageIcon referencia = new ImageIcon("res\\1Vida.png");
			imagemStatusVida = referencia.getImage();
		}
	}
	
	public String getStatusVida() {
		return statusVida;
	}



	public Image getImagemStatusVida () {
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
				this.y -= 2;
			}
			else if(keyH.down1Pressed == true) {
				this.y += 2;
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

	public void encherNitro () {
		
	}
	
	public void turbinar () {
		
	}
	
    
	
	public void diminuirVida () {
		
	}
	
	public void encherVida () {
		
	}
	
}
