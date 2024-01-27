package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathValidator {

    private static final Logger logger = LogManager.getLogger();

    private char[][] maze;
    private int currX, currY;
    private Direction currDir;

    public PathValidator(char[][] maze) {
        this.maze = maze;
        this.currX = 0;
        this.currY = findEntry();
        this.currDir = Direction.EAST;
    }

    private void reset() {
        this.currX = 0;
        this.currY = findEntry();
        this.currDir = Direction.EAST;
    }

    private String reverseDir(String path) {
        return path.replace('R', 'X').replace('L', 'R').replace('X', 'L');
    }

    private int findEntry() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == '0') { // Find path on West
                return i;
            }
        }
        logger.trace("Error: Entry point not found");
        return -1;
    }

    public boolean validatePath(String path) {
        path = canonPath(path); // Make sure it's canonical form
        // logger.trace(path);

        reset(); 
        if (tryValidation(path)) {
            return true;
        }

        reset(); // Reset for test opposite start tile
        return tryValidation(reverseDir(path));
    }

    private boolean tryValidation(String path){
        for (char move : path.toCharArray()) {
            switch (move) {
                case 'F':
                    if (!canMoveForward())
                        return false;
                    moveForward();
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'L':
                    turnLeft();
                    break;
            }

            if (exit()) {
                return true;
            }
        }
        return false;
    }

    private String canonPath(String path) {
        StringBuilder toCanonPath = new StringBuilder();
        int count = 0;
        for (char ch : path.toCharArray()) {
            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            } else {
                for (int i = 0; i < (count == 0 ? 1 : count); i++) {
                    toCanonPath.append(ch);
                }
                count = 0;
            }
        }
        return toCanonPath.toString();
    }

    private boolean canMoveForward() {
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

    private void turnRight() {
        currDir = DirectionUtility.turnRight(currDir);
    }

    private void turnLeft() {
        currDir = DirectionUtility.turnLeft(currDir);
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

    private boolean exit() {
        return currX == maze[0].length - 1; // Exit on East border
    }

}
