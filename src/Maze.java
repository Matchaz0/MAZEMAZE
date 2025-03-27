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

        // check if checkpoint valid
        if (directions.size() > 1) {
            // check if previously created checkpoint
            boolean notPreviouslyDone = true;
            for (CheckPoint p : checkPointsBeenTo) {
                if (p.getxValue() == currentX && p.getyValue() == currentY) {
                    notPreviouslyDone = false;
                }
            }
            // add new checkpoint if not previously tracked
            if (notPreviouslyDone) {
                checkPoints.add(new CheckPoint(maze, currentX, currentY, instructions, previousDirection));
                checkPointsBeenTo.add(checkPoints.getLast());
            }
            // if it has, then update the directions to check
            else {
                instructions = checkPoints.getLast().instructions;
                // you have to remove checked directions before adding the next direction to traverse
                for (String direction:checkPoints.getLast().getDirectionsChecked()) {
                    directions.remove(direction);
                }
                checkPoints.getLast().addDirectionChecked(directions.getFirst());


            }
        }

        // check if checkpoint
        if (!checkPoints.isEmpty()) {
            lastCheckPoint = checkPoints.getLast();
        }
        // Edit location based on directions
        moveOne(directions);

    }

    public void moveOne(ArrayList<String> directions) {
        // Check dead ends or endpoints
        if (directions.isEmpty() && !checkEnd()) {
            if (lastCheckPoint.getxValue() == currentX && lastCheckPoint.getyValue() == currentY) {
                checkPoints.removeLast();
                lastCheckPoint = checkPoints.getLast();
            }
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

    public String getPreviousDirection() {
        return previousDirection;
    }

}
