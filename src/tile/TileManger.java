package tile;

import main.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class TileManger {
    public Tile[] tiles;

    public TileManger() {
        tiles = new Tile[10];
        getTileImage();
    }

    private void getTileImage() {
        // Implementation to load tile images from resources
        try{
            String[] files = {"grass01", "wall", "water01", "earth", "tree"};
            for(int i = 0; i < files.length; i++) {
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(Objects.requireNonNull(getClass().
                            getResourceAsStream("/tiles/" + files[i] + ".png")));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Implementation to draw tiles on the game panel
        try{
            Scanner sc = new Scanner(new File("res/Map/test.txt"));
            Scanner token;
            int x=0,  y=0;
            while (sc.hasNext()){
                String line = sc.nextLine();
                token = new Scanner(line);
                token.useDelimiter(" ");
                while (token.hasNext()){
                    int num = token.nextInt();
                    g2.drawImage(tiles[num-1].image, x, y, Window.tileSize, Window.tileSize, null);
                    x+=Window.tileSize;
                }
                y+=Window.tileSize;
                x=0;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
//
//        g2.drawImage(tiles[0].image, 0, 0, Window.tileSize, Window.tileSize, null);
//        g2.drawImage(tiles[1].image, Window.tileSize, 0, Window.tileSize, Window.tileSize, null);
//        g2.drawImage(tiles[2].image, Window.tileSize * 2, 0, Window.tileSize, Window.tileSize, null);
    }

}
