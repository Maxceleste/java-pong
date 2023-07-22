package mobs;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    private Color color;
    private int screenHeigth;
    private int cornerUp;
    private int cornerDown;
    private int x;
    private int y;
    private int width;
    private int height;
    

    public Player(Color color, int screenHeigth, int cornerUp, int cornerDown, int x, int y, int width){
        this.color = color;
        this.screenHeigth = screenHeigth;
        this.cornerUp = cornerUp;
        this.cornerDown = cornerDown;
        this.x = x;
        this.y = y;
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

    public void moveUp(){
        if (y > cornerUp) y--;
    }
    public void moveDown(){
        if (y + height < screenHeigth - cornerDown) y++;
    }

    public void playerRender(Graphics g){
        g.setColor(color);
        g.fillRect( x, y, width, height);
    }
}
