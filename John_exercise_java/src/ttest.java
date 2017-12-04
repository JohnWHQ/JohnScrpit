/**
 * Created by baidu on 2017/10/23.
 */
import java.util.*;

public class ttest {

    public static void main (String[] args){

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int target = in.nextInt();

        int max = Integer.MAX_VALUE;

        int[][] dp =new int[n][target + 1];


        for (int j = 1; j <= target; j++) {
            dp[0][j] = max;
            if (j - arr[0] >=0 && dp[0][j - arr[0]] != max){
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }

        int left = 0 ;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target ; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max){
                    left = dp[i][ j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i-1][j],left);
            }
        }
        if(dp[n-1][target] != max ){
           System.out.println(dp[n - 1][target]);
        } else {
            System.out.println(-1);
        }
    }
}
