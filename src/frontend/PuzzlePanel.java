package frontend;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;

// Panel that displays puzzle
public class PuzzlePanel extends JPanel {

    private Puzzle puzzle;
    private JTable puzzleTable;

    // Please set WIDTH = HEIGHT = 500 + 2 * OFFSET
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int OFFSET = 50;

    public PuzzlePanel(Puzzle puzzle) {
        this.puzzle = puzzle;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawPuzzle(g);
    }

    private void drawPuzzle(Graphics g) {
        if (puzzle == null) {
            return;
        }
        puzzle.draw(g);
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle(){
        return this.puzzle;
    }
}
