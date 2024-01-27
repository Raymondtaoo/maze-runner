package ca.mcmaster.se2aa4.mazerunner;

public interface MazeNav {
    String findPath();
    void moveForward();
    void turnRight();
    void turnLeft();
}
