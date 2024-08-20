import java.awt.image.BufferedImage;

public class Character {
    public int xPos, yPos, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0, spriteNum = 1;

    public void move(int x, int y) {
        xPos += x;
        yPos += y;
    }

    public void move(int x, int y, int speed) {
        xPos += x;
        yPos += y;
        this.speed = speed;
    }


}
