package frontend;

import model.NodeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPanel extends JPanel {

    private PuzzlePanel pp;

    private JButton wall, start, end;

    public EditPanel(PuzzlePanel pp) {
        setBackground(Color.LIGHT_GRAY);
        this.pp = pp;
        initialize();
    }

    /**
     * Initialize components
     */
    private void initialize() {
        initializeWall();
        initializeStart();
        initializeEnd();
    }

    /**
     * Initialize Wall button
     */
    private void initializeWall() {

        wall = new JButton("Add Wall");
        wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pp.setCursorState(NodeType.Wall);
            }
        });
        add(wall);
    }

    private void initializeStart() {

        start = new JButton("Add Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pp.setCursorState(NodeType.Start);

            }
        });
        add(start);
    }

    private void initializeEnd() {

        end = new JButton("Add End");
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pp.setCursorState(NodeType.End);
            }
        });

        add(end);
    }

}
