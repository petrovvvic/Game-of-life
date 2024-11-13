import java.util.*;



public class GameOfLife {
    public static void clearConsole() {
        // Print 50 newlines to "clear" the screen
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

    }
    public static void toWait(){
        try {
            Thread.sleep(1000); // Adjust the time (in milliseconds) as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void showState(int [][] arr ){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int setNeighbours(int row, int col, int [][]arr){//fucking spaghetti code nahui blyat,
        int countOfAliveNeighbours =0;
        int [] neighbourArr = new int[4];
        if(row!=0 && col!=0 && row != arr.length-1 && col != arr[0].length-1 ){
            neighbourArr[0] = arr[row][col+1];
            neighbourArr[1] = arr[row][col-1];
            neighbourArr[2] = arr[row+1][col];
            neighbourArr[3] = arr[row-1][col];
        }
        if(row == 0 && col !=0 && col!=arr[0].length-1 ) {
            neighbourArr[0] = arr[row][col+1];
            neighbourArr[1] = arr[row][col-1];
            neighbourArr[2] = arr[row+1][col];
            neighbourArr[3] = arr[arr.length-1][col];
        }
        if(row == arr.length-1  && col !=0 && col!=arr[0].length-1 ) {
            neighbourArr[0] = arr[row][col+1];
            neighbourArr[1] = arr[row][col-1];
            neighbourArr[2] = arr[row-1][col];
            neighbourArr[3] = arr[0][col];
        }
        if(row==0 && col ==0){
            neighbourArr[0] = arr[row][col+1];
            neighbourArr[1] = arr[row+1][col];
            neighbourArr[2] = arr[row][arr.length-1];
            neighbourArr[3] = arr[arr.length-1][col];
        }
        if(row==0 && col ==7){
            neighbourArr[0] = arr[row][col-1];
            neighbourArr[1] = arr[row+1][col];
            neighbourArr[2] = arr[row][0];
            neighbourArr[3] = arr[col][col];

        }
        if(row==7 && col ==0){
            neighbourArr[0] = arr[row][col+1];
            neighbourArr[1] = arr[row-1][col];
            neighbourArr[2] = arr[col][col];
            neighbourArr[3] = arr[row][row];
        }
        if(row==7 && col ==7){
            neighbourArr[0] = arr[row][col-1];
            neighbourArr[1] = arr[row-1][col];
            neighbourArr[2] = arr[row][0];
            neighbourArr[3] = arr[0][col];
        }
        for(int item : neighbourArr){
            if(item ==1){
                countOfAliveNeighbours++;
            }
        }
        return countOfAliveNeighbours;
    }

    public static void main(String [] args) {
        Random random = new Random();
        final int HEIGHT =20;
        final int WIDTH = 50;
        Scanner scan = new Scanner(System.in);
        int [][] arr = new int [HEIGHT][WIDTH];
        int [][] updatedState = new int [HEIGHT][WIDTH];

        int neighbors = 0;
        System.out.println("Enter the amount of rounds");
        int rounds = scan.nextInt();
        int roundsTracker = 0;

        for(int i =0;i<arr.length;i++) {
            for(int j =0;j<arr[i].length;j++) {
                arr[i][j] = random.nextInt(2);
            }
        }
        System.out.println("Starting grid");
        showState(arr);


        while(roundsTracker<rounds) {

            clearConsole();
            for(int i =0;i<arr.length;i++) {
                for(int j =0;j<arr[i].length;j++) {
                    neighbors= setNeighbours(i,j,arr);
                    if(arr[i][j]==1){//1 == alive
                        if(neighbors==2 || neighbors==3 ){
                            updatedState[i][j] = 1;
                        }
                        else{
                            updatedState[i][j] = 0;
                        }

                    } else if (arr[i][j]==0) {// 0 == death
                        if(neighbors==3 ){
                            updatedState[i][j] = 1;
                        }
                        else{
                            updatedState[i][j] = 0;
                        }

                    }


                }
            }
            showState(updatedState);

            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(updatedState[i], 0, arr[i], 0, arr[i].length);
            }
            roundsTracker ++;
            toWait();
        }


    }
}

