
package jogo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	
	//private Image vencedor;
	//private Image perdedor;
	//private Image podioImagem;
	private String personagem1Player;
	private String personagem2Player;
	public boolean iniciouJogo;
	
	public String getPersonagem1Player() {
		return personagem1Player;
	}
	
	public MenuPrincipal () {
		super("Deuses da Corrida!");
		iniciouJogo = false;
		criarMenu();
		criarLayoutInicial();
	}
	
	
	
	public boolean isIniciouJogo() {
		return iniciouJogo;
	}
	
	public boolean testeSeOJogoFoiIniciado() {
		if(iniciouJogo == true) {
			return true;
		} else {
			return false;
		}
	}



	public void criarLayoutInicial() {
		
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
					menuEscolherPersonagem("1-Player");
				} else if(iniciarJogo == "2-Player") {
					menuEscolherPersonagem("2-Player");
				} else {
					iniciouJogo = false;
					return;
				}
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
				System.exit(0);
				//podio("Hades", "Zeus");
			}
		
		});
		
		panelBotoes.add(botaoIniciarJogo);
		panelBotoes.add(botaoComoJogar);
		panelBotoes.add(botaoSair);
		add(panelBotoes, BorderLayout.SOUTH);
		System.out.println("a");
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

	public void criarMenu() {
		
		JMenu menuConheçaOsPersonagens = new JMenu ("Conheça os personagens!");
		
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

			}

			@Override
			public void menuCanceled(MenuEvent e) {

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
		poseidonImagem.setBorder(new EmptyBorder(5, 0, 0, 0));		
		poseidon.add(poseidonImagem, BorderLayout.NORTH);
		poseidon.setBackground(Color.blue);
		poseidon.setFocusable(true);
		poseidon.setDoubleBuffered(true);
		JLabel poseidonInfo = new JLabel("<html>Poseidon era o deus grego dos mares e dos rios."
				+ "Os gregos consideravam que ele era responsável por catástrofes como enchentes e terremotos."
				+ "Era filho de Cronos e Reia e foi resgatado do ventre de seu pai pelo seu irmão Zeus."
				+ "Acredita-se que o culto a Poseidon seja advindo do período micênico.</html>");
		poseidonInfo.setFont(new Font("Arial", Font.BOLD, 14));
		poseidonInfo.setBorder(new EmptyBorder(0,5,0,0));
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
		hadesImagem.setBorder(new EmptyBorder(5,0,0,0));
		hades.add(hadesImagem, BorderLayout.NORTH);
		hades.setBackground(Color.black);
		hades.setFocusable(true);
		hades.setDoubleBuffered(true);
		JLabel hadesInfo = new JLabel("<html>Hades foi um deus grego do submundo e o responsável "
				+ "por manter as almas dos mortos nesse local."
				+ " Era irmão de Zeus e casado com Perséfone."
				+ " Hades era o deus do submundo e contava com a ajuda de seu cão de três cabeças, Cérbero, "
				+ "para impedir que as almas fugissem de lá.</html>");
		hadesInfo.setBorder(new EmptyBorder(0,5,0,0));
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
		zeusImagem.setBorder(new EmptyBorder(5,0,0,0));
		zeus.add(zeusImagem, BorderLayout.NORTH);
		zeus.setFocusable(true);
		zeus.setDoubleBuffered(true);
		zeus.setBackground(Color.white);
		JLabel zeusInfo = new JLabel("<html>Zeus era a divindade suprema na religiosidade dos gregos antigos."
				+ "Era conhecido como deus dos céus, do raio e do trovão e era filho de Cronos e Reia."
				+ "Foi salvo pela mãe de ser devorado pelo próprio pai e,"
				+ "depois de adulto, resgatou seus irmãos e vingou-se de seu genitor.</html>");
		zeusInfo.setBorder(new EmptyBorder(0,5,0,0));
		zeusInfo.setFont(new Font("Arial", Font.BOLD, 14));
		zeusInfo.setForeground(Color.blue);
		zeus.add(zeusInfo, BorderLayout.CENTER);
		JLabel zeusPoder = new JLabel (new ImageIcon("res\\PoderZeus.png"));
		zeus.add(zeusPoder, BorderLayout.SOUTH);
    };
    
    public void menuEscolherPersonagem(String modoDeJogo) {
    	
    	Icon carroZeus = new ImageIcon("res\\CarroZeus.png");
    	Icon carroPoseidon = new ImageIcon("res\\CarroPoseidon.png");
    	Icon carroHades = new ImageIcon("res\\CarroHades.png");
    	
    	
    	JFrame menuEscolherPersonagem = new JFrame("Selecione o personagem para o Player 1!");
    	menuEscolherPersonagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
    	menuEscolherPersonagem.setLayout(new BorderLayout());
    	menuEscolherPersonagem.setSize(1000,400);
    	menuEscolherPersonagem.setVisible(true);
    	menuEscolherPersonagem.setLocationRelativeTo(null);
    	menuEscolherPersonagem.setResizable(true);		
    	menuEscolherPersonagem.toFront();
    	
    	
    	
    	JPanel titulo = new JPanel();
    		JLabel info = new JLabel("<html>Player 1! <br>"
    				+ "Selecione seu carro: <br>"
    				+ "(Zeus, Poseidon, Hades, em ordem)</html>");
    		info.setBorder(new EmptyBorder(25, 0, 0, 0));
    		info.setFont(new Font("Arial", Font.BOLD, 25));
    		titulo.setBackground(Color.WHITE);
    		titulo.add(info, BorderLayout.NORTH);
    		menuEscolherPersonagem.add(titulo, BorderLayout.NORTH);
    		menuEscolherPersonagem.setBackground(Color.white);
    	JPanel zeusPainel = new JPanel(new FlowLayout());
    		JLabel imagemCarroZeus = new JLabel();
    		zeusPainel.setBorder(new EmptyBorder(25, 0, 0, 0));
    		zeusPainel.add(imagemCarroZeus, FlowLayout.LEFT);
    		zeusPainel.setBackground(Color.WHITE);
    		JLabel imagemPersonagemZeus = new JLabel();
    		zeusPainel.add(imagemPersonagemZeus, BorderLayout.CENTER);
    		imagemPersonagemZeus.setIcon(animacaoDeuses("Zeus", "feliz"));
            imagemCarroZeus.setIcon((Icon) carroZeus);
            menuEscolherPersonagem.add(zeusPainel, BorderLayout.LINE_START);
        JPanel poseidonPainel = new JPanel(new FlowLayout());
    		JLabel imagemCarroPoseidon = new JLabel();
    		poseidonPainel.setBorder(new EmptyBorder(25, 0, 0, 0));
    		poseidonPainel.add(imagemCarroPoseidon, FlowLayout.LEFT);
    		poseidonPainel.setBackground(Color.WHITE);
    		JLabel imagemPersonagemPoseidon = new JLabel();
    		poseidonPainel.add(imagemPersonagemPoseidon, BorderLayout.CENTER);
    		imagemPersonagemPoseidon.setIcon(animacaoDeuses("Poseidon", "feliz"));
    		imagemCarroPoseidon.setIcon((Icon)carroPoseidon);
            menuEscolherPersonagem.add(poseidonPainel, BorderLayout.CENTER);
        JPanel hadesPainel = new JPanel(new FlowLayout());
    		JLabel imagemCarroHades = new JLabel();
    		hadesPainel.setBorder(new EmptyBorder(25, 0, 0, 0));
    		hadesPainel.add(imagemCarroHades, FlowLayout.LEFT);
    		hadesPainel.setBackground(Color.WHITE);
    		JLabel imagemPersonagemHades = new JLabel();
    		hadesPainel.add(imagemPersonagemHades, BorderLayout.CENTER);
    		imagemPersonagemHades.setIcon(animacaoDeuses("Hades", "feliz"));
    		imagemCarroHades.setIcon((Icon) carroHades);
            menuEscolherPersonagem.add(hadesPainel, BorderLayout.LINE_END);

        JPanel botaoEscolha = new JPanel(new FlowLayout());
            JButton escolhaZeus = new JButton("Zeus");
            escolhaZeus.setFont(new Font("Arial", Font.BOLD, 12));
            escolhaZeus.setForeground(Color.DARK_GRAY);
            escolhaZeus.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = { "Confirmar", "Cancelar" };
					if(personagem1Player == null && personagem2Player == null) {
					    int opcaoSelecionada = JOptionPane.showOptionDialog(null, "<html>Zeus foi selecionado, Player 1!<br>Confirmar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					    if(opcaoSelecionada == 0 && modoDeJogo == "1-Player" && personagem1Player == null) {
						    personagem1Player = "Zeus";
						    iniciouJogo = true;
						    menuEscolherPersonagem.dispose();
					    } else if (opcaoSelecionada == 0 && modoDeJogo == "2-Player") {
						    personagem1Player = "Zeus";
						    personagem2Player = null;
						    menuEscolherPersonagem("1-Player");
						    menuEscolherPersonagem.dispose();
					    }
					} else {
						int opcaoSelecionada = JOptionPane.showOptionDialog(null, "<html>Zeus foi selecionado, Player 2!<br>Confirmar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					    if(opcaoSelecionada == 0 && modoDeJogo == "1-Player" && personagem1Player != null) {
						    personagem2Player = "Zeus";
						    iniciouJogo = true;
						    menuEscolherPersonagem.dispose();
					    }
					}
				}});
            botaoEscolha.add(escolhaZeus);
            JButton escolhaPoseidon = new JButton("Poseidon");
            escolhaPoseidon.setFont(new Font("Arial", Font.BOLD, 12));
            escolhaPoseidon.setForeground(Color.BLUE);
            escolhaPoseidon.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = { "Confirmar", "Cancelar" };
					if(personagem1Player == null && personagem2Player == null) {
					    int opcaoSelecionada = JOptionPane.showOptionDialog(null, "<html>Poseidon foi selecionado, Player 1!<br>Confirmar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					    if(opcaoSelecionada == 0 && modoDeJogo == "1-Player" && personagem1Player == null) {
						    personagem1Player = "Poseidon";
						    iniciouJogo = true;
						    menuEscolherPersonagem.dispose();
					    } else if (opcaoSelecionada == 0 && modoDeJogo == "2-Player") {
						    personagem1Player = "Poseidon";
						    personagem2Player = null;
						    menuEscolherPersonagem("1-Player");
						    menuEscolherPersonagem.dispose();
					    }
					} else {
						int opcaoSelecionada = JOptionPane.showOptionDialog(null, "<html>Poseidon foi selecionado, Player 2!<br>Confirmar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					    if(opcaoSelecionada == 0 && modoDeJogo == "1-Player" && personagem1Player != null) {
						    personagem2Player = "Poseidon";
						    iniciouJogo = true;
						    menuEscolherPersonagem.dispose();
					    }
					}
				}
            });
            botaoEscolha.add(escolhaPoseidon);
            JButton escolhaHades = new JButton("Hades");
            escolhaHades.setFont(new Font("Arial", Font.BOLD, 12));
            escolhaHades.setForeground(Color.RED);
            escolhaHades.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = { "Confirmar", "Cancelar" };
					if(personagem1Player == null && personagem2Player == null) {
					    int opcaoSelecionada = JOptionPane.showOptionDialog(null, "<html>Hades foi selecionado, Player 1!<br>Confirmar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					    if(opcaoSelecionada == 0 && modoDeJogo == "1-Player" && personagem1Player == null) {
						    personagem1Player = "Hades";
						    iniciouJogo = true;
						    menuEscolherPersonagem.dispose();
					    } else if (opcaoSelecionada == 0 && modoDeJogo == "2-Player") {
						    personagem1Player = "Hades";
						    personagem2Player = null;
						    menuEscolherPersonagem("1-Player");
						    menuEscolherPersonagem.dispose();
					    }
					} else {
						int opcaoSelecionada = JOptionPane.showOptionDialog(null, "<html>Hades foi selecionado, Player 2!<br>Confirmar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					    if(opcaoSelecionada == 0 && modoDeJogo == "1-Player" && personagem1Player != null) {
						    personagem2Player = "Hades";
						    iniciouJogo = true;
						    menuEscolherPersonagem.dispose();
					    }
					}
				}});
            botaoEscolha.add(escolhaHades);
            botaoEscolha.setBackground(Color.WHITE);
            menuEscolherPersonagem.add(botaoEscolha, BorderLayout.SOUTH);
            
            if(personagem1Player == "Zeus" && modoDeJogo == "1-Player" && personagem2Player == null) {
            	info.setText("<html>Player 2! <br>"
    				+ "Selecione seu carro: <br>"
    				+ "(Poseidon, Hades, em ordem)</html>");
        		imagemCarroZeus.setVisible(false);
        		imagemPersonagemZeus.setVisible(false);
        		escolhaZeus.setVisible(false);
        	} else if (personagem1Player == "Poseidon" && modoDeJogo == "1-Player" && personagem2Player == null) {
        		info.setText("<html>Player 2! <br>"
        				+ "Selecione seu carro: <br>"
        				+ "(Zeus, Hades, em ordem)</html>");
        		imagemCarroPoseidon.setVisible(false);
        		imagemPersonagemPoseidon.setVisible(false);
        		escolhaPoseidon.setVisible(false);
        	} else if(personagem1Player == "Hades" && modoDeJogo == "1-Player" && personagem2Player == null) {
        		info.setText("<html>Player 2! <br>"
        				+ "Selecione seu carro: <br>"
        				+ "(Zeus, Poseidon, em ordem)</html>");
        		imagemCarroHades.setVisible(false);
        		imagemPersonagemHades.setVisible(false);
        		escolhaHades.setVisible(false);
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
	
	/*public void podio (String primeiroLugar, String segundoLugar) {
		
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
	}*/
}		
