import java.util.*;

public class GameOfLife {

    // Method to clear the console screen
    public static void clearConsole() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }

    // Method to wait for a certain period before moving to the next generation
    public static void toWait() {
        try {
            Thread.sleep(1000); // Wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to display the current state of the grid
    public static void showState(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to count the number of live neighbors for a given cell
    public static int setNeighbours(int row, int col, String[][] arr) {
        int aliveNeighbours = 0; //add logic!!!
        int [] rowDirections = {-1, -1,-1, 0, 0, 1, 1,1};
        int[] colDirections = {-1, 0,1, 0, -1, 1, -1, 0,1};
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


    public static void main(String[] args) {
        Random random = new Random();
        final int HEIGHT = 5;
        final int WIDTH = 5;
        Scanner scan = new Scanner(System.in);
        String[][] arr = new String[HEIGHT][WIDTH];
        String[][] updatedState = new String[HEIGHT][WIDTH];

        int neighbors;
        System.out.println("Enter the amount of rounds");
        int rounds = scan.nextInt();
        int roundsTracker = 0;

        // Initialize the grid with random values: "." (dead) or "0" (alive)
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (random.nextInt(2) == 1) {
                    arr[i][j] = "0"; // Alive
                } else {
                    arr[i][j] = "."; // Dead
                }
            }
        }

        System.out.println("Starting grid");
        showState(arr);

        while (roundsTracker < rounds) {
            clearConsole();

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    neighbors = setNeighbours(i, j, arr);

                    if (arr[i][j].equals("0")) { // Alive
                        if (neighbors == 2 || neighbors == 3) {
                            updatedState[i][j] = "0"; // Stay alive
                        } else {
                            updatedState[i][j] = "."; // Die
                        }
                    } else if (arr[i][j].equals(".")) { // Dead
                        if (neighbors == 3) {
                            updatedState[i][j] = "0"; // Reproduce
                        } else {
                            updatedState[i][j] = "."; // Stay dead
                        }
                    }
                }
            }

            // Display updated state
            showState(updatedState);

            // Copy updated state back to the original grid
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(updatedState[i], 0, arr[i], 0, arr[i].length);
            }

            roundsTracker++;
            toWait();
        }
    }
}
