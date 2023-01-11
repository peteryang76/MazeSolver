package model;

import frontend.PuzzlePanel;

import java.awt.*;

public class Node {

    double heuristic;
    public NodeType type;

    public Node left;
    public Node right;
    public Node top;
    public Node bot;

    // this is 0-index
    int row;
    int col;

    private static final Color backgroundColor = new Color(255,235,175);

    public Node(NodeType type){
        this.type = type;
        left = null;
        right = null;
        top = null;
        bot = null;
        heuristic = -1;

        row = -1;
        col = -1;
    }

    /**
     * Set the this.type = type
     * @param newType the new type
     */
    public void setType(NodeType newType) {
        this.type = newType;
    }

    /**
     * Set this.heuristic = h
     * @param h the heuristic value for this cell
     */
    public void setHeuristic(int h) {
        this.heuristic = h;
    }

    public NodeType getType() {
        return this.type;
    }

    public double getH(){
        return heuristic;
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int[] getLocation() {
        return new int[] {row, col};
    }

    /**
     * Helper function that draw walls
     * @param g the canvas
     * @param side length of side of each node
     */
    private void drawWall(Graphics g, int side) {
        g.setColor(Color.BLACK);
        int offset = PuzzlePanel.OFFSET;
        int length = side/5;
        if (top == null || (top.type == NodeType.Wall)) {
            drawLine(g, side, offset, length, "Top");
        }
        if (bot == null || (bot.type == NodeType.Wall)) {
            drawLine(g, side, offset, length, "Bot");
        }
        if (left == null || (left.type == NodeType.Wall)) {
            drawLine(g, side, offset, length, "Left");
        }
        if (right == null || (right.type == NodeType.Wall)) {
            drawLine(g, side, offset, length, "Right");
        }
        g.fillRect(offset + col * side + 2 * length, offset + row * side + 2 * length, length, length);

    }

    private void drawLine(Graphics g, int side, int offset, int length, String dir) {
        if (dir.equals("Top")) {
            g.fillRect(offset + col * side + 2 * length, offset + row * side, length, 3 * length);
        } else if (dir.equals("Bot")) {
            g.fillRect(offset + col * side + 2 * length, offset + row * side + 2 * length, length, 3 * length);
        } else if (dir.equals("Left")) {
            g.fillRect(offset + col * side, offset + row * side + 2 * length, 3 * length, length);
        } else if (dir.equals("Right")) {
            g.fillRect(offset + col * side + 2 * length, offset + row * side + 2 * length, 3 * length, length);
        }

    }

    /**
     * draw each node according to its type and its neighbours
     * node are be drawn as squares
     * @param g the canvas to draw on
     * @param side the length of side of each node
     * NOTE: the row is y-axis and col is x-axis
     */
    public void draw(Graphics g, int side) {
        int offset = PuzzlePanel.OFFSET;
        g.setColor(backgroundColor);
        g.fillRect(offset + col * side, offset + row * side, side, side);
        if (type == NodeType.Start) {
            g.setColor(Color.red);
            g.fillOval(offset + col * side, offset + row * side, side, side);
            g.setColor(backgroundColor);
        } else if (type == NodeType.End) {
            g.setColor(Color.BLUE);
            g.fillRect(offset + col * side, offset + row * side, side, side);
        } else if (type == NodeType.Wall) {
            drawWall(g, side);
        } else if (type == NodeType.Solution) {
            g.setColor(Color.RED);
            g.fillRect(offset + col * side, offset + row * side, side, side);
        }
    }

}
