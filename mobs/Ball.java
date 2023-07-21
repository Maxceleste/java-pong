package mobs;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    private int bouncingCount;
    private int screenWidth;
    private int screenHeigth;
    private int x;
    private int y;
    private int width;
    private int height;
    private double velocityX = 1;
    private double velocityY = 1;
    private Color color;
    
    public Ball(int x, int y, int width, int height, Color color, int screenWidth, int screenHeigth){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeigth;
    }


    public double getVelocityX(){
        return velocityX;
    }
    public void setVelocityX(double newVelocityX){
        velocityX = newVelocityX;
    }


    public double getVelocityY(){
        return velocityY;
    }
    public void setVelocityY(double newVelocityY){
        velocityY = newVelocityY;
    }

    public int getBouncingCount(){
        return bouncingCount;
    }
    public void setBouncingCount(int newBouncingCount){
        bouncingCount = newBouncingCount;
    }



    public void ballTick(){
        x += (int) velocityX;
        y += (int) velocityY;

        if (x > screenWidth - 1 - width | x < 0){
            velocityX *= -1;
            bouncingCount++;
        }
        if (y > screenHeigth - 1 - height | y < 0){
            velocityY *= -1;
            bouncingCount++;
        }
    }

    public void ballRender(Graphics g){
        g.setColor(color);
        g.fillOval( x, y, width, height);
    }
}
