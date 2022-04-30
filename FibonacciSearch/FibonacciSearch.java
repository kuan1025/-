package FibonacciSearch;

import java.util.Arrays;

/**
 * 要有序
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 10));
    }

    // 因為後面我們mid = low+F(K-1)-1，需要使用到費氏數列，因此需要先獲取一個費氏數列
    // 非遞歸的方式得到一個費氏數列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 2] + f[i - 1];
        }
        return f;
    }

    // 編寫查找方法

    /**
     * @param a   數組
     * @param key 需要找的關鍵值
     * @return 返回對應的下標 反有就返回-1
     * 這裡用非遞迴寫法
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 表示費波那契分割數值的下標
        int mid = 0; // 存放mid
        int f[] = fib(); // 獲取費撥那契數列
        // 獲取到k
        while (high > f[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(a, f[k]);
        for (int i = k + 1; i < f[k]; i++) {
            temp[i] = a[high];
        }
        return find(temp, a, f, low, mid, high, key, k);
    }

    public static int find(int[] temp, int[] a, int f[], int low, int mid, int high, int key, int k) {
        mid = low + f[k - 1] -1 ;
        if (temp[mid]==key) {
            return mid;
        } else {
            if (key < temp[mid] && high >= low) {

                return find(temp, a, f, low, mid, mid-1, key, k-=1);

            } else if (key > temp[mid] && high >= low) {

                return find(temp, a, f, mid+1, mid, high, key, k-=2);

            } else {
               return -1;
            }
        }
    }
}
