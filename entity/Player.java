package ProjectViolet.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// import ProjectViolet.main.GamePanel;
import ProjectViolet.main.GamePanel;
// Update the import path to match the actual location of KeyHandler
import ProjectViolet.main.KeyHandler;

public class Player extends Entity 
{   
    public BufferedImage img_1; //replace asap
    public BufferedImage a; //replace asap
    public GamePanel gp;
    public KeyHandler keys;

    public Player(GamePanel gp, KeyHandler keys)
    {
        this.gp = gp;
        this.keys = keys;
        getplayerImage();
        setDefaultValues();
    }

    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down"; 
        
    }

    public void getplayerImage() 
    {
        try {
            /*image input */img_1=ImageIO.read(getClass().getResourceAsStream("//images/player.png"));
            //add images

        }catch(IOException e) {
            e.printStackTrace();
          }
        }

    public void update() {
        if(keys.upPressed==true || keys.downPressed==true || keys.leftPressed==true || keys.rightPressed==true) 
        {
           
             if(keys.upPressed==true) {
                    direction="up";
                    y -= speed;
             } else if(keys.downPressed==true) {
                    direction="down";
                    y += speed;
             } else if(keys.leftPressed==true) {
                    direction="left";
                    x -= speed;
             } else if(keys.rightPressed==true) {
                    direction="right";
                    x += speed;
            }
         spritecounter++;
         if(spritecounter>12) {
            if(spriteroll==1) {
                spriteroll=2;
            } else if(spriteroll==2) {
                spriteroll=1;
            }
            spritecounter=0;
         }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction)
        {
            case "up":
            if(spriteroll==1){
                image = a ;//input image
            }
            if(spriteroll==2){
             image = a;//input image
            }
            break;
            case "down":
            if(spriteroll==1){
                image = a;//input image
            }
            if(spriteroll==2){
             image =a ;//input image
            }
            break;   
            case "left":
            if(spriteroll==1){
                image = a;//input image
            }
            if(spriteroll==2){
             image = a;//input image
            }
            break;
            case "right":
            if(spriteroll==1){
                image = a;//input image
            }
            if(spriteroll==2){
             image = a;//input image
            }
            break;  
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
    }

