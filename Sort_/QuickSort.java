package Sort_;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70,-1,900,4561};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp = 0;
        int mid = (right + left) / 2;
        int pivot = arr[mid]; // 中軸

        while (l < r) { // 循環目的: 讓比pivot小的值放到左邊，比pivot大的放右邊

            // 在pivot的左邊一直找，找到大於等於pivot才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 代表左邊都小於pivot 右邊都大於pivot 沒有就下面做交換
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //若遇到pivot或等於pivot的數 就將對象的指標-1或+1
            //達到將該變數向空間靠的效果，同時達到順利交換
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 遞迴
        if (left>r) {
        }else{
            quickSort(arr,left,r);
        }
        if (right<l) {
        }else{
            quickSort(arr,l,right);
        }

    }
}
