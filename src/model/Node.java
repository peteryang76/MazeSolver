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
     * draw each node according to its type and its neighbours
     * node are be drawn as squares
     * @param g the canvas to draw on
     * @param side the length of side of each node
     * NOTE: the row is y-axis and col is x-axis
     */
    public void draw(Graphics g, int side) {
        int y = PuzzlePanel.HEIGHT;
        g.setColor(backgroundColor);
        g.fillRect(col * side, row * side, side, side);
        if (type == NodeType.Start) {
            g.setColor(backgroundColor);
            g.fillRect(col * side, row * side, side, side);
            g.setColor(Color.red);
            g.fillOval(col * side, row * side, side, side);
            g.setColor(backgroundColor);
        }
    }

}
