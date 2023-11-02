package jogo;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import Menu.MenuPrincipal;

public class Jogo {
	
	public static void main (String[] args) {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //definir padrão de fechamento da janela
		//JFrame.Exit_ON_CLOSE retorna um inteiro que atribuirá um X para a janela
		menuPrincipal.setSize(720, 500); //setar o tamanho da janela
		menuPrincipal.setLocationRelativeTo(null); //setar a janela no meio da tela
		menuPrincipal.setResizable(false);
		menuPrincipal.setVisible(true); //definir que a janela será visível
	}

}
