import Exceptions.InvalidFileException;
import model.NodeType;
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
                if (i != 0 || j != 0) {
                    assertTrue(puzzle.getNode(i, j).getType() == NodeType.Path);
                }

            }
        }
    }

    @Test
    void testSetCell() {
        puzzle.setCell(2, 3, NodeType.Start);
        assertEquals(NodeType.Start, puzzle.getNode(2, 3).getType());
        assertFalse(puzzle.isValid());
        puzzle.setCell(puzzle.getWidth() - 1, puzzle.getHeight() - 1, NodeType.End);
        puzzle.printPuzzle();
        assertEquals(NodeType.End, puzzle.getNode(puzzle.getWidth() - 1, puzzle.getHeight() - 1).getType());
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
            assertEquals(NodeType.Start, puzzle.getNode(2,4).getType());
            assertEquals(NodeType.End,puzzle.getNode(3,5).getType());
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
