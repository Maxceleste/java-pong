package mobs;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    private int bouncingCount;
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

    public int getBouncingCount(){
        return bouncingCount;
    }
    public void setBouncingCount(int newBouncingCount){
        bouncingCount = newBouncingCount;
    }



    public void ballTick(){
        x += velocityX;
        y += velocityY;

        if (x > screenWidth - 1 - width | x < 0){
            velocityX *= -1;
            bouncingCount++;
        }
        if (y > screenHeigth - 1 - height - cornerDown | y < cornerUp){
            velocityY *= -1;
            bouncingCount++;
        }
    }

    public void ballRender(Graphics g){
        g.setColor(color);
        g.fillOval( x, y, width, height);
    }
}
