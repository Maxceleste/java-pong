package graficos;

import java.awt.Color;
import java.awt.Graphics;

public class MidLine {
    private int screenWidth;
    private int screenHeigth;
    private int cornerUp;
    private int cornerDown;
    private Color color;

    public MidLine(int screenWidth, int screenHeigth, int cornerUp, int cornerDown, Color color){
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeigth;
        this.cornerUp = cornerUp;
        this.cornerDown = cornerDown;
        this.color = color;
    }

    public void MidLineRender(Graphics g){

        g.setColor(color);

        int squareSize = (screenHeigth - cornerUp - cornerDown) / 9;
        int y = cornerUp;

        while (y < screenHeigth - cornerDown - squareSize){
            g.fillRect((screenWidth / 2) - 5, y, 5, squareSize);
            y += squareSize * 2;
        }
    }
}
