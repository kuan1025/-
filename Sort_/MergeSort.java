package Sort_;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[]= new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("遞迴排序 = "+ Arrays.toString(arr));


    }

    // 分 + 合 的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left>=right) {
            return;
        } else {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp); // 像左遞迴
            mergeSort(arr, mid + 1, right, temp); // 向右遞迴
            merge(arr,left,mid,right,temp);
        }

    }


    // 合併的過程

    /**
     * @param arr
     * @param left  左邊初始index
     * @param right
     * @param temp  中轉
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println("----");
        int i = left; // 左邊有序序列的初始索引
        int j = mid + 1;
        int t = 0; // 指向temp的當前索引

        // 1. 先把左右兩邊的數據按照規則填充到temp數組 直到左右兩邊的有序序列處理完畢為止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        // 2. 把剩餘數據的地方依序填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        // 3. 將temp數組的元素拷貝到arr
        // 注意 並不適每次copy 8個
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft = "+"\t" +tempLeft +"\t" + "right = " + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }


}
