package Project_Escapeade.main;
import Project_Escapeade.tile.TileManager;
//import Project_Escapeade.main.KeyHandler;


import javax.swing.JPanel;
//import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.RenderingHints.Key;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

import Project_Escapeade.entity.Player;



public class GamePanel extends JPanel implements Runnable 
{
    //screen settings
    public final int originalTileSize = 16;//input tile size
    final int scale = 3 ; //input scale size
    public final int tileSize = originalTileSize * scale; //input tile size
    public final int ScreenColumns=16; //input screen columns
    public final int ScreenRows=12; //input screen rows
    public final int ScreenWidth = tileSize * ScreenColumns; 
    public final int ScreenHeight= tileSize * ScreenRows; 

    //Frames per second
    final int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keys = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keys);


    public GamePanel() 
    {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
        this.setFocusable(true);
    }


    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
        
    }


    @Override
    public void run()
    {
       
        
         
    }


    public void update() 
    {
       player.update();
        
    }


    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
        
    }
 public void TimeViewer()
 {    double drawInterval = 1000000000 / FPS;
     double nextDrawTime = System.nanoTime() + drawInterval;
          while (gameThread!=null)
        {
             update();
             repaint();

             try 
             {   double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; 
                 Thread.sleep(1000/FPS);  //else use '(long)remainingTime' for condition
                 if(remainingTime<0)
                 {
                     remainingTime = 0;
                 }

                 Thread.sleep((long)remainingTime); 
                 nextDrawTime += drawInterval; 
             }
              catch (InterruptedException e)
             {
                 e.printStackTrace();
             }
         }
 }
 public void deltaTimeMethod()
    {
        double drawInterval = 1000000000/FPS; 
        double delta=0;
        long lastTime = System.nanoTime();
        long timer = 0;
        long currentTime=0;
        int drawCount = 0;
        
        delta +=(currentTime - lastTime) / drawInterval;
        timer += (currentTime - lastTime);
        lastTime = currentTime;
        if (delta >= 1) 
        {
            update();
            repaint();
            delta--;
            drawCount++;
        }
        if(timer >= 1000000000) 
        {
            System.out.println("FPS: " + drawCount);
            drawCount = 0;
            timer = 0;
        }
        
    }
}