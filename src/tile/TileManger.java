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
        map = new int[Window.maxWorldRow][Window.maxWorldCol];
        getTileImage();
        loadTileMap("test.txt");
    }

    private void getTileImage() {
        // Implementation to load tile images from resources
        try{
            String[] files = {"grass01", "wall", "water01", "earth", "tree", "road"};
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
        int mapX = 0, mapY = 0, worldX, worldY, screenX, screenY;

        while (mapX < Window.maxWorldCol && mapY < Window.maxWorldRow) {
            int tileNum = map[mapX][mapY];

            worldX = mapX * Window.tileSize;
            worldY = mapY * Window.tileSize;
            screenX = worldX - Window.player.world_Xpos + Window.player.SCREEN_X;
            screenY = worldY - Window.player.world_Ypos + Window.player.SCREEN_Y;

            if(worldX > Window.player.world_Xpos - Window.player.SCREEN_X - Window.tileSize &&
                    worldX < Window.player.world_Xpos + Window.player.SCREEN_X + Window.tileSize &&
                    worldY > Window.player.world_Ypos - Window.player.SCREEN_Y - Window.tileSize &&
                    worldY < Window.player.world_Ypos + Window.player.SCREEN_Y + Window.tileSize
            ){
                g2.drawImage(tiles[tileNum].image,
                        screenX, screenY,
                        Window.tileSize, Window.tileSize, null);
            }



            mapX++;
            if (mapX == Window.maxWorldCol) {
                mapX = 0;
                mapY++;
            }
        }

    }
}
