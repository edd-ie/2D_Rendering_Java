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
    public int[][] map;

    public TileManger() {
        tiles = new Tile[10];
        map = new int[Window.aspectRatio[1]][Window.aspectRatio[0]];
        getTileImage();
        loadTileMap("test.txt");
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

    public void loadTileMap(String fileName) {
        // Implementation to load tile map from a file
        Scanner sc = null, token = null;
        try{
            sc = new Scanner(new File("res/Map/"+fileName));

            int x=0,  y=0;
            while (sc.hasNext()){
                String line = sc.nextLine();
                token = new Scanner(line);
                token.useDelimiter(" ");
                while (token.hasNext()){
                    map[x][y] = token.nextInt();
                    y++;
                    if (y >= map[0].length) break;
                }
                y=0;
                x++;
                if (x >= map.length) break;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if (sc != null) sc.close();
            if (token != null) token.close();
        }
    }

    public void draw(Graphics2D g2) {
        // Implementation to draw tiles on the game panel
        int xAxis = 0, yAxis = 0;
        for (int[] x : map) {
            for (int y : x) {
                g2.drawImage(tiles[y - 1].image, xAxis, yAxis, Window.tileSize, Window.tileSize, null);
                xAxis += Window.tileSize;
            }
            yAxis += Window.tileSize;
            xAxis = 0;
        }

    }
}
