import java.util.*;

public class GameOfLife {

    // Method to clear the console screen, but we do with 100 empty lines
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Method to wait for a  period before moving to the next generation
    public static void toWait() {
        try {
            Thread.sleep(600); // Wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to display the current state of the grid(just shows the grid)
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
        int [] rowDirections = {-1,-1,-1, 0, 0, 1, 1,1};//direction for row
        int[] colDirections = {-1, 0, 1, -1, 1, -1, 0,1};//direction for col
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

    public static void welcomeUser(){
        System.out.println("Willkommen zum Game of Life!\n" +
                "=========================================\n" +
                "Das Game of Life ist eine Simulation zellulärer Automaten, entwickelt von John Conway.\n" +
                "Regeln:\n" +
                "1. Lebende Zellen überleben mit 2 oder 3 Nachbarn.\n" +
                "2. Tote Zellen werden bei genau 3 Nachbarn lebendig.\n" +
                "3. Alle anderen Zellen sterben oder bleiben tot.\n");
        System.out.println(
                "Sie legen die gewünschte Anzahl an Generationen fest.\n" +
                "Die Startkonfiguration wird zufällig erzeugt.\n" +
                "Viel Spaß beim Game of Life!\n"+
                "=========================================\n");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        boolean gotRounds = true;

        final int HEIGHT = 30;
        final int WIDTH = 30;

        String[][] arr = new String[HEIGHT][WIDTH];
        String[][] updatedState = new String[HEIGHT][WIDTH];

        int neighbors;
        int rounds = 0;

        welcomeUser();


        System.out.println("Geben Sie die Anzahl der Generationen ein: ");
        while(gotRounds){
            try {
                rounds = scan.nextInt();
                if(rounds<=0){
                    System.out.println("Fehler! Bitte geben Sie eine positive ganze Zahl ein.");
                    continue;
                    }
                gotRounds = false;

            } catch (Exception e) {
                System.out.println("Fehler! Bitte geben Sie eine positive ganze Zahl ein.");
                scan.nextLine(); // sonst wird es die gleiche zeile mit falscher eingabe immer wieder lesen, so geht es auf die nächste Zeile
            }

        }

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

        System.out.println("Startkonfiguration \n" );
        showState(arr);

        while (roundsTracker < rounds) {
            clearConsole();

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

            // Display updated state
            showState(updatedState);

            // Copy updated state back to the original grid
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(updatedState[i], 0, arr[i], 0, arr[i].length);
            }

            roundsTracker++;
            toWait();
        }
        System.out.println("=========================================\n"+
                "Das Spiel ist beendet!\n" +
                "Vielen Dank, dass Sie das Game of Life gespielt haben.\n" +
                "Wir hoffen, es hat Ihnen Spaß gemacht, die Evolution der Zellen zu beobachten!");

    }
}
