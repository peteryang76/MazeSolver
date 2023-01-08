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
    private EditPanel ep;
    private Timer t;

    public PuzzleSolverApp() {
        super("Puzzle Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        puzzle = null;
        pp = new PuzzlePanel(puzzle);
        mp = new MenuPanel(pp);
        ep = new EditPanel(pp);

        mp.setPuzzle(puzzle);

        add(mp, BorderLayout.NORTH);
        add(pp, BorderLayout.CENTER);
        add(ep, BorderLayout.SOUTH);

        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        t.start();


    }

    private void addTimer() {
        t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pp.repaint();
            }
        });
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}
