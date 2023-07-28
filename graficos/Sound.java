package graficos;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    String sound;
    Clip clip;

    public Sound(String sound){
        this.sound = sound;
        try{
            // Carrega o arquivo de som a partir do caminho fornecido
            URL soundURL = Sound.class.getResource("/res/soundEffects/" + sound);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            // Obtém um Clip e o abre com o AudioInputStream
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }


    public void playSound() {
        
        // Cria uma thread para reproduzir o som
        Thread soundThread = new Thread(new Runnable() {
            public void run() {
                clip.setFramePosition(0);
                // Reproduz o som
                clip.start();
                      
            }
        });
            // Inicia a thread para reproduzir o som
            soundThread.start();
    }
}

