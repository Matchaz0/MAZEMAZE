import java.util.ArrayList;

public class CheckPoint {
    int xValue;
    int yValue;
    ArrayList<String> directions = new ArrayList<String>();
    ArrayList<String> directionsChecked = new ArrayList<>();

    public CheckPoint(String[][] maze, int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        directions = MazeUtility.checkDirections(maze, xValue, yValue);
    }



}
