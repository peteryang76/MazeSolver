package frontend;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleSolverApp extends JFrame {

    private Puzzle puzzle;
    private static final int INTERVAL = 20;

    private MenuPanel mp;
    private PuzzlePanel pp;
    private Timer t;

    public PuzzleSolverApp() {
        super("Puzzle Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        puzzle = new Puzzle(4, 5);
        pp = new PuzzlePanel(puzzle);
        mp = new MenuPanel(puzzle, pp);
        add(mp, BorderLayout.NORTH);
        add(pp, BorderLayout.CENTER);

        pack();
        setVisible(true);
        addTimer();
        t.start();


    }

    private void addTimer() {
        t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pack();
                setVisible(true);
            }
        });
    }
}
