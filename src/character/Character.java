package character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    public int world_Xpos, world_Ypos, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0, spriteNum = 1;
    public Rectangle collisionBox;
    public boolean collisionOn = false;



}
