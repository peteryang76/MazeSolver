package model;

import Exceptions.InvalidFileException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Puzzle is a 2d array of cells
public class Puzzle {
//    TODO: implement class

    List<List<Cell>> puzzle; // maybe change into a 3D array?
    int width;
    int height;

    // row : in which row
    //col  : in which column

    /**
     * Initialize puzzle such that every cell is a path
     *
     * @param width  width of the puzzle
     * @param height height of the puzzle
     */
    public Puzzle(int width, int height) {
        this.width = width;
        this.height = height;
        puzzle = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            List<Cell> line = new ArrayList<Cell>();
            for (int j = 0; j < width; j++) {
                line.add(Cell.Path);
            }
            puzzle.add(line);
        }
    }


    /**
     * Set the cell at (x,y) as new_cell
     *
     * @param row      number of row
     * @param col      number of col1
     * @param new_cell the new cell to be set as
     */
    public void setCell(int row, int col, Cell new_cell) {
        puzzle.get(row).set(col, new_cell);

    }

    /**
     * Check if this puzzle is a valid one
     * To be considered as valid, a puzzle must have:
     * exactly 1 start and
     * at least 1 end
     *
     * @return true if the puzzle is valid; false otherwise
     */
    public boolean isValid() {
        boolean hasEnd = false;
        boolean hasStart = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = puzzle.get(j).get(i);
                hasEnd = (cell == Cell.End);
                if (cell == Cell.Start) {
                    if (hasStart) {
                        return false;
                    } else {
                        hasStart = true;
                    }
                }
            }
        }
        return hasStart && hasEnd;
    }

    /**
     * @param row number of row
     * @param col number of col
     * @return cell at (row, col)
     */
    public Cell getCell(int row, int col) {
        return puzzle.get(row).get(col);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Write this puzzle to the file
     *
     * @param path the path of file to be written into
     * @return true if success; false otherwise
     */
    public boolean writeToFile(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i <height ; i++) {

                for (int j = 0; j <width ; j++) {
                    switch (getCell(i, j)) {
                        case Path:
                            writer.write(' ');
                            break;
                        case Wall:
                            writer.write('-');
                            break;
                        case End:
                            writer.write('e');
                            break;
                        case Start:
                            writer.write('s');
                            break;
                    }

                }
                if (i != height - 1) {
                    writer.write("\n");
                }

            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void printPuzzle() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = this.getCell(row, col);
                switch (cell) {
                    case Path:
                        System.out.print(' ');
                        break;
                    case Wall:
                        System.out.print('-');
                        break;
                    case End:
                        System.out.print('e');
                        break;
                    case Start:
                        System.out.print('s');
                        break;
                }

            }
            System.out.println("\n");
        }
    }
}
