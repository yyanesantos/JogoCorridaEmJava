package jogo.Grafico;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import jogo.Jogo;


public class Player1 extends Veiculo{
	Corrida corrida;
	
	private Personagem personagem;
	private Image imagem;
	private int altura, largura;
	private List <Poder> poderesPraFrente;
	private List <Poder> poderesPraTras;
	private boolean isVisivel;
	//private List <Nitro> nitros;
	
	
	
	public Player1 (String personagemSelecionado, Corrida corrida, KeyHandler keyH) {
		this.corrida = corrida;		
		this.keyH = keyH;
		
		load(personagemSelecionado);
		setDefaultValues();
				
		poderesPraFrente = new ArrayList<Poder>();
		poderesPraTras = new ArrayList<Poder>();
	}
	
	public void setDefaultValues() {
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		this.speed = 0;
		this.aceleracao = 0.3;
		this.MinSpeed = 0;
		this.MaxSpeed = 8;
		this.direction = "direita";
	}
	
    public void load(String deus){
    	
    	altura = corrida.tileSize - 10;
		largura = corrida.tileSize - 10;
    	
    	if(deus == "Zeus") {
		    ImageIcon referencia = new ImageIcon("res\\CarroZeus.png");
			imagem = referencia.getImage();
		} else if(deus == "Poseidon") {
			ImageIcon referencia = new ImageIcon("res\\CarroPoseidon.png");
			imagem = referencia.getImage();
		} else {
			ImageIcon referencia = new ImageIcon("res\\CarroHades.png");
			imagem = referencia.getImage();
		}
	}
	
	public void update () {
		x += speed;

		this.frear();
		this.desacelerar();
		this.velocidade();
		this.virar();
		vantagens();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void vantagens() {

		if(keyH.spacePressed == true) {
		   this.poderesPraFrente.add(new Poder(x + largura, y + (altura/2) - 16));
		   this.poderesPraTras.add(new Poder(x - 82, y + (altura/2) - 16));
		   //this.nitros.add(new Nitro(x - largura, y));
		}
	}
	
	public void draw(Graphics2D g2) {
	    g2.drawImage(imagem, x, y, corrida.tileSize - 10, corrida.tileSize - 10, null);
		
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Image getImagem() {
		return this.imagem;
	}

	public List<Poder> getPoderesPraFrente() {
		return this.poderesPraFrente;
	}

	public List<Poder> getPoderesPraTras() {
		return this.poderesPraTras;
	}
	
	
	

	/*public List<Nitro> getNitros() {
		return nitros;
	}*/
	
	
	
	
	
}
