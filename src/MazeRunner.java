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

        boolean running = !maze.checkEnd();
        int count = 0;
        while (running && count < 10) {
            maze.move();
            running = !maze.checkEnd();
            System.out.println(running);
            System.out.println(maze.instructions);
            count++;
        }
        System.out.println(maze.checkPoints);
        for (CheckPoint c : maze.checkPoints) {
            System.out.println("("+c.yValue+ "," + c.xValue+")");
        }

        System.out.println(maze.instructions + "(" + maze.currentY + ", " + maze.currentX + ")");


    }
}
