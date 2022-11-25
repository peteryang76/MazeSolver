package model;

import Exceptions.InvalidFileException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Puzzle is a 2d array of cells
public class Puzzle {

    private List<List<Cell>> puzzle;
    private int width;
    private int height;

    private Cell start;
    private List<Cell> end;

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
        start = null;
        end = new ArrayList<>();
        puzzle = new ArrayList<>();

        for (int row = 0; row < height; row++) {
            List<Cell> line = new ArrayList<>();
            for (int col = 0; col < width; col++) {
                line.add(new Cell(row, col, -1, CellType.Path));
            }
            puzzle.add(line);
        }
    }


    /**
     * Set the cell at (x,y) as newType
     *
     * @param row      number of row
     * @param col      number of col1
     * @param newType the new cell to be set as
     */
    public void setCell(int row, int col, CellType newType) {
        Cell cell = puzzle.get(row).get(col);
        if (newType == CellType.Start) {
            start = cell;
        } else if (newType == CellType.End) {
            end.add(cell);
        }
        cell.setType(newType);
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
        return (start != null) && (end.size() > 0);
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

    public List<Cell> getEnd() {
        return end;
    }

    public Cell getStart() {
        return start;
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
            for (int row = 0; row <height ; row++) {

                for (int col = 0; col <width ; col++) {
                    switch (getCell(row, col).getType()) {
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
                if (row != height - 1) {
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
                CellType type = this.getCell(row, col).getType();
                switch (type) {
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
            System.out.print("\n");
        }
    }

    public void printSolution(List<Cell> solution) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = puzzle.get(row).get(col);
                if (solution.contains(cell)) {
                    System.out.print("-");
                } else {
                    System.out.print("0");
                }
            }
            System.out.print("\n");
        }
    }
}
