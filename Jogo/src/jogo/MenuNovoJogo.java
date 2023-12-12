package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MenuNovoJogo extends JPanel implements ActionListener{
	
	//EM PROCESSO DE CONSTRUCAO PARA O MENU PRINCIPAL DEFINITIVO
	
	private MenuPrincipalDefinitivo menuPrincipalDefinitivo;
	private Timer timer;
	private String modoDeJogo;
	private String qualPersonagem;
	private String onde;
	private int cliques;
	private boolean umaJanelaAberta = false;
	
	private String quemEstaEscolhendo;
	private String jaEscolhido;
	private String personagem1Player;
	private String personagem2Player;
	
	private JFrame janelaNovoJogo;
	
	public MenuNovoJogo(MenuPrincipalDefinitivo menuPrincipalDefinitivo) {
		this.menuPrincipalDefinitivo = menuPrincipalDefinitivo;
		timer = new Timer(100, this);
		qualPersonagem = "Zeus";
		quemEstaEscolhendo = "Player1";
		
		timer.start();
	    janelaNovoJogo = new JFrame("Novo Jogo");
	    janelaNovoJogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	    janelaNovoJogo.setSize(800, 500);
	    janelaNovoJogo.setLocationRelativeTo(null);
	    janelaNovoJogo.setResizable(false);
	    setBackground(Color.WHITE);
	    janelaNovoJogo.add(this);
	    modoDeJogo = menuIniciarJogo();
	    janelaNovoJogo.setVisible(true);
	    
	    repaint();
	    
	    
	}
	
	public void paintComponent(Graphics g) {
	    g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 500);
		desenharEscolhas(g, this.modoDeJogo);
		menuPrincipalDefinitivo.setJanelaNovoJogoJaCriada(false);
		
		
	}
	
	public void desenharEscolhas(Graphics g, String modoDeJogo) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
		int x = this.getWidth()/2 - 260 ;
		int y = 50;
		String texto;
		if(quemEstaEscolhendo == "Player1") {
		    texto = "Player 1! Selecione seu personagem: ";
		    g2.setColor(Color.YELLOW);
		    g2.drawString(texto, x+1, y+1);
		    g2.setColor(Color.RED);
		    g2.drawString(texto, x, y);
		}
		
		if(quemEstaEscolhendo == "Player2") {
			texto = "Player 2! Selecione seu personagem: ";
		    g2.setColor(Color.YELLOW);
		    g2.drawString(texto, x+1, y+1);
		    g2.setColor(Color.RED);
		    g2.drawString(texto, x, y);
		}
		
		    if(qualPersonagem == "Zeus") {
		        menuPrincipalDefinitivo.getZeus().mostrarCarro(g2, x + 230, y + this.getHeight()/2 - 100);
		    } else if(qualPersonagem == "Hades") {
		        menuPrincipalDefinitivo.getHades().mostrarCarro(g2, x + 230, y + this.getHeight()/2 - 100);
		    } else if(qualPersonagem == "Poseidon") {
		        menuPrincipalDefinitivo.getPoseidon().mostrarCarro(g2, x + 230, y + this.getHeight()/2 - 100);
		    }
		
		g2.setFont(g.getFont().deriveFont(Font.BOLD, 50F));
		texto = ">";
		x += 610;
		y+= this.getHeight()/2 - 50;
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x, y);
		g2.setColor(Color.RED);
		g2.drawString(texto, x+1, y+1);
		
		texto = "<";
		x -= 732;
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x, y);
		g2.setColor(Color.RED);
		g2.drawString(texto, x+1, y+1);
		
		g2.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
		texto = "Escolher!";
		x += 310;
		y += 180;
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x+1, y+1);
		g2.setColor(Color.RED);
		g2.drawString(texto, x, y);
		
		setFocusable(true);
		addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	if(e.getYOnScreen() > 340 && e.getYOnScreen() < 368) {
            		if(e.getXOnScreen() > 1025 && e.getXOnScreen() < 1051) {
                		onde = "Proximo";
                	} else if(e.getXOnScreen() > 303 && e.getXOnScreen() < 329) {
                		onde = "Anterior";
                	} else {
                		onde = null;
                	}
            	} else if(e.getXOnScreen() > 612 && e.getXOnScreen() < 745 && e.getYOnScreen() < 553 && e.getYOnScreen() > 528) {
            		onde = "Escolher";
            	}
            }

            public void mouseDragged(MouseEvent e) {
            }               
        });
		addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               cliques = 1;
               if(onde == "Proximo") {
            	   if(qualPersonagem == "Zeus" && umaJanelaAberta == false) {
            		   umaJanelaAberta = true;
            		   timer.start();
            		   qualPersonagem = "Hades";
            	   } else if(qualPersonagem == "Hades" && umaJanelaAberta == false) {
            		   umaJanelaAberta = true;
            		   timer.start();
            		   qualPersonagem = "Poseidon";
            	   } else if (qualPersonagem == "Poseidon" && umaJanelaAberta == false){
            		   umaJanelaAberta = true;
            		   timer.start();
            		   qualPersonagem = "Zeus";
            	   }
               } else if(onde == "Anterior") {
            	   if(qualPersonagem == "Zeus" && umaJanelaAberta == false) {
            		   umaJanelaAberta = true;
            		   timer.start();
            		   qualPersonagem = "Hades";
            	   } else if(qualPersonagem == "Hades" && umaJanelaAberta == false) {
            		   umaJanelaAberta = true;
                	   timer.start();
            		   qualPersonagem = "Poseidon";
            	   } else if(qualPersonagem == "Poseidon" && umaJanelaAberta == false) {
            		   umaJanelaAberta = true;
            		   timer.start();
            		   qualPersonagem = "Zeus";
            	   }
               } else if(onde == "Escolher" && umaJanelaAberta == false) {
            	   timer.stop();
            	       if(quemEstaEscolhendo == "Player1") {
            	    	  if(modoDeJogo == "1-Player") {
            	    		   umaJanelaAberta = true;
                    		   timer.start();
            	    		   menuPrincipalDefinitivo.setSituacaoJogo("Escolhido");
            	    		   janelaNovoJogo.dispose();
            	    	   } else {
            	    		   quemEstaEscolhendo = "Player2";
            	    	   }
            	       } else {
            	    	   menuPrincipalDefinitivo.setPersonagem2Player(qualPersonagem);
            	    	   menuPrincipalDefinitivo.setSituacaoJogo("Escolhido");
            	    	   janelaNovoJogo.dispose();
            	       }
                   }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                cliques = 0;
            }
        });
		
	}
	
	public String menuIniciarJogo() {
	    Object[] selectionValues = { "1-Player", "2-Player", "Sair" };
	    String initialSelection = "1-Player";
	    Object selection = JOptionPane.showInputDialog(null, "Selecione o modo de jogo:",
	        "Antes de iniciar", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
	    if(selection == "1-Player") {
	    	return "1-Player";
	    } else if (selection == "2-Player") {
	    	return "2-Player";
	    } else {
	    	return "sair";
	    }
	}

	public String getModoDeJogo() {
		return modoDeJogo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		System.out.println(modoDeJogo);
		System.out.println(quemEstaEscolhendo);
		if(timer.isRunning()) {
			if(umaJanelaAberta == true) {
				umaJanelaAberta = false;
				timer.stop();
			}
		}
		
		if(menuPrincipalDefinitivo.getSituacaoJogo() == "Escolhido") {
			janelaNovoJogo.dispose();
		}
		
	}
}

