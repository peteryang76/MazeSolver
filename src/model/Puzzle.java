package model;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Puzzle is a 2d array of cells
public class Puzzle {

//    private List<List<Node>> puzzle;
    private Node root;
    private int width;
    private int height;

    private Node start;
    private List<Node> end;

    // row : in which row
    //col  : in which column

    /**
     * Initialize puzzle such that every cell is a path
     *
     * @param width  width of the puzzle
     * @param height height of the puzzle
     */
    public Puzzle(int width, int height) {
        this.width = width;
        this.height = height;
        root = new Node(NodeType.Start);
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
        for (int row = 0; row < height; row++) {
            initializeOneRow(width, currNode, row, 0);
            Node bNode = new Node(NodeType.Path);
            bNode.setLocation(row + 1, 0);
            currNode.bot = bNode;
            bNode.top = currNode;
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

    public void printPuzzle() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                NodeType type = this.getNode(row, col).getType();
                switch (type) {
                    case Path:
                        System.out.print(' ');
                        break;
                    case Wall:
                        System.out.print('-');
                        break;
                    case End:
                        System.out.print('e');
                        break;
                    case Start:
                        System.out.print('s');
                        break;
                }

            }
            System.out.print("\n");
        }
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
}
