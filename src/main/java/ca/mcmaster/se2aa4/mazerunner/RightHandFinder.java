package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandFinder implements MazeNav {

    private static final Logger logger = LogManager.getLogger();

    private char[][] maze;
    private int currX, currY;
    private Direction currDir;

    public RightHandFinder(char[][] maze) {
        this.maze = maze;
        this.currX = 0;
        this.currY = findEntry();
        this.currDir = Direction.EAST; // Assume entry is on West border
    }

    private int findEntry() throws IllegalStateException {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == '0') { // Find path on West
                return i;
            }
        }
        logger.trace("Error: Entry point not found");
        throw new IllegalStateException("NoPathFound"); // Entry is all wall D:
    }

    @Override
    public String factorizePath(String path) {
        StringBuilder factorizedPath = new StringBuilder();
        int count = 1; // Init count for path chars

        for (int i = 1; i < path.length(); i++){
            if (path.charAt(i) == path.charAt(i - 1)){ 
                count++; // Keep count of repeat char
            } else {
                if (count > 1) {
                    factorizedPath.append(count);
                }
                factorizedPath.append(path.charAt(i - 1));

                count = 1; // Reset count
            }
        }
        // Last char of path logic
        if (count > 1) {
            factorizedPath.append(count);
        }
        factorizedPath.append(path.charAt(path.length() - 1));

        return factorizedPath.toString();
    }

    @Override
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

    @Override
    public void turnRight() {        // Turning to the right of current direction
        currDir = DirectionUtility.turnRight(currDir);
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

    @Override
    public void turnLeft() {              // Opposite of turnRight!
        currDir = DirectionUtility.turnLeft(currDir);
    }

    @Override
    public void moveForward() {
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
