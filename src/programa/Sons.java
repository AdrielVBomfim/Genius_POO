/**@author Adriel Venâncio Bonfim - 201500030838
 *@author Igor de Figueiredo Rodrigues - 201500018083
 *@since  20/04/2015
 *@version v1.0.3
 *
 *Classe Sons: responsável pelo controle dos diretórios e uso de arquivos de sons na aplicação.
 *
 *@param String file - Nome do arquivo de som a ser reproduzido
 *		 boolean delay - Define se haverá um intervalo de tempo até	que o som comece a ser reproduzido
 **/
package programa;

import java.io.File;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sons {

	public static synchronized void Reproduzir(String file, boolean delay){
		
		new Thread(new Runnable() {
			 public void run() {
				 try{
					 if(delay == true){
						 if(file.equals("Errou"))
							 Thread.sleep(400);
						 else
							 Thread.sleep(700);
					 }
					 
					 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./Media/audios/"+file+".wav").getAbsoluteFile());
					 Clip clip = AudioSystem.getClip();
					 clip.open(audioInputStream);
					 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					 gainControl.setValue(4.0f);
					 clip.start();
					    
					 do{
						 Thread.sleep(15);
			         }while (clip.isRunning());
					    
					 clip.drain();
					 clip.close();
				 } 
				 catch (Exception e){
					System.out.println("Erro ao tentar carregar arquivo de som");
					e.printStackTrace();
				 }	
			 }
		}).start();
	}
}
