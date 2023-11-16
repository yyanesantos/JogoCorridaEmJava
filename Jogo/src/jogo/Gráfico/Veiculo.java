package jogo.Gráfico;

import java.awt.image.BufferedImage;
public class Veiculo {
	
	KeyHandler keyH = new KeyHandler();
	
	protected int x, y;
	protected double speed;
	protected double aceleracao;
	protected int MaxSpeed;
	protected int MinSpeed;
	//public int gear;
	protected double MaxAceleracao;
	protected double MinAceleracao;
	
	protected BufferedImage imagem;
	protected String direction;
	
	private float vida;
	
	public void setVida (float vida) {
		this.vida = vida;
	}
	
	public float getVida () {
		return this.vida;
	}
	
	public void acelerar () {
		if(keyH.rightPressed == true) {
			speed += aceleracao;
		}
		/*if(aceleracao > 0.5) {
			aceleracao = 0.3;
		}
		if(aceleracao < 0) {
			aceleracao = 0;
		}*/
	}
	
	public void velocidade() {
		acelerar();
		if(speed < MinSpeed) {
			speed = MinSpeed;
		}
		if (speed > MaxSpeed) {
			speed = MaxSpeed;
		}
	}
	
	public void frear () {
		if(keyH.leftPressed == true) {
			speed -= 2;
		}
	}
	
	public void desacelerar() {
		if(keyH.leftPressed == false) {
			speed -= 0.09;
		}
	}
	
	public void derrapar () {
		
	}
	
	public void virar () {
		if(speed != 0) {
			if(keyH.upPressed == true) {
				y -= 1;
			}
			else if(keyH.downPressed == true) {
				y += 1;
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
