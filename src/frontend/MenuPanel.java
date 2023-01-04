package frontend;

import Exceptions.InvalidFileException;
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
        //TODO: add action listener
    }

    /**
     * Initialize Solve Button
     */
    private void initializeSolve() {
        solve = new JButton("Solve");
        //TODO: add action listener
    }

    /**
     * Initialize new puzzle button
     */
    private void initializeNewPuzzle() {
        newPuzzle = new JButton("New");
        //TODO: add action listener

    }
}
