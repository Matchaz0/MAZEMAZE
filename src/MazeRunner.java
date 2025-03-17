import java.util.ArrayList;

public class MazeRunner {
    public static void main(String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();
        Maze maze = new Maze("Data/MazeData");
        MazeUtility.printMaze(maze.getMaze());
//        for (int i = 0; i < maze.length; i ++) {
//            for (int j = 0; j < maze[0].length; j++) {
//                if (MazeUtility.isPoint(maze, j, i)) {
//                    System.out.println(MazeUtility.checkDirections(maze, j, i));
//                }
//            }
//
//        }
        boolean running = maze.checkRunning();

        int count = 0;
        while (count < 15) {
            maze.move();
            running = maze.checkRunning();
            System.out.println(running);
            count++;
            System.out.println(maze.intrustions);
        }
        System.out.println(maze.checkPoints);

    }
}
