package main;

import character.Player;
import tile.TileManger;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;

public class Window extends JPanel implements Runnable{
    //Screen settings
    private static final int tile = 12;
    private static final int scale = 3;
    public static final int tileSize = tile * scale;
    public static int[] aspectRatio = {24, 18};
    public static int[] dimensions = {tileSize * aspectRatio[0], tileSize * aspectRatio[1]};


    //main.Game runtime
    public static Thread gameThread;
    public static final int FPS = 60;

    //Event listener
    public EventListener event = new EventListener();
    public static Collisions collision = new Collisions();

    // Characters
    public static Player player;

    // World settings
    public static TileManger tileManger = new TileManger();
    public static final int maxWorldCol = 50,
            maxWorldRow = 50;
    public static final int worldWidth = tileSize * maxWorldCol,
            worldHeight = tileSize * maxWorldRow;

    public Window() {
        this.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(event);
        this.setFocusable(true); // Panel can focus on key presses

        player = new Player(event, Window.tileSize*23,Window.tileSize*21);
    }

    public void setAspectRatio(int x, int y) {
        aspectRatio[0] = x;
        aspectRatio[1] = y;
    }

    /**
     * Starts the game.
     * Creates a new thread and starts the game loop.
     * @see Thread#start()
     */
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

                fpsCounter++;
            }

            if(timer >= 1000000000) { // Display fps
                System.out.println("FPS: " + fpsCounter);
                fpsCounter = 0;
                timer  = 0;
            }

        }
    }

    /**
     * Updates the game state.
     */
    public void update() {
        player.update();
    }

    /**
     * Paints the game on the screen.
     * @see JPanel#paintComponent(Graphics)
     * @param fx the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics fx) {
        super.paintComponent(fx);
        // Using graphics2D library to draw on the screen
        Graphics2D gfx = (Graphics2D) fx;

        // Draw background first, then characters on top of it
        tileManger.draw(gfx);
        player.draw(gfx);


        // Disposes of this graphics context and releases any system resources that it is using
        gfx.dispose();
    }
}