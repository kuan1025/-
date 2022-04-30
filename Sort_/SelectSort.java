package Sort_;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};

        showRes(selectSort(arr));


    }
    public static int[] selectSort(int[] arr){


        for (int i = 0; i <arr.length-1; i++) {
            int temp = arr[i];
            int index = i;

            for (int j = i+1; j < arr.length; j++) {
                if(temp>arr[j]){
                    temp=arr[j];
                    index=j;
                }
            }
            if(index!=i) {
                arr[index] = arr[i];
                arr[i] = temp;
            }

        }
        return arr;
    }
    public static void showRes(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t"+arr[i]);
        }
        System.out.println();
    }
}
