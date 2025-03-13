public class Maze {
    public static void main(String[] args) {
        String[][] maze = MazeUtility.getMaze("Data/MazeData");
        MazeUtility.printMaze(maze);
        for (String[] row:maze) {
            for (String v:row) {
                System.out.print(v);
            }
            System.out.println();
        }
    }
}
