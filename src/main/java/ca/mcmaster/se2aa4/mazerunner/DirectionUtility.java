package ca.mcmaster.se2aa4.mazerunner;

// Define directions
enum Direction {
    NORTH, EAST, SOUTH, WEST
}

public class DirectionUtility {
    public static Direction turnRight(Direction currDir) { // Turning to the right of current direction
        switch (currDir) {
            case NORTH:
                return Direction.EAST;
            case EAST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.WEST;
            case WEST:
                return Direction.NORTH;
        }
        throw new IllegalStateException("Unknown direction: " + currDir);
    }

    public static Direction turnLeft(Direction currDir) { // Turning to the left of current direction
        switch (currDir) {
            case NORTH:
                return Direction.WEST;
            case WEST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.EAST;
            case EAST:
                return Direction.NORTH;
        }
        throw new IllegalStateException("Unknown direction: " + currDir);
    }

    

}
