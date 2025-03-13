import java.util.ArrayList;

public class Point {
    int xValue;
    int yValue;
    boolean allChecked;
    ArrayList<String> directions = new ArrayList<String>();

    public Point(String[][] maze, int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        allChecked = false;
        directions = MazeUtility.checkDirections(maze, xValue, yValue);
    }



}
