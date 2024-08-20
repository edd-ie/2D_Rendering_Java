public class Maze {
    public final int WIDTH;
    public final int HEIGHT;

    public static int[][] maze;

    public Maze(int x, int y, int[][] blueprint){
        WIDTH = x;
        HEIGHT = y;
        maze = blueprint;
    }
}
