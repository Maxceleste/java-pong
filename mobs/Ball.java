package mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

    private int screenWidth;
    private int screenHeigth;
    private int cornerUp;
    private int cornerDown;
    private int x;
    private int y;
    private int width;
    private int height;
    private int velocityX = 1;
    private int velocityY = 1;
    private Color color;


    Random random;
    
    public Ball(int x, int y, int width, int height, Color color, int screenWidth, int screenHeigth, int cornerUp, int cornerDown){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeigth;
        this.cornerUp = cornerUp;
        this.cornerDown = cornerDown;
        random = new Random();
    }


    public int getVelocityX(){
        return velocityX;
    }
    public void setVelocityX(int newVelocityX){
        velocityX = newVelocityX;
    }


    public int getVelocityY(){
        return velocityY;
    }
    public void setVelocityY(int newVelocityY){
        velocityY = newVelocityY;
    }

    public void randomVelocity(){
        int newVelocityX = random.nextInt(1, 5);
        int newVelocityY = random.nextInt(1, 5);

        if (velocityX > 0) velocityX = newVelocityX;
        else velocityX = -newVelocityX;

        if (velocityY > 0) velocityY = newVelocityY;
        else velocityY = -newVelocityY;

    }



    public void ballTick(Player player){
        x += velocityX;
        y += velocityY;

        int centerY = y + height/2;

        boolean hitPlayer = (centerY > player.getY() & centerY  < player.getY() + player.getHeigth()) & x < player.getX() + player.getWidth();

        if (x > screenWidth - 1 - width){
            velocityX *= -1;
            randomVelocity();
            x = screenWidth - width - 1;
        }
        if (hitPlayer & x > 0){
            velocityX *= -1;
            randomVelocity();
            x = player.getX() + player.getWidth();
        }

        if (y > screenHeigth - 1 - height - cornerDown){
            velocityY *= -1;
            y = screenHeigth - height - cornerDown - 1;
        }
        if (y < cornerUp){
            velocityY *= -1;
            y = cornerUp;
        }

    }

    public void ballRender(Graphics g){
        g.setColor(color);
        g.fillOval( x, y, width, height);
    }
}
