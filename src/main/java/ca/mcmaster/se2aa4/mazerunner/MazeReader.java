package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeReader {

    private char[][] maze;

    // Taking the file path to maz.txt
    public MazeReader(String filePath) throws IOException {
        readMaze(filePath);
    }

    // Read maz.txt to make 2d array
    private void readMaze(String filePath) throws IOException {
        int width = 0;
        int height = 0;

        // Find dimension of array
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                height++;
                if (line.length() > width) {
                    width = line.length();
                }
            }
        }

        // Make maze array with dimension
        maze = new char[height][width];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    maze[row][col] = (line.charAt(col) == ' ') ? '0' : '1';
                }
                row++;
            }
        }
    }

    // Getter for 2d maze array
    public char[][] getMaze() {
        return maze;
    }
}