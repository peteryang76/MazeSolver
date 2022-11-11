package model;

import Exceptions.InvalidFileException;

import java.util.List;

// Puzzle is a 2d array of cells
public class Puzzle {
//    TODO: implement class

    List<List<Cell>> puzzle;
    int width;
    int height;

    /**
     * Initialize puzzle such that every cell is a path
     *
     * @param width width of the puzzle
     * @param height height of the puzzle
     */
    public Puzzle(int width, int height) {
//        TODO: implement constructor
    }


    /**
     * Set the cell at (x,y) as new_cell
     *
     * @param x x index
     * @param y y index
     * @param new_cell the new cell to be set as
     */
    private void setCell(int x, int y, Cell new_cell) {
        //TODO:
    }

    /**
     * Check if this puzzle is a valid one
     * To be considered as valid, a puzzle must have:
     * at least 1 start and
     * at least 1 end
     *
     * @return true if the puzzle is valid; false otherwise
     */
    public boolean isValid() {
        //TODO:
        return false;
    }

    public Cell getCell(int x, int y) {
        //TODO:
        return null;
    }

    /**
     * Read from file and set this puzzle to the puzzle in the file.
     *
     * @param path the path to the file that will be read from.
     * @return true if success
     * @throws InvalidFileException if the path is invalid
     * or the file doesn't contain a puzzle
     */
    private boolean readFromFile(String path) {
        //TODO:
        return false;
    }

    /**
     * Write this puzzle to the file
     *
     * @param path the path of file to be written into
     * @return true if success; false otherwise
     */
    private boolean writeToFile(String path) {
        //TODO:
        return false;
    }
}
