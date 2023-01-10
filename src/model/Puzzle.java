package model;


import frontend.PuzzlePanel;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Puzzle is a 2d array of cells
public class Puzzle {

//    private List<List<Node>> puzzle;
    private Node root;
    private int width;
    private int height;
    private int side;

    private Node start;
    private List<Node> end;

    // row : in which row
    // col : in which column

    /**
     * Initialize puzzle such that every node is a path
     *
     * @param width  width of the puzzle
     * @param height height of the puzzle
     */
    public Puzzle(int width, int height) {
        this.width = width;
        this.height = height;
        root = new Node(NodeType.Start);
        root.row = 0;
        root.col = 0;
        int canvasSide = PuzzlePanel.WIDTH - 2 * PuzzlePanel.OFFSET;
        if (width > height) {
            side = canvasSide/width;
        } else {
            side = canvasSide/height;
        }
        start = root;
        end = new ArrayList<>();
        initializePuzzle(width, height);
    }

    /**
     * Helper function for initialzePuzzle, initialize one row of the puzzle
     *
     * @param width number of nodes in a row
     * @param node root
     */
    private void initializeOneRow(int width, Node node, int row, int currCol) {
        if (currCol >= width) {
            return;
        }
        Node rNode = new Node(NodeType.Path);
        rNode.setLocation(row, currCol);
        node.right = rNode;
        rNode.left = node;
        if (node.top != null) {
            rNode.top = node.top.right;
            node.top.right.bot = rNode;
        }
        initializeOneRow(width, node.right, row, currCol + 1);
    }

    /**
     * initialize puzzle so that all nodes have the type PATH
     *
     * @param width number of columns
     * @param height number of rows
     */
    private void initializePuzzle(int width, int height) {
        Node currNode = root;
        initializeOneRow(width, currNode, 0, 1);
        for (int row = 1; row < height; row++) {
            Node bNode = new Node(NodeType.Path);
            bNode.setLocation(row, 0);
            currNode.bot = bNode;
            bNode.top = currNode;
            initializeOneRow(width, bNode, row, 1);
            currNode = currNode.bot;
        }
    }

    /**
     * Set the cell at (x,y) as newType
     * NOTE: this function uses 0-index
     *
     * @param row      number of row
     * @param col      number of col1
     * @param newType the new cell to be set as
     */
    public void setCell(int row, int col, NodeType newType) {
        Node node = root;
        for (int r = 0; r < row; r++) {
            node = node.bot;
        }
        for (int c = 0; c < col; c++) {
            node = node.right;
        }
        node.type = newType;
        if (newType == NodeType.Start) {
            start.setType(NodeType.Path);
            start = node;
        } else if (newType == NodeType.End) {
            end.add(node);
        }
    }

    /**
     * Check if this puzzle is a valid one
     * To be considered as valid, a puzzle must have:
     * exactly 1 start and
     * at least 1 end
     *
     * @return true if the puzzle is valid; false otherwise
     */
    public boolean isValid() {
        return (start != null) && (end.size() > 0);
    }

    /**
     * get the node at (row, col)
     * NOTE: this function uses 0-index
     *
     * @param row number of row
     * @param col number of col
     * @return node at (row, col)
     */
    public Node getNode(int row, int col) {
        Node node = root;
        for (int r = 0; r < row; r++) {
            node = node.bot;
        }
        for (int c = 0; c < col; c++) {
            node = node.right;
        }
        return node;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Node> getEnd() {
        return end;
    }

    public Node getStart() {
        return start;
    }

    /**
     * Write this puzzle to the file
     *
     * @param path the path of file to be written into
     * @return true if success; false otherwise
     */
    public boolean writeToFile(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (int row = 0; row <height ; row++) {

                for (int col = 0; col <width ; col++) {
                    switch (getNode(row, col).getType()) {
                        case Path:
                            writer.write(' ');
                            break;
                        case Wall:
                            writer.write('-');
                            break;
                        case End:
                            writer.write('e');
                            break;
                        case Start:
                            writer.write('s');
                            break;
                    }

                }
                if (row != height - 1) {
                    writer.write("\n");
                }
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Helper function for draw.
     * @param g canvas
     * @param node current node to draw
     * @param visited node that has been drawn
     * @param side length of side for each node
     */
    private void drawHelper(Graphics g, Node node, List<Node> visited, int side) {
        if (visited.size() == (width * height)) {
            return;
        }
        visited.add(node);
        node.draw(g, side);
        if ((node.top != null) && (!visited.contains(node.top))) {
            drawHelper(g, node.top, visited, side);
        }
        if ((node.bot != null) && (!visited.contains(node.bot))) {
            drawHelper(g, node.bot, visited, side);
        }
        if ((node.left != null) && (!visited.contains(node.left))) {
            drawHelper(g, node.left, visited, side);
        }
        if ((node.right != null) && (!visited.contains(node.right))) {
            drawHelper(g, node.right, visited, side);
        }
    }

    private void drawBorders(Graphics g, int length, int width, int height) {
        Color backgroundColor = g.getColor();
        int offset = PuzzlePanel.OFFSET;
        g.setColor(Color.BLACK);
        // draw top border
        g.fillRect(offset - length, offset - length, width + 2 * length, length);
        // draw left border
        g.fillRect(offset - length, offset - length, length, height + 2 * length);
        // draw right border
        g.fillRect(offset + width, offset - length, length, height + 2 * length);
        // draw bottom border
        g.fillRect(offset - length, offset + height, width + 2 * length, length);
        g.setColor(backgroundColor);
    }

    /**
     * draw puzzle on canvas
     * @param g canvas
     */
    public void draw(Graphics g) {
//        int canvasSide = PuzzlePanel.WIDTH - 2 * PuzzlePanel.OFFSET;
//        if (width > height) {
//            side = canvasSide/width;
//        } else {
//            side = canvasSide/height;
//        }
        drawHelper(g, root, new ArrayList<>(), side);
        int borderWidth = side/5;
        int canvasWidth = side * width;
        int canvasHeight = side * height;
        drawBorders(g, borderWidth, canvasWidth, canvasHeight);
    }

    public void printSolution(List<Node> solution) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Node node = getNode(row, col);
                if (solution.contains(node)) {
                    System.out.print("-");
                } else {
                    System.out.print("0");
                }
            }
            System.out.print("\n");
        }
    }

    public int getSide() {
        return side;
    }


}
