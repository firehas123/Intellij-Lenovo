import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\mrhas\\IdeaProjects\\JavaPractise\\src\\input.txt");
            Scanner scanner = new Scanner(file);

            // Read first line
            String[] firstLine = scanner.nextLine().split(" ");
            int columns = Integer.parseInt(firstLine[0]);
            int rows = Integer.parseInt(firstLine[1]);
            int snakeCount = Integer.parseInt(firstLine[2]);

            ArrayList<ArrayList<String>> matrixResult = new ArrayList<ArrayList<String>>();

            // Read second line
            String[] secondLine = scanner.nextLine().split(" ");
            int[] snakeValues = new int[snakeCount];
            for (int i = 0; i < snakeCount; i++) {
                snakeValues[i] = Integer.parseInt(secondLine[i]);
            }

            // Read and store matrix
            MyClass[][] matrix = new MyClass[rows][columns];
            for (int i = 0; i < rows; i++) {
                String[] line = scanner.nextLine().split(" ");
                for (int j = 0; j < columns; j++) {
                    if (line[j].equals("*")) {
                        matrix[i][j] = new MyClass(99, -1);
                    } else {
                        matrix[i][j] = new MyClass(Integer.parseInt(line[j]), 1);
                    }
                }
            }

            //my business solution
            int bestCount  = 0;
            SingleResult myclass = new SingleResult();
            for (int i=0;i<snakeCount;i++){
                for(int j=0;j<rows;j++){
                    for(int k=0;k<columns;k++){
                        if(matrix[i][j].value!=99) {
                            myclass = allPossibleIteration(j, k, snakeValues[i], matrix, 1, rows, columns);
                            if (bestCount < myclass.value) {
                                matrixResult.get(i).clear();
                                matrixResult.get(i).add(myclass.path);
                            }
                        }
                    }
                }
                // now changing indicators
                settingIndicators(myclass.path,matrix,snakeValues[i],rows,columns);
            }

            // Print matrix
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void settingIndicators(String path, MyClass[][] matrix, int snakeValue, int Maxrows, int Maxcolumns) {
        String[] strArray = path.split(" ");
        int startingRowPoint = Integer.parseInt(strArray[0]);
        int startingColPoint = Integer.parseInt(strArray[1]);
        matrix[startingRowPoint][startingColPoint].indicator=0;
        for(int i=2;i< strArray.length;i++){
            if(strArray[i]=="U"){
            //checking boundaries
            if(startingRowPoint==0){
                startingRowPoint = Maxrows - 1;
            }else {
                startingRowPoint--;
            }
            } else if (strArray[i]=="D") {
                //checking boundaries
                if(startingRowPoint==Maxrows){
                    startingRowPoint = 0;
                }else {
                    startingRowPoint++;
                }
            } else if (strArray[i]=="L") {

            } else if (strArray[i]=="R") {

            }
            else{
                //for wormhole
            }
        }
    }

    private static SingleResult allPossibleIteration(int startingRow, int startingCol, int snakeValue, MyClass[][] matrix, int limit, int rowSize, int colSize) {
        SingleResult sr = new SingleResult();
        SingleResult temp = new SingleResult();
        if(limit==snakeValue+1) {
            return sr;
        }
        //all four possible directions
        int randomValue = -99;
        int r=startingRow,c=startingCol;
        String randomPath  = "";
        if(matrix[r][c].indicator==1) {//checking for overlapse in up
            //checking for boundaries first
            if (startingRow == 0) {
                r = rowSize - 1;
            }
            //up case
            if (matrix[r][startingCol].value > randomValue) {
                randomValue = matrix[r][c].value;
                randomPath += "U";
            }
        }
        if(matrix[r][c].indicator==1) {//checking for overlapse in down
            //checking for boundaries first
            if (startingRow == rowSize - 1) {
                r = 0;
            }
            //down case
            if (matrix[r][startingCol].value > randomValue) {
                randomValue = matrix[r][c].value;
                randomPath += "D";
            }
        }
        if(matrix[r][c].indicator==1) {//checking for overlapse in left
            //checking for boundaries first
            if (startingCol == 0) {
                c = colSize - 1;
            }
            //left case
            if (matrix[startingRow][c].value > randomValue) {
                randomValue = matrix[r][c].value;
                randomPath += "L";
            }
        }
        if(matrix[r][c].indicator==1) {//checking for overlapse in right
            //checking for boundaries first
            if (c == colSize - 1) {
                c = 0;
            }
            //right case
            if (matrix[startingRow][c].value > randomValue) {
                randomValue = matrix[r][c].value;
                randomPath += "R";
            }
        }
        sr.value+=randomValue;
        sr.path=randomPath;
        //now we have the highest value
        if(limit+1==snakeValue) {
            return sr;
        }
        else {
            sr.path+=" ";
            temp = allPossibleIteration(r, c, snakeValue, matrix, limit+1, rowSize, colSize);
            sr.value+= temp.value;
            sr.path+= temp.path;
        }

        return  sr; // will be never called
    }


}

class MyClass {
    public int value;
    public int indicator;

    public MyClass(int value, int indicator) {
        this.value=value;
        this.indicator=indicator;
    }
}

class SingleResult {
    public int value;
    public String path;

    public SingleResult(){
        value = 0;
        path = "";
    }

}

