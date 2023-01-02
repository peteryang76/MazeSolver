package frontend;

import model.Puzzle;

import javax.swing.*;

// Panel that displays puzzle
public class PuzzlePanel extends JPanel {

    private Puzzle puzzle;
    private JTable puzzleTable;

    public PuzzlePanel(Puzzle puzzle) {
        this.puzzle = puzzle;
        displayPuzzle();
    }

    private void displayPuzzle() {
        if (puzzle == null) {
            return;
        }
        int row = puzzle.getNumRow();
        int col = puzzle.getNumCol();
        System.out.println("\nthis is puzzle: {" +row + ", " + col + "}");
        puzzleTable = new JTable(row, col);
        add(puzzleTable);
    }

    public void update() {
        displayPuzzle();
    }
}
