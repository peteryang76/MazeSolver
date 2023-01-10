import Exceptions.InvalidFileException;
import Exceptions.InvalidPuzzleException;
import backend.PuzzleSolver;
import model.Node;
import model.NodeType;
import model.Puzzle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.IOException;
import java.util.List;

//import static org.junit.Assert.fail;

public class TestPuzzleSolver {

    private PuzzleSolver ps;
    private Puzzle p;

    private final int HEIGHT = 6;
    private final int WIDTH = 5;

    @BeforeEach
    void setUp() {
        ps = new PuzzleSolver();
        p = new Puzzle(WIDTH, HEIGHT);
        p.setCell(0, 0, NodeType.Start);
        p.setCell(HEIGHT - 1, WIDTH - 1, NodeType.End);

    }

    @Test
    void testConstructHeuristicValues() {
        try {
            List<Node> solution = ps.hSolvePuzzle(p);
            ps.printHeuristic(p);
//            p.printSolution(solution);
        } catch (InvalidPuzzleException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSolvePuzzle() {
        Reader reader = new Reader();
        try {
            p = reader.readFromFile("data/testSimple.txt");
            List<Node> solution = ps.hSolvePuzzle(p);
//            ps.printHeuristic(p);
            p.printSolution(solution);
        } catch (InvalidFileException e) {
            e.printStackTrace();
        } catch (InvalidPuzzleException e) {
            ///fail();
        }
    }
    @Test
    void testLoad() throws IOException, InvalidFileException {
        Reader reader = new Reader();
        reader.readFromFile("D://Desktop/test.txt");



    }
}
