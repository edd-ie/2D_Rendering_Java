package character;

import java.awt.image.BufferedImage;

public class Character {
    public int world_Xpos, world_Ypos, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0, spriteNum = 1;

    public void move(int x, int y) {
        world_Xpos += x;
        world_Ypos += y;
    }

    public void move(int x, int y, int speed) {
        world_Xpos += x;
        world_Ypos += y;
        this.speed = speed;
    }


}
