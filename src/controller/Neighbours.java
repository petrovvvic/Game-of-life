package controller;

public class Neighbours {
    public static int setNeighbours(int row, int col, String[][] arr) {
        int aliveNeighbours = 0;
        int [] rowDirections = {-1, -1,-1, 0, 0, 1, 1,1};
        int[] colDirections = {-1, 0,1, -1, 1, -1, 0,1};
        int currentRow;
        int currentCol;
        for(int i =0; i < 8; i++){
            currentRow = (row + rowDirections[i] + arr.length) % arr.length;
            currentCol = (col + colDirections[i]+arr[0].length) % arr[0].length;
            if(arr[currentRow][currentCol].equals("0")){
                aliveNeighbours++;
            }


        }
        return aliveNeighbours;
    }
    public static String [][] computeNextGeneration(String[][] arr){
        String [][] updatedState = new String[arr.length][arr[0].length];
        int neighbors=0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                neighbors = setNeighbours(i, j, arr);

                if (arr[i][j].equals("0")) { // Alive
                    if (neighbors == 2 || neighbors == 3) {
                        updatedState[i][j] = "0"; //  alive
                    } else {
                        updatedState[i][j] = "."; // die
                    }
                } else if (arr[i][j].equals(".")) { // Dead
                    if (neighbors == 3) {
                        updatedState[i][j] = "0"; // re-live
                    } else {
                        updatedState[i][j] = "."; // dead
                    }
                }
            }
        }
        return updatedState;

    }
}
