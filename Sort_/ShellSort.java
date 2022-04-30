package Sort_;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 0, 6};


       sortS(arr);
    }

    /**
     *
     *  public static int[] sort(int[] arr) {
     *         int temp = 0;
     *         for (int gap = arr.length / 2; gap > 0; gap /= 2) {
     *             for (int i = gap; i < arr.length; i++) {
     *                 for (int j = i - gap; j >= 0; j -= gap) {
     *                     if (arr[j] > arr[j + gap]) {
     *                         temp = arr[j + gap];
     *                         arr[j + gap] = arr[j];
     *                         arr[j] = temp;
     *                     }
     *                 }
     *             }
     *
     *         }
     *         return arr;
     *     }
     *
     *
     */
    public static int[] sortS(int[] arr){
        int gap = 2;

        for (int group =arr.length/gap; group >0 ; group/=gap) {
            for (int i = 0; i <= arr.length-group ; i++) {
                int temp = 0;
                for(int j =i;j>=0;j-=group){
                    temp=arr[j];
                    if ( j>=group&&temp< arr[j-group]){
                        arr[j]=arr[j-group];
                        arr[j-group]=temp;
                        showRes(arr);
                    }
                }
            }
        }

return arr;
    }

    public static int[] shellSort(int[] arr) {

        // 第一輪 :
        // 將10個數據分成5組
        for (int i = 5; i < arr.length; i++) {
            int temp = 0;
            //遍歷各組中所有的元素
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j + 5];
                    arr[j + 5] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        // 第二輪
        for (int i = 2; i < arr.length; i++) {
            int temp = 0;
            //遍歷各組中所有的元素
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j + 2];
                    arr[j + 2] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        // 第三輪
        for (int i = 1; i < arr.length; i++) {
            int temp = 0;
            //遍歷各組中所有的元素
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] sort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
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
