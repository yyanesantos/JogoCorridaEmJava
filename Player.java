package jogo.Grafico;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import jogo.Jogo;


public class Player extends Veiculo implements ActionListener{
	private static final Graphics2D g2 = null;

	Corrida corrida;
	
	private Personagem personagem;
	
	private int altura, largura;
	private List <Poder> poderesPraFrente;
	private List <Poder> poderesPraTras;

	private MonitorTempo monitor;
	private String deus;
	private int repAnimacaoBatida;
	private int repAnimacaoExplosao;
	
	private boolean explodiu = false;
	
	
	
	
	public Player (String personagemSelecionado, Corrida corrida, KeyHandler keyH) {
		imagemExplosao = carregadorImagens.getImagem("Explosao");
		
		isVisivel = true;
		
		this.corrida = corrida;		
		this.keyH = keyH;
		this.deus = personagemSelecionado;	
		monitor = new MonitorTempo(keyH);
		setStatusVida("3Vidas");
		setStatusPoder("PoderOFF");
		setStatusNitro("NitroOFF");
		
		timerExplosao = new Timer(150, this);
		timerAnimacao = new Timer(150, this);
		
		
		load();
		setDefaultValues();
		poderesPraFrente = new ArrayList<Poder>();
		poderesPraTras = new ArrayList<Poder>();
		
	}
		
	public void setDefaultValues() {
		
		this.speed = 0;
		this.aceleracao = 0.8;
		this.MinSpeed = 0;
		this.MaxSpeed = 10;
		this.direction = "direita";
	}
	
    public void load(){
    	
    	if(this.deus == "Zeus") {
			imagem = carregadorImagens.getImagem("CarroZeus");
		} else if(this.deus == "Poseidon") {
			imagem = carregadorImagens.getImagem("CarroPoseidon");
		} else {
			imagem = carregadorImagens.getImagem("CarroHades");
		}
    	
    	altura = 60;
		largura = 70;
	}
	
	public void update (ArrayList<Vantagem> vantagens) {
		x += speed;
		this.frear();
		this.desacelerar();
		this.velocidade();
		if(explodiu == false) {
		this.virar();
		}
		vantagens(vantagens);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y + 20, largura, altura - 20);
	}

	public void vantagens(ArrayList<Vantagem> vantagens) {

		   if(keyH.space1Pressed == true && isPoder == true) {
			   monitor.startTimerPoder();
		       this.poderesPraFrente.add(new Poder(x + largura, y + (altura/2) - 16));
		       this.poderesPraTras.add(new Poder(x - 82, y + (altura/2) - 16));
		   } else if(keyH.space1Pressed == true && isPoder == false && isNitro == false) {
			   keyH.space1Pressed = false;
		   } else if(keyH.space1Pressed == true && isNitro == true) {
			   nitro();
			   monitor.startTimerNitro();
		   } 
	        if(monitor.isContagemFinalizada() == true) {
	    	   keyH.space1Pressed = false;
	    	   isNitro = false;
	    	   load();
	    	   setStatusNitro("NitroOFF");
	    	   monitor.setContagemFinalizada(false);
	       }
	    
	}
	
	public void nitro() {
		speed += 3;
		if(this.deus == "Zeus") {
			imagem = carregadorImagens.getImagem("CarroZeusNitro");
		} else if(this.deus == "Poseidon") {
			imagem = carregadorImagens.getImagem("CarroPoseidonNitro");
		} else {
			imagem = carregadorImagens.getImagem("CarroHadesNitro");
		}
	}
	
	public void piscar() {
		if(this.isVisivel == true && repAnimacaoBatida < 12) {
			isVisivel = false;
			repAnimacaoBatida++;
			timerAnimacao.start();
		} else if(this.isVisivel() == false && repAnimacaoBatida < 12d) {
			this.setVisivel(true);
			repAnimacaoBatida++;
			this.timerAnimacao.start();
		} else {
			repAnimacaoBatida = 0;
		}
	}

	public void explodir () {
		setImagem(imagemExplosao);
		setDefaultValues();
		explodiu = true;
		
		if(isVisivel() == true && repAnimacaoExplosao < 12) {
			setVisivel(false);
			repAnimacaoExplosao++;
			getTimerExplosao().start();
		} else if(isVisivel() == false && repAnimacaoExplosao < 12) {
			setVisivel(true);
			repAnimacaoExplosao++;
			getTimerExplosao().start();
		} else {
			repAnimacaoExplosao = 0;
			load();
			setY(140);
			explodiu = false;
		}
	}
	
	public void derrapar(String modo) {
		if(modo == "Pra baixo") {
		    for(int a = 0; a < 3 ; a ++) {
			    setY(this.y + 5 + a*5);
		    }
		} else if(modo == "Pra cima") {
			for(int a = 0; a < 3; a ++) {
				setY(this.y - 5 - a*5);
			}
		}
	}
	
	
	public void draw(Graphics2D g2, int desvioDeNivel) {
		if(this.isVisivel() == true) {
	    g2.drawImage(imagem, x - desvioDeNivel, y, largura, altura, null);
		}
	    g2.drawImage(getImagemStatusVida(), xVida, 260 + 250 +100, null); 
	    g2.drawImage(getImagemStatusNitro(), xNitro, 286 + 250 +100, null); 
	    g2.drawImage(getImagemStatusPoder(), xPoder, 303 + 250 +100, null); 
		
	}
	
	public void draw(Graphics2D g2, int desvioDeNivel, String qualPlayer) {
		if(this.isVisivel() == true && qualPlayer == "1-Player") {
	    g2.drawImage(imagem, x - desvioDeNivel, 400 + y, largura, altura, null);
		} else {
			g2.drawImage(imagem, x - desvioDeNivel, 500 + y, largura, altura, null);
		}
	    g2.drawImage(getImagemStatusVida(), xVida, 260 + 500, null); 
	    g2.drawImage(getImagemStatusNitro(), xNitro, 286 + 500, null); 
	    g2.drawImage(getImagemStatusPoder(), xPoder, 303 + 500, null); 
		
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}
	
	public Timer getTimerExplosao() {
		return timerExplosao;
	}

	public Timer getTimerAnimacao() {
		return timerAnimacao;
	}

	public boolean isNitro() {
		return isNitro;
	}

	public boolean isPoder() {
		return isPoder;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public void setNitro(boolean isNitro) {
		this.isNitro = isNitro;
	}

	public void setPoder(boolean isPoder) {
		this.isPoder = isPoder;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	


	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public BufferedImage getImagem() {
		return this.imagem;
	}
	
	public MonitorTempo getMonitor() {
		return monitor;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	public List<Poder> getPoderesPraFrente() {
		return this.poderesPraFrente;
	}

	public List<Poder> getPoderesPraTras() {
		return this.poderesPraTras;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.getTimerAnimacao().isRunning()) {
			this.getTimerAnimacao().stop();
			piscar();
		}
		
		if(this.getTimerExplosao().isRunning()) {
			this.getTimerExplosao().stop();
			explodir();
		}
		
	}

	
	
	
	
	
	
}
