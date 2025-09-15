import java.util.Arrays;
import java.util.Random;

public class DotProduct {
    public static void main(String[] args) {
        int n = 5; 
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];

        Random rng = new Random();

        for (int i = 0; i < n; i++) {
            a[i] = rng.nextInt(10);
            b[i] = rng.nextInt(10);
            c[i] = a[i] * b[i];
        }

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("c = " + Arrays.toString(c));
    }
}
