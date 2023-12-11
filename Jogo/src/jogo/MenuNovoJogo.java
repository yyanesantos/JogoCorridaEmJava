package jogo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuNovoJogo extends JPanel{
	
	public MenuNovoJogo() {
	    JFrame janelaNovoJogo = new JFrame("Novo Jogo");
	    janelaNovoJogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	    janelaNovoJogo.setSize(800, 500);
	    janelaNovoJogo.setLocationRelativeTo(null);
	    janelaNovoJogo.setResizable(false);
	    janelaNovoJogo.setVisible(true);
	    JPanel painelNovoJogo = new JPanel();
	    painelNovoJogo.setBackground(Color.WHITE);
	    janelaNovoJogo.add(painelNovoJogo);
	    repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 500);
		desenharTelaInicio(g);
	}

}
