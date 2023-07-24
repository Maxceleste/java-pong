package gameScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import graficos.MidLine;
import graficos.Score;
import mobs.Ball;
import mobs.Player;

public class MultiMode {

    Ball ball;
    Player player1;
    Player player2;
    Score score;
    MidLine midLine;
    int screenWidth;
    int screenHeigth;
    int cornerUp;
    int cornerDown;
    private boolean player1movingUp = false;
    private boolean player1movingDown = false;
    private boolean player2movingUp = false;
    private boolean player2movingDown = false;

    public MultiMode( 
    int screenWidth,
    int screenHeigth,
    int cornerUp,
    int cornerDown
    ){
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeigth;
        this.cornerUp = cornerUp;
        this.cornerDown = cornerDown;
        ball = new Ball(screenWidth / 2, screenHeigth / 2, 15, 15, Color.WHITE, screenWidth, screenHeigth, cornerUp, cornerDown);
        player1 = new Player(Color.GREEN, screenHeigth, cornerUp, cornerDown, 0, 70, 10);
        player2 = new Player(Color.RED, screenHeigth, cornerUp, cornerDown, screenWidth - 10, 70, 10 );
        score = new Score(25, "Arial", screenWidth, 22, Color.WHITE);
        midLine = new MidLine(screenWidth, screenHeigth, cornerUp, cornerDown, Color.WHITE);
        

    }
    
    public void multiModeTick(){
        //Ball tick code
        ball.ballTick(player1, player2);
       
        //*******************/

        //Player1 tick code

        if (player1movingUp){
            player1.moveUp();
        }
        if (player1movingDown){
            player1.moveDown();
        }

        //******************/

        //Player2 tick code

        if (player2movingUp){
            player2.moveUp();
        }
        if (player2movingDown){
            player2.moveDown();
        }

        /*******************/

        // Score tick code

        score.scoreTick(ball, this);
        if (score.getGameReset()) resetGame();
    }

    public void multiModeRender(Graphics g, String fpsCount){
        g.setColor(new Color(0,0,0)); // Valores RGB, cor atual: PRETO
        g.fillRect(0, 0, screenWidth, screenHeigth); // estabelecendo o fundo do cenário

        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, cornerUp - 5, screenWidth, 5);
        g.fillRect(0, screenHeigth - cornerDown, screenWidth, 5); // 3 linhas para as bordas  

        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.WHITE);
        g.drawString(fpsCount, 10, screenHeigth - cornerDown + 17); // toda essas 3 linhas são para o String

        ball.ballRender(g);
        player1.playerRender(g);
        player2.playerRender(g);
        score.scoreRender(g);
        midLine.MidLineRender(g);

    }

    public void playerMovementPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            player1movingUp = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            player1movingDown = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_O){
            player2movingUp = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_L){
            player2movingDown = true;
        }
    }

    public void playerMovementRelease(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            player1movingUp = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            player1movingDown = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_O){
            player2movingUp = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_L){
            player2movingDown = false;
        }
    }

    public void resetGame(){
        player1.resetPlayer();
        player2.resetPlayer();
        score.resetScore();
        ball.resetBall();
    }
}
