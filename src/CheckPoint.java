import java.util.ArrayList;

public class CheckPoint {
    int xValue;
    int yValue;
    ArrayList<String> directions;
    ArrayList<String> directionsChecked;

    public CheckPoint(String[][] maze, int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        directions = new ArrayList<String>(MazeUtility.checkDirections(maze, xValue, yValue));
        ArrayList<String> directionsChecked = new ArrayList<>();
    }

    public int getxValue() {
        return xValue;
    }
    public int getyValue() {
        return yValue;
    }
    public ArrayList<String> getDirections() {
        return directions;
    }
    public ArrayList<String> getDirectionsChecked() {
        return directionsChecked;
    }


}
