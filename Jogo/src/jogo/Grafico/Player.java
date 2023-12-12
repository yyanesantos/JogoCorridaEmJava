package jogo.Grafico;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;



public class Player extends Veiculo implements ActionListener{
	private static final Graphics2D g2 = null;

	Corrida corrida;
	
	private int altura, largura;
	private List <Poder> poderesPraFrente;
	private List <Poder> poderesPraTras;

	private MonitorTempo monitorInvencibilidade;
	private MonitorTempo monitorNitro;
	private MonitorTempo monitorPoder;
	private MonitorTempo monitorVoltaCompleta;
	
	
	
	
	private String nomePersonagem;
	private int repAnimacaoBatida;
	private int repAnimacaoExplosao;
	
	private boolean explodiu = false;
	
	
	
	
	public Player (String personagemSelecionado, Corrida corrida, KeyHandler keyH, String qualPlayer) {
		imagemExplosao = carregadorImagens.getImagem("Explosao");
		setImagemPlayer(qualPlayer);
		
		isVisivel = true;
		
		numeroVoltas = 1;
		this.corrida = corrida;		
		this.keyH = keyH;
		this.nomePersonagem = personagemSelecionado;	
		monitorInvencibilidade = new MonitorTempo(keyH, 1500);
		monitorNitro = new MonitorTempo(keyH, 2000);
		monitorPoder = new MonitorTempo(keyH, 1000);
		monitorVoltaCompleta = new MonitorTempo(keyH, 1000);
		setStatusVida("3Vidas");
		setStatusPoder("PoderOFF");
		setStatusNitro("NitroOFF");
		setStatusVoltas("Volta1");
		
		timerExplosao = new Timer(115, this);
		timerAnimacao = new Timer(115, this);
		
		
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
	}
	
    public void load(){
    	
    	if(this.nomePersonagem == "Zeus") {
			imagem = carregadorImagens.getImagem("CarroZeus");
		} else if(this.nomePersonagem == "Poseidon") {
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
		if(this.x > 2845) {
			x = 0;
		}
		vantagens(vantagens);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y + 20, largura, altura/2);
	}

	public void vantagens(ArrayList<Vantagem> vantagens) {
		
		if(explodiu == true) {
			load();
			monitorNitro.setContagemFinalizada(false);
			monitorPoder.setContagemFinalizada(false);
		}
		
		if(monitorNitro.isContagemFinalizada() == false && monitorPoder.isContagemFinalizada() == false && monitorInvencibilidade.getTimerStatus() == "OFF") {
			if(explodiu == false) {
				if(isNitro == true && keyH.space1Pressed == true) {
					nitro();
					monitorNitro.startTimer();
				} else if(isPoder == true && keyH.space1Pressed == true) {
					monitorPoder.startTimer();
				    this.poderesPraFrente.add(new Poder(x + largura, y + (altura/2) - 16));
				    this.poderesPraTras.add(new Poder(x - largura, y + (altura/2) - 16));
				}
			}
		}
		   if(keyH.space1Pressed == true && isPoder == false && isNitro == false) {
			   keyH.space1Pressed = false;
		   } else if(keyH.space1Pressed == true && isPoder == false && isNitro == false && monitorInvencibilidade.getTimerStatus() == "ON") {
			   keyH.space1Pressed = false;
		   }

	        if(monitorNitro.isContagemFinalizada() == true) {
	           monitorNitro.setContagemFinalizada(false);
	    	   keyH.space1Pressed = false;
	    	   load();
	    	   setStatusNitro("NitroOFF");
	    	   isNitro = false;
	    	   
	       } else if(monitorPoder.isContagemFinalizada() == true) {
	    	   monitorPoder.setContagemFinalizada(false);
	    	   keyH.space1Pressed = false;
	    	   setStatusPoder("PoderOFF");
	    	   isPoder = false;
	       }
	    
	}
	
	public void nitro() {
		speed += 3;
		if(this.nomePersonagem == "Zeus") {
			imagem = carregadorImagens.getImagem("CarroZeusNitro");
		} else if(this.nomePersonagem == "Poseidon") {
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
		} else if(this.isVisivel() == false && repAnimacaoBatida < 12) {
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
			setY(130);
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
	
	public void checarVoltas(int x) {
		if(x >= 2835 && monitorVoltaCompleta.getTimerStatus() == "OFF") {
			this.numeroVoltas ++;
			monitorVoltaCompleta.startTimer();
		}
		
		if(numeroVoltas == 2) {
			setStatusVoltas("Volta2");
		} else if(numeroVoltas == 3) {
			setStatusVoltas("Volta3");
		} else if(numeroVoltas == 4) {
			setStatusVoltas("Volta4");
		} else if(numeroVoltas == 5) {
			setStatusVoltas("Volta5");
		}
		
	}
	
	public void draw(Graphics2D g2, int desvioDeNivel) {
		if(this.isVisivel() == true) {
	    g2.drawImage(imagem, x - desvioDeNivel, y, largura, altura, null);
		}
	    g2.drawImage(getImagemStatusVida(), xVida, 260 + 43 , null); 
	    g2.drawImage(getImagemStatusNitro(), xNitro, 286 + 43 , null); 
	    g2.drawImage(getImagemStatusPoder(), xPoder, 303 + 43 , null); 
	    g2.drawImage(getImagemStatusVoltas(), xVolta, 300 + 43, null);
	    g2.drawImage(getImagemPlayer(), xPlayer, 270+45, null);
	}
	
	public void draw(Graphics2D g2, int desvioDeNivel, String qualPlayer) {
		if(this.isVisivel() == true && qualPlayer == "1-Player") {
	    g2.drawImage(imagem, x - desvioDeNivel, 375 + y, largura, altura, null);
		} else {
			g2.drawImage(imagem, x - desvioDeNivel, 375 + y, largura, altura, null);
		}
		
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

	public String getNomePersonagem() {
		return nomePersonagem;
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
	
	public MonitorTempo getMonitorInvencibilidade() {
		return monitorInvencibilidade;
	}

	public MonitorTempo getMonitorNitro() {
		return monitorNitro;
	}

	public MonitorTempo getMonitorPoder() {
		return monitorPoder;
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
