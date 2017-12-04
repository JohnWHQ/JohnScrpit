import java.util.Scanner;

/**
 * Created by baidu on 2017/7/13.
 */
public class Fuck {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] sarr = str.split("->");
        if(sarr.length == 0 || sarr[0].equals("NULL")){
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sarr.length - 1; i++){
            if((Integer.parseInt(sarr[i])&1) == 1){
                sb.append(sarr[i]).append("->");
            }
        }
        for(int i = 0; i < sarr.length - 1; i++){
            if((Integer.parseInt(sarr[i])&1) != 1){
                sb.append(sarr[i]).append("->");
            }
        }
        sb.append("NULL");
        System.out.println(sb.toString());
    }
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++){
//            arr[i] = in.nextInt();
//        }
//
//        for(int i = 0; i < n; i++){
//            if(arr[i] == 0){
//                arr[i] = -1;
//            }
//        }
//        int sum = 0;
//        int l = 0;
//        int r = 0;
//        int res = 0;
//        while(r < arr.length || l < arr.length){
//            if(l < arr.length){
//                sum = sum + arr[l++];
//            }else if(r < arr.length){
//                sum = sum - arr[r++];
//            }
//            if(sum == 0){
//                res = Math.max(res, r - l + 1);
//            }
//        }
//        System.out.println(res);
//
//    }
}
