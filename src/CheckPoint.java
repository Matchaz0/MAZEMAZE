import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckPoint {
    int xValue;
    int yValue;
    ArrayList<String> directionsChecked;
    String instructions;
    String directionFrom;

    public CheckPoint(String[][] maze, int xValue, int yValue, String instructions, String directionFrom) {
        this.xValue = xValue;
        this.yValue = yValue;
        directionsChecked = new ArrayList<>();
        this.instructions = instructions;
        this.directionFrom = directionFrom;
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public String getCurrentInstructions() {
        return instructions;
    }
    public ArrayList<String> getDirectionsChecked() {
        return directionsChecked;
    }
    public void addDirectionChecked(String direction) {directionsChecked.add(direction);}


}
