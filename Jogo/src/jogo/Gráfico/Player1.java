package jogo.Gráfico;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import jogo.Jogo;


public class Player1 extends Veiculo{
	Jogo gp;
	KeyHandler keyH;
	
	private int x, y;
	private int dx, dy;
	private Personagem personagem;
	private Image imagem;
	private int altura, largura;
	private List <Poder> poderesPraFrente;
	private List <Poder> poderesPraTras;
	//private List <Nitro> nitros;
	
	public Player1 (KeyHandler keyH) {
		this.x = 100;
		this.y = 100;
		
		
		this.keyH = keyH;
		setDefaultValues();
		getCarroImage("Zeus");
		
		poderesPraFrente = new ArrayList<Poder>();
		poderesPraTras = new ArrayList<Poder>();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		this.speed = 0;
		this.aceleracao = 0.2;
		this.MinSpeed = 0;
		this.MaxSpeed = 10;
		this.direction = "direita";
		//gear = 1;
	}
	
    public void getCarroImage(String deus){
		/*if(deus == "Zeus") {
		    try {
			    imagem = ImageIO.read(getClass().getResourceAsStream("/CarroZeus.png"));
		    }catch(IOException e) {
			    e.printStackTrace();
		    }
		} else if(deus == "Poseidon") {
			try {
			    imagem = ImageIO.read(getClass().getResourceAsStream("/res/CarroPoseidon.png"));
		    }catch(IOException e) {
			    e.printStackTrace();
		    }
		} else {
			try {
			    imagem = ImageIO.read(getClass().getResourceAsStream("/res/CarroHades.png"));
		    }catch(IOException e) {
			    e.printStackTrace();
		    }
		}*/
    	
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
	
	public void load () {
		/*ImageIcon referencia = new ImageIcon("res\\CarroPoseidon.png");
		imagem = referencia.getImage();*/
		
		//altura = imagem.getHeight(null);
		//largura = imagem.getWidth(null);
	}
	
	public void update () {
		x += speed;
		//marchas();
		frear();
		desacelerar();
		velocidade();
		virar();
	}
	

	public void vantagens() {
		
		this.poderesPraFrente.add(new Poder(x + largura, y + (altura/2) - 16));
		this.poderesPraTras.add(new Poder(x - 82, y + (altura/2) - 16));
		//this.nitros.add(new Nitro(x - largura, y));
	}
	
	/*public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		if(direction == "direita") {
			image = (BufferedImage) imagem;
		}
		g2.drawImage(image, x, y, );
		
	}*/
	
	public void keyPressed (KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if(codigo == KeyEvent.VK_ENTER) {
			vantagens();
		}
		if(codigo == KeyEvent.VK_UP) {
			(this).virar();
		}
		if(codigo == KeyEvent.VK_DOWN) {
			(this).virar();
		}
		if(codigo == KeyEvent.VK_LEFT) {
			(this).frear();
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			(this).acelerar();
		}
	}
	
	public void keyRelease (KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
	    if(codigo == KeyEvent.VK_UP) {
			dy = 0;
		}
		if(codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if(codigo == KeyEvent.VK_LEFT) {
			(this).desacelerar();
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			(this).desacelerar();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Poder> getPoderesPraFrente() {
		return poderesPraFrente;
	}

	public List<Poder> getPoderesPraTras() {
		return poderesPraTras;
	}

	

	/*public List<Nitro> getNitros() {
		return nitros;
	}*/
	
	
	
	
	
}
