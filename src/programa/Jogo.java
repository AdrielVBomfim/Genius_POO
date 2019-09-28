/**@author Adriel Venâncio Bonfim - 201500030838
 *@author Igor de Figueiredo Rodrigues - 201500018083
 *@since  20/04/2015
 *@version v1.0.3
 *
 *Classe Jogo: Responsável por criar o jogo com os dados passados pela classe principal (Interface).
 */
package programa;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;



public class Jogo{
	/**
	 * Metodo do jogo.
	 * Variáveis auxiliares.
	 */
	private Interface UI;
	private int quantSequencia;
	private int gamemode;
	private int limiteSeqMaq = 0;
	private int comparaSeqs = 0;
	private String[] cor = {"green","red","yellow","blue"};
	Random r = new Random();
	ArrayList<String> Maquina = new ArrayList();
	ArrayList<String> Player = new ArrayList();
	ArrayList<String> Player2 = new ArrayList();
	
	public Jogo(int quantSequencia, int gamemode, Interface UI){
		/**
		 * Construtor do jogo, define o tamanho da sequencia (dificuldade) e modo de jogo (possivelmente haverá um segundo).
		 * 
		 * @param int quantSequencia - Tamanho das sequencia a ser gerada pelo computador;
		 * 	      int gamemode - Numero inteiro que representa o modo de jogo;
		 * 		  Interface UI - Objeto do tipo Interface responsavel pela interface do programa.	
		 */
		this.quantSequencia = quantSequencia;
		this.gamemode = gamemode; 
		this.UI = UI; 
		
	}

	public void SequenciaMaquina(){
	/**
	 * Metodo que cria uma sequencia de cores utilizado no jogo como objetivo.
	 */
		for (int i = 0; i < quantSequencia; i++){
			Maquina.add(cor[r.nextInt(4)]);
		}
	}
	
	public void SequenciaJogador(){
		/**
		 * Metodo que compara a sequencia no array de cores do jogador (criada conforme aperta os botões) com a da máquina (objetivo).
		 */
			if(Player.get(comparaSeqs) == Maquina.get(comparaSeqs))
				if(comparaSeqs == quantSequencia - 1){
					comparaSeqs = 0;
					limiteSeqMaq = 0;
					UI.Vitoria();
				}
				else
					if(comparaSeqs == limiteSeqMaq){
						comparaSeqs = 0;
						limiteSeqMaq++;
					}
					else{
						comparaSeqs++;
						UI.Esperando();
					}
			else{
				comparaSeqs = 0;
				limiteSeqMaq = 0;
				UI.Derrota();
			}
	}
	
	public void JogoPrincipal(){
		/**
		 * Metodo que inicia ou prosegue com o jogo principal. É responsável em limpar a sequencia de cores do jogador para a próxima etapa.
		 * 
		 */
			
			if(comparaSeqs == 0){
				showMaquina(limiteSeqMaq);
				Player.clear();
			}		
	}
	
	
	//Jogo em que o Jogador 2 cria uma sequencia completa
	/**
	 * Possivelmente haverá um modo de jogo secundário, no qual um jogador cria uma sequencia no lugar de cores aleatórias da máquina.
	 */
	/*public void JogoSecundario(){
		for (limiteSeqMaq = 0; limiteSeqMaq < quantSequencia;limiteSeqMaq++){
			//Maquina.add(Cor pressionada pelo jogador 2);
		}
		for(limiteSeqMaq=0;limiteSeqMaq < quantSequencia;limiteSeqMaq++){
			showMaquina(limiteSeqMaq);
			//SequenciaJogador(i,Player,"green");
		}
	}*/
	
	//Jogo em que o Jogador 1 cria a sequencia junto ao jogador 2
	/**
	 * Possivelmente haverá um modo de jogo terciário, no qual cada jogador repete a sequencia já criada pelo anterior e acrescenta uma cor nova.
	 */
	//Falta arrumar para criar a cor [0] e nï¿½o criar o [difficult]->i+1
	/*public void JogoPvP(){
		for (limiteSeqMaq = 0; limiteSeqMaq < quantSequencia;limiteSeqMaq++){
			if((limiteSeqMaq % 2) != 0){
				showMaquina(limiteSeqMaq);
				//SequenciaJogador(i,Player2);
				//Player2.add(Cor pressionada pelo jogador 2);
				//Sinal de troca de jogador (Botï¿½o de ligar pisca, um bip ou algo do tipo)
				//SequenciaJogador(i,Player);
			}
			else {
				showMaquina(limiteSeqMaq);
				//SequenciaJogador(i,Player);
				//Player1.add(Cor pressionada pelo jogador 2);
				//Sinal de troca de jogador (Botï¿½o de ligar pisca, um bip ou algo do tipo)
				//SequenciaJogador(i+1, Player2);
			}
		}
	}*/
	
	private void showMaquina(int limiteSeqMaq){
		/**
		 * Metodo que exibe todas as cores da sequencia da máquina até a posição indicada pelo 
		 * 
		 *@param int limiteSeqMaq - Limite atual na sequência de cores gerada pelo computador.
		 */
		new Thread(new Runnable() {
			public void run() {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				
				for (int n = 0;n <= limiteSeqMaq;n++){
					try {
						Thread.sleep(650);
						PlayCor(Maquina.get(n));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}
				
				UI.Esperando();
			}
		}).start();
	}	

	
	private void PlayCor(String cor){
		/**
		 * Metodo definido na classe principal(Interface) que faz determinado botão piscar com base na sequencia da Maquina.
		 * 
		 * @param String cor - String representando a cor que
		 */
		switch(cor){
			case "green":{UI.actGreen();break;}
			case "red":{UI.actRed();break;}
			case "blue":{UI.actBlue();break;}
			case "yellow":{UI.actYellow();break;}
		}
	}
}