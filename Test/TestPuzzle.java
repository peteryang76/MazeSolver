import model.Cell;
import model.Puzzle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPuzzle {

    Puzzle puzzle;
    static final int x = 5;
    static final int y = 5;
    @BeforeEach
    void init() {
        puzzle = new Puzzle(x, y);
    }

    @Test
    void testConstructor() {
        assertFalse(puzzle.isValid());
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                assertTrue(puzzle.getCell(i, j) == Cell.Path);
            }
        }
    }

}
