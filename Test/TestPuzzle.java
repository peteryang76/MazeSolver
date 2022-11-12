import Exceptions.InvalidFileException;
import model.Cell;
import model.Puzzle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import static org.junit.jupiter.api.Assertions.*;

public class TestPuzzle {

    Puzzle puzzle;
    static final int x = 5;
    static final int y = 5;
    Reader reader;

    @BeforeEach
    void init() {
        puzzle = new Puzzle(x, y);
        reader = new Reader();
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

    @Test
    void testSetCell() {
        puzzle.setCell(2, 3, Cell.Start);
        assertEquals(Cell.Start, puzzle.getCell(2, 3));
        assertFalse(puzzle.isValid());
        puzzle.setCell(puzzle.getWidth() - 1, puzzle.getHeight() - 1, Cell.End);
        puzzle.printPuzzle();
        assertEquals(Cell.End, puzzle.getCell(puzzle.getWidth() - 1, puzzle.getHeight() - 1));
        assertTrue(puzzle.isValid());
    }

    @Test
    void testReadEmptyFile() {
        try {
            reader.readFromFile("./data/corrupted.txt");
            fail("Exception Un-thrown");
        } catch (InvalidFileException e) {

        }


    }

    @Test
    void testReadNormalFile(){
        try {
            puzzle = reader.readFromFile("./data/success.txt");
            puzzle.printPuzzle();
            assertTrue(puzzle.isValid());
            assertEquals(Cell.Start, puzzle.getCell(2,4));
            assertEquals(Cell.End,puzzle.getCell(3,5));
        }catch (InvalidFileException e){
            System.out.println(e.getMessage());
            fail("Unexpected exception");
        }
    }

    @Test
    void testReadFromInvalidPuzzle() {
        try {
            puzzle = reader.readFromFile("./data/NoValidPuzzle.txt");
            fail("No exception was thrown");
        }catch (InvalidFileException e){
        }
    }

    @Test
    void testWriteFileAllPath(){
        puzzle.writeToFile("./out/test1.txt");
    }

    @Test
    void testWriteFromFile(){
        try {
            puzzle = reader.readFromFile("./data/success.txt");
            puzzle.writeToFile("./out/test2.txt");

        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }


    }
}
