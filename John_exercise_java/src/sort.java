/**
 * Created by baidu on 2017/7/3.
 */
public class sort {
    public static void main(String[] args){
        int[] arr = {7,1,6,5,4,2,3,8,9,0};
//        bubbleSort(arr);
//        insertSort(arr);
//        selectSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);

        for (int i = 0; i < arr.length; i++){
            heapBuild(arr,i);
        }
        for (int i = arr.length - 1; i > 0; i--){
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            heapSort(arr, i);
        }
        for (int x : arr) {
            System.out.println(x);
        }

    }
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length == 0){return;}
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void insertSort(int[] arr){
        if (arr == null || arr.length == 0){return;}
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    public static void selectSort(int[] arr){
        if (arr == null || arr.length == 0){return;}
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void quickSort(int[] arr, int l, int r){
        if (l < r) {
            int x = partition(arr, l, r);
            quickSort(arr, l, x);
            quickSort(arr, x + 1, r);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int magic = arr[start];
        while (start < end) {
            while (start < end && arr[end] >= magic) {
                end--;
            }
            arr[start] = arr[end];
            while (start < end && arr[start] <= magic) {
                start++;
            }
            arr[end] = arr[start];
        }
        arr[start] = magic;
        return end;
    }

    public static void mergeSort(int[] arr, int l, int r, int[] tmp){
        if (l < r) {
            int mid = (l + r)/2;
            mergeSort(arr, l, mid, tmp);
            mergeSort(arr, mid + 1, r, tmp);
            merge(arr, l, mid, r, tmp);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end, int[] tmp) {
        int l = start;
        int r = mid + 1;
        int index = l;
        while (l <= mid && r <= end) {
            if (arr[l] <= arr[r]){
                tmp[index++] = arr[l++];
            }else {
                tmp[index++] = arr[r++];
            }
        }
        while (l <= mid) {
            tmp[index++] = arr[l++];
        }
        while (r <= end) {
            tmp[index++] = arr[r++];
        }
        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i];
        }
    }

    public static void heapBuild(int[] arr, int x){
        int root = (x - 1)/2;
        while (root >= 0) {
            if (arr[root] >= arr[x]) {
                break;
            }else {
                int tmp = arr[x];
                arr[x] = arr[root];
                arr[root] = tmp;
            }
            x = root;
            root = (x - 1)/2;
        }
    }
    public static void heapSort(int[] arr, int n){
        int x = 0;
        int l = 2 * x + 1;
        int r = 2 * x + 2;
        int max = x;
        while (l < n){
            if (arr[x] < arr[l]){
                max = l;
            }
            if (r < n && arr[max] < arr[r]) {
                max = r;
            }
            if (max == x) {
                break;
            }else {
                int tmp = arr[max];
                arr[max] = arr[x];
                arr[x] = tmp;
            }
            x = max;
            l = 2 * x + 1;
            r = 2 * x + 2;
        }
    }

}
