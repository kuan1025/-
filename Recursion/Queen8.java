package Recursion;


public class Queen8 {
    static int max = 8;
    static int[] arr = new int[max];

    public static void main(String[] args) {
    check(0);

    }

    public static boolean Repeat(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n]) {
                return false;
            } else if (Math.abs(arr[n] - arr[i] )== Math.abs(n - i)) {
                return false;
            }
        }
        return true;
    }

    public static void print() {

        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t" + arr[i]);

        } System.out.println();
    }

    public static void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (Repeat(n)){
                check(n+1);
            }
        }

    }
}