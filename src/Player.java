import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Character{
    public EventListener keys;
    public Window gamePanel;

    public Player(EventListener keyHandler, Window panel) {
        gamePanel = panel;
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/up1"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/up2"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/down1"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/down2"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/left1"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/left2"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/right1"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/right2"));
        }
        catch (IOException e) {
            //code to handle exception
            e.printStackTrace();
        }
    }

    public void update(){
        if(keys.up && yPos > speed) {
            yPos -= speed;
            direction = "up";
        }
        else if(keys.down && yPos < (Window.dimensions[1] - Window.tileSize - speed)) {
            yPos += speed;
            direction = "down";
        }
        else if(keys.left && xPos > speed) {
            xPos -= speed;
            direction = "left";
        }
        else if(keys.right && xPos < (Window.dimensions[0] - Window.tileSize - speed)) {
            xPos += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.white);
        g2d.fillRect(xPos, yPos, Window.tileSize, Window.tileSize);
    }
}
