package ca.mcmaster.se2aa4.mazerunner;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

public class RightHandFinder {

    // private static final Logger logger = LogManager.getLogger();

    private enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    private char[][] maze;
    private int currX, currY;
    private Direction currDir;

    public RightHandFinder(char[][] maze) {
        this.maze = maze;
        this.currX = 0;
        this.currY = findEntry();
        this.currDir = Direction.EAST; // Assume entry is on West border
    }

    private int findEntry() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == '0') { // Find path on West
                return i;
            }
        }
        return -1; // Entry is all wall D:
    }

    public String findPath() {

        StringBuilder path = new StringBuilder();
        // int stepCounter = 0;

        while (!exit()) {
            // logger.trace("Step: " + stepCounter + ", Current Pos: (X=" + currX + ", Y=" + currY
            //         + "), Direction: " + currDir);

            if (canMoveRight()) {
                turnRight();
                path.append("R");
                moveForward();
                path.append("F");
                // logger.trace("Turned Right and Moved Forward");
            } else if (canMoveForward()) {
                moveForward();
                path.append("F");
                // logger.trace("Moved Forward");
            } else if (canMoveLeft()) {
                turnLeft();
                path.append("L");
                moveForward();
                path.append("F");
                // logger.trace("Turned Left and Moved Forward");
            } else {
                turnAround();
                path.append("RR");
                moveForward();
                path.append("F");
                // logger.trace("Turned Around and Moved Forward");
            }
            // stepCounter++;
        }
        return path.toString();
    }

    private boolean exit() {
        return currX == maze[0].length - 1; // Exit is on the East border
    }

    private boolean canMoveRight() {
        switch (currDir) {
            case NORTH:
                return currX + 1 < maze[0].length && maze[currY][currX + 1] == '0'; // Check East tile
            case EAST:
                return currY + 1 < maze.length && maze[currY + 1][currX] == '0'; // Check South tile
            case SOUTH:
                return currX - 1 >= 0 && maze[currY][currX - 1] == '0'; // Check West tile
            case WEST:
                return currY - 1 >= 0 && maze[currY - 1][currX] == '0'; // Check North tile
        }
        return false;
    }

    private boolean canMoveForward() {       // Checking tile ahead
        switch (currDir) {
            case NORTH:
                return currY - 1 >= 0 && maze[currY - 1][currX] == '0';
            case EAST:
                return currX + 1 < maze[0].length && maze[currY][currX + 1] == '0';
            case SOUTH:
                return currY + 1 < maze.length && maze[currY + 1][currX] == '0';
            case WEST:
                return currX - 1 >= 0 && maze[currY][currX - 1] == '0';
        }
        return false;
    }

    private void turnRight() {        // Turning to the right of current direction
        switch (currDir) {
            case NORTH:
                currDir = Direction.EAST;
                break;
            case EAST:
                currDir = Direction.SOUTH;
                break;
            case SOUTH:
                currDir = Direction.WEST;
                break;
            case WEST:
                currDir = Direction.NORTH;
                break;
        }
    }

    private boolean canMoveLeft() {
        switch (currDir) {
            case NORTH:
                return currX - 1 >= 0 && maze[currY][currX - 1] == '0';
            case EAST:
                return currY - 1 >= 0 && maze[currY - 1][currX] == '0';
            case SOUTH:
                return currX + 1 < maze[0].length && maze[currY][currX + 1] == '0';
            case WEST:
                return currY + 1 < maze.length && maze[currY + 1][currX] == '0';
        }
        return false;
    }

    private void turnLeft() {              // Opposite of turnRight!
        switch (currDir) {
            case NORTH:
                currDir = Direction.WEST;
                break;
            case WEST:
                currDir = Direction.SOUTH;
                break;
            case SOUTH:
                currDir = Direction.EAST;
                break;
            case EAST:
                currDir = Direction.NORTH;
                break;
        }
    }

    private void moveForward() {
        switch (currDir) {
            case NORTH:
                currY--;
                break;
            case EAST:
                currX++;
                break;
            case SOUTH:
                currY++;
                break;
            case WEST:
                currX--;
                break;
        }
    }

    private void turnAround() {
        turnRight();
        turnRight();
    }
}
