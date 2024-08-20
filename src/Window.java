import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;

public class Window extends JPanel implements Runnable{
    //Screen settings
    private static final int tile = 16;
    private static final int scale = 3;
    public static final int tileSize = tile * scale;
    public static int[] aspectRatio = {16, 12};
    public static int[] dimensions = {tileSize * aspectRatio[0], tileSize * aspectRatio[1]};


    //Game runtime
    public static Thread gameThread;
    public static final int FPS = 60;

    //Event listener
    public EventListener event = new EventListener();

    // Characters
    public Player player;

    public Window() {
        this.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(event);
        this.setFocusable(true); // Panel can focus on key presses

        player = new Player(event);
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
        double intervals = (double) 1000000000 /FPS, countdown=0;
        long lastUpdateTime = System.nanoTime(), timer = 0, currentTime,diff;
        int fpsCounter = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            diff = (currentTime - lastUpdateTime);
            timer += diff;
            countdown +=  (diff/intervals);
            lastUpdateTime = currentTime;

            if (countdown >= 1) { // Update game at 60 FPS
                update();
                repaint();
                countdown = 0;

//                fpsCounter++;
            }

//            if(timer >= 1000000000) { // Display fps
//                System.out.println("FPS: " + fpsCounter);
//                fpsCounter = 0;
//                timer  = 0;
//            }

        }
    }

    // Update screen with new frames
    public void update() {
        player.update();
    }

    public void paintComponent(Graphics fx) {
        super.paintComponent(fx);
        // Using graphics2D library to draw on the screen
        Graphics2D gfx = (Graphics2D) fx;

        player.draw(gfx);

        // Disposes of this graphics context and releases any system resources that it is using
        gfx.dispose();
    }
}