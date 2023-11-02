package Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuPrincipal extends JFrame{
	
	private Image vencedor;
	private Image perdedor;
	private Image podioImagem;
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
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		barraMenu.add(menuConheçaOsPersonagens);
		barraMenu.add(menuCreditos);
		
		
		
	}
	
	public void menuPoseidon () {
		JFrame menuPoseidon = new JFrame("Poseidon");
		menuPoseidon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		menuPoseidon.setLayout(new BorderLayout());
		menuPoseidon.setSize(500,360);
		menuPoseidon.setVisible(true);
		menuPoseidon.setLocationRelativeTo(null);
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
		menuZeus.toFront();
		
		JPanel zeus = new JPanel(new BorderLayout());
		menuZeus.add(zeus, BorderLayout.CENTER);
		JLabel zeusImagem = new JLabel(animacaoDeuses("Zeus", "feliz"));
		zeus.add(zeusImagem, BorderLayout.NORTH);
		zeus.setFocusable(true);
		zeus.setDoubleBuffered(true);
		zeus.setBackground(Color.white);
		JLabel zeusInfo = new JLabel("<html>Zeus era a divindade suprema na religiosidade dos gregos antigos.<br>"
				+ "Era conhecido como deus dos céus, do raio e do trovão e era filho de Cronos e Reia.<br>"
				+ "Foi salvo pela mãe de ser devorado pelo próprio pai e,<br>"
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
	
	public ImageIcon animacaoDeuses (String deus, String mood) {
		
		ImageIcon zeusParado = new ImageIcon ("res\\Zeus1(1).png");
		ImageIcon zeusFeliz = new ImageIcon ("res\\Zeus2.png");
		ImageIcon zeusTriste = new ImageIcon ("res\\Zeus3.png");
		ImageIcon hadesParado = new ImageIcon ("res\\Hades1(1).png");
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
		podio.setSize(800,400);
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
				graficos.drawImage(podioImagem, 150, 50, this);
				graficos.drawImage(vencedor, 360, 49, this);
				graficos.drawImage(perdedor, 135, 154, null);   
			}
		};
		podio.add(podioCorrida);
		podioCorrida.setSize(800,400);
	}
}
	
	/*public class podioFinal extends JPanel {
		
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			g.drawImage(podioImagem, 150, 50, this);
			g.drawImage(vencedor, 510, 89, this);
			g.drawImage(perdedor, 285, 204, this);
			
		}
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


