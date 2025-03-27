import java.util.ArrayList;

public class MazeRunner {
    public static void main(String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();
        Maze maze = new Maze("Data/MazeData");
        MazeUtility.printMaze(maze.getMaze());

        boolean running = !maze.checkEnd();
        while (running) {
            maze.move();
            running = !maze.checkEnd();
        }
        System.out.println(maze.instructions + "(" + maze.currentY + ", " + maze.currentX + ")");


    }

}