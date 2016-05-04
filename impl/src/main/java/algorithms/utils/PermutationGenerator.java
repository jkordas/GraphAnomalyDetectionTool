package algorithms.utils;

/**
 * Created by jkordas on 29/04/16.
 */
public class PermutationGenerator {
//    Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
//    Find the largest index l greater than k such that a[k] < a[l].
//    Swap the value of a[k] with that of a[l].
//    Reverse the sequence from a[k + 1] up to and including the final element a[n].

    private int [] arr;
    private int n; // array length
    private boolean first = true;

    public PermutationGenerator(int n) {
        if(n > 9) {
            throw new RuntimeException("Permutation shouldnt be higher than 9. Due to performance issues.");
        }
        this.n = n;

        arr = new int[n];
        for(int i = 0; i < n; ++i) {
            arr[i] = i;
        }
    }

    private int findIndexK() {
        for(int i = n - 2; i >= 0; --i) {
            if(arr[i] < arr[i+1]){
                return i;
            }
        }

        return -1;
    }

    private int findIndexL(int k) {
        for(int i = n - 1; i >= 0; --i) {
            if(arr[k] < arr[i]){
                return i;
            }
        }

        throw new RuntimeException("Illegal state");
    }

    public int [] getState() {
        return arr;
    }

    public int [] next() {
        if(!hasNext()){
            throw new RuntimeException("Cannot generate next state. All states already generated.");
        }

        if(first) {
            first = false;
            return arr;
        }

        int k = findIndexK();
        int l = findIndexL(k);

        int tmp = arr[k];
        arr[k] = arr[l];
        arr[l] = tmp;

        for(int i = k + 1, j = n - 1; i < j; ++i, --j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        return arr;
    }

    public boolean hasNext(){
        return findIndexK() >= 0;
    }

    public static void main(String[] args) {
        PermutationGenerator generator = new PermutationGenerator(10);

        while(generator.hasNext()){
//            System.out.println(Arrays.toString(generator.next()));
            generator.next();
        }
    }

}
