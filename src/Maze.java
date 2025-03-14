import java.util.ArrayList;

public class Maze {
    private String[][] maze;
    private ArrayList<CheckPoint> checkPoints;
    String previousDirection;
    boolean running;
    int currentY;
    int currentX;

    public Maze(String mazeName) {
        maze = MazeUtility.getMaze(mazeName);
        checkPoints = new ArrayList<CheckPoint>();
        previousDirection = "";
        running = true;
        currentY = 0;
        currentX = 0;
    }
    public void move() {
        ArrayList<String> directions = MazeUtility.checkDirections(maze, currentX, currentY);
        directions.remove(previousDirection);
        if (!directions.isEmpty()) {
            System.out.println();
        }
        else if (directions.get(0).equals("W")) {
            currentX++;
        }
        else if (directions.get(0).equals("S")) {
            currentY--;
        }
        else if (directions.get(0).equals("E")) {
            currentX--;
        }
        else if (directions.get(0).equals("N")) {
            currentY++;
        }
        previousDirection = directions.get(0);
    }

    public boolean checkRunning() {
        ArrayList<String> directions = MazeUtility.checkDirections(maze, currentX, currentY);
        if (directions.contains(previousDirection)) {
            directions.remove(previousDirection);
        }
        if (directions.isEmpty()) {
            return false;
        }
        return true;
    }

    public String[][] getMaze() {
        return maze;
    }

    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    public String getPreviousDirection() {
        return previousDirection;
    }

}
