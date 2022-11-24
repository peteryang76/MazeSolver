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

    /**
     * Set the this.type = type
     * @param newType the new type
     */
    public void setType(CellType newType) {
        this.type = newType;
    }

    /**
     * Set this.heuristic = h
     * @param h the heuristic value for this cell
     */
    public void setHeuristic(int h) {
        this.heuristic = h;
    }

    public int[] getLocation(){
        return new int[] {row, col};
    }

    public CellType getType() {
        return this.type;
    }

    public double getH(){
        return heuristic;
    }

}
