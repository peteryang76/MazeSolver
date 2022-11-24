import Exceptions.InvalidPuzzleException;
import backend.PuzzleSolver;
import model.Cell;
import model.CellType;
import model.Puzzle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestPuzzleSolver {

    private PuzzleSolver ps;
    private Puzzle p;

    private final int HEIGHT = 6;
    private final int WIDTH = 5;

    @BeforeEach
    void setUp() {
        ps = new PuzzleSolver();
        p = new Puzzle(WIDTH, HEIGHT);
        p.setCell(0, 0, CellType.Start);
        p.setCell(HEIGHT - 1, WIDTH - 1, CellType.End);
    }

    @Test
    void testConstructHeuristicValues() {
        try {
            List<Cell> solution = ps.hSolvePuzzle(p);
            ps.printHeuristic(p);
            p.printSolution(solution);
        } catch (InvalidPuzzleException e) {
            e.printStackTrace();
        }

    }
}
