package frontend;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPanel extends JPanel {

    private Puzzle puzzle;
    private PuzzlePanel pp;

    private JButton wall, path, start, end;

    public EditPanel(PuzzlePanel pp) {
        this.puzzle = null;
        setBackground(Color.LIGHT_GRAY);
        this.pp = pp;
        initialize();
    }

    private void initialize() {
        initializeWall();
        initializePath();
        initializeStart();
        initializeEnd();
    }

    private void initializeWall() {
        wall = new JButton("Add Wall");
        wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void initializePath() {

    }

    private void initializeStart() {

    }

    private void initializeEnd() {

    }
}
