import java.util.ArrayList;

public class MazeRunner {
    public static void main(String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();
        Maze maze = new Maze("Data/MazeData");
        MazeUtility.printMaze(maze.getMaze());

        boolean running = !maze.checkEnd();
        int count = 0;
        while (running) {
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
//        // Check if returning
//        CheckPoint lastCheckPoint = null;
//        if (!checkPoints.isEmpty()) {
//            lastCheckPoint = checkPoints.getLast();
//        }
//        if (lastCheckPoint != null && currentX == lastCheckPoint.getxValue() && currentY == lastCheckPoint.getyValue()) {
//            for (String direction:lastCheckPoint.getDirectionsChecked()) {
//                if (directions.contains(direction)) {
//                    directions.remove(direction);
//                }
//            }
//            instructions = lastCheckPoint.getCurrentInstructions();
//
//
//
//            while (directions.isEmpty() && !checkEnd()) {// while dead end
//                checkPoints.remove(lastCheckPoint);
//                currentX = checkPoints.getLast().getxValue();
//                currentY = checkPoints.getLast().getyValue();
//                instructions = lastCheckPoint.instructions;
//
//                directions = MazeUtility.checkDirections(maze, currentX, currentY);
//                directions.remove(checkPoints.getLast().directionFrom);
//
//                for (CheckPoint checkpoint: checkPoints) {
//                    System.out.println(checkpoint.yValue + " " + checkpoint.xValue);
//                }
//
//
//            }
//            lastCheckPoint.addDirectionChecked(directions.get(0));

//            if (directions.size() > 0) {
//                lastCheckPoint.addDirectionChecked(directions.get(0));
//            }
//            else {
//                checkPoints.remove(lastCheckPoint);
//            }