import java.security.KeyStore;
import java.util.ArrayList;

public class Maze {
    private String[][] maze;
    int currentY;
    int currentX;
    ArrayList<String> directions;
    String previousDirection;


    // Just for printing out
    String instructions = "";

    public Maze(String mazeName) {
        maze = MazeUtility.getMaze(mazeName);
        currentY = 0;
        currentX = 0;
        previousDirection = " ";
    }

    // this one mainly has checkpoint logic
    public void move() {
        instructions += "(" + currentY + ", " + currentX + ") ---> ";
        directions = MazeUtility.checkDirections(maze, currentX, currentY);
        directions.remove(previousDirection);

        // Edit location based on directions
        moveOne(directions);

    }

    public void moveOne(ArrayList<String> directions) {
        // Check dead ends or endpoints
        if (directions.isEmpty() && !checkEnd()) {
            maze[currentY][currentX] = "#";
            currentX = 0;
            currentY = 0;
            instructions = "";
            previousDirection = "";
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



}
