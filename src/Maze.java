import java.security.KeyStore;
import java.util.ArrayList;

public class Maze {
    private String[][] maze;
    ArrayList<CheckPoint> checkPoints;
    String previousDirection;
    boolean running;
    int currentY;
    int currentX;
    CheckPoint lastCheckPoint;

    // Just for printing out
    String instructions = "";

    public Maze(String mazeName) {
        maze = MazeUtility.getMaze(mazeName);
        checkPoints = new ArrayList<CheckPoint>();
        previousDirection = " ";
        running = true;
        currentY = 0;
        currentX = 0;
        lastCheckPoint = null;
    }
    // this one mainly has checkpoint logic
    public void move() {
        instructions += "(" + currentY + ", " + currentX + ") ---> ";
        ArrayList<String> directions = MazeUtility.checkDirections(maze, currentX, currentY);
        directions.remove(previousDirection);

        // add new checkpoint if not previously tracked
        if (directions.size() > 1) {
            boolean notPreviouslyDone = true;
            for (CheckPoint p : checkPoints) {
                if (p.getxValue() == currentX && p.getyValue() == currentY) {
                    notPreviouslyDone = false;
                }
            }
            if (notPreviouslyDone) {
                checkPoints.add(new CheckPoint(maze, currentX, currentY, instructions, previousDirection));
            }
            else {
                checkPoints.getLast().addDirectionChecked(directions.getFirst());
                for (String direction:checkPoints.getLast().getDirectionsChecked()) {
                    directions.remove(direction);
                }
            }
        }

        // check if checkpoint
        if (!checkPoints.isEmpty()) {
            lastCheckPoint = checkPoints.getLast();
        }

        // Edit location based on directions
        boolean deadEnd = !moveOne(directions); // if false then we have dead end


        // Check dead ends or endpoints
        if (deadEnd) {
            currentX = lastCheckPoint.getxValue();
            currentY = lastCheckPoint.getyValue();
        }


    }

    public void updateCheckpoints(ArrayList<String> directions, boolean deadEnd) {
        //  Check if there is a checkpoint



        if (lastCheckPoint != null && directions.isEmpty() && !checkEnd()) { // if dead end
            checkPoints.remove(lastCheckPoint);
            currentX = checkPoints.getLast().getxValue();
            currentY = checkPoints.getLast().getyValue();
            instructions = lastCheckPoint.instructions;

            directions = MazeUtility.checkDirections(maze, currentX, currentY);
            directions.remove(checkPoints.getLast().directionFrom);

            for (CheckPoint checkpoint: checkPoints) {
                System.out.println(checkpoint.yValue + " " + checkpoint.xValue);
            }

        }

        // while returing
        if (lastCheckPoint != null && currentX == lastCheckPoint.getxValue() && currentY == lastCheckPoint.getyValue()) {
            for (String direction:lastCheckPoint.getDirectionsChecked()) {
                if (directions.contains(direction)) {
                    directions.remove(direction);
                }
            }
            instructions = lastCheckPoint.getCurrentInstructions();
            }
            lastCheckPoint.addDirectionChecked(directions.get(0));

            if (directions.size() > 0) {
                lastCheckPoint.addDirectionChecked(directions.get(0));
            }
            else {
                checkPoints.remove(lastCheckPoint);
            }

//            for (String direction : checkPoints.getLast().getDirectionsChecked()) {
//                directions.remove(direction);
//            }
//            checkPoints.getLast().addDirectionChecked(directions.get(0));
        }


    public boolean moveOne(ArrayList<String> directions) {
        // Edit location based on directions
        if (directions.isEmpty() && !checkEnd()) {
            return false; // dead end
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
        return true;
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
