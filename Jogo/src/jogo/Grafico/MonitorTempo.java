package jogo.Grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MonitorTempo implements ActionListener{
		  private boolean contagemFinalizada = false;
		  private Timer timer;
		  private String timerStatus;
		  
		  public MonitorTempo(KeyHandler keyH, int delay) {
			  timer = new Timer(delay, this);
			  timerStatus = "OFF";
		  }
		  
		  public void startTimer() {
			  timer.start();
			  timerStatus = "ON";
		  }
		  
		public boolean isContagemFinalizada(){
		     return this.contagemFinalizada;
		  }
		  
		  public String getTimerStatus() {
			return timerStatus;
		}

		public void setContagemFinalizada(boolean contagemFinalizada) {
			 this.contagemFinalizada = contagemFinalizada;
		 
		  }
		  
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  
			  if(timerStatus == "ON") {
				  timerStatus = "OFF";
				  contagemFinalizada = true;
				  timer.stop();
			  }
			  
		}
		  
}

