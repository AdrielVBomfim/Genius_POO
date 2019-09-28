/**@author Adriel Venâncio Bonfim - 201500030838
 *@author Igor de Figueiredo Rodrigues - 201500018083
 *@since  20/04/2015
 *@version v1.0.3
 *
 *Classe Interface: Classe principal. Responsável por iniciar e passar as definições básica do jogo.
 */
package programa;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import java.awt.Color;

import javax.swing.JToggleButton;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class Interface extends JFrame implements fazParte{

	private JPanel contentPane;
	private JButton botaoVermelho;
	private JButton botaoVerde;
	private JButton botaoAmarelo;
	private JButton botaoAzul;
	private Jogo jogo;
	private Object[] niveis = {"Nivel 1", "Nivel 2", "Nivel 3", "Nivel 4"};
	private int gamemode;
	private Timer timer;
	
	
	public static void main(String[] args) {
		/**
		 * Inicia a aplicação.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Interface() {
		/**
		 * Cria o frame o qual se desenvolve a aplicação.
		 */
		setTitle("Genius");
		setIconImage(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Background.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		Interface UI = this;
		/**
		 * JComboBox 
		 */
		JComboBox seletorNiveis = new JComboBox(niveis);
		seletorNiveis.setBounds(155, 248, 75, 25);
		contentPane.add(seletorNiveis);
		/**
		 * Código dos botões.
		 * Botão de ligar:
		 */
		JToggleButton Partida = new JToggleButton("");
		Partida.addMouseListener(new MouseAdapter() {
			/**
			 * Parte responsável pelo inicio das partidas.
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				/**
				 * Parte responsável pela emissão do som ao pressionar
				 */
				Partida.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Ligar(apertado).png"));
				Sons.Reproduzir("Pressionar", false);
				
				if(timer != null)
					timer.stop();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				/**
				 * Parte responsável em iniciar o jogo. O método "SequenciaMaquina()" cria um array de Strings com as cores da sequencia do jogo.
				 * Em seguida invoca o método "JogoPrincipal()", que exibe a primeira cor e aguarda o jogador agir.
				 */
   				Partida.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Ligar.png"));
				
				jogo = new Jogo(NumSequencias(seletorNiveis.getSelectedItem().toString()), 1, UI);
				jogo.SequenciaMaquina();
				jogo.JogoPrincipal();
			}
		});
		
		
		/**
		 * Botão vermelho:
		 */
		botaoVermelho = new JButton("");
		ActionListener piscarVermelho = new ActionListener(){
			/**
			 *Parte responsável na animação de piscar o botão. 
			 */
			@Override
            public void actionPerformed(ActionEvent event){
				pressRed();
			}
		};
		botaoVermelho.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				/**
				 *Parte responsável no efeito sonoro do botão. 
				 */
				try{
					botaoVermelho.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Red_Button(apertado).png"));
				}
				catch(Exception e){
					e.printStackTrace();
				}
			
				Sons.Reproduzir("Pressionar", false);
				
				if(timer != null)
					timer.stop();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				/**
				 * Parte responsável em adicionar a cor à sequencia ao soltar o botão.
				 */
				botaoVermelho.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Red_Button.png"));
				actRed();
				
				if(jogo != null){
					jogo.Player.add("red");
					jogo.SequenciaJogador();
					if(jogo != null)
						jogo.JogoPrincipal();
				}
			}
		});
		botaoVermelho.setForeground(Color.GREEN);
		botaoVermelho.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Red_Button.png"));
		botaoVermelho.setBounds(229, 12, 211, 208);
		
		Partida.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Ligar.png"));
		Partida.setBounds(240, 248, 24, 25);
		Partida.setBorder(null);
		Partida.setContentAreaFilled(false);
		Partida.setFocusPainted(false);
		contentPane.add(Partida);
		botaoVermelho.setBorder(null);
		botaoVermelho.setContentAreaFilled(false);
		botaoVermelho.setFocusPainted(false);
		contentPane.add(botaoVermelho);
		
		/**
		 * Botão amarelo:
		 */
		botaoAmarelo = new JButton("");
		ActionListener piscarAmarelo = new ActionListener(){
			/**
			 *Parte responsável na animação de piscar o botão. 
			 */
			@Override
            public void actionPerformed(ActionEvent event){
				pressYellow();
			}
		};
		botaoAmarelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				/**
				 *Parte responsável no efeito sonoro do botão. 
				 */
				botaoAmarelo.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Yellow_Button(apertado).png"));
				Sons.Reproduzir("Pressionar", false);
				
				if(timer != null)
					timer.stop();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				/**
				 * Parte responsável em adicionar a cor à sequencia ao soltar o botão.
				 */
				botaoAmarelo.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Yellow_Button.png"));
				actYellow();
				
				if(jogo != null){
					jogo.Player.add("yellow");
					jogo.SequenciaJogador();
					if(jogo != null)
						jogo.JogoPrincipal();
				}
			}
		});
		
		botaoAmarelo.setForeground(Color.GREEN);
		botaoAmarelo.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Yellow_Button.png"));
		botaoAmarelo.setBounds(14, 232, 204, 203);
		botaoAmarelo.setBorder(null);
		botaoAmarelo.setContentAreaFilled(false);
		botaoAmarelo.setFocusPainted(false);
		contentPane.add(botaoAmarelo);
		
		/**
		 * Botão azul:
		 */
		botaoAzul = new JButton("");
		ActionListener piscarAzul = new ActionListener(){
			/**
			 *Parte responsável na animação de piscar o botão. 
			 */
			@Override
            public void actionPerformed(ActionEvent event){
				pressBlue();
			}
		};
		botaoAzul.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				/**
				 *Parte responsável no efeito sonoro do botão. 
				 */
				botaoAzul.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Blue_Button(apertado).png"));
				Sons.Reproduzir("Pressionar", false);
				
				if(timer != null)
					timer.stop();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				/**
				 * Parte responsável em adicionar a cor à sequencia ao soltar o botão.
				 */
				botaoAzul.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Blue_Button.png"));
				actBlue();
				
				if(jogo != null){
					jogo.Player.add("blue");
					jogo.SequenciaJogador();
					if(jogo != null)
						jogo.JogoPrincipal();
				}
			}
		});
		botaoAzul.setForeground(Color.GREEN);
		botaoAzul.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Blue_Button.png"));
		botaoAzul.setBounds(231, 231, 204, 203);
		botaoAzul.setBorder(null);
		botaoAzul.setContentAreaFilled(false);
		botaoAzul.setFocusPainted(false);
		contentPane.add(botaoAzul);
		
		/**
		 * Botão verde:
		 */
		botaoVerde = new JButton("");
		ActionListener piscarVerde = new ActionListener(){
			/**
			 *Parte responsável na animação de piscar o botão. 
			 */
			@Override
            public void actionPerformed(ActionEvent event){
				pressGreen();
			}
		};
		botaoVerde.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				/**
				 *Parte responsável no efeito sonoro do botão. 
				 */
				botaoVerde.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Green_Button(apertado).png"));
				Sons.Reproduzir("Pressionar", false);
				
				if(timer != null)
					timer.stop();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				/**
				 * Parte responsável em adicionar a cor à sequencia ao soltar o botão.
				 */
				botaoVerde.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Green_Button.png"));
				actGreen();
				
				if(jogo != null){
					jogo.Player.add("green");
					jogo.SequenciaJogador();
					if(jogo != null)
						jogo.JogoPrincipal();
				}
			}
		});
		botaoVerde.setForeground(Color.GREEN);
		botaoVerde.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Green_Button.png"));
		botaoVerde.setBounds(16, 15, 204, 203);
		botaoVerde.setBorder(null);
		botaoVerde.setContentAreaFilled(false);
		botaoVerde.setFocusPainted(false);
		contentPane.add(botaoVerde);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Background.jpg"));
		background.setBounds(0, 0, 451, 450);
		contentPane.add(background);

		
	}

	/**
	 * Metodos responsaveis pelo ato de piscar o botão.
	 * 
	 * Metodo do botão vermelho.
	 */
	private void pressRed(){
		new Thread(new Runnable() {	
			 public void run() {
				 
				 try {
					Thread.sleep(150);
					botaoVermelho.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Red_Button(luz).png"));
					 Sons.Reproduzir("botao_red", false);		
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				 new Thread(new Runnable() {
					 public void run() {
							
						 try{
							 Thread.sleep(500);
							 botaoVermelho.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Red_Button.png"));
						 } 
						 catch (Exception e){
							 e.printStackTrace();
						 }	
					 }
				 }).start();
			 }
		}).start();
	}

	/**
	 * Metodo do botão amarelo.
	 */
	private void pressYellow(){
		new Thread(new Runnable() {	
			 public void run() {
				 
				 try {
					Thread.sleep(150);
					botaoAmarelo.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Yellow_Button(luz).png"));
					Sons.Reproduzir("botao_yellow", false);		
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				 new Thread(new Runnable() {
					 public void run() {
							
						 try{
							 Thread.sleep(500);
							 botaoAmarelo.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Yellow_Button.png"));
						 } 
						 catch (Exception e){
							 e.printStackTrace();
						 }	
					 }
				 }).start();
			 }
		}).start();
	}

	/**
	 * Metodo do botão verde.
	 */
	private void pressGreen(){
		new Thread(new Runnable() {	
			 public void run() {
				 
				 try {
					Thread.sleep(150);
					botaoVerde.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Green_Button(luz).png"));
					Sons.Reproduzir("botao_green", false);		
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				 new Thread(new Runnable() {
					 public void run() {
							
						 try{
							 Thread.sleep(500);
							 botaoVerde.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Green_Button.png"));
						 } 
						 catch (Exception e){
							 e.printStackTrace();
						 }	
					 }
				 }).start();
			 }
		}).start();
	}
	/**
	 * Metodo do botão azul.
	 */
	private void pressBlue(){
		new Thread(new Runnable() {	
			 public void run() {
				 
				 try {
					Thread.sleep(150);
					botaoAzul.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Blue_Button(luz).png"));
					 Sons.Reproduzir("botao_blue", false);		
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				 new Thread(new Runnable() {
					 public void run() {
							
						 try{
							 Thread.sleep(500);
							 botaoAzul.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Media\\imagens\\Blue_Button.png"));
						 } 
						 catch (Exception e){
							 e.printStackTrace();
						 }	
					 }
				 }).start();
			 }
		}).start();
	}
	

	/**
	 *  Usar o 'piscar botões' em Jogo por meio da interface fazParte.
	 */
	@Override
	public void actRed() {
		pressRed();
	}
	
	@Override
	public void actYellow(){
		pressYellow();
	}
	
	@Override
	public void actBlue(){
		pressBlue();
	}
	
	@Override
	public void actGreen(){
		pressGreen();
	}
	
	
	/**
	 * Metodo que encerra o jogo após uma vitoria.
	 */
	public void Vitoria(){
		Integer numAudio;
		Random random = new Random();
		numAudio = random.nextInt(5);
		
		Sons.Reproduzir("Acertou"+numAudio.toString(), true);
		jogo = null;
	}
	
	/**
	 * Metodo que encerra o jogo após uma derrota, seja por sequencia errada ou tempo inativo.
	 */
	public void Derrota(){
		Sons.Reproduzir("Errou", true);
		jogo = null;
		if(timer != null){
			timer.stop();
			timer = null;
		}	
	}
	

	/**
	 * Metodo seletor de dificuldade. Este será usado como parâmetro ao criar o tamanho da sequencia do jogo.
	 * 
	 * @param String dificulty - String que representa o nível de dificuldade selecionado pelo usuário.
	 * @return Inteiro que corresponde ao tamanho da sequência a ser gerada pelo computador.
	 */
	private int NumSequencias(String dificulty){
		if(dificulty.equals("Nivel 1"))
			return 8;
		else if(dificulty.equals("Nivel 2"))
			return 14;
		else if(dificulty.equals("Nivel 3"))
			return 16;
		else
			return 32;
	}
	
	/**
	 *  Metodo executado em uma thread paralela que encerra o jogo caso nao haja resposta do jogador apos 5 segundos.
	 */
	public void Esperando(){
	
			if(timer != null && timer.isRunning()){
				timer.stop();
			}
		
			timer = null;
			timer = new Timer(5000, new ActionListener(){
				@Override
	            public void actionPerformed(ActionEvent event){
					Derrota();
				}
			});
			timer.setRepeats(false);
			timer.setInitialDelay(5000);
			timer.start();
	}
}


	
