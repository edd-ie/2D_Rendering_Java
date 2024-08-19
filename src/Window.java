import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;

public class Window extends JPanel implements Runnable{
    //Screen settings
    private static final int tile = 16;
    private static final int scale = 3;
    private static final int tileSize = tile * scale;
    public static int[] aspectRatio = {16, 12};
    public static int[] dimensions = {tileSize * aspectRatio[0], tileSize * aspectRatio[1]};


    //Game runtime
    public static Thread gameThread;
    public static final int FPS = 60;

    //Event listener
    public EventListener event = new EventListener();

    // Characters
    Character player;

    public Window() {
        this.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(event);
        this.setFocusable(true); // Panel can focus on key presses

        player = new Character(100, 100, 4);
    }

    public void setAspectRatio(int x, int y) {
        aspectRatio[0] = x;
        aspectRatio[1] = y;
    }


    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Runs this operation.
     * Starts a thread that runs parallel to the main thread.
     * The game loop is implemented in this method.
     * @see Thread#run()
     */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS; // 0.016666 seconds
        double nextUpdateTime = System.nanoTime() - drawInterval;
        double countdown;
        int fpsCounter = 0;

        while (gameThread != null) {
            update();
            repaint();

             // measures time from last update
            try {
                countdown = (nextUpdateTime - System.nanoTime())/1000000;

                if (countdown <= 0) {
                    countdown = 0;
                }

                Thread.sleep((long)countdown);
                nextUpdateTime += drawInterval;

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // Update screen with new frames
    public void update() {
        if(event.up && player.yPos > player.speed)
            player.yPos -= player.speed;
        else if(event.down && player.yPos < (dimensions[1] - tileSize - player.speed))
            player.yPos += player.speed;
        else if(event.left && player.xPos > player.speed)
            player.xPos -= player.speed;
        else if(event.right && player.xPos < (dimensions[0] - tileSize - player.speed))
            player.xPos += player.speed;
    }

    public void paintComponent(Graphics fx) {
        super.paintComponent(fx);
        // Using graphics2D library to draw on the screen
        Graphics2D gfx = (Graphics2D) fx;

        gfx.setColor(Color.white);
        gfx.fillRect(player.xPos, player.yPos, tileSize, tileSize);

        // Disposes of this graphics context and releases any system resources that it is using
        gfx.dispose();
    }
}