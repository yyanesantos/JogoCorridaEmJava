package jogo.Grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class MonitorTempo implements ActionListener{
		  private boolean contagemFinalizada = false;
		  private Timer timerNitro;
		  private Timer timerPoder;
		  private Timer timerInvencibilidade;
		  private String timerNitroStatus = "OFF";
		  private String timerPoderStatus = "OFF";
		  private String timerInvencibilidadeStatus = "OFF";
		  
		  
		  private KeyHandler keyH;
		  private String modo;
		  
		  public MonitorTempo(KeyHandler keyH) {
			  timerNitro = new Timer(2000, this);
			  timerPoder = new Timer(500, this);
			  timerInvencibilidade = new Timer(1500, this);
			  
			  
			  this.keyH = keyH;
		  }
		  
		  public void startTimerNitro() {
			  timerNitro.start();
			  timerNitroStatus = "ON";
		  }
		  
		  public void startTimerPoder() {
			  timerPoder.start();
			  timerPoderStatus = "ON";
		  }
		 
		  public void startTimerInvencibilidade() {
			  timerInvencibilidade.start();
			  timerInvencibilidadeStatus = "ON";
		  }

		  public String getTimerNitroStatus() {
			  return timerNitroStatus;
		  }
		 
		  public String getTimerPoderStatus() {
			 return timerPoderStatus;
		  }
		 
		  public String getTimerInvencibilidadeStatus() {
			 return timerInvencibilidadeStatus;
		  }
		 
		  public boolean isContagemFinalizada(){
		     return this.contagemFinalizada;
		  }
		  
		  public void setContagemFinalizada(boolean contagemFinalizada) {
			 this.contagemFinalizada = contagemFinalizada;
		 
		  }
		  
		  @Override
		  public void actionPerformed(ActionEvent e) {
			if(timerNitroStatus == "ON") {
			System.out.println("aeee");
			timerNitroStatus = "OFF";
			contagemFinalizada = true;
			timerNitro.stop();
			} else if(timerInvencibilidadeStatus == "ON") {
				timerInvencibilidadeStatus = "OFF";
				timerInvencibilidade.stop();
			} else if(timerPoderStatus == "ON") {
				timerPoderStatus = "OFF";
				keyH.space1Pressed = false;
				contagemFinalizada = true;
				timerPoder.stop();
			}
			
		}
		  
}

