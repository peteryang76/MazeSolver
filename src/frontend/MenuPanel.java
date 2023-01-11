package frontend;

import Exceptions.InvalidFileException;
import backend.PuzzleSolver;
import model.Node;
import model.NodeType;
import model.Puzzle;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel that displays menus and actions
public class MenuPanel extends JPanel {

    private JButton save;
    private JButton load;
    private JButton solve;
    private JButton newPuzzle;

    private Puzzle puzzle;
    private Reader reader;
    private PuzzlePanel pp;

    private PuzzleSolver ps;
    
    public MenuPanel (PuzzlePanel pp) {
        this.puzzle = null;
        this.pp = pp;
        this.ps = new PuzzleSolver();
        reader = new Reader();
        setBackground(Color.LIGHT_GRAY);
        initialize();

    }

    /**
     * Initialize components for this panel
     */
    private void initialize() {
        initializeLoad();
        initializeSave();
        initializeSolve();
        initializeNewPuzzle();
    }

    /**
     * Initialize load button
     */
    private void initializeLoad() {
        load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("What is the path to the file?");
                try {
                    puzzle = reader.readFromFile(path);
                    // update puzzle panel so that the new puzzle is displayed
                    pp.setPuzzle(puzzle);
                } catch (InvalidFileException invalidFileException) {
                    JOptionPane.showConfirmDialog(load, invalidFileException.getMessage());
                }
            }
        });
        add(load);
    }

    /**
     * Initialize Save Button
     */
    private void initializeSave() {
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("Want is the path save the file?");
                puzzle.writeToFile(path);
            }
        });
        add(save);
    }



    /** may be better if there is a corresponding method in the PuzzlePanel
     * Initialize Solve Button
     */
    private void initializeSolve() {
        solve = new JButton("Solve");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {

//                    List<Node> solution = ps.hSolvePuzzle(puzzle);
////                    pp.setSolution(solution);
//                    Node sol = new Node(NodeType.Solution);
//                    sol.setLocation(2, 2);
//                    sol.bot = new Node(NodeType.End);
//                    pp.setSolution(sol);
//                } catch (InvalidPuzzleException ex) {
//                    throw new RuntimeException(ex);
//                }
                Node sol = new Node(NodeType.Solution);
                sol.setLocation(2, 2);
                sol.bot = new Node(NodeType.End);
                pp.setSolution(sol);

            }
        });
        add(solve);
    }


    /**
     * Initialize new puzzle button
     */
    private void initializeNewPuzzle() {
        newPuzzle = new JButton("New");
        newPuzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int col = Integer.parseInt(JOptionPane.showInputDialog("What is the width of the new puzzle?"));
                int row = Integer.parseInt(JOptionPane.showInputDialog("What is the height of the new puzzle?"));
                puzzle = new Puzzle(col, row);
                pp.setPuzzle(puzzle);
            }
        });
        add(newPuzzle);
    }

    /**
     * This is only for testing purpose
     * DO NOT USE THIS IN THE FINAL PROJECT!!!
     * @param puzzle the puzzle to be set as
     */
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;

    }
}
