package frontend;

import Exceptions.InvalidPuzzleException;
import backend.PuzzleSolver;
import model.Node;
import model.NodeType;
import model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditPanel extends JPanel {

    private Puzzle puzzle;
    private PuzzlePanel pp;
    private PuzzleSolver ps;
    private int cont = 0;

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
                puzzle = pp.getPuzzle();
                puzzle.setCell(cont/puzzle.getWidth(),cont%puzzle.getWidth(), NodeType.Wall);
                cont++;
                //pp.setPuzzle(puzzle);
            }
        });

        add(wall);
    }

    private void initializePath() {

        path = new JButton("Add Path");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puzzle = pp.getPuzzle();
                puzzle.setCell(cont/puzzle.getWidth(),cont%puzzle.getWidth(), NodeType.Path);
                cont++;
//                pp.setPuzzle(puzzle);
            }
        });

        add(path);
    }

    private void initializeStart() {

        start = new JButton("Add Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puzzle = pp.getPuzzle();
                puzzle.setCell(cont/puzzle.getWidth(),cont%puzzle.getWidth(), NodeType.Start);
                cont++;
//                pp.setPuzzle(puzzle);

            }
        });
        add(start);
    }

    private void initializeEnd() {

        end = new JButton("Add End");
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puzzle = pp.getPuzzle();
                puzzle.setCell(cont/puzzle.getWidth(),cont%puzzle.getWidth(), NodeType.End);
                cont++;
//                pp.setPuzzle(puzzle);
            }
        });

        add(end);
    }

    private void done(){
        if (cont > (puzzle.getWidth()+1)*(puzzle.getHeight()+1)){
            //solution
            try {

                List<Node> solution = ps.hSolvePuzzle(puzzle);
                System.out.println(solution);
            } catch (InvalidPuzzleException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}
