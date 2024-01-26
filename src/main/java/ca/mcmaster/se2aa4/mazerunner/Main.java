package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.Level;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);

        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);
        
        Option pathOption = new Option("p", "path", true, "Path to validate");
        pathOption.setRequired(false);
        options.addOption(pathOption);

        Option methodOption = new Option("m", "method", true, "Algorithm for path finding (tremaux, righthand)")
        pathOption.setRequired(false);
        options.addOption(methodOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        logger.info("** Starting Maze Runner");
        try {
            cmd = parser.parse(options, args);

            String mazeMethod = "righthand"; // Default method
            if (cmd.hasOption("method")) {
                mazeMethod = cmd.getOptionValue("method");
            }

            if (cmd.hasOption("i")) {
                String inputFilePath = cmd.getOptionValue("i");
                logger.info("**** Reading the maze from file " + inputFilePath);

                MazeReader mazeReader = new MazeReader(inputFilePath);
                char[][] maze = mazeReader.getMaze();

                // // Logging MazeReader
                // logger.info("**** Testing MazeReader.java ****");
                // for (char[] row : maze) {
                //     for (char cell : row) {
                //         logger.info(cell == '0' ? "PASS " : "WALL ");
                //     }
                //     logger.info(System.lineSeparator());
                // }
                // logger.info("**** Testing Ended of MazeRunner.java ****");

                if (cmd.hasOption("p")) {
                    // Path validation logic
                } else {
                    // Path finding logic
                    if (mazeMethod.equals("tremaux")) {
                        // Tremaux logic
                    } else {
                        // Righthand logic
                    }
                }

                // // OG Logger
                // BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                // String line;
                // while ((line = reader.readLine()) != null) {
                //     for (int idx = 0; idx < line.length(); idx++) {
                //         if (line.charAt(idx) == '#') {
                //             logger.trace("WALL ");
                //         } else if (line.charAt(idx) == ' ') {
                //             logger.trace("PASS ");
                //         }
                //     }
                //     logger.trace(System.lineSeparator());
                // }
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}