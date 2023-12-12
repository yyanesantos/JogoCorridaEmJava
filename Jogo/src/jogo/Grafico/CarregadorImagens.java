package jogo.Grafico;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregadorImagens {
	
	private BufferedImage imagem;

	public BufferedImage getImagem(String qualImagem) {
		    try {
		    	imagem = ImageIO.read(new File("res/" + qualImagem + ".png"));
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		
		return imagem;
	}
	
	

}
