package character;

import main.EventListener;
import main.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Character{
    public EventListener keys;
    public final int SCREEN_X, SCREEN_Y;

    /**
     * Constructor for Player
     * @param keyHandler event listener for keyboard input
     * @param worldX x position in the world map
     * @param worldY y position in the world map
     */
    public Player(EventListener keyHandler, int worldX, int worldY) {
        keys = keyHandler;
        this.init(worldX, worldY, 4);
        SCREEN_X = Window.dimensions[0]/2 - Window.tileSize/2; // Player x-position on the camera
        SCREEN_Y = Window.dimensions[1]/2 - Window.tileSize/2; // Player y-position on the camera
        getPlayerImage();
    }

    public void init(int x, int y, int speed){
        world_Xpos = x;
        world_Ypos = y;
        this.speed = speed;
        direction = "down";
        collisionBox = new Rectangle(8, 16, 32, 32);
    }

    public void getPlayerImage(){
        try {
            //code to get player image
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk/right2.png")));
        }
        catch (IOException e) {
            //code to handle exception
            e.printStackTrace();
        }
    }

    public void update(){
        if(keys.right|| keys.down || keys.left || keys.up){
            if(keys.up && world_Ypos > speed) {
                direction = "up";
            }
            else if(keys.down) {
                direction = "down";
            }
            else if(keys.left) {
                direction = "left";
            }
            else if(keys.right) {
                direction = "right";
            }

            //Checking world tile collisions
            collisionOn = false;
            Window.collision.checkCollision( this);

            if(!collisionOn){
                switch (direction) {
                    case "up" -> world_Ypos -= speed;
                    case "down" -> world_Ypos += speed;
                    case "left" -> world_Xpos -= speed;
                    case "right" -> world_Xpos += speed;
                }
            }


            spriteCounter++;
            if(spriteCounter > 12){ //changes player image every 12 fps
                if(spriteNum == 1) spriteNum = 2;
                else spriteNum = 1;

                spriteCounter = 0;
            }
        }
        else spriteNum = 1;
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                switch (spriteNum){
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                }
            }
            case "down" -> {
                switch (spriteNum){
                    case 1 -> image = down1;
                    case 2 -> image = down2;
                }
            }
            case "left" -> {
                switch (spriteNum){
                    case 1 -> image = left1;
                    case 2 -> image = left2;
                }
            }
            case "right" -> {
                switch (spriteNum){
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                }
            }
        }

        g2d.drawImage(image, SCREEN_X, SCREEN_Y,
                main.Window.tileSize, main.Window.tileSize, null);
    }
}
