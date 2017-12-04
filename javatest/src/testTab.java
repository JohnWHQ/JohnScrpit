import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baidu on 2017/9/2.
 */
public class testTab {
    public static void main(String[] args){

//        System.out.println(getKey(310));
//        System.out.println(getKey(918));


//        int[] arr = {2, 3, 6, 4, 5};
//        List<Integer> res = getBoom(-7, 5, arr);
//        int[] arr = {4, 2, -5, 11};
//        List<Integer> res = getBoom(3, 4, arr);

//        int[] arr = {1, 2, 3, 4, 5};
//        List<Integer> res = getBoom(2, 5, arr);
//        for (int x : res) {
//            System.out.println(x);
//        }

//        String[] strs = {"abc", "cde", "efx", "xhi"};
//        System.out.println(checkHT(strs));

        System.out.println(checkCompleteN(28));
    }

    // f1
    public static int getKey(int x){
        int tmp = Math.abs(x);
        int n = 0;
        while (tmp != 0) {
            n++;
            tmp = tmp/10;
        }
        tmp = Math.abs(x);
        int[] arr = new int[n];
        int index = 0;
        while(tmp != 0){
            arr[index++] = (tmp%10);
            tmp = tmp / 10;
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
        for (int i = 0; i < arr.length; i++) {
            res = res * 10 + arr[i];
        }
        return x >= 0 ? res : -res;

    }

    // f2
    public static List<Integer> getBoom(int key, int N, int[] arr){
        ArrayList<Integer> al = new ArrayList<Integer>();
        if (key >= 0) {
            for (int i = 0; i < arr.length; i++) {
                int res = 0;
                for (int j = 1; j <= key; j++) {
                    int z = i + j;
                    z = (z%N);
                    res = res + arr[z];
                }
                al.add(res);
            }
        }else {
            key = Math.abs(key);
            for (int i = 0; i < arr.length; i++) {
                int res = 0;
                for (int j = 1; j <= key; j++) {
                    int z = i - j;
                    if (z < 0 && Math.abs(z) > N) {
                        z = (-1) * (Math.abs(z)%N);
                    }
                    z = z >= 0 ? z : N + z;
                    res = res + arr[z];
                }
                al.add(res);
            }
        }
        return al;
    }

    // t1
    public static boolean checkHT(String[] sarr){
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        getPe(res, sarr, 0);
        for (ArrayList<String> l : res){
            int index = 0;
            for (int i = 0; i < l.size() - 1; i++) {
                if (l.get(i).charAt(l.get(i).length() - 1) == l.get(i + 1).charAt(0)) {
                    index++;
                }
            }
            if (index == l.size() - 1) {
                return true;
            }
        }
        return false;
    }
    public static void getPe(ArrayList<ArrayList<String>> res, String[] sarr, int index){
        if (index == sarr.length) {
            ArrayList<String> al = new ArrayList<String>();
            for (int i = 0; i < sarr.length; i++) {
                al.add(sarr[i]);
            }
            res.add(al);
            return;
        }
        for (int i = index; i < sarr.length; i++) {
            if (i == index || !sarr[i].equals(sarr[index])) {
                swap(sarr, i, index);
                getPe(res, sarr, index + 1);
                swap(sarr, i, index);
            }
        }
    }
    public static void swap(String[] sarr, int a, int b){
        String t = sarr[a];
        sarr[a] = sarr[b];
        sarr[b] = t;
    }

    // t2
    public static boolean checkCompleteN(int x){
        if (x <= 0) {
            return false;
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 1; i < x; i++) {
            if ((x%i) == 0) {
                al.add(i);
            }
        }
        int res = 0;
        for (int per : al) {
            res = res + per;
        }
        return res == x;
    }
}
