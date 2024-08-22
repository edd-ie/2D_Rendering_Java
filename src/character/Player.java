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

    public Player(EventListener keyHandler) {
        keys = keyHandler;
        this.init(100,100, 4);
        getPlayerImage();
    }

    public void init(int x, int y, int speed){
        xPos = x;
        yPos = y;
        this.speed = speed;
        direction = "down";
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
            if(keys.up && yPos > speed) {
                yPos -= speed;
                direction = "up";
            }
            else if(keys.down && yPos < (main.Window.dimensions[1] - Window.tileSize - speed)) {
                yPos += speed;
                direction = "down";
            }
            else if(keys.left && xPos > speed) {
                xPos -= speed;
                direction = "left";
            }
            else if(keys.right && xPos < (main.Window.dimensions[0] - main.Window.tileSize - speed)) {
                xPos += speed;
                direction = "right";
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

        g2d.drawImage(image, xPos, yPos, main.Window.tileSize, main.Window.tileSize, null);
    }
}
