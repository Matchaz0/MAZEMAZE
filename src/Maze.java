import java.util.ArrayList;

public class Maze {
    private String[][] maze;
    ArrayList<CheckPoint> checkPoints;
    String previousDirection;
    boolean running;
    int currentY;
    int currentX;

    // Just for printing out
    String intrustions = "";

    public Maze(String mazeName) {
        maze = MazeUtility.getMaze(mazeName);
        checkPoints = new ArrayList<CheckPoint>();
        previousDirection = "";
        running = true;
        currentY = 0;
        currentX = 0;
    }
    public void move() {
        intrustions += "(" + currentY + ", " + currentX + ") ---> ";
        ArrayList<String> directions = MazeUtility.checkDirections(maze, currentX, currentY);
        directions.remove(previousDirection);
        // Find checkpoints
        if (directions.size() > 1) {
            checkPoints.add(new CheckPoint(maze, currentX, currentY));
        }

        // Edit location based on directions
        if (directions.isEmpty()) {
            directions.add(null);
        }
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

        // Check deadends or endpoints
        if (checkDeadEnd(directions)) {
            currentX = checkPoints.getLast().getxValue();
            currentY = checkPoints.getLast().getyValue();
        }


    }

    public boolean checkRunning() {
        return !checkEnd(); // if end point, then returns false and ends loop
    }

    public String[][] getMaze() {
        return maze;
    }

    public boolean checkEnd() {
        return currentX == maze[0].length - 1 && currentY == maze.length - 1; // return true if at the end
    }

    public boolean checkDeadEnd(ArrayList<String> directions) {
        return (directions.contains(null) && !checkEnd()); // dead end if true

    }

    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    public String getPreviousDirection() {
        return previousDirection;
    }

}
