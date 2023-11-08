
package jogo.Modelo;
import jogo.Modelo.Player1;
// jogo.Modelo.Fase.TecladoAdapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuPrincipal extends JFrame{
	
	private Image vencedor;
	private Image perdedor;
	private Image podioImagem;
	private Icon carro;
	private Image deus;
	private Timer timer;
	public MenuPrincipal () {
		super("Deuses da Corrida!");
		
		criarMenu();
		criarLayoutInicial();
	}
	
	private void criarLayoutInicial() {
		
		setLayout(new BorderLayout());
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.red);
		panelTitulo.setLayout(new FlowLayout());
		
		Icon tituloImagem = new ImageIcon("res\\tituloMenu.png");
		
		JLabel titulo = new JLabel(tituloImagem);
		panelTitulo.add(titulo);
		add(panelTitulo, BorderLayout.NORTH);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout());
		panelBotoes.setBackground(Color.red);
		
		JButton botaoIniciarJogo = new JButton("Iniciar Jogo");
		botaoIniciarJogo.setFont(new Font("Arial", Font.BOLD, 12));
		botaoIniciarJogo.setForeground(Color.red);
		botaoIniciarJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String iniciarJogo;
				iniciarJogo = menuIniciarJogo();
				if(iniciarJogo == "1-Player") {
					menuEscolherPersonagem("1-Player", "Zeus");
				} else if(iniciarJogo == "2-Player") {
					menuEscolherPersonagem("2-Player", "Zeus");
				} else {
					return;
				}
				//MenuEscolherPersonagem();
				
			}
			
			
		});
		JButton botaoComoJogar = new JButton("Como Jogar");
		botaoComoJogar.setFont(new Font("Arial", Font.BOLD, 12));
		botaoComoJogar.setForeground(Color.red);
		botaoComoJogar.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuComoJogar();
				
			}
			
		});
		JButton botaoSair = new JButton("Sair");
		botaoSair.setFont(new Font("Arial", Font.BOLD, 12));
		botaoSair.setForeground(Color.red);
		botaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				podio("Hades", "Zeus");
				
			}
		
		});
		
		
		panelBotoes.add(botaoIniciarJogo);
		panelBotoes.add(botaoComoJogar);
		panelBotoes.add(botaoSair);
		add(panelBotoes, BorderLayout.SOUTH);
	
		
	}
	
	public void menuComoJogar() {
		JFrame menuComoJogar = new JFrame("Como Jogar");
		menuComoJogar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		menuComoJogar.setLayout(new BorderLayout());
		menuComoJogar.setSize(700,400);
		menuComoJogar.setVisible(true);
		menuComoJogar.setLocationRelativeTo(null);
		menuComoJogar.toFront();
		
		JPanel comoJogar = new JPanel(new BorderLayout());
		comoJogar.setBackground(Color.white);
		menuComoJogar.add(comoJogar, BorderLayout.CENTER);
		JLabel comoJogarImagem = new JLabel (new ImageIcon ("res\\MenuComoJogar.png"));
		comoJogarImagem.setFocusable(true);
		comoJogarImagem.setDoubleBuffered(true);
		comoJogar.add(comoJogarImagem, BorderLayout.CENTER);
		
	}

	public void criarMenu() { //botões na parte de cima do menu (botar créditos)
		
		JMenu menuConheçaOsPersonagens = new JMenu ("Conheça os personagens!"); //instancia do objeto Menu
		
		
		
		JMenuItem menuZeus = new JMenuItem("Zeus");
		
		menuZeus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				menuZeus();
				
			}
		});
		JMenuItem menuHades = new JMenuItem ("Hades");
		menuHades.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuHades();
				JOptionPane.showMessageDialog(null, "Programa criado pelos alunos José Hilton e Yane dos Santos.", "Créditos", JOptionPane.PLAIN_MESSAGE);
				
			}
			
		});
		
		JMenuItem menuPoseidon = new JMenuItem ("Poseidon");
		menuPoseidon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuPoseidon();
				
			}
			
		});
			
		menuConheçaOsPersonagens.add(menuZeus);
		menuConheçaOsPersonagens.add(menuHades);
		menuConheçaOsPersonagens.add(menuPoseidon);
		
		JMenu menuCreditos = new JMenu ("Créditos");
		menuCreditos.setToolTipText("Colaboradores e inspirações para o jogo.");
		menuCreditos.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				JOptionPane.showMessageDialog(null, "Programa criado pelos alunos José Hilton e Yane dos Santos"
						+ " do segundo semestre de Engenharia de "
						+ "Computação no IFCE.", "Créditos", JOptionPane.PLAIN_MESSAGE);

				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		barraMenu.add(menuConheçaOsPersonagens);
		barraMenu.add(menuCreditos);
		
		
		
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
	
	
	public void menuPoseidon () {
		JFrame menuPoseidon = new JFrame("Poseidon");
		menuPoseidon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		menuPoseidon.setLayout(new BorderLayout());
		menuPoseidon.setSize(500,360);
		menuPoseidon.setVisible(true);
		menuPoseidon.setLocationRelativeTo(null);
		menuPoseidon.setResizable(false);
		menuPoseidon.toFront();
		
		JPanel poseidon = new JPanel(new BorderLayout());
		menuPoseidon.add(poseidon, BorderLayout.CENTER);
		JLabel poseidonImagem = new JLabel(animacaoDeuses("Poseidon", "feliz"));
		poseidon.add(poseidonImagem, BorderLayout.NORTH);
		poseidon.setBackground(Color.blue);
		poseidon.setFocusable(true);
		poseidon.setDoubleBuffered(true);
		JLabel poseidonInfo = new JLabel("<html>Poseidon era o deus grego dos mares e dos rios."
				+ "Os gregos consideravam que ele era responsável por catástrofes como enchentes e terremotos."
				+ "Era filho de Cronos e Reia e foi resgatado do ventre de seu pai pelo seu irmão Zeus."
				+ "Acredita-se que o culto a Poseidon seja advindo do período micênico.</html>");
		poseidonInfo.setFont(new Font("Arial", Font.BOLD, 14));
		poseidonInfo.setForeground(Color.green);
		poseidon.add(poseidonInfo, BorderLayout.CENTER);
		JLabel poseidonPoder = new JLabel(new ImageIcon ("res\\PoderPoseidon.png"));
		poseidon.add(poseidonPoder, BorderLayout.SOUTH);
	}
	
	public void menuHades () {
		JFrame menuHades = new JFrame("Hades");
		menuHades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		menuHades.setLayout(new BorderLayout());
		menuHades.setSize(500,360);
		menuHades.setVisible(true);
		menuHades.setLocationRelativeTo(null);
		menuHades.setResizable(false);
		menuHades.toFront();
		
		JPanel hades = new JPanel(new BorderLayout());
		menuHades.add(hades, BorderLayout.CENTER);
		JLabel hadesImagem = new JLabel(animacaoDeuses("Hades", "feliz"));
		hades.add(hadesImagem, BorderLayout.NORTH);
		hades.setBackground(Color.black);
		hades.setFocusable(true);
		hades.setDoubleBuffered(true);
		JLabel hadesInfo = new JLabel("<html>Hades foi um deus grego do submundo e o responsável "
				+ "por manter as almas dos mortos nesse local."
				+ " Era irmão de Zeus e casado com Perséfone."
				+ " Hades era o deus do submundo e contava com a ajuda de seu cão de três cabeças, Cérbero, "
				+ "para impedir que as almas fugissem de lá.</html>");
		hadesInfo.setFont(new Font("Arial", Font.BOLD, 14));
		hadesInfo.setForeground(Color.red);
		hades.add(hadesInfo, BorderLayout.CENTER);
		JLabel poderHades = new JLabel (new ImageIcon ("res\\PoderHades.png"));
		hades.add(poderHades, BorderLayout.SOUTH);
		
	}
	
	public void menuZeus() {
		
		JFrame menuZeus = new JFrame("Zeus");
		menuZeus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		menuZeus.setLayout(new BorderLayout());
		menuZeus.setSize(500,360);
		menuZeus.setVisible(true);
		menuZeus.setLocationRelativeTo(null);
		menuZeus.setResizable(false);		
		menuZeus.toFront();
		
		JPanel zeus = new JPanel(new BorderLayout());
		menuZeus.add(zeus, BorderLayout.CENTER);
		JLabel zeusImagem = new JLabel(animacaoDeuses("Zeus", "feliz"));
		zeus.add(zeusImagem, BorderLayout.NORTH);
		zeus.setFocusable(true);
		zeus.setDoubleBuffered(true);
		zeus.setBackground(Color.white);
		JLabel zeusInfo = new JLabel("<html>Zeus era a divindade suprema na religiosidade dos gregos antigos."
				+ "Era conhecido como deus dos céus, do raio e do trovão e era filho de Cronos e Reia."
				+ "Foi salvo pela mãe de ser devorado pelo próprio pai e,"
				+ "depois de adulto, resgatou seus irmãos e vingou-se de seu genitor.</html>");
		zeusInfo.setFont(new Font("Arial", Font.BOLD, 14));
		zeusInfo.setForeground(Color.blue);
		zeus.add(zeusInfo, BorderLayout.CENTER);
		JLabel zeusPoder = new JLabel (new ImageIcon("res\\PoderZeus.png"));
		zeus.add(zeusPoder, BorderLayout.SOUTH);
				
				
		/*menuZeus.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent event) {
				
			}
			
			
		});*/// tentativa de animação do personagem, por enquanto falho
		

    };
    
    public void menuEscolherPersonagem(String modoDeJogo, String deusMenu) {
    	
    	boolean escolheuPersonagem = false;
    	
    	Icon carroZeus = new ImageIcon("res\\CarroZeus.png");
    	Icon carroPoseidon = new ImageIcon("res\\CarroPoseidon");
    	Icon carroHades = new ImageIcon("res\\CarroHades");
    	
    	this.carro =  carroZeus;
	    this.deus = animacaoDeuses("Zeus", "feliz").getImage();
    	
    	/*if(deusMenu == "Zeus") {
		    this.carro = new ImageIcon("res\\CarroZeus.png").getImage();
		    this.deus = animacaoDeuses("Zeus", "feliz").getImage();
    	} else if (deusMenu == "Hades") {
    		this.carro = new ImageIcon("res\\CarroHades.png").getImage();
    		this.deus = animacaoDeuses("Hades", "feliz").getImage();
    	} else if (deusMenu == "Poseidon"){
    		this.carro = new ImageIcon("res\\CarroPoseidon.png").getImage();
    		this.deus = animacaoDeuses("Poseidon", "feliz").getImage();
    	}*/
    	
    	JFrame menuEscolherPersonagem = new JFrame("Selecione o personagem para o Player 1!");
    	menuEscolherPersonagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
    	menuEscolherPersonagem.setLayout(new BorderLayout());
    	menuEscolherPersonagem.setSize(800,400);
    	menuEscolherPersonagem.setVisible(true);
    	menuEscolherPersonagem.setLocationRelativeTo(null);
    	menuEscolherPersonagem.setResizable(true);		
    	menuEscolherPersonagem.toFront();
    	
    	JPanel deusPainel = new JPanel(new BorderLayout(50, 0));
    		JLabel info = new JLabel("Player 1! Selecione seu carro:");
    		info.setFont(new Font("Arial", Font.BOLD, 25));
    		deusPainel.add(info, BorderLayout.NORTH);
    		menuEscolherPersonagem.add(deusPainel, BorderLayout.CENTER);
    		menuEscolherPersonagem.setBackground(Color.white);
    		JLabel imagemCarro = new JLabel();
    		deusPainel.setBorder(new EmptyBorder(0, 200, 150, 0));
    		deusPainel.add(imagemCarro, BorderLayout.LINE_START);
    		JLabel imagemPersonagem = new JLabel();
    		deusPainel.add(imagemPersonagem, BorderLayout.CENTER);
    		imagemPersonagem.setIcon(animacaoDeuses("Zeus", "feliz"));
            imagemCarro.setIcon((Icon) this.carro);
    		
    		menuEscolherPersonagem.addKeyListener(new TecladoAdapter(modoDeJogo, deusMenu));
    		do {
    			menuEscolherPersonagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
    		}while(escolheuPersonagem == true);
    		
    }
    
    private class TecladoAdapter extends KeyAdapter {
		
    	private String modoDeJogo;
    	private String deusAtual;
    	public TecladoAdapter(String modoDeJogo, String deusAtual) {
    		
    	}
    	
		@Override
		public void keyPressed(KeyEvent e) {
			MenuPrincipal.this.keyPressed(e, modoDeJogo, deusAtual);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			MenuPrincipal.this.keyReleased(e, modoDeJogo, deusAtual);
		}
	}
    
    public void keyPressed (KeyEvent tecla, String modoDeJogo, String deusAtual) {
		int codigo = tecla.getKeyCode();
		if(codigo == KeyEvent.VK_LEFT) {
			if(deusAtual == "Zeus") {
				this.carro = new ImageIcon("res\\CarroPoseidon.png").getImage();
	    		this.deus = animacaoDeuses("Poseidon", "feliz").getImage();
			} else if (deusAtual == "Hades") {
				this.carro = new ImageIcon("res\\CarroZeus.png").getImage();
			    this.deus = animacaoDeuses("Zeus", "feliz").getImage();
			} else {
				this.carro = new ImageIcon("res\\CarroHades.png").getImage();
	    		this.deus = animacaoDeuses("Hades", "feliz").getImage();
			}
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			if(deusAtual == "Zeus") {
				this.carro = new ImageIcon("res\\CarroHades.png").getImage();
	    		this.deus = animacaoDeuses("Hades", "feliz").getImage();
			} else if (deusAtual == "Hades") {
				this.carro = new ImageIcon("res\\CarroPoseidon.png").getImage();
	    		this.deus = animacaoDeuses("Poseidon", "feliz").getImage();
			} else {
				this.carro = new ImageIcon("res\\CarroZeus.png").getImage();
			    this.deus = animacaoDeuses("Zeus", "feliz").getImage();
			}
		}
	}
	
	public boolean keyReleased (KeyEvent tecla, String modoDeJogo, String deusAtual) {
		int codigo = tecla.getKeyCode();
		if(codigo == KeyEvent.VK_LEFT) {
			if(deusAtual == "Zeus") {
				//menuEscolherPersonagem(modoDeJogo, "Zeus");
				return false;
			} else if (deusAtual == "Hades") {
				//menuEscolherPersonagem(modoDeJogo, "Hades");
				return true;
			} else {
				//menuEscolherPersonagem(modoDeJogo, "Poseidon");
				return false;
			}
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			if(deusAtual == "Zeus") {
				//menuEscolherPersonagem(modoDeJogo, "Zeus");
				return false;
			} else if (deusAtual == "Hades") {
				//menuEscolherPersonagem(modoDeJogo, "Hades");
				return false;
			} else {
				//menuEscolherPersonagem(modoDeJogo, "Poseidon");
				return false;
			}
		}
		
		return true;
	}
	
	public class paint extends JComponent {
	    public void paintComponent(Graphics g){
	    super.paintComponents(g);
		//Graphics2D graficos = (Graphics2D)g;
		g.drawImage(carro, 300, 100, this);
		g.drawImage(deus, 500 , 100, this); 
		g.dispose();
	    }
    }
	
	public ImageIcon animacaoDeuses (String deus, String mood) {
		
		ImageIcon zeusParado = new ImageIcon ("res\\Zeus1.png");
		ImageIcon zeusFeliz = new ImageIcon ("res\\Zeus2.png");
		ImageIcon zeusTriste = new ImageIcon ("res\\Zeus3.png");
		ImageIcon hadesParado = new ImageIcon ("res\\Hades1.png");
		ImageIcon hadesFeliz = new ImageIcon ("res\\Hades2.png");
		ImageIcon hadesTriste = new ImageIcon ("res\\Hades3.png");
		ImageIcon poseidonParado = new ImageIcon("res\\Poseidon1.png");
		ImageIcon poseidonFeliz = new ImageIcon ("res\\Poseidon2.png");
		ImageIcon poseidonTriste = new ImageIcon ("res\\Poseidon3.png");
		
		
		if(mood == "feliz" && deus == "Zeus") {
			return zeusFeliz;
		} else if (mood == "triste" && deus == "Zeus") {
			return zeusTriste;
		} else if(mood == "feliz" && deus == "Hades") {
			return hadesFeliz;
		} else if (mood == "triste" && deus == "Hades") {
			return hadesTriste;
		} else if (mood == "feliz" && deus == "Poseidon"){
			return poseidonFeliz;
		} else if (mood == "triste" && deus == "Poseidon") {
			return poseidonTriste;
		} else {
			return zeusParado;
		}
		
		
		
	}
	
	public void podio (String primeiroLugar, String segundoLugar) {
		
		JFrame podio = new JFrame ("Pódio!");
		podio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		podio.setLayout(new BorderLayout());
		podio.setSize(500,500);
		podio.setVisible(true);
		podio.setLocationRelativeTo(null);
		podio.toFront();
		
		this.vencedor = animacaoDeuses(primeiroLugar, "feliz").getImage();
		this.perdedor = animacaoDeuses(segundoLugar, "triste").getImage();
		this.podioImagem = new ImageIcon("res\\Podio.png").getImage();
		
		JPanel podioCorrida = new JPanel() {
			
			@Override 
			public void paint(Graphics g){
				super.paint(g);
				Graphics2D graficos = (Graphics2D)g;
				graficos.drawImage(podioImagem, 100, 250, this);
				graficos.drawImage(vencedor, 281 , 184, this);
				graficos.drawImage(perdedor, 139, 232, null);   
			}
		};
		podio.add(podioCorrida);
		podioCorrida.setBackground(Color.white);
	}
}		


/*public MenuPrincipal () {
		add(new Fase());
		setTitle("Meu jogo!");
		setSize(1440, 1200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(true);
		setVisible(true);
	}*/
	
	/*public static void main (String[] args) {
		new MenuPrincipal();
	}*/


