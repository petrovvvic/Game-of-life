package grid;
import java.util.*;


public class GridGenerator {
    public static String [][] generateGrid() {
        Scanner scanner = new Scanner(System.in);
        boolean gotGrid = true;
        int HEIGHT = 0;
        int WIDTH=0;
        System.out.println("Please enter the height and the width of the grid in format: WIDTHxHEIGHT");
        while(gotGrid){
            try{
                String input = scanner.nextLine();
                WIDTH = Integer.parseInt(input.substring(0, input.indexOf("x")));
                HEIGHT = Integer.parseInt(input.substring( input.indexOf("x")+1));
                gotGrid = false;
            } catch (Exception e) {
                System.out.println("Error!!Please enter the height and the width of the grid in format: WIDTHxHEIGHT");
            }

        }
        return new String[HEIGHT][WIDTH];
    }
}
