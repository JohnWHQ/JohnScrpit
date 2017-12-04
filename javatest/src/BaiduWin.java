import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baidu on 2017/8/31.
 */
public class BaiduWin {
    public static void main(String[] args){
//        System.out.println(getKey(310));
//        System.out.println(getKey(918));

//        String[] strs = {"abc", "cde", "efy", "yhi"};
//        System.out.println(checkHT(strs));

//        System.out.println(checkComplete(11));
//        System.out.println(checkComplete(28));

//        int[] arr = {1,2,3,4,5};
        int[] arr = {2, 3, 6, 4, 5};
        List<Integer> res = findBoom(arr.length, -7, arr);
        for (int x : res) {
            System.out.println(x);
        }

    }

    // clear boom
    public static List<Integer> findBoom(int N, int key, int[] arr){
        ArrayList<Integer> al = new ArrayList<Integer>();
        if (key >= 0) {
            for (int i = 0; i < arr.length; i++) {
                int res = 0;
                int index = 0;
                for (int j = 1; j <= key; j++) {
                    index = (i + j)%N;
                    res = res + arr[index];
                }
                al.add(res);
            }
        }else {
            key = Math.abs(key);
            for (int i = 0; i < arr.length; i++) {
                int res = 0;
                int index = 0;
                for (int j = 1; j <= key; j++) {
                    index = i - j;
                    if (index < 0 && Math.abs(index) > N ) {
                        index = (-1) * (Math.abs(index)%N);
                    }
                    index = index >= 0 ? index : N + index;
                    res = res + arr[index];
                }
                al.add(res);

            }

        }
        return al;
    }

    // get key
    public static int getKey(int x){
        if (x == 0) {
            return x;
        }
        boolean pos = true;
        pos = x > 0 ? true : false;
        int n = 0;
        int tmp = Math.abs(x);
        while (tmp != 0){
            tmp = tmp/10;
            n++;
        }
        tmp = Math.abs(x);
        int[] arr = new int[n];
        n = 0;
        while (tmp != 0) {
            arr[n++] = tmp%10;
            tmp = tmp/10;
        }
        Arrays.sort(arr);
        if (arr[0] == 0) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    int t = arr[i];
                    arr[i] = arr[0];
                    arr[0] = t;
                    break;
                }
            }
        }
        int res = 0;
        for(int i = 0; i< arr.length; i++){
            res = res * 10 + arr[i];
        }
        return pos ? res : -res;
    }


    //  check if head to tail
    public static boolean checkHT(String[] strs){
        ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
        Arrays.sort(strs);
        getPe(strs, al , 0);
        for (ArrayList<String> l : al) {
            int index = 0;
            for (int i = 0; i < l.size() - 1; i++) {
                if (l.get(i).charAt(l.get(i).length() - 1) == l.get(i + 1).charAt(0)) {
                    index++;
                }
            }
            if (index == strs.length - 1) {
                return true;
            }
        }
        return false;
    }

    private static void getPe(String[] strs, ArrayList<ArrayList<String>> al, int index) {
        if (index == strs.length) {
            ArrayList<String> tmp = new ArrayList<String>();
            for (String s : strs) {
                tmp.add(s);
            }
            al.add(new ArrayList<String>(tmp));
        }
        for (int i = index; i < strs.length; i++) {
            if (i == index || !strs[i].equals(strs[index])) {
                swap(strs, i, index);
                getPe(strs, al, index + 1);
                swap(strs, i, index);
            }
        }
    }

    private static void swap(String[] strs, int a, int b) {
        String s = strs[a];
        strs[a] = strs[b];
        strs[b] = s;

    }

    // check if n is completelyN
    public static boolean checkComplete(int x){
        if (x < 0) {
            return false;
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 1; i < x; i++) {
            if ((x%i) == 0) {
                al.add(i);
            }
        }
        int res = 0;
        for (int y : al) {
            res = res + y;
        }
        return res == x ? true : false;
    }
}
