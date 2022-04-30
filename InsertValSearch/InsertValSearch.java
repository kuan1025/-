package InsertValSearch;

public class InsertValSearch {
    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int arr2[] = {1, 8, 10,89,89,89, 89,89, 1000, 1234};
        System.out.println(insertValSearch(arr, 0, arr.length - 1, 45));
        System.out.println(insertValSearch(arr2, 0, arr2.length - 1, 1));

    }

    public static int insertValSearch(int arr[], int left, int right, int findVal) {
        System.out.println("調用!");
        int mid = left + (right - left) * (findVal - arr[left]) /( arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal == arr[mid] ) {
            return mid;
        } else {
            if (findVal > midVal&&(!((left > right || findVal > arr[arr.length-1] || findVal < arr[0]))) ) {
                return insertValSearch(arr, mid + 1, right, findVal);
            } else if (findVal < midVal&&(!((left > right || findVal > arr[arr.length-1] || findVal < arr[0])))) {
                return insertValSearch(arr, left, mid - 1, findVal);
            }else{
                return -1;
            }
        }
    }
}
