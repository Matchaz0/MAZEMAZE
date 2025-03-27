import java.security.KeyStore;
import java.util.ArrayList;

public class Maze {
    private String[][] maze;
    ArrayList<CheckPoint> checkPoints;
    ArrayList<CheckPoint> checkPointsBeenTo;
    String previousDirection;
    boolean running;
    int currentY;
    int currentX;
    CheckPoint lastCheckPoint;
    ArrayList<String> directions;

    // Just for printing out
    String instructions = "";

    public Maze(String mazeName) {
        maze = MazeUtility.getMaze(mazeName);
        checkPoints = new ArrayList<CheckPoint>();
        checkPointsBeenTo = new ArrayList<CheckPoint>();
        previousDirection = " ";
        running = true;
        currentY = 0;
        currentX = 0;
        lastCheckPoint = null;
    }
    // this one mainly has checkpoint logic
    public void move() {
        instructions += "(" + currentY + ", " + currentX + ") ---> ";
        directions = MazeUtility.checkDirections(maze, currentX, currentY);
        directions.remove(previousDirection);

        System.out.println(previousDirection);
        System.out.println(directions);
        if (directions.size() > 1) {
            // check if previously created checkpoint
            boolean notPreviouslyDone = true;
            for (CheckPoint p : checkPointsBeenTo) {
                if (p.getxValue() == currentX && p.getyValue() == currentY) {
                    notPreviouslyDone = false;
                }
            }
            if (notPreviouslyDone) {
                checkPoints.add(new CheckPoint(maze, currentX, currentY, instructions, previousDirection));
            }
        }

        if (!checkPoints.isEmpty()) {
            lastCheckPoint = checkPoints.getLast();
        }



        // Edit location based on directions
        moveOne(directions);

    }
    public void deleteDirection() {
        // Edit location based on directions
        CheckPoint lastCheckpoint = checkPoints.getLast();
        int lastXValue = lastCheckpoint.getxValue();
        int lastYValue = lastCheckpoint.getyValue();
        directions = MazeUtility.checkDirections(maze, lastXValue, lastYValue);
        directions.remove(lastCheckpoint.directionFrom);

        if (directions.getFirst().equals("W")) {
            maze[currentY][currentX - 1] = "#";
        }
        else if (directions.getFirst().equals("S")) {
            maze[currentY + 1][currentX - 1] = "#";
        }
        else if (directions.getFirst().equals("E")) {
            maze[currentY][currentX + 1] = "#";
        }
        else if (directions.getFirst().equals("N")) {
            maze[currentY - 1][currentX - 1] = "#";
        }
    }

    public void moveOne(ArrayList<String> directions) {
        // Check dead ends or endpoints
        if (directions.isEmpty() && !checkEnd() && lastCheckPoint != null) {
            deleteDirection();
            currentX = lastCheckPoint.getxValue();
            currentY = lastCheckPoint.getyValue();
        }
        // Edit location based on directions
        else if (directions.get(0).equals("W")) {
            currentX--;
            previousDirection = "E";
        }
        else if (directions.get(0).equals("S")) {
            currentY++;
            previousDirection = "N";
        }
        else if (directions.get(0).equals("E")) {
            currentX++;
            previousDirection = "W";
        }
        else if (directions.get(0).equals("N")) {
            currentY--;
            previousDirection = "S";
        }
    }

    public String[][] getMaze() {
        return maze;
    }

    public boolean checkEnd() {
        return currentX == maze[0].length - 1 && currentY == maze.length - 1; // return true if at the end
    }

    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }


}
