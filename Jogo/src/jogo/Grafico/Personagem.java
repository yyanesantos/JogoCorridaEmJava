package jogo.Grafico;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class Personagem implements ActionListener{
	private String nome;
	
	private BufferedImage imagem;
	private BufferedImage imagemFeliz;
	private BufferedImage imagemTriste;
	private BufferedImage imagemExistindo;
	
	private BufferedImage imagemCarro;
	
	private String modo;
	
	private Timer timer;
	private CarregadorImagens carregadorImagens;
	
	public Personagem(String nome) {
		this.nome = nome;
		
		timer = new Timer(500, this);
		carregadorImagens = new CarregadorImagens();
		
		imagemExistindo = carregadorImagens.getImagem(nome+"Existindo");
		imagemFeliz = carregadorImagens.getImagem(nome+"Feliz");
		imagemTriste = carregadorImagens.getImagem(nome+"Triste");
		imagemCarro = carregadorImagens.getImagem("Carro" + nome);
		
	}




	public void reacao(Graphics2D g2, String modo, int x, int y) {
		timer.start();
		if(this.modo == "") {
			imagem = imagemExistindo;
			g2.drawImage(imagem, x, y, null);
		}else {
		    imagem = carregadorImagens.getImagem(this.nome + modo);
		    g2.drawImage(imagem, x, y, null);
		}
	}
	
	public void mostrarCarro(Graphics2D g2, int x, int y) {
		g2.drawImage(imagemExistindo, x - 100, y - 20, null);
		g2.drawImage(imagemCarro, x, y, null);
	}

	public void comemorar() {
		imagem = imagemFeliz;
	}
	
	public void existir() {
		imagem = imagemExistindo;
	}
	
	public void trister() {
		imagem = imagemTriste;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(modo != "") {
			modo = "";
		} else {
			modo = "Reagindo";
		}
		
		
	}
	
}
