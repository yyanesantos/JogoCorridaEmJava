package jogo.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private Image fundoStatus;
	private Player1 player1;
	private Timer timer;
	
	public Fase () {
		setFocusable(true);
		setDoubleBuffered(true);
		
		
		ImageIcon pistaDeCorrida = new ImageIcon("res\\PistaDeCorrida2.png");
		ImageIcon status = new ImageIcon ("res\\fundoBranco.png");
		fundo = pistaDeCorrida.getImage();
		fundoStatus = status.getImage();
		
		player1 = new Player1();
		player1.load();
		
		addKeyListener(new TecladoAdapter());
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D)g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundoStatus, 0, 343, null);
		graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player1.update();
		repaint();
	}
	
	private class TecladoAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			player1.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player1.keyRelease(e);
		}
	}

}
