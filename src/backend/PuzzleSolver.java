package backend;

import Exceptions.InvalidPuzzleException;
import model.Cell;
import model.CellType;
import model.Puzzle;

import java.util.*;

public class PuzzleSolver {

    private static final int INFINITY = 100;

    public PuzzleSolver() {

    }

    public List<Cell> hSolvePuzzle(Puzzle p) throws InvalidPuzzleException {
        if (!p.isValid()) {
            throw new InvalidPuzzleException("The puzzle does not have a start or an end");
        }
        constructHeuristic(p);
        printHeuristic(p);
        return findSolution(p);
    }

    /**
     * Set heuristic value for each cell in the puzzle according to their distance to the nearest end
     * @param p the puzzle to be set
     */
    private void constructHeuristic(Puzzle p) {
        List<Cell> ends = p.getEnd();
        for (int row = 0; row < p.getHeight(); row++) {
            for (int col = 0; col < p.getWidth(); col++) {
                Cell cell = p.getCell(row, col);
                if (cell.getType() == CellType.Wall) {
                    cell.setHeuristic(INFINITY);
                    continue;
                }
                int minH = INFINITY;
                for (Cell end : ends) {
                    int e_row = end.getLocation()[0];
                    int e_col = end.getLocation()[1];
                    int h = Math.abs(e_row - row) + Math.abs(e_col - col);
                    if (h < minH) {
                        minH = h;
                    }
                }
                cell.setHeuristic(minH);
            }
        }
    }

    private List<Cell> findSolution(Puzzle p) {
        Cell start = p.getStart();
        List<Cell> solution = new ArrayList<>();
        List<Cell> visited = new ArrayList<>();
        return findSolutionHelper(p, start, solution, visited);
    }

    private List<Cell> findSolutionHelper(Puzzle p, Cell currCell, List<Cell> solution, List<Cell> visited) {
        if (currCell.getH() == 0) {
            solution.add(currCell);
            return solution;
        }
        Map<String, Double> map = new HashMap<>();
        double leftH = getNeighbourHeuristic("Left", p, currCell);
        double rightH = getNeighbourHeuristic("Right", p, currCell);
        double topH = getNeighbourHeuristic("Top", p, currCell);
        double bottomH = getNeighbourHeuristic("Bottom", p, currCell);
        map.put("Left", leftH);
        map.put("Right", rightH);
        map.put("Top", topH);
        map.put("Bottom", bottomH);
        List<String> dirs = sortH(leftH, rightH, topH, bottomH);
        String next;
        Cell nextCell;
        for (int i = 0; i < dirs.size(); i++) {
            double h_val = map.get(dirs.get(i));
            if (h_val != INFINITY) {
                next = dirs.get(i);
                nextCell = getNeighbourCell(next, p, currCell);
                visited.add(currCell);
                if (!visited.contains(nextCell)) {
                    List<Cell> sol = findSolutionHelper(p, nextCell, solution, visited);
                    if (sol != null) {
                        solution.add(currCell);
                        return solution;
                    }
                }

            }

        }
        return null;
    }

    private List<String> sortH(double left, double right, double top, double bottom) {
        double[] sorted = {left, right, top, bottom};
        List<String> dirs = new ArrayList<>(Arrays.asList("Left", "Right", "Top", "Bottom"));
        Arrays.sort(sorted);
        List<String> sortedDir = new ArrayList<>(Arrays.asList(null, null, null, null));
        for (int i = 0; i < 4; i++) {
            if (sorted[i] == left) {
                if (dirs.contains("Left")) {
                    sortedDir.set(i, "Left");
                    dirs.remove("Left");
                    continue;
                }
            }
            if (sorted[i] == right) {
                if (dirs.contains("Right")) {
                    sortedDir.set(i, "Right");
                    dirs.remove("Right");
                    continue;
                }
            }
            if (sorted[i] == top) {
                if (dirs.contains("Top")) {
                    sortedDir.set(i, "Top");
                    dirs.remove("Top");
                    continue;
                }
            }
            if (sorted[i] == bottom) {
                if (dirs.contains("Bottom")) {
                    sortedDir.set(i, "Bottom");
                    dirs.remove("Bottom");
                    continue;
                }
            }

        }
        return sortedDir;
    }

    private double getNeighbourHeuristic(String dir, Puzzle p, Cell currCell) {
        Cell neighbour = getNeighbourCell(dir, p, currCell);
        if (neighbour == null) {
            return INFINITY;
        } else {
            return neighbour.getH();
        }
    }

    /**
     * Return the neighbour cell in the direction specified
     * @param dir the direction of neighbour
     * @param p puzzle
     * @param currCell current cell
     * @return the neighbour cell in the direction specified
     */
    private Cell getNeighbourCell(String dir, Puzzle p, Cell currCell) {
        int height = p.getHeight();
        int width = p.getWidth();
        int currRow = currCell.getLocation()[0];
        int currCol = currCell.getLocation()[1];
        switch (dir) {
            case "Left":
                if (currCol - 1 >= 0) {
                    return p.getCell(currRow, currCol - 1);
                } else {
                    return null;
                }
            case "Right":
                if (currCol + 1 < width) {
                    return p.getCell(currRow, currCol + 1);
                } else {
                    return null;
                }
            case "Top":
                if (currRow - 1 >= 0) {
                    return p.getCell(currRow - 1, currCol);
                } else {
                    return null;
                }
            case "Bottom":
                if (currRow + 1 < height) {
                    return p.getCell(currRow + 1, currCol);
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    /**
     * print function for test purpose
     * @param p puzzle to be printed
     */
    public void printHeuristic(Puzzle p) {
        for (int row = 0; row < p.getHeight(); row++) {
            for (int col = 0; col < p.getWidth(); col++) {
                System.out.print(p.getCell(row, col).getH() + "   ");
            }
            System.out.print("\n");
        }
    }
}
