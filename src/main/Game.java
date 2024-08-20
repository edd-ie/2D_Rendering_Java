package main;

import javax.swing.*;

public class Game extends JFrame {
    //Application window
    public static Window panel = new Window();

    public Game(){
        this.setTitle("RayCasting 2D");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel.startGame();
    }


    public static void main(String[] args) {
        Game app = new Game();
    }
}
