package BinaryTree.HeapSort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {

        int arr[] = {4,6,8,5,9};
    heapSort(arr);


    }
    public static void heapSort(int[] arr){
        int temp = 0;
        int length = arr.length;
        System.out.println("HeapSort!!");


            for (int i = (arr.length / 2) - 1; i >= 0; i--) {
                tree(arr, i, arr.length);
            }

            for (int j = 0; j < arr.length-1; j++) {
            temp=arr[(arr.length-1)-j];
            arr[(arr.length-1)-j]=arr[0];
            arr[0]=temp;
            tree(arr,0, (arr.length-1)-j);

        }


        System.out.println(Arrays.toString(arr));


    }
    // 將一個樹組轉換成樹

    /**
     *  功能 : 將以i指向的非葉子結點的數調整成大頂堆
     * @param arr 調整數組
     * @param i 索引
     * @param length 多少個元素進行調整
     */
    public static void tree(int[] arr,int i,int length){
        int temp = arr[i];
        for (int k = i*2+1; k <length ; k=k*2+1) {
            if(arr[k+1]>arr[k]&&k+1<length){
                k++;
            }
            if(temp<arr[k]){
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
