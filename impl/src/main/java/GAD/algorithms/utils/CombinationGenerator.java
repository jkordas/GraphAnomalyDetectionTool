package GAD.algorithms.utils;

import java.util.Arrays;

/**
 * Created by jkordas on 08/04/16.
 */
public class CombinationGenerator {
    private int [] arr;
    private int n; // array length
    private int k; // number of ones
    private boolean first = true;

    public CombinationGenerator(int n, int k) {
        this.n = n;
        this.k = k;

        arr = new int[n];
        for(int i = n - 1; i + k >= n; --i) {
            arr[i] = 1;
        }
    }

    public int [] getState() {
        return arr;
    }

    public int [] next() {
        if(first) {
            first = false;
            return arr;
        }

        if(!hasNext()){
            throw new RuntimeException("no next value");
        }

        //move back one of ones
        int indexToMove = indexOfOneToMove();
        arr[indexToMove] = 0;
        arr[indexToMove - 1] = 1;

        //move to the end all of following ones
        int onesAfterIndex = onesAfterIndex(indexToMove);
        int positionsAfterIndex = n - indexToMove - 1;
        int zerosToWrite = positionsAfterIndex - onesAfterIndex;

        for(int i = indexToMove + 1; i < n; ++i) {
            if(zerosToWrite > 0) {
                arr[i] = 0;
                zerosToWrite--;
            } else {
                arr[i] = 1;
            }
        }

        return arr;
    }

    private int indexOfOneToMove() {
        for(int i = n - 1; i > 0; --i) {
            if(arr[i] == 1 && arr[i - 1] == 0) {
                return i;
            }
        }

        throw new RuntimeException("Invalid index.");
    }

    private int onesAfterIndex(int index) {
        int counter = 0;
        for(int i = index + 1; i < n; ++i) {
            if(arr[i] == 1) {
                counter++;
            }
        }

        return counter;
    }

    public boolean hasNext(){
        int lastIndex = -1;
        for(int i = n - 1; i > 0; --i) {
            if(arr[i] == 1) {
                lastIndex = i;
                break;
            }
        }
        return lastIndex >= k;
    }

    //TODO: change to unit test
    public static void main(String[] args) {
        CombinationGenerator g = new CombinationGenerator(7, 4);

        while (g.hasNext()){
            System.out.println(Arrays.toString(g.next()));
        }

    }
}
