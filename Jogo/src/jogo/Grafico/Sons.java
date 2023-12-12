package jogo.Grafico;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sons {
	Clip clip;
	URL somURL[] = new URL[30];
	public Sons() {
		somURL[0] = getClass().getResource("/Audio/barulhoAcelerar1.wav");
		somURL[1] = getClass().getResource("/Audio/barulhoAcelerar2.wav");
		somURL[4] = getClass().getResource("/Audio/barulhoFreador.wav");
		somURL[6] = getClass().getResource("/Audio/barulhoDesacelerar.wav");
	}
	public void setFile(int i) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(somURL[i]);
				clip = AudioSystem.getClip();
				clip.open(ais);
			}catch(Exception e) {
			}
	}
	public void tocar() {
		clip.start();
	}
	public void loop() {
		//clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void parar() {
		clip.stop();
	}

}
