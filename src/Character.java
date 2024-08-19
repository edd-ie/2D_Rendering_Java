public class Character {
    public int xPos;
    public int yPos;
    public int speed;


    public Character(int xPos, int yPos, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
    }

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
