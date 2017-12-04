
/**
 * Created by baidu on 2017/7/15.
 */
public class test {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(FindK(arr, arr.length, 4));

    }
    public static int FindK(int[] arr, int size, int k) {
        if(arr == null || arr.length == 0 || arr.length < k || k <= 0){
            return -1; //  -1标识不存在
        }
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++){
            max = Math.max(arr[i], max);
        }
        if(k == 1){
            return max;
        }
        int res = 0;
        while(k != 1){
            res = Integer.MIN_VALUE;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] > res && arr[i] < max){
                    res = arr[i];

                }
            }
            System.out.println(res + "xxx" );
            max = res;
            k--;
        }
        return res;

    }



}

