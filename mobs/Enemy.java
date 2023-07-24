package mobs;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    private Color color;
    private int screenHeigth;
    private int cornerUp;
    private int cornerDown;
    private int x;
    private int y;
    private int initialY;
    private int width;
    private int height;
    private int speed = 1;
    

    public Enemy(Color color, int screenHeigth, int cornerUp, int cornerDown, int x, int y, int width){
        this.color = color;
        this.screenHeigth = screenHeigth;
        this.cornerUp = cornerUp;
        this.cornerDown = cornerDown;
        this.x = x;
        this.y = y;
        initialY = y;
        this.width = width;

        this.height = screenHeigth / 4;
    }

    public int getWidth(){
        return width;
    }

    public int getHeigth(){
        return height;
    }

    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }

    public void resetEnemy(){
        y = initialY;
    }

    public void moveUp(int speed){
        if (y > cornerUp) y -= speed;
    }
    public void moveDown(int speed){
        if (y + height < screenHeigth - cornerDown) y += speed;
    }

    public void enemyRender(Graphics g){
        g.setColor(color);
        g.fillRect( x, y, width, height);
    }

    public void enemyTick(Ball ball){
        if (ball.getY() < y + height / 2){
            moveUp(speed); 
        } 
        else if(ball.getY() > y + height / 2){
            moveDown(speed); 
        }
    }

}