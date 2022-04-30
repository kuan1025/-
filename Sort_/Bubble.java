package Sort_;

/**
 *
 * 所花時間 O(n^2)
 */
public class Bubble {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};

        showRes(bubbleSort(arr));

    }
    public static int[] bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        long time = System.currentTimeMillis();
        for (int i = 0; i < (arr.length-1); i++) {
            for (int j = 0; j < (arr.length-1)-i; j++) {
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if(flag==false){
                break;
            }else{
                flag = false; // 重製!! 用來判斷下一輪
            }
        }
        System.out.println(System.currentTimeMillis()-time);
        return arr;
    }
    public static void showRes(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t"+arr[i]);
        }
    }
}
