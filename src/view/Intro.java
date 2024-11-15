package view;
import java.util.*;

public class Intro {
    static Scanner scan  = new Scanner(System.in);


    public static int startGame(){
        System.out.println("Welcome to the Game of Life!");
        System.out.println();
        System.out.println("Watch as simple rules create complex patterns. Cells live, die, and evolve, forming a delicate balance of survival. Begin and witness the unfolding of life.");
        System.out.println();
        System.out.print("Enter the amount of rounds: ");
        return scan.nextInt();

    }
}
