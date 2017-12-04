import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by baidu on 2017/7/7.
 */
public class DFS {
    public static void main(String[] args){

//        String str = "abcd";
//        System.out.println(Permutation(str));

        int[] nums = {1,1,2};
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        subSet(res, new ArrayList<Integer>(), nums, 0);
        System.out.println(res);


    }

    // permutation
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<String>();
        if(str == null || str.length() == 0){
            return res;
        }
        char[] carr = str.toCharArray();
        Arrays.sort(carr);
        dfs(res, carr, 0);
        return res;
    }
    public static void dfs(ArrayList<String> res, char[] arr, int index){
        if(index == arr.length){
            res.add(new String(arr));
        }
        for(int i = index; i < arr.length; i++){
            if (i == index || arr[i] != arr[index]) {
                swap(arr, i, index);
                dfs(res, arr, index + 1);
                swap(arr, i, index);
            }
        }
    }
    public static void swap(char[] arr, int a, int b){
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // subset
    public static void subSet(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index){
        res.add(new ArrayList<Integer>(tmp));
        for (int i = index; i < nums.length; i++) {
            if (index == i || nums[i - 1] != nums[i]){
                tmp.add(nums[i]);
                subSet(res, tmp, nums, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}

