package ca.mcmaster.se2aa4.mazerunner;

public class TremauxFinder implements MazeNav {

    private enum Mark {
        UNVISITED, ONCE, TWICE
    }

    private char[][] maze;
    private Mark[][] visited;
    private int currX, currY;
    private Direction currDir;

    public TremauxFinder(char[][] maze) {
        this.maze = maze;
        // this.visited = 
        this.currX = 0;
        this.currY = findEntry();
        this.currDir = Direction.EAST;
    }

    private int findEntry() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == '0') { // Find path on West
                return i;
            }
        }
        return -1; 
    }

    @Override
    public String factorizePath(String path){
        StringBuilder factorizedPath = new StringBuilder();
        // Add factorizePath Logic
        return factorizedPath.toString();
    }

    @Override
    public String findPath() {
        StringBuilder path = new StringBuilder();

    //     while (!exit()) {
    //         markCurrent();

    //         if (canMoveForward() && shouldMoveForward()) {
    //             moveForward();
    //             path.append("F");
    //         } else if (canTurnRight() && shouldTurnRight()) {
    //             turnRight();
    //             path.append("R");
    //             moveForward();
    //             path.append("F");
    //         } else if (canTurnLeft() && shouldTurnLeft()) {
    //             turnLeft();
    //             path.append("L");
    //             moveForward();
    //             path.append("F");
    //         } else {
    //             turnAround();
    //             path.append("RR");
    //             moveForward();
    //             path.append("F");
    //         }
    //     }
        return path.toString();
    }

    @Override
    public void moveForward(){

    }

    @Override
    public void turnRight(){

    }

    @Override
    public void turnLeft(){

    }

    // private void markCurrent() {
    //     // Update to visited of the current cell
    // }

    // private boolean shouldMoveForward() {
        
    // }

    // private boolean shouldTurnRight() {
        
    // }

    // private boolean shouldTurnLeft() {
        
    // }


    // private boolean exit() {
    // }
}
