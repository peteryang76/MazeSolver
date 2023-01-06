package frontend;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;

// Panel that displays puzzle
public class PuzzlePanel extends JPanel {

    private Puzzle puzzle;
    private JTable puzzleTable;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public PuzzlePanel(Puzzle puzzle) {
        this.puzzle = puzzle;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        displayPuzzle();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawPuzzle(g);
    }

    private void drawPuzzle(Graphics g) {
        puzzle.draw(g);
    }

    private void displayPuzzle() {
        if (puzzle == null) {
            return;
        }
        int row = puzzle.getNumRow();
        int col = puzzle.getNumCol();
        removeAll();
        puzzleTable = new JTable(row, col);
        add(puzzleTable);
    }

    public void update() {
        displayPuzzle();
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
}
