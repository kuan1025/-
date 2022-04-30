package Sort_;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        showRes(sort2(arr));
        showRes(sort(arr));
    }
    //老師的方法
    public static int[] sort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int index = i-1;

            while(index>=0&&temp<arr[index]){
                arr[index+1]=arr[index];
                index--;
            }
            arr[index+1]=temp;
        }
        return arr;
    }
    public static int[] sort(int[] arr) {

        for (int i = 1; i < arr.length ; i++) {
            int temp = 0;
            int index = i;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    temp = arr[i];
                    index = j;
                    break;
                }
            }
            if (index!=i) {
                for (int j = i; j>index; j--) {
                    arr[j] = arr[j-1];
                }
                arr[index] = temp;
            }

        }
        return arr;
    }

    public static void showRes(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t" + arr[i]);
        }
        System.out.println();
    }
}
