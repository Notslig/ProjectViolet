package ProjectViolet.tile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;

// import Project_Escapeade.main.GamePanel;
import ProjectViolet.main.GamePanel;
//import Project_Escapeade.tile.Tiles;

public class TileManager
 {
    GamePanel gp;
    Tiles[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tiles[10]; // input tile variaties
        mapTileNum=new int[gp.ScreenColumns][gp.ScreenRows];
        getTileImage();
        loadMap("/maps/map01.txt");
    }
    public void getTileImage() {
        try{
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0.png"));
           

            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_1.png"));
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    public void draw(Graphics2D g2) {
       int col=0;
       int row=0;
       int x=0;
       int y=0;

       while(col<gp.ScreenColumns && row<gp.ScreenRows) {
        //int tileNum=mapTileNum[col][row];
            g2.drawImage(tiles[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+=gp.tileSize;
            if(col==gp.ScreenColumns) {
                col=0;
                row++;
                x=0;
                y+=gp.tileSize;
            }
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.ScreenColumns && row<gp.ScreenRows) {
                String line = br.readLine();
                while(col<gp.ScreenColumns) 
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col==gp.ScreenColumns)
                 {
                    col=0;
                    row++;
                 }
            }
            br.close();
        }catch(IOException e) {
            e.printStackTrace();
    }
    
}
}
