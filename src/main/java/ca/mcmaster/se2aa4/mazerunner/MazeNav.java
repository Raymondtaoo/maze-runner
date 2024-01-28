package ca.mcmaster.se2aa4.mazerunner;

public interface MazeNav {
    String findPath();
    String factorizePath(String path);
    void moveForward();
    void turnRight();
    void turnLeft();
}
