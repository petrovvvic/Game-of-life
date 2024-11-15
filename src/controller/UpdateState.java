package controller;

public class UpdateState {
    public static void setUpdatedState( String [][] updatedArr,String [][] arr){
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(updatedArr[i], 0, arr[i], 0, arr[i].length);
        }


    }
}
