import static grid.GridGenerator.generateGrid;//generates grid in format: WIDTHxHEIGHT
import static  controller.Neighbours.computeNextGeneration;// computes next generation and updates updatedState
import static view.ConsoleControl.*;// pizda we have hier all of our cpnsole controlling(layout and so forth)
import static controller.InitialState.setInitialState;//setting start arr
import static view.Intro.*;//intro to the game
import static controller.UpdateState.setUpdatedState;//updates old arr from the updated(post-generation) one



public class GameOfLife {

    public static void main(String[] args) {
        int rounds=startGame();

        int roundsTracker = 0;

        String[][] arr = setInitialState( generateGrid());
        String[][] updatedState;

        System.out.println("There is the Starting Grid starting grid");
        showState(arr);
        consoleWait();

        while (roundsTracker < rounds) {
            clearConsole();

            updatedState=computeNextGeneration(arr);

            showState(updatedState);
            setUpdatedState(updatedState,arr);

            roundsTracker++;
            consoleWait();
        }
    }
}
