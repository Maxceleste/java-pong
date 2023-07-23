package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {

    private int playerScore = 0;
    private int enemyScore = 0;
    private int fontSize;   
    private String font;
    private int screenWidth;  
    private int x;
    private int y;  
    private Color color;
    
    public Score(int fontSize, String font, int screenWidth, int y, Color color){
        this.fontSize = fontSize;
        this.font = font;
        this.screenWidth = screenWidth;
        x = this.screenWidth / 2 - 26;
        this.y = y;
        this.color = color;
    }

    public void playerScoreUp(){
        playerScore++;
    }
    public void enemyScoreUp(){
        enemyScore++;
    }
    public void resetScore(){
        playerScore = 0;
        enemyScore = 0;
    }

    public String completeScore(){
        return playerScore + " : " + enemyScore;
    }

    public void scoreRender(Graphics g){
        g.setFont(new Font(font, Font.BOLD, fontSize));
        g.setColor(color);
        g.drawString(completeScore(), x, y);
    }
}
