import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MazeUtility {

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }
    public static void printMaze(String[][] maze) {
        for (String[] row:maze) {
            for (String v:row) {
                System.out.print(v);
            }
            System.out.println();
        }
    }

    public static ArrayList<String> checkDirections(String[][] maze, int x, int y) {
        ArrayList<String> directions = new ArrayList<>();
        if (y > 0 && maze[y - 1][x].equals(".")) {
            directions.add("N");
        }
        if (y < maze.length - 1 && maze[y + 1][x].equals(".")) {
            directions.add("S");
        }
        if (x < maze[0].length - 1 && maze[y][x + 1].equals(".")) {
            directions.add("E");
        }
        if (x > 0 && maze[y][x - 1].equals(".")) {
            directions.add("W");
        }
        return directions;

    }


}
