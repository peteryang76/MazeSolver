package model;

import Exceptions.InvalidFileException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Puzzle is a 2d array of cells
public class Puzzle {
//    TODO: implement class

    List<List<Cell>> puzzle; // maybe change into a 3D array?
    int width;
    int height;

    /**
     * Initialize puzzle such that every cell is a path
     *
     * @param width width of the puzzle
     * @param height height of the puzzle
     */
    public Puzzle(int width, int height) {
        this.width = width;
        this.height = height;
//        TODO: implement constructor
    }


    /**
     * Set the cell at (x,y) as new_cell
     *
     * @param x x index
     * @param y y index
     * @param new_cell the new cell to be set as
     */
    private void setCell(int x, int y, Cell new_cell) {
        puzzle.get(x).set(y, new_cell);

    }

    /**
     * Check if this puzzle is a valid one
     * To be considered as valid, a puzzle must have:
     * at least 1 start and
     * at least 1 end
     *
     * @return true if the puzzle is valid; false otherwise
     */
    public boolean isValid() {
        if (puzzle.contains(Cell.Start) && puzzle.contains(Cell.End)){
            return true;
        }

        return false;
    }

    public Cell getCell(int x, int y) {
        if (x < width && y < height){
            return puzzle.get(x).get(y);
        }

        return null;
    }

    /**
     * Read from file and set this puzzle to the puzzle in the file.
     *
     * @param path the path to the file that will be read from.
     * @return true if success
     * @throws InvalidFileException if the path is invalid
     * or the file doesn't contain a puzzle
     */
    private boolean readFromFile(String path)  {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String stop = "/n";
            List<String> templist = new ArrayList<>();
            int x = 0;
            int y = 0;

            String templine = "";
            while ((templine = reader.readLine()) != null) {
                templist.add(templine);
                y++;
            }
            x = templine.length();

            for(int i=0;i<y;i++){
                String object = templist.get(i);
                for (int j=0;j<x;j++){
                    char CellType = object.charAt(j);
                    switch (CellType){
                        case ' ':
                            setCell(j,i,Cell.Path);

                        case '-':
                            setCell(j,i,Cell.Wall);

                        case 'e':
                            setCell(j,i,Cell.End);
                        case 's':
                            setCell(j,i,Cell.Start);
                    }
                }
            }
            x=width;
            y=height;
            inputStream.close();
            inputStreamReader.close();
            reader.close();
            return true;

        }catch (FileNotFoundException e){
            return false;
        } catch (IOException e){
                return false;
        }catch (InvalidFileException e) {
            return false;                     //!!!
        }

        //TODO:
        return false;
    }

    /**
     * Write this puzzle to the file
     *
     * @param path the path of file to be written into
     * @return true if success; false otherwise
     */
    private boolean writeToFile(String path) {
    try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for(int i=0;i<height;i++){

            for (int j=0;j<width;j++){
                switch (getCell(j,i)){
                    case Path:
                        writer.write(' ');
                    case Wall:
                        writer.write('-');
                    case End:
                        writer.write('e');
                    case Start:
                        writer.write('s');                }

            }
            writer.write("/n");
        }
        writer.close();
        return true;
    } catch (IOException e) {
        return false;
    }


        //TODO:

    }
}
