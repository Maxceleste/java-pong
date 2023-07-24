package gameScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import graficos.MidLine;
import graficos.Score;
import mobs.Ball;
import mobs.Enemy;
import mobs.Player;

public class SoloMode {

    Ball ball;
    Player player;
    Enemy enemy;
    Score score;
    MidLine midLine;
    int screenWidth;
    int screenHeigth;
    int cornerUp;
    int cornerDown;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public SoloMode( 
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
        player = new Player(Color.GREEN, screenHeigth, cornerUp, cornerDown, 0, 70, 10);
        enemy = new Enemy(Color.RED, screenHeigth, cornerUp, cornerDown, screenWidth - 10, 70, 10 );
        score = new Score(25, "Arial", screenWidth, 22, Color.WHITE);
        midLine = new MidLine(screenWidth, screenHeigth, cornerUp, cornerDown, Color.WHITE);
        

    }
    
    public void soloModeTick(){
        //Ball tick code
        ball.ballTick(player, enemy);
       
        //*******************/

        //Player tick code

        if (movingUp){
            player.moveUp();
        }
        if (movingDown){
            player.moveDown();
        }

        //******************/

        //Enemy tick code

        enemy.enemyTick(ball);

        /*******************/

        // Score tick code

        score.scoreTick(ball, this);
        if (score.getGameReset()) resetGame();
    }

    public void soloModeRender(Graphics g, String fpsCount){
        g.setColor(new Color(0,0,0)); // Valores RGB, cor atual: PRETO
        g.fillRect(0, 0, screenWidth, screenHeigth); // estabelecendo o fundo do cenário

        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, cornerUp - 5, screenWidth, 5);
        g.fillRect(0, screenHeigth - cornerDown, screenWidth, 5); // 3 linhas para as bordas  

        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.WHITE);
        g.drawString(fpsCount, 10, screenHeigth - cornerDown + 17); // toda essas 3 linhas são para o String

        ball.ballRender(g);
        player.playerRender(g);
        enemy.enemyRender(g);
        score.scoreRender(g);
        midLine.MidLineRender(g);

    }

    public void playerMovementPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            movingUp = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            movingDown = true;
        }
    }

    public void playerMovementRelease(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            movingUp = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            movingDown = false;
        }
    }

    public void resetGame(){
        player.resetPlayer();
        score.resetScore();
        enemy.resetEnemy();
        ball.resetBall();
    }
}
