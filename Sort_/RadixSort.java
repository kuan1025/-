package Sort_;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {

        int arr[] = {53, 3, 542, 748, 14, 214};

        radixSort2(arr);

    }

    // 基數排序法
    public static void radixSort(int[] arr) {

        // 第一輪(針對每個元素進行排序)

        // 定義一個二維數組，表示10個桶，每個桶就是一個一維數組
        // 為了防止溢出，只能將數組預設最大使用量，很明確基數排序是空間換時間的經典算法
        int[][] bucket = new int[10][arr.length];

        // 為了記錄每個桶實際存放多少數據，我們定義一個一維數組來記錄各個統每次放入的數據個數
        int[] bucketElementCounts = new int[10];
        for (int i = 0; i < arr.length; i++) {

            int num = arr[i] % 10;
            bucket[num][bucketElementCounts[num]] = arr[i];
            bucketElementCounts[num]++;
        }
        // 按照桶子順序 重新列出來
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            // 第一輪後 要將bucketElement重製
            bucketElementCounts[i] = 0;
        }
        System.out.println("第一輪" + Arrays.toString(arr));

        // 第二輪

        for (int i = 0; i < arr.length; i++) {

            int num = arr[i] / 10 % 10;
            bucket[num][bucketElementCounts[num]] = arr[i];
            bucketElementCounts[num]++;
        }
        // 按照桶子順序 重新列出來
        index = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            // 第一輪後 要將bucketElement重製
            bucketElementCounts[i] = 0;
        }
        System.out.println("第二輪" + Arrays.toString(arr));

        // 第三輪
        for (int i = 0; i < arr.length; i++) {

            int num = arr[i] / 100 % 10;
            bucket[num][bucketElementCounts[num]] = arr[i];
            bucketElementCounts[num]++;
        }
        // 按照桶子順序 重新列出來
        index = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            // 第一輪後 要將bucketElement重製
            bucketElementCounts[i] = 0;
        }
        System.out.println("第三輪" + Arrays.toString(arr));


    }

    public static void radixSort2(int[] arr) {
        int[][] bk = new int[10][arr.length];
        int[] index1 = new int[10];
        int index2 = 0;
        int time = 1;
        // 求出arr中的最高位數 先假設是[0]
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();




        for (int i = 0 ,m = 1; i < maxLength; i++,m*=10) {

            for (int j = 0; j < arr.length; j++) {
                int val = arr[j] / m % 10;
                bk[val][index1[val]] = arr[j];
                index1[val]++;
            }
            index2=0;
            for (int z = 0; z < bk.length; z++) {
                if (index1[z] != 0) {
                    for (int j = 0; j < index1[z]; j++) {
                        arr[index2] = bk[z][j];
                        index2++;
                    }

                }
                index1[z] = 0;

            }

            System.out.printf("第%d輪 = " + Arrays.toString(arr) + "\n", i+1);

        }
    }
}