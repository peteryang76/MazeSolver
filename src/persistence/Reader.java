package persistence;

import Exceptions.InvalidFileException;
import model.Cell;
import model.Puzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public Reader() {

    }

    /**
     * Read from file and set this puzzle to the puzzle in the file.
     *
     * @param path the path to the file that will be read from.
     * @return true if success
     * @throws InvalidFileException if the path is invalid
     * or the file doesn't contain a puzzle
     */
    public Puzzle readFromFile(String path) throws InvalidFileException {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);


            List<String> templist = new ArrayList<>();

            String templine;

            while ((templine = reader.readLine()) != null) {
                templist.add(templine);
            }
            if (templist.size()==0){
                throw new InvalidFileException("empty file!");
            }

            int numRow = templist.size();
            int numCol = templist.get(0).length();
            Puzzle result = new Puzzle(numCol, numRow);
            for(int row=0;row< numRow;row++){
                String object = templist.get(row);
                for (int col=0;col<numCol;col++){
                    char CellType = object.charAt(col);
                    switch (CellType){
                        case ' ':
                            result.setCell(row, col, Cell.Path);
                            break;
                        case '-':
                            result.setCell(row, col, Cell.Wall);
                            break;

                        case 'e':
                            result.setCell(row, col, Cell.End);
                            break;
                        case 's':
                            result.setCell(row, col, Cell.Start);
                            break;
                    }
                }
            }
            inputStream.close();
            inputStreamReader.close();
            reader.close();
            if (result.isValid()) {
                return result;
            } else {
                throw new InvalidFileException("No valid puzzle in the file");
            }
        }catch (FileNotFoundException e){
            throw new InvalidFileException("File not found");
        } catch (IOException e){
            throw new InvalidFileException(e.getMessage());
        }
    }
}
