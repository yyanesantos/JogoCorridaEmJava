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

public class MenuPrincipalDefinitivo extends JPanel implements  ActionListener{
	
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
	
	public MenuPrincipalDefinitivo() {
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
	
	public void paint(Graphics g, String situacaoJogo) {
		int y = 250;
		int x = 100;
		g.setColor(Color.RED);
		g.fillRect(x, y, 200, 100);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
		String texto = "1-Player";
		g.drawString(texto, x, y);
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
	
	public void menuNovoJogo() {
		situacaoJogo = "Novo Jogo";
		JFrame janelaNovoJogo = new JFrame("Novo Jogo");
		janelaNovoJogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		janelaNovoJogo.setSize(800, 500);
		janelaNovoJogo.setLocationRelativeTo(null);
		janelaNovoJogo.setResizable(false);
		janelaNovoJogo.setVisible(true);
		JPanel painelNovoJogo = new JPanel();
		painelNovoJogo.setBackground(Color.WHITE);
		painelNovoJogo.paint(g);
		janelaNovoJogo.add(painelNovoJogo);
		
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
		
	}

	
}
