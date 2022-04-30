package BinarySearch;

import java.util.Arrays;

/**
 * 只能夠搜尋有序的陣列 確認該陣列有無該value
 * binarySearch 只能找到一個！
 * binarySearch2 找到後往左右找 -> 但是僅能往某一個方向找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10,89,89,89, 89,89, 1000, 1234};
        System.out.println(Arrays.toString(binarySearch2(arr, 0, arr.length - 1, 89)));
        System.out.println(binarySearch(arr, 0, arr.length - 1, 89));
    }


    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        System.out.println("開始找");
        int mid = (left + right) / 2; // 找中間
        int midVal = arr[mid]; // 找中間值

        if (midVal == findVal) { // 判斷中間值與尋找的值施否相等
            return mid;
        } else {
            if (midVal > findVal && right > left) {
                return binarySearch(arr, left, mid - 1, findVal); // 比中間值小 往左找 遞迴
            } else if (midVal < findVal && right > left) {
                return binarySearch(arr, mid + 1, right, findVal); // 比中間值大 往右找 遞迴
            } else {
                return -1; // 找不到
            }
        }
    }
    public static int[] binarySearch2(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2; // 找中間index
        int midVal = arr[mid]; // 中間值
        int[] res = new int[arr.length]; // 建立另外一個arr
        int temp = 0; // 暫存變數
        if (midVal == findVal) { // 是否與中間值相同
            temp=mid; // 中間index
            int temp2 = 0;
            while(findVal == arr[temp]){ // 找到後，繼續往後找，確認是否有重複的值
                res[temp2] = arr[temp];
                if(temp>arr.length){
                    break;
                }
                temp++;
                temp2++;
            }
            if(temp==0) { // 第一個while沒找到 表示為偶數 先-1 往前找
                temp = mid - 1;
            }

            while(findVal == arr[temp]){   // 往前找
                res[temp2] = arr[temp];
                if(temp<=0){
                    break;
                }
                temp--;
                temp2++;
            }
            return res;
        } else {
            if (midVal > findVal && right > left) { // 若中間的val > 目標 val 且 最右index > 最左index
                return binarySearch2(arr, left, mid - 1, findVal); // 往左邊找
            } else if (midVal < findVal && right > left) {
                return binarySearch2(arr, mid + 1, right, findVal); // 往右邊找
            } else {
                return res;
            }
        }
    }
}
