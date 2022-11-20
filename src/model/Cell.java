package model;

public class Cell {
    int row;
    int col;
    double heuristic;
    CellType type;


    public Cell(int row,int col,double h,CellType type){
        this.row = row;
        this.col = col;
        this.heuristic = h;
        this.type = type;
    }

    public int[][] getLocation(){
        return new int[row][col];
    }

    public double geth(){
        return heuristic;
    }

}
