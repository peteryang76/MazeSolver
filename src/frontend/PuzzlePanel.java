package frontend;

import model.NodeType;
import model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Panel that displays puzzle
public class PuzzlePanel extends JPanel implements MouseListener {

    private Puzzle puzzle;
    private JTable puzzleTable;

    // Please set WIDTH = HEIGHT = 500 + 2 * OFFSET
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int OFFSET = 50;

    private NodeType cursorState;
    private int side;

    public PuzzlePanel(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.cursorState = null;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(this);
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

    public void setCursorState(NodeType state) {
        cursorState = state;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((puzzle != null) && (cursorState != null)) {
            int x = e.getX();
            int y = e.getY();
            int side = puzzle.getSide();
            int width = puzzle.getWidth();
            int height = puzzle.getHeight();
            if ((x >= OFFSET) && (y >= OFFSET) && (x <= width * side + OFFSET) && (y <= height * side + OFFSET)) {
                int row = (y - OFFSET)/side;
                int col = (x - OFFSET)/side;
                switch(cursorState) {
                    case Wall:
                        if (puzzle.getNode(row, col).getType() == NodeType.Wall) {
                            puzzle.setCell(row, col, NodeType.Path);
                        } else {
                            puzzle.setCell(row, col, NodeType.Wall);
                        }
                        break;
                    case Start:
                        puzzle.setCell(row, col, NodeType.Start);
                        break;
                    case End:
                        puzzle.setCell(row, col, NodeType.End);
                        break;

                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
