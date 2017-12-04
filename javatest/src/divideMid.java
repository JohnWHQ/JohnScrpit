/**
 * Created by baidu on 2017/7/9.
 */
public class divideMid {
    public static void main(String[] args){
//        int[] arr = {0,1,1,3,4,5,6};
//        System.out.println(getKindex(arr, 0, arr.length - 1, 2));

        int[] arr = {3,3,3,3};
        System.out.println(GetNumberOfK(arr, 3));
//        System.out.println(getStart(arr, 0, arr.length - 1, 3));
//        System.out.println(getEnd(arr, 0, arr.length - 1, 3));
    }

    // standard
    public static int getKindex(int[] arr, int l, int r, int k){
        int mid = 0;
        while (l <= r){
            mid = (l + r)/2;
            System.out.println("a:" + l + " b" + r);
            if (arr[mid] == k){
                return mid;
            }else if (arr[mid] > k){
                r = mid - 1;
            }else if (arr[mid] < k){
                l = mid + 1;
            }
        }
        return l;
    }

    // l r bound :
    public static int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0){
            return -1;
        }
        int start = getStart(array, 0, array.length - 1, k);
        int end = getEnd(array, 0, array.length - 1, k);
        if(start == -1 || end == -1){
            return -1;
        }
        System.out.println(start);
        System.out.println(end);
        return end - start + 1;
    }
    public static int getStart(int[] array, int l, int r, int k){

        while(l <= r){
            int mid = (l + r)/2;
            if (array[mid] > k){
                r = mid - 1;
            }else if (array[mid] < k){
                l = mid + 1;
            }else if (mid >= 1 && array[mid - 1] == k){
                r = mid - 1;
            }else {
                return mid;
            }

        }
        return -1;
    }
    public static int getEnd(int[] array, int l, int r, int k){
        while(l <= r){
            int mid = (l + r)/2;
            if (array[mid] > k){
                r = mid - 1;
            }else if (array[mid] < k){
                l = mid + 1;
            }else if (mid < array.length - 1 &&  array[mid + 1] == k ){
                l = mid + 1;
            }else {
                return  mid;
            }
        }
        return -1;
    }
}
