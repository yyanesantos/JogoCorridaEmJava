package jogo.Gráfico;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	private Image fundoCorrida;
	private Player1 player1;
	private Timer timer;
	//private KeyHandler keyH;
	
	public Fase (KeyHandler keyH) {
		setFocusable(true);
		setDoubleBuffered(true);
		
		
		ImageIcon pistaDeCorrida = new ImageIcon("res\\FundoCorrida.png");
		fundoCorrida = pistaDeCorrida.getImage();
		keyH = new KeyHandler();
		addKeyListener(keyH);
		
		player1 = new Player1(keyH);
		//player1.load();
		
		
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D)g;
		graficos.drawImage(fundoCorrida, 0, 0, null);
		graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
		List<Poder> poderesFrente = player1.getPoderesPraFrente();
		List<Poder> poderesTras = player1.getPoderesPraTras();
		for(int i = 0; i < poderesFrente.size(); i++) {
		    Poder p = poderesFrente.get(i);
		    p.load();
		    graficos.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this);
		}
		for(int i = 0; i < poderesTras.size(); i++) {
		    Poder p = poderesTras.get(i);
		    p.load();
		    graficos.drawImage(p.getImagemPoder(), p.getX(), p.getY(), this);
		}
		
		/*List<Nitro> nitros = player1.getNitros();
		for(int i = 0; i < nitros.size(); i++) {
			Nitro n = nitros.get(i);
		    n.load();
		    graficos.drawImage(n.getImagemNitro(), n.getX(), n.getY(), this);
		}*/
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player1.update();
		List<Poder> poderesFrente = player1.getPoderesPraFrente();
		for(int i = 0; i < poderesFrente.size(); i++) {
		    Poder p = poderesFrente.get(i);
		        if(p.isVisivel()) {
		            p.update("Pra frente");
		        }else{
		            poderesFrente.remove(i);  
		        }
		}
		List<Poder> poderesTras = player1.getPoderesPraTras();
		for(int i = 0; i < poderesTras.size(); i++) {
		    Poder p = poderesTras.get(i);
		        if(p.isVisivel()) {
		            p.update("Pra tras");
		        }else{
		            poderesTras.remove(i);  
		        }
		}
		
		/*List<Nitro> nitros = player1.getNitros();
		for(int i = 0; i < nitros.size(); i++) {
		    Nitro n = nitros.get(i);
		        if(n.isVisivel()) {
		            n.update();
		        }else{
		            nitros.remove(i);  
		        }
		}*/
		
		repaint();
	}
	
	/*private class TecladoAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			player1.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player1.keyRelease(e);
		}
	}*/

}
