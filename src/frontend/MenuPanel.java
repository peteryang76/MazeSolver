package frontend;

import Exceptions.InvalidFileException;
import Exceptions.InvalidPuzzleException;
import model.Node;
import model.Puzzle;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import backend.PuzzleSolver;

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
    
    public MenuPanel(Puzzle puzzle, PuzzlePanel pp) {
        this.puzzle = puzzle;
        this.pp = pp;
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
                    pp.update();
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
                //String path = JOptionPane.showInputDialog("Want to solve the puzzle?");
                try {

                    List<Node> solution = ps.hSolvePuzzle(puzzle);
                    System.out.println(solution);
                } catch (InvalidPuzzleException ex) {
                    throw new RuntimeException(ex);
                }

                //puzzle = reader.readFromFile(path);
            }
        });
        add(solve);
    }


    /**? trying to load a new puzzle
     * Initialize new puzzle button
     */
    private void initializeNewPuzzle() {
        newPuzzle = new JButton("New");
        newPuzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puzzle = null;
                String path = JOptionPane.showInputDialog("What is the path to the file of the new puzzle?");
                try {
                    puzzle = reader.readFromFile(path);
                } catch (InvalidFileException invalidFileException) {
                    JOptionPane.showConfirmDialog(load, invalidFileException.getMessage());
                }
            }
        });
        add(newPuzzle);
    }
}
