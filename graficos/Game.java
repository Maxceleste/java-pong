package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = false;
    private final int SCALE = 3;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;

    private BufferedImage image;
    

    public Game(){
        // Temos abaixo basicamente configuações padrão da tela, nesse método construtor.
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void initFrame(){
        frame = new JFrame("Java Pong");
        frame.add(this); // não sei que merda é essa, mas pelo visto da acesso ao Canvas
        frame.setResizable(false); // não deixa redimensionar a tela
        frame.pack(); // Não entendi mas precisa
        frame.setLocationRelativeTo(null); // Deixa a janela no centro
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quando fecha a tela realmente fecha
        frame.setVisible(true); // Quando inicializar a classe a janela fica visível
    }

    public void run(){

        //Variáveis que cuidam do gameLoop

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0.0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        //GameLoop
        while(isRunning){
            //Variáveis delimitadoras de fps
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            //Momento de atualizar o jogo
            if (delta >= 1){
                tick();
                render();
                frames++;
                delta = 0;
            }
            //Exibir FPS no terminal
            if (System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }

        stop();
    }

    public synchronized void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop(){ // Método apenas para ter certeza de finalizar tudo.
        isRunning = false;
        try {
            thread.join(); // Essa linha pode dar errado, então adicionamos o try catch.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick(){

    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy(); // Buffer é necessário para mais performance na renderização.
        if (bs == null){
            this.createBufferStrategy(3); //Número de buffers, 3 e 2 é legal.
            return;
        }

        Graphics g = image.getGraphics();
        g.setColor(new Color(0,0,0)); // Valores RGB, cor atual: PRETO
        g.fillRect(0, 0, WIDTH, HEIGHT); // estabelecendo o fundo do cenário

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Pong", 10, 20); // toda essas 3 linhas são para o String

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); // Após eu configurar acima, eu renderizo tudo pronto agora,
        bs.show();
    }
}
