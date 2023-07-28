package gameScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import graficos.Game;
import graficos.Sound;

public class MainMenu {

    private int screenWidth;
    private int screenHeigth;
    private int options = 0; // 0 == singleplayer, 1 == multiplayer
    private int actualScreen = 0;
    Game game;
    Sound soundSwitch;
    Sound soundStart;



    public MainMenu(int screenWidth, int screenHeigth, Game game){
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeigth;
        this.game = game;
        soundSwitch = new Sound("wallHit.wav");
        soundStart = new Sound("gamePoint.wav");
    }
    
    public void menuRender(Graphics g){
        g.setColor(new Color(0,0,0)); // Valores RGB, cor atual: PRETO
        g.fillRect(0, 0, screenWidth, screenHeigth); // estabelecendo o fundo do cenário

        g.setFont(new Font("Arial", Font.BOLD, 15)); // FOnte dos botões

        //Botão Single-player
        if(options == 0){
            g.setColor(Color.GREEN);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.drawRoundRect(10, 70, 110, 25, 15, 15);
        g.drawString("Single-player", 15, 87);

        //Botão Multi-player
        if(options == 1){
            g.setColor(Color.GREEN);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.drawRoundRect(10, 100, 110, 25, 15, 15);
        g.drawString("Multi-player", 15, 117);

        //Titulo
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        g.drawString("Java Pong", 10, 50);

        //Créditos
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Game by Max Paulo", 10, screenHeigth - 10);

        //Instruções
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Player one", 140, 87);
        g.drawString("W - UP", 140, 105);
        g.drawString("S - DOWN", 140, 120);

        g.drawString("Player two", 220, 87);
        g.drawString("O - UP", 220, 105);
        g.drawString("L - DOWN", 220, 120);

        g.drawLine(205, 70, 205, 125 );
    }

    public void menuTick(){

    }

    public int getActualScreen(){
        return actualScreen;
    }

    public void menuNavigatePressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W & options > 0){
            options--;
            soundSwitch.playSound();
        }
        if(e.getKeyCode() == KeyEvent.VK_S & options < 1){
            options++;
            soundSwitch.playSound();
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (options == 0) actualScreen = 1;
            if (options == 1) actualScreen = 2;
            soundStart.playSound();
        }
    }
}
