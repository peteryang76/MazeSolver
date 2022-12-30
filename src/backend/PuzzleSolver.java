package backend;

import Exceptions.InvalidPuzzleException;
import model.Node;
import model.NodeType;
import model.Puzzle;

import java.util.*;

public class PuzzleSolver {

    private static final int INFINITY = 100;

    public PuzzleSolver() {

    }

    public List<Node> hSolvePuzzle(Puzzle p) throws InvalidPuzzleException {
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
        List<Node> ends = p.getEnd();
        for (int row = 0; row < p.getHeight(); row++) {
            for (int col = 0; col < p.getWidth(); col++) {
                Node node = p.getNode(row, col);
                if (node.getType() == NodeType.Wall) {
                    node.setHeuristic(INFINITY);
                    continue;
                }
                int minH = INFINITY;
                for (Node end : ends) {
                    int e_row = end.getLocation()[0];
                    int e_col = end.getLocation()[1];
                    int h = Math.abs(e_row - row) + Math.abs(e_col - col);
                    if (h < minH) {
                        minH = h;
                    }
                }
                node.setHeuristic(minH);
            }
        }
    }

    private List<Node> findSolution(Puzzle p) {
        Node start = p.getStart();
        List<Node> solution = new ArrayList<>();
        List<Node> visited = new ArrayList<>();
        return findSolutionHelper(p, start, solution, visited);
    }

    private List<Node> findSolutionHelper(Puzzle p, Node currNode, List<Node> solution, List<Node> visited) {
        if (currNode.getH() == 0) {
            solution.add(currNode);
            return solution;
        }
        Map<String, Double> map = new HashMap<>();
        double leftH = getNeighbourHeuristic("Left", p, currNode);
        double rightH = getNeighbourHeuristic("Right", p, currNode);
        double topH = getNeighbourHeuristic("Top", p, currNode);
        double bottomH = getNeighbourHeuristic("Bottom", p, currNode);
        map.put("Left", leftH);
        map.put("Right", rightH);
        map.put("Top", topH);
        map.put("Bottom", bottomH);
        List<String> dirs = sortH(leftH, rightH, topH, bottomH);
        String next;
        Node nextNode;
        for (int i = 0; i < dirs.size(); i++) {
            double h_val = map.get(dirs.get(i));
            if (h_val != INFINITY) {
                next = dirs.get(i);
                nextNode = getNeighbourCell(next, p, currNode);
                visited.add(currNode);
                if (!visited.contains(nextNode)) {
                    List<Node> sol = findSolutionHelper(p, nextNode, solution, visited);
                    if (sol != null) {
                        solution.add(currNode);
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

    private double getNeighbourHeuristic(String dir, Puzzle p, Node currNode) {
        Node neighbour = getNeighbourCell(dir, p, currNode);
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
     * @param currNode current cell
     * @return the neighbour cell in the direction specified
     */
    private Node getNeighbourCell(String dir, Puzzle p, Node currNode) {
        int height = p.getHeight();
        int width = p.getWidth();
        int currRow = currNode.getLocation()[0];
        int currCol = currNode.getLocation()[1];
        switch (dir) {
            case "Left":
                if (currCol - 1 >= 0) {
                    return p.getNode(currRow, currCol - 1);
                } else {
                    return null;
                }
            case "Right":
                if (currCol + 1 < width) {
                    return p.getNode(currRow, currCol + 1);
                } else {
                    return null;
                }
            case "Top":
                if (currRow - 1 >= 0) {
                    return p.getNode(currRow - 1, currCol);
                } else {
                    return null;
                }
            case "Bottom":
                if (currRow + 1 < height) {
                    return p.getNode(currRow + 1, currCol);
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
                System.out.print(p.getNode(row, col).getH() + "   ");
            }
            System.out.print("\n");
        }
    }
}
