package controller;
import java.util.*;

public class InitialState {
    public static String [][] setInitialState(String [][] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (random.nextInt(2) == 1) {
                    arr[i][j] = "0"; // Alive
                } else {
                    arr[i][j] = "."; // Dead
                }
            }
        }
        return arr;
    }
}
