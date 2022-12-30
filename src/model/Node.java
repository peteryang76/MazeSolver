package model;

public class Node {
    double heuristic;
    NodeType type;

    Node left;
    Node right;
    Node top;
    Node bot;

    int row;
    int col;

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

}
