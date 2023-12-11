package jogo.Grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class MonitorTempo implements ActionListener{
		  private boolean contagemFinalizada = false;
		  private Timer timer;
		  private String timerStatus;
		  //private Timer timerNitro;
		  //private Timer timerPoder;
		  //private Timer timerInvencibilidade;
		 // private String timerNitroStatus = "OFF";
		  //private String timerPoderStatus = "OFF";
		  //private String timerInvencibilidadeStatus = "OFF";
		  
		  public MonitorTempo(KeyHandler keyH, int delay) {
			  
			  timer = new Timer(delay, this);
			  timerStatus = "OFF";
			  
//			  timerNitro = new Timer(2000, this);
//			  timerPoder = new Timer(500, this);
//			  timerInvencibilidade = new Timer(1500, this);
			  

		  }
		  
		  public void startTimer() {
			  timer.start();
			  timerStatus = "ON";
		  }
		  
		  /*public void startTimerNitro() {
			  timerNitro.start();

		  }
		  
		  public void startTimerPoder() {
			  timerPoder.start();

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
		  }*/
		 
		

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
			  /*if(timerNitro.isRunning() == true) {
				  timerNitro.setRepeats(false);
				  timerNitro.stop();
				  timerNitroStatus = "ON";
			  }
			  
			  if(timerNitro.isRunning() == false && timerNitroStatus == "ON") {
				  System.out.println("aaaaanitro");
				   timerNitroStatus = "OFF";
				   contagemFinalizada = true;
			  }
			  else if(timerInvencibilidadeStatus == "ON" && timerInvencibilidade.isRunning() == true) {
				System.out.println("narcissistic");
				timerInvencibilidadeStatus = "OFF";
				timerInvencibilidade.stop();
			  } else if(timerPoderStatus == "ON" && timerPoder.isRunning() == true) {
				timerPoderStatus = "OFF";
				contagemFinalizada = true;
				timerPoder.stop();
			  }*/
			
		}
		  
}

