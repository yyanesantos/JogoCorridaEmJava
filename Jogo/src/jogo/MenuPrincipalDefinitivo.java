package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import jogo.Grafico.CarregadorImagens;
import jogo.Grafico.Personagem;

public class MenuPrincipalDefinitivo extends JPanel implements  ActionListener{
	
	//EM PROCESSO DE CONSTRUCAO
	
	Graphics g;
	private CarregadorImagens carregadorImagens;
	private BufferedImage imagemMenuPrincipal;
	private BufferedImage imagemSelecao;
	private BufferedImage imagemComoJogar;
	private Timer timer;
	private Timer timerJanelas;
	private String qualMenu;
	private int cliques;
	private String situacaoJogo;
	private boolean novoJogoIsVisivel = false;
	private boolean comoJogarIsVisivel = false;
	private boolean sairIsVisivel = false;
	private boolean umaJanelaAberta = false;
	
	private boolean janelaNovoJogoJaCriada = false;
	
	private MenuNovoJogo menuNovoJogo;
	
	private Personagem zeus;
	private Personagem hades;
	private Personagem poseidon;
	
	private String personagem1Player;
	private String personagem2Player;
	
	public MenuPrincipalDefinitivo() {
		
		
	    zeus = new Personagem("Zeus");
	    hades = new Personagem("Hades");
	    poseidon = new Personagem("Poseidon");
		carregadorImagens = new CarregadorImagens();
		timer = new Timer(100, this);
		timerJanelas = new Timer(1000, this);
		timer.start();
		imagemMenuPrincipal = carregadorImagens.getImagem("tituloMenu");
		imagemSelecao = carregadorImagens.getImagem("Vantagem");
		imagemComoJogar = carregadorImagens.getImagem("MenuComoJogar");
		repaint();
	}
	
	public String getSituacaoJogo() {
		return situacaoJogo;
	}



	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 700, 400);
		desenharTelaInicio(g);
	}
	
	public void desenharTelaInicio(Graphics g) {
		setFocusable(true);
		addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	if(e.getXOnScreen() > 609 && e.getXOnScreen() < 762 && e.getYOnScreen() > 408 && e.getYOnScreen() < 431) {
                	novoJogoIsVisivel = true;
                	qualMenu = "Novo Jogo";
        		} else {
        			novoJogoIsVisivel = false;
        		} 
                if(e.getXOnScreen() > 599 && e.getXOnScreen() < 773 && e.getYOnScreen() > 443 && e.getYOnScreen() < 467) {
                	comoJogarIsVisivel = true;
                	qualMenu = "Como Jogar";
                } else {
                	comoJogarIsVisivel = false;
                }
                if(e.getXOnScreen() > 653 && e.getXOnScreen() < 710 && e.getYOnScreen() > 478 && e.getYOnScreen() < 502) {
                	sairIsVisivel = true;
                	qualMenu = "Sair";
                } else {
                	sairIsVisivel = false;
                }
            }

            public void mouseDragged(MouseEvent e) {
            }               
        });
		addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               cliques = 1;
               if(qualMenu == "Novo Jogo" && umaJanelaAberta == false) {
            	   umaJanelaAberta = true;
            	   timerJanelas.start();
            	   menuNovoJogo();
               }else if(qualMenu == "Como Jogar" && umaJanelaAberta == false) {
            	   umaJanelaAberta = true;
            	   timerJanelas.start();
            	   menuComoJogar();
               } else if(qualMenu == "Sair" && umaJanelaAberta == false) {
            	   umaJanelaAberta = true;
            	   timerJanelas.start();
            	   situacaoJogo = "Fechar";
               }
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                cliques = 0;
            }
        });
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(imagemMenuPrincipal, 0, 0, null);
		
		zeus.reacao(g2, "Feliz", 80, 120);
		hades.reacao(g2, "Feliz", 550, 20);
		poseidon.reacao(g2, "Feliz", 580, 200);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		g2.setColor(Color.RED);
		
		String texto = "Novo Jogo";
		int x = this.getWidth()/2 - 75;
		int y = this.getHeight()/2 + 60;
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x+1, y+1);
		g2.setColor(Color.RED);
		g2.drawString(texto, x, y);
		
		if(novoJogoIsVisivel == true) {
			g2.drawImage(imagemSelecao, x - 30, y - 25, 25, 25, null);
		}
		
		texto = "Como Jogar";
		x -= 10;
		y += 35;
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x+1, y+1);
		g2.setColor(Color.RED);
		g2.drawString(texto, x, y);
		
		if(comoJogarIsVisivel == true) {
			g2.drawImage(imagemSelecao, x - 30, y - 25, 25, 25, null);
		}
		
		
		texto = "Sair";
		x += 55;
		y += 35;
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x+1, y+1);
		g2.setColor(Color.RED);
		g2.drawString(texto, x, y);
		
		if(sairIsVisivel == true) {
			g2.drawImage(imagemSelecao, x - 30, y - 25, 25, 25, null);
		}
	}
	
	public void menuComoJogar() {
		JFrame janelaComoJogar = new JFrame("Como Jogar");
		janelaComoJogar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		janelaComoJogar.setSize(720, 400);
		janelaComoJogar.setLocationRelativeTo(null);
		janelaComoJogar.setResizable(false);
		janelaComoJogar.setVisible(true);
		JPanel painelComoJogar = new JPanel();
		painelComoJogar.setBackground(Color.WHITE);
		JLabel picLabel = new JLabel(new ImageIcon(imagemComoJogar));
		painelComoJogar.add(picLabel, BorderLayout.CENTER);
		janelaComoJogar.add(painelComoJogar);
		
		
	}
	
	public String getPersonagem1Player() {
		return personagem1Player;
	}

	public void setPersonagem1Player(String personagem1Player) {
		this.personagem1Player = personagem1Player;
	}

	public String getPersonagem2Player() {
		return personagem2Player;
	}

	public void setPersonagem2Player(String personagem2Player) {
		this.personagem2Player = personagem2Player;
	}

	public void setJanelaNovoJogoJaCriada(boolean janelaNovoJogoJaCriada) {
		this.janelaNovoJogoJaCriada = janelaNovoJogoJaCriada;
	}
	
	public Personagem getZeus() {
		return zeus;
	}

	public Personagem getHades() {
		return hades;
	}

	public Personagem getPoseidon() {
		return poseidon;
	}
	
	public void setSituacaoJogo(String situacaoJogo) {
		this.situacaoJogo = situacaoJogo;
	}

	public void menuNovoJogo() {
		situacaoJogo = "Novo Jogo";
		if(janelaNovoJogoJaCriada == false) {
			janelaNovoJogoJaCriada = true;
			menuNovoJogo = new MenuNovoJogo(this);
		}
	}

	public MenuNovoJogo getMenuNovoJogo() {
		return menuNovoJogo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if(timerJanelas.isRunning()) {
			if(umaJanelaAberta == true) {
				umaJanelaAberta = false;
				timerJanelas.stop();
			}
		}
		
		if(situacaoJogo == "Escolhido") {
			situacaoJogo = "Iniciado";
		}
		
	}

	
}
