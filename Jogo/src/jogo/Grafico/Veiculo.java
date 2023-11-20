package jogo.Grafico;

import java.awt.image.BufferedImage;
public class Veiculo {
	
	
	protected int x, y;
	protected int dx, dy;
	protected double speed;
	protected double aceleracao;
	protected int MaxSpeed;
	protected int MinSpeed;
	protected double MaxAceleracao;
	protected double MinAceleracao;
	
	protected BufferedImage imagem;
	protected String direction;
	
	protected float vida;
	protected KeyHandler keyH;
	
	public void setVida (float vida) {
		this.vida = vida;
	}
	
	public float getVida () {
		return this.vida;
	}
	
	public void acelerar () {
		if(keyH.rightPressed == true) {
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
		if(keyH.leftPressed == true) {
			this.speed -= 2;
		}
	}
	
	public void desacelerar() {
		if(keyH.leftPressed == false) {
			this.speed -= 0.09;
		}
	}
	
	public void derrapar () {
		
	}
	
	public void virar () {
		if(this.speed != 0) {
			if(keyH.upPressed == true) {
				this.y -= 2;
			}
			else if(keyH.downPressed == true) {
				this.y += 2;
			}
		}
	}

	public void explodir () {
		
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
