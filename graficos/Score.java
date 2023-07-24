package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gameScreens.MultiMode;
import gameScreens.SoloMode;
import mobs.Ball;

public class Score {

    private int playerScore = 0;
    private int enemyScore = 0;
    private int fontSize;   
    private String font;
    private int screenWidth;  
    private int x;
    private int y;  
    private Color color;
    private boolean playerWin = false;
    private boolean enemyWin = false;
    private boolean gameEnd = false;
    private boolean gameReset = false;
    private int timer = 0;

    
    public Score(int fontSize, String font, int screenWidth, int y, Color color){
        this.fontSize = fontSize;
        this.font = font;
        this.screenWidth = screenWidth;
        x = this.screenWidth / 2 - 27;
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
        timer = 0;
        gameEnd = false;
        gameReset = false;
        playerWin = false;
        enemyWin = false;
    }

    public boolean getGameReset(){
        return gameReset;
    }

    public String completeScore(){
        return playerScore + " : " + enemyScore;
    }

    public void scoreTick(Ball ball, MultiMode game){
        if (ball.getX() + ball.getWidth() < 0){
            enemyScoreUp();
            ball.resetBall();
        }

        if (ball.getX() > screenWidth){
            playerScoreUp();
            ball.resetBall();
        }

        if (playerScore > 4){
            playerWin = true;
            gameEnd = true;
            ball.resetBall();
            ball.setVelocityX(0);
            ball.setVelocityY(0);
        }
        if (enemyScore > 4){
            enemyWin = true;
            gameEnd = true;
            ball.resetBall();
            ball.setVelocityX(0);
            ball.setVelocityY(0);
        }
        if (gameEnd){
            timer++;
        }
        if (timer > 180){
            gameReset = true;
        }
    }

    public void scoreTick(Ball ball, SoloMode game){
        if (ball.getX() + ball.getWidth() < 0){
            enemyScoreUp();
            ball.resetBall();
        }

        if (ball.getX() > screenWidth){
            playerScoreUp();
            ball.resetBall();
        }

        if (playerScore > 4){
            playerWin = true;
            gameEnd = true;
            ball.resetBall();
            ball.setVelocityX(0);
            ball.setVelocityY(0);
        }
        if (enemyScore > 4){
            enemyWin = true;
            gameEnd = true;
            ball.resetBall();
            ball.setVelocityX(0);
            ball.setVelocityY(0);
        }
        if (gameEnd){
            timer++;
        }
        if (timer > 180){
            gameReset = true;
        }
    }


    public void scoreRender(Graphics g){
        g.setFont(new Font(font, Font.BOLD, fontSize));
        g.setColor(color);
        g.drawString(completeScore(), x, y);

        if (playerWin){
            g.setFont(new Font(font, Font.BOLD, fontSize));
            g.setColor(color);
            g.drawString("WINNER", 30, 100);
        }
        if (enemyWin){
            g.setFont(new Font(font, Font.BOLD, fontSize));
            g.setColor(color);
            g.drawString("WINNER", (screenWidth / 2) + 10, 100);
        }
        
    }
}
