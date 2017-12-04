import java.io.*;
import java.util.*;

interface A{
    public void f();
}
interface D extends  A {

}
class B implements A {

    @Override
    public void f() {
        System.out.print("B");
    }
}
class C implements A {

    @Override
    public void f() {
        System.out.print("C");
    }
}


public class Main


{
    public static void main(String args[])
    {
        A a1 = new B();
        A a2 = new C();
        a1.f();
        a2.f();

//        Scanner in = new Scanner(System.in);
//        int n = Integer.valueOf(in.nextLine());
//        String[] sarr = new String[n];
//        for (int i = 0; i < n; i ++) {
//            sarr[i] = in.nextLine();
//        }
//        for (int i = 0; i < n; i ++) {
//            System.out.println(f(sarr[i]));
//        }


    }
    public static int f(String s){
        if (s.length() == 1) {
            if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
                return 1;
            }else {
                return 2;
            }
        }else {
            int res = 0;

            boolean d = true;
            boolean u = true;

            int pre = 1;
            int cur = 0;

            if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
                d = true;
                u = false;
                res = 1;
            }else {
                d = false;
                u = true;
                res = 2;
            }

            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    if (d) {
                        res++;
                        cur++;
                    }else {
                        res = res + 2;
                        if (cur > pre){
                            d = true;
                            u = false;
                            pre = cur;
                            cur = 1;
                        }

                    }
                }else {
                    if (u) {
                        res++;
                        cur++;
                    }else {
                        res = res + 2;
                        if (cur > pre){
                            d = false;
                            u = true;
                            pre = cur;
                            cur = 1;
                        }

                    }
                }
            }
            return res;


        }

    }
}
//
//    public static List<List<Integer>> getN(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
//        backtrack(list, new ArrayList<Integer>(), nums, 0);
//        return list;
//    }
//
//    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
//        if (!tempList.isEmpty()) {
//            list.add(new ArrayList<>(tempList));
//        }
//        for(int i = start; i < nums.length; i++){
//            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
//            tempList.add(nums[i]);
//            backtrack(list, tempList, nums, i + 1);
//            tempList.remove(tempList.size() - 1);
//        }
//    }
//}

//
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String per = in.nextLine();
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        while (!per.trim().equals("-")) {
//            String[] tmp = per.split(" ");
//            map.put(tmp[0], Integer.valueOf(tmp[1]));
//            per = in.nextLine();
//        }
//        ArrayList<String> al = new ArrayList<String>();
//
//        while (in.hasNextLine()) {
////            al.add(in.nextLine());
//            System.out.println(f(in.nextLine(), map));
//        }
//
//
//    }
//    public static int f(String s, HashMap<String, Integer> map){
//        String str = null;
//        int res = 0;
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            String key = (String) entry.getKey();
//            int val = (int)entry.getValue();
//            if (s.indexOf(key) == 0 && key.length() > res) {
//                if (key.length() < s.length() && s.charAt(key.length()) != '/') {
//                    continue;
//                }
//                str = key;
//                res = key.length();
//            }
//        }
//        return str == null ? 0 : map.get(str);
//    }
//}
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        StringBuilder sb = new StringBuilder();
//        String[] sarr = str.split("/");
//        Deque<String> deq = new LinkedList<String>();
//        for (int i = 0; i < sarr.length; i++) {
//            if (sarr[i].equals(".") ||  sarr[i].equals("")) {
//                continue;
//            }
//            if (sarr[i].equals("..")) {
//                if (!deq.isEmpty()){
//                    deq.pollLast();
//                }
//            }else {
//                deq.addLast(sarr[i]);
//            }
//        }
//
//        if (deq.isEmpty()) {
//            System.out.println("/");
//            return;
//        }
//        while(!deq.isEmpty()){
//            sb.append("/").append(deq.pollFirst());
//        }
//        System.out.println(sb.toString());
//    }
//}


//public class Solution {
//    public String simplifyPath(String path) {
//        Stack<String> stk = new Stack<String>();
//        String[] parts = path.split("/");
//        for(String part : parts){
//            switch(part){
//                case ".":
//                case "" :
//                    break;
//                case "..":
//                    if(!stk.isEmpty()){
//                        stk.pop();
//                    }
//                    break;
//                default:
//                    stk.push(part);
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        if(stk.isEmpty()){
//            return "/";
//        }
//        while(!stk.isEmpty()){
//            sb.insert(0, "/"+stk.pop());
//        }
//        return sb.toString();
//    }
//}

//import java.util.*;
//
//class Test {
//}
//public class Main {
//    public static void main(String args[]) {
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//            String str = in.nextLine();
//            char[] carr = str.toCharArray();
//            ArrayList<Integer> al = new ArrayList<Integer>();
//            int i = 0;
//            while(i < carr.length && i != -1){
//                i = str.indexOf("&lt;", i);
//                if(i != -1) {
//                    al.add(i);
//                    i = i + 4;
//                } else {
//                    break;
//                }
//                System.out.println(i);
//            }
//            for(int x : al){
//                for(int j = x; j < x + 4; j++){
//                    carr[j] = 0;
//                }
//                carr[x] = '<';
//            }
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < carr.length; j++) {
//                if (carr[j] != 0){
//                    sb.append(carr[j]);
//                }
//
//            }
//            System.out.println(sb.toString());
//        }
//    }
//}
//







//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = in.nextInt();
//        }
//        int a = aGet(arr, 0);
//        int sum = 0;
//        for(int i = 0; i < arr.length; i++){
//            sum += arr[i];
//        }
//        int b = sum - a;
//        System.out.println(a > b);
//
//    }
//    public static int aGet(int[] arr, int i){
//        if(i == arr.length){
//            return 0;
//        }
//        if(i == arr.length - 1){
//            return arr[i];
//        }
//
//        return Math.max(arr[i] + arr[i + 1] +bGet(arr, i + 2), arr[i] + bGet(arr, i + 1));
//    }
//    public static int bGet(int[] arr, int i){
//        if(i == arr.length || i == arr.length - 1){
//            return 0;
//        }
//        return Math.min(aGet(arr, i + 2), aGet(arr, i + 1));
//    }
//}
//import java.util.HashSet;
//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int a = in.nextInt();
//        int b = in.nextInt();
//        int n = in.nextInt();
//
//        int min = Math.min(a, b);
//        int res = 0;
//        int i = 1;
//        HashSet<Integer> set = new HashSet<Integer>();
//        while(min <= n){
//            if(!set.contains(min) && min >= Math.max(a, b) && (min%a) == 0 && (min%b) == 0){
//                res++;
//                set.add(min);
//            }
//            min = Math.min(a, b)*i;
//            i++;
//        }
//        System.out.println(res);
//    }
//}

//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine().trim();
//
//        if(str.length() == 0 || str.equals("")){
//            System.out.print(0);
//        }
//
//        int res = 0;
//        int tmp1 = 0;
//        int tmp2 = 0;
//
//        for(int i = 0; i < str.length(); i++) {
//
//            if(str.charAt(i) == '('){
//                tmp1++;
//            }else{
//                res = Math.max(res, tmp1);
//                tmp1 = 0;
//            }
//
//            if(str.charAt(i) == ')'){
//                tmp2++;
//            }else{
//                res = Math.max(res, tmp2);
//                tmp2 = 0;
//            }
//
//        }
//
//        res = Math.max(tmp1, tmp2);
//
//        System.out.println(res);
//    }
//}


//import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int a = in.nextInt();
//        int b = in.nextInt();
//        int res = 0;
//        for(int i = 1; i <= a; i++){
//            for(int j = 1; j <= b; j++){
//                if((Math.sqrt(a)+ Math.sqrt(b))*(Math.sqrt(a)+ Math.sqrt(b))
//                        == (int)(Math.sqrt(a)+ Math.sqrt(b))*(Math.sqrt(a)+ Math.sqrt(b))){
//                    res++;
//                }
//            }
//        }
//        System.out.println(res);
//        //System.out.println(b - a);
//    }
//}

//import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        if(str.length() == 0 || str.equals("")){
//            System.out.print(0);
//        }
//        int res = 0;
//        int tmp = 0;
//        int start = -1;
//        for(int i = 0; i < str.length(); i++){
//            if(str.charAt(i) == '('){
//                tmp++;
//            }else{
//                res = Math.max(res, tmp);
//                tmp = 0;
//                start = -1;
//            }
//        }
//        System.out.println(res);
//    }
//}



//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int x1 = in.nextInt();
//        int k1 = in.nextInt();
//
//        int x2 = in.nextInt();
//        int k2 = in.nextInt();
//
//        String s1 = "";
//        for(int i = 0; i < k1; i++){
//            s1 = s1 + x1;
//        }
//        String s2 = "";
//        for(int i = 0; i < k2; i++){
//            s2 = s2 + x2;
//        }
//        System.out.println(s1.compareTo(s2));
//        System.out.println("10".compareTo("5"));
//    }
//}




//
//import java.io.*;
//import java.util.*;
//class Test {
//}
//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner in = new Scanner(System.in);
//        String key = in.nextLine();
//
//        int res = 0;
//
//        if(key.length() <= 4){
//            res = res + 5;
//        }else if(key.length() >= 5 && key.length() <= 7){
//            res = res + 10;
//        }else if(key.length() >= 8){
//            res = res + 25;
//        }
//
//        boolean s = false;
//        boolean b = false;
//
//        for(int i = 0; i < key.length(); i++){
//            if(key.charAt(i) >= 'a' && key.charAt(i) <= 'z'){
//                s = true;
//            }
//            if(key.charAt(i) >= 'A' && key.charAt(i) <= 'Z'){
//                b = true;
//            }
//            if(s && b){
//                res = res + 20;
//            }else if(!s && !b){
//                res = res + 0;
//            }else if(s || b){
//                res = res + 10;
//            }
//        }
//
//        boolean nf = false;
//        int count = 0;
//        for(int i = 0; i < key.length(); i++){
//            if(key.charAt(i) >= '0' && key.charAt(i) <= '9'){
//                nf = true;
//                count++;
//            }
//        }
//        if(count == 0){
//            res = res + 0;
//        }else if(count == 1){
//            res = res + 10;
//        }else {
//            res = res + 20;
//        }
//
//        count = 0;
//
//        boolean syb = false;
//        for(int i = 0; i < key.length(); i++){
//            if(!(key.charAt(i) >= '0' && key.charAt(i) <= '9')
//                    && !(key.charAt(i) >= 'a' && key.charAt(i) <= 'z')
//                    && !(key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')){
//                syb = true;
//                count++;
//            }
//        }
//        if(count == 0){
//            res = res + 0;
//        }else if(count == 1){
//            res = res + 10;
//        }else{
//            res = res + 25;
//        }
//
//        if(s && b && nf && syb){
//            res = res + 5;
//        }else if((s || b) && nf && syb){
//            res = res + 3;
//        }else if((s || b) && nf){
//            res = res + 2;
//        }
//
//        if(res >= 90){
//            System.out.println("VERY_SECURE");
//        }else if(res >= 80 && res < 90){
//            System.out.println("SECURE");
//        }else if(res >= 70 && res < 80){
//            System.out.println("VERY_STRONG");
//        }else if(res >= 60 && res < 70){
//            System.out.println("STRONG");
//        }else if(res >= 50 && res < 60){
//            System.out.println("AVERAGE");
//        }else if(res >= 25 && res < 50){
//            System.out.println("WEAK");
//        }else if(res >= 0 && res < 25){
//            System.out.println("VERY_WEAK");
//        }
//
//
//    }
//}

//import java.util.*;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++){
//            arr[i] = in.nextInt();
//        }
//        int[] dp = new int[n];
//        int t = 0;
//
//        for (int i = 0; i < dp.length; i++) {
//            if(arr[i] == 0){
//                t = 0;
//            }else {
//                dp[i] = t == 0 ? arr[i] : (arr[i]^t);
//                t = dp[i];
//            }
//        }
//
//        int res = 0;
//        int index = 0;
//        while (index < arr.length) {
//            if (dp[index] == 0) {
//                res++;
//                index++;
//            }else {
//                int j = index;
//                index++;
//                while (j != index && index < arr.length) {
//                    if ((arr[j]^dp[index]) == 0){
//                        res++;
//                    }
//                    index++;
//                }
//            }
//        }
//
//
//
//        System.out.println(res);
//    }
//
//}

//import java.util.*;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++){
//            arr[i] = in.nextInt();
//        }
//        int k = 0;
//        int start = -1;
//        int tmp = -1;
//        for(int i = 0; i < n; i++){
//            if(arr[i] == 0){
//                k++;
//                start = -1;
//            }else{
//                if(start == -1){
//                    tmp = arr[i];
//                    start = i;
//                }else{
//                    tmp = (tmp^arr[i]);
//                }
//                if(tmp == 0){
//                    k++;
//                    start = -1;
//                }
//            }
//        }
//        System.out.println(k);
//    }
//
//}


//import java.util.*;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++){
//            arr[i] = in.nextInt();
//        }
//        int k = 0;
//        int start = -1;
//        HashMap<Integer, Integer> map = null;
//        for(int i = 0; i < n; i++){
//            if(arr[i] == 0){
//                k++;
//                start = -1;
//            }else{
//                if(start == -1){
//                    map = new HashMap<Integer, Integer>();
//                    start = i;
//                    map.put(arr[i], 1);
//                }else{
//                    if(map.containsKey(arr[i])){
//                        map.put(arr[i], map.get(arr[i]) + 1);
//                    }else{
//                        map.put(arr[i], 1);
//                    }
//                }
//                if(check(map, arr, start, i)){
//                    k++;
//                    start = -1;
//                }
//            }
//        }
//        System.out.println(k);
//    }
//    public static boolean check(HashMap<Integer, Integer> map, int[] arr, int i, int j){
//        for(int z = i; z <= j; z++){
//            if((map.get(arr[z])&1) != 0){
//                return false;
//            }
//        }
//        return true;
//    }
//}




//import java.util.*;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        if(str.length() == 0 || str == ""){
//            System.out.println(0);
//            return;
//        }
//        ArrayList<Integer> cl = new ArrayList<Integer>();
//        ArrayList<Character> al = new ArrayList<Character>();
//        for(int i = 0; i < str.length(); i++){
//            al.add(str.charAt(i));
//        }
//        while(al.size() != 0){
//            al.remove(0);
//            int t = 0;
//            int index = -1;
//            for(int i = al.size() - 1; i >= 0; i--){
//                char c = al.get(i);
//                al.remove(i);
//                if (check(al)) {
//                    t++;
//                    index = i;
//                }
//                al.add(i, c);
//            }
//            al.remove(index);
//            cl.add(t);
//        }
//        int res = 1;
//        for (int i = 0; i < cl.size(); i++) {
//            res = res * cl.get(i);
//        }
//        System.out.println(res);
//
//    }
//    public static boolean check(ArrayList<Character> al){
//        if (al.size() == 0) {
//            return true;
//        }
//        int count = 0;
//        for(int i = 0; i < al.size(); i++){
//            if (al.get(i) == '(') {
//                count++;
//            }
//            if (al.get(i) == ')' && --count < 0) {
//                return false;
//            }
//        }
//        return count == 0 ;
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++){
//            arr[i] = i + 1;
//        }
//        int[] res = new int[1];
//        backtrack(new ArrayList<Integer>(), arr, 0, res);
//        System.out.println(2 * res[0]);
//    }
//    public static void backtrack(List<Integer> tempList, int [] nums, int start,int[] res){
//        if (tempList.size() == 4){
////            for (int i = 0; i < 4; i++) {
////                System.out.print(tempList.get(i) + " ");
////            }
////            System.out.println();
//            int a = tempList.get(0);
//            int b = tempList.get(1);
//            int c = tempList.get(2);
//            int d = tempList.get(3);
//
//            if (((a^b) == (c^d)) || ((a^c) == (b^d))) {
//                res[0]++;
////                System.out.println(a + " ," + b + " " + c + " " + d);
////                if ((a == b) && (b == c) && (c != d)) {
////                    res[0]++;
////                }
//            }
//        }else {
//
//            for (int i = start; i < nums.length; i++) {
//                tempList.add(nums[i]);
//                backtrack(tempList, nums, i, res); // not i + 1 because we can reuse same elements
//                tempList.remove(tempList.size() - 1);
//            }
//        }
//    }
//
//}




//import java.util.*;

//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        ArrayList<Integer> cl = new ArrayList<Integer>();
//        ArrayList<Character> al = new ArrayList<Character>();
//        for(int i = 0; i < str.length(); i++){
//            al.add(str.charAt(i));
//        }
//        while(al.size() != 0){
//            al.remove(0);
//            int t = 0;
//            int index = -1;
//            for(int i = al.size() - 1; i >= 0; i--){
//                char c = al.get(i);
//                al.remove(i);
//
//                if (check(al)) {
//                    for (char p : al) {
//                        System.out.print(p + " ,");
//                    }
//                    System.out.println();
//
//                    t++;
//                    index = i;
//                }
//                al.add(i, c);
//            }
//            System.out.println("t :" + t);
//            al.remove(index);
//            cl.add(t);
//        }
//        int res = 1;
//        for (int i = 0; i < cl.size(); i++) {
//            res = res * (cl.get(i) == 0 ? 1 : cl.get(i));
//        }
//        System.out.println(res);
//
//    }
//    public static boolean check(ArrayList<Character> al){
//        if (al.size() == 0) {
//            return true;
//        }
//        int count = 0;
//        for(int i = 0; i < al.size(); i++){
//            if (al.get(i) == '(') {
//                count++;
//            }
//            if (al.get(i) == ')' && --count < 0) {
//                return false;
//            }
//        }
//        return count == 0 ;
//    }
//}


//import java.util.*;
//
//public class Main {
//    public static void main(String args[]) {
//
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        double[] arr = new double[n];
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = in.nextDouble();
//        }
//        double min = arr[0];
//        double max = arr[n - 1];
//        if(max - min <= 180.0){
//            System.out.println(max - min);
//        }else{
//            double s180 = getS180(arr, min);
//            double b180 = getB180(arr, min);
//            if(s180 == -1){
//                System.out.println(360.0 - (b180 - min));
//            }else {
//                System.out.println(Math.max(s180 - min,360.0 - (b180 - min)));
//            }
//        }
//        System.out.println("xxx: " + getB180pro(arr, 10.0));
//    }
//
//    public static double getB180pro(double[] arr, double min){
//        int l = 1;
//        int r = arr.length - 1;
//        while (l != r) {
//            int mid = (l + r)/2;
//            if (mid + 1 < arr.length && arr[mid] - min <= 180 && arr[mid  + 1] > 180) {
//                return arr[mid];
//            }else if(arr[]){
//
//            }else if (arr[mid] - min > 180){
//                r = mid - 1;
//            }
//        }
//        return l;
//
//    }
//    public static double getS180pro(double[] arr, double min){
//        int l = 1;
//        int r = arr.length - 1;
//        while (l != r) {
//            int mid = (l + r)/2;
//            if (arr[mid] <= 180) {
//                l = mid + 1;
//            }else {
//                r = mid - 1;
//            }
//        }
//        return l != arr.length - 1 ? arr[l] : -1;
//
//    }
//
//    public static double getB180(double[] arr, double min){
//        for(int i = arr.length - 1; i > 0; i--){
//            if(arr[i] - min > 180.0 && arr[i - 1]  - min <= 180.0 && i - 1 != 0){
//                return arr[i];
//            }
//        }
//        return -1.0;
//    }
//    public static double getS180(double[] arr, double min){
//        for(int i = arr.length - 1; i > 0; i--){
//            if(arr[i] - min > 180.0 && arr[i - 1]  - min <= 180.0 && i - 1 != 0){
//                return arr[i - 1];
//            }
//        }
//        return -1.0;
//    }
//}



//public class Main {
//    public static void main(String args[]) {
////        Scanner cin = new Scanner(System.in);
////        int a = cin.nextInt();
////        int b = cin.nextInt();
////
////        System.out.println(0);
//
//        solution s = new solution();
//        System.out.println(s.secretCodeOfTerrorist(4, new int[]{1,3,5,7}));
//        System.out.println(s.secretCodeOfTerrorist(3, new int[]{1,3,27}));
//        System.out.println(s.secretCodeOfTerrorist(2, new int[]{1,502}));
//
//        int[][] arr = {{1,1},{-1,1},{2,3}};
//        System.out.println(minNumberOfLines(3, 0, 0, arr));
//
////        int[][] arr = {{2,2},{3,3},{-4, -8},{8, 8}, {-1,-1}, {5,8}};
////        System.out.println(minNumberOfLines(6,0,0,arr));
//
//    }
//
//    // zhongxing 1
//    public static int minNumberOfLines(int num, int x, int y, int[][] arr){
//
//        HashSet<Integer> set = new HashSet<Integer>();
//        int res = 0;
//
//        for (int i = 0; i < num; i++) {
//            if (!set.contains(i)){
//
//                double k = (x - arr[i][0])/(y - arr[i][1]);
//                double b = y - k * x;
//
//                res++;
//                set.add(i);
//
//                for(int j = 0;  j < num; j++){
//                    if (j != i) {
//                        if (!set.contains(j) && arr[j][1] == arr[j][0] * k + b) {
//                            set.add(j);
//                        }
//                    }
//
//                }
//            }
//        }
//        return  res;
//
//    }
//
//}
//
//// zhongxing 2
//class solution {
//    public int secretCodeOfTerrorist(int num, int[] secretCode){
//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < num; i++) {
//            map.put(i, secretCode[i]);
//        }
//        while (map.size() != 1) {
//
//            int arrcode = 0;
//            for (Map.Entry<Integer , Integer> e : map.entrySet()) {
//                int v = (int)(e.getValue());
//                arrcode += v;
//            }
//            arrcode = arrcode/2;
//
//            int pass = -1;
//            int t = Integer.MAX_VALUE;
//            for (Map.Entry<Integer , Integer> e : map.entrySet()) {
//                int v = (int)(e.getValue());
//                if (Math.abs(arrcode - v) < t) {
//                    t = Math.abs(arrcode - v);
//                    pass = (int)(e.getKey());
//                }
//            }
//            map.remove(pass);
//
//        }
//        int res = 0;
//        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
//            res = (int)e.getValue();
//        }
//        return res;
//    }
//}



///*
//4
//27 27 0 2
//58 88 -8 -1
//-22 7 1 -1
//-38 -26 5 9
//
//2
//0 0 1 0
//2 0 -1 0
// */
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {//注意while处理多个case
//            int n = Integer.valueOf(in.nextLine());
//            String[] ps = new String[n];
//            for(int i = 0; i < ps.length; i++){
//                ps[i] = in.nextLine();
//            }
//            String[] s = getS(ps);
//            System.out.println(s[0]);
//            System.out.println(s[1]);
//            String res = getR(s[0], s[1]);
//            System.out.println(res);
//        }
//    }
//
//    public static String getR(String p1, String p2){
//
//        String[] sarr1 = p1.split(" ");
//        String[] sarr2 = p2.split(" ");
//
//
//        Double x1 = Double.valueOf(sarr1[0]);
//
//        Double y1 = Double.valueOf(sarr1[1]);
//
//        Double vx1 = Double.valueOf(sarr1[2]);
//
//        Double vy1 = Double.valueOf(sarr1[3]);
//
//
//        Double x2 = Double.valueOf(sarr2[0]);
//
//        Double y2 = Double.valueOf(sarr2[1]);
//
//        Double vx2 = Double.valueOf(sarr2[2]);
//
//        Double vy2= Double.valueOf(sarr2[3]);
//
//        double dism = getDisDouble(x1, y1, x2, y2);
//
//        double d = dism;
//        double res = dism;
//        double t = 0;
//
//
//        double tx1 = 0;
//        double ty1 = 0;
//        double tx2 = 0;
//        double ty2 = 0;
//
//
//        double rt = 0;
//
//        while (d <= dism) {
//            if (d < res) {
//                rt = t;
//                res = d;
//            }
//            tx1 = x1 + vx1 * t;
//            ty1 = y1 + vy1 * t;
//            tx2 = x2 + vx2 * t;
//            ty2 = y2 + vy2 * t;
//            d = getDisDouble(tx1, ty1, tx2, ty2);
//
//            t = t + 0.0001;
//        }
//
//
//
//        String v1 = String.format("%.2f", rt);
//        String v2 = String.format("%.2f", res);
//
//        return v1 + " " + v2;
//    }
//
//
//    public static double getDisDouble(Double x1, Double y1, Double x2, Double y2) {
//        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
//    }
//
//    public static String[] getS(String[] ps){
//        int dis = 0;
//        String[] res = new String[2];
//        for(int i = 0; i < ps.length - 1; i++){
//            String[] sarr1 = ps[i].split(" ");
//            for(int j = i + 1; j < ps.length; j++){
//                String[] sarr2 = ps[j].split(" ");
//                int x1 = Integer.valueOf(sarr1[0]);
//                int y1 = Integer.valueOf(sarr1[1]);
//                int x2 = Integer.valueOf(sarr2[0]);
//                int y2 = Integer.valueOf(sarr2[1]);
//                int tmp = getDisSq(x1, y1, x2, y2);
//                if(tmp > dis){
//                    res[0] = ps[i];
//                    res[1] = ps[j];
//                    dis = tmp;
//                }
//            }
//        }
//        return res;
//    }
//    public static int getDisSq(int x1, int y1, int x2, int y2){
//        return (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2);
//    }
//}





//import java.io.*;
//import java.util.*;
//class Test {
//}
//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner cin = new Scanner(System.in);
//        int a = cin.nextInt();
//        int b = cin.nextInt();
//
//        int res = 0;
//
//        int t1 = 0;
//        int t2 = 0;
//        while(a != 0 || b != 0){
//            t1 = a == 0 ? 0 : (a&1);
//            t2 = b == 0 ? 0 : (b&1);
//            if((t1^t2) == 1){
//                res++;
//            }
//            a = (a >>> 1);
//            b = (b >>> 1);
//        }
//
//        System.out.println(res);
//    }
//}


//
//import java.io.*;
//import java.util.*;
//class Test {
//}
//public class Main
//{
//    public static void main(String args[])
//    {
////        int res = 0;
////        int a = -1;
////        while(a != 0){
////            res++;
////            a = (a >>> 1);
////
////        }
////        System.out.println(res);
//
//
////        Scanner cin = new Scanner(System.in);
////        String str1 = cin.nextLine();
////        String str2 = cin.nextLine();
////        int[][] dp = new int[str1.length()][str2.length()];
////        dp[0][0] = str1.charAt(0) == str2.charAt(0) ? 1 : 0;
////        int res = 0;
////        for(int i = 1; i < str1.length(); i++){
////            dp[i][0] = str2.charAt(0) == str1.charAt(i) ? 1 : 0;
////            res = Math.max(res, dp[i][0]);
////        }
////        for(int i = 1; i < str2.length(); i++){
////            dp[0][i] = str1.charAt(0) == str2.charAt(i) ? 1 : 0;
////            res = Math.max(res, dp[0][i]);
////        }
////
////        for(int i = 1; i < str1.length(); i++){
////            for(int j = 1; j < str2.length(); j++){
////                if(str1.charAt(i) == str2.charAt(j)){
////                    dp[i][j] = dp[i - 1][j - 1] + 1;
////                }
////                res = Math.max(res, dp[i][j]);
////            }
////        }
////        System.out.println(res);
//    }
//}
//import java.io.*;
//import java.util.*;
//import java.text.*;
//import java.math.*;
//import java.util.regex.*;
//
//public class Main {
//
//
//    /*请完成下面这个函数，实现题目要求的功能
//    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
//    ******************************开始写代码******************************/
//    static boolean Check24(int[] data) {
//        boolean[] res = new boolean[1];
//        res[0] = false;
//        Arrays.sort(data);
//        for (int i = 0; i <= 3; i++) {
//            for (int j = i + 1; j <=3; j++) {
//                int tmp = data[i];
//                data[i] = data[j];
//                data[j] = tmp;
//                dfs(data[0], data[1],1,data,res);
//                tmp = data[i];
//                data[i] = data[j];
//                data[j] = tmp;
//            }
//
//        }
//
//        return res[0];
//    }
//    public static void dfs(int sum,int next,int index, int[] arr,boolean[] res)
//    {
//        if(index == 3)
//        {
//
//            if(sum+next == 24 || sum-next== 24 || sum*next == 24) {
//                res[0] = true;
//                return;
//            }
//            if(next!=0 && (sum%next==0) && sum/next==24){
//                res[0] = true;
//                return;
//            }
//            return;
//        }
//
//        dfs(sum+next,arr[index+1],index+1,arr,res);
//        dfs(sum-next,arr[index+1],index+1,arr,res);
//        dfs(sum*next,arr[index+1],index+1,arr,res);
//
//        if(next != 0 && (sum%next == 0)) {
//            dfs(sum / next, arr[index + 1], index + 1, arr, res);
//        }
//
//        dfs(sum,next+arr[index+1],index+1,arr,res);
//        dfs(sum,next-arr[index+1],index+1,arr,res);
//        dfs(sum,next*arr[index+1],index+1,arr,res);
//
//        if(arr[index+1]!=0 && (next%arr[index+1] == 0)) {
//            dfs(sum, next / arr[index + 1], index + 1, arr, res);
//        }
//    }
//
//    /******************************结束写代码******************************/
//
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        String[] res;
//
//        int _data_rows = 0;
//        int _data_cols = 0;
//        _data_rows = Integer.parseInt(in.nextLine().trim());
//        _data_cols = Integer.parseInt(in.nextLine().trim());
//
//        int[][] _data = new int[_data_rows][_data_cols];
//        for(int _data_i=0; _data_i<_data_rows; _data_i++) {
//            for(int _data_j=0; _data_j<_data_cols; _data_j++) {
//                _data[_data_i][_data_j] = in.nextInt();
//
//            }
//        }
//
//        if(in.hasNextLine()) {
//            in.nextLine();
//        }
//
//        if(_data_cols == 4){
//            for(int res_i=0; res_i < _data_rows; res_i++) {
//                System.out.println(Check24(_data[res_i]));
//            }
//        }
//
//
//    }
//}




//import java.io.*;
//import java.util.*;
//import java.text.*;
//import java.math.*;
//import java.util.regex.*;
//
//public class Main {
//
//
//    /*请完成下面这个函数，实现题目要求的功能
//    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
//    ******************************开始写代码******************************/
//    static int GetAn(int a1, int a2, int a3, long n) {
//        if(n == 1){
//            return a1%10000;
//        }
//        if(n == 2){
//            return a2%10000;
//        }
//        if(n == 3){
//            return a3%10000;
//        }
//
//        int res = 0;
//        for(int i = 4; i <= n; i++){
//            res = (a1 + a2 + a3)%10000;
//            a1 = a2%10000;
//            a2 = a3%10000;
//            a3 = res;
//
//        }
//        return res;
//
//    }
//    /******************************结束写代码******************************/
//
//
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
////        int res;
////
////        int _a1;
////        _a1 = Integer.parseInt(in.nextLine().trim());
////
////        int _a2;
////        _a2 = Integer.parseInt(in.nextLine().trim());
////
////        int _a3;
////        _a3 = Integer.parseInt(in.nextLine().trim());
////
////        long _n;
////        _n = Long.parseLong(in.nextLine().trim());
////
////        res = GetAn(_a1, _a2, _a3, _n);
////        System.out.println(String.valueOf(res));
//        int res;
//
//        int _a1;
//        _a1 = in.nextInt();
//
//        int _a2;
//        _a2 = in.nextInt();
//
//        int _a3;
//        _a3 = in.nextInt();
//
//        long _n;
//        _n = in.nextLong();
//
//        res = GetAn(_a1, _a2, _a3, _n);
//        System.out.println(String.valueOf(res));
//    }
//}
//import java.io.*;
//import java.util.*;
//
//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner in = new Scanner(System.in);
//        int n = Integer.valueOf(in.nextLine());
//        String[] strs = new String[n];
//        for(int z = 0; z < n; z++){
//            strs[z] = in.nextLine();
//        }
//
//        int[] rs = new int[n];
//
//        for(int z = 0; z < n; z++){
//            char[] carr = strs[z].toCharArray();
//            int res = 0;
//
//            for(int i = 0; i < carr.length; i++){
//                if(carr[i] == 'd'){
//                    int a = -1;
//                    int b = -1;
//                    for(int j = i; j < carr.length; j++){
//                        if(carr[j] == 'j'){
//                            a = 0;
//                        }
//                        if(carr[j] == 'i'){
//                            b = 0;
//                        }
//                    }
//                    if(a == 0 && b == 0){
//                        res++;
//                    }
//                }
//            }
//            rs[z] = res;
//        }
//
//        for (int i = 0; i < rs.length; i++) {
//            System.out.println(rs[i]);
//        }
//    }
//
//}
//import java.io.*;
//import java.util.*;
//
//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        ArrayList<String> al = new ArrayList<String>();
//        for(int i = 0; i < n; i++){
//            al.add(in.nextLine());
//        }
//        Collections.sort(al);
//
////        int res = 0;
////        int i = 0;
////        while (i < al.size()){
////            String cur = al.get(i);
////            String data = cur.split(" ")[0];
////            String time = cur.split(" ")[1];
////            if(data.compareTo("03:00:00") < 0){
////                i++;
////                continue;
////            }
////            if(data >= "03:00:00" && (i + 1 != al.size())){
////
////            }
////        }
//    }
//    public int getRes(String s1, String s2){
//
//    }
//}
//import java.util.*;
//
//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner in = new Scanner(System.in);
//
//        int n = in.nextInt();
//        int[] arr = new int[n];
//
//        for(int i = 0; i < n; i++){
//            arr[i] = in.nextInt();
//        }
//
//        int sum = 0;
//        for(int i = 0; i < arr.length - 1; i++){
//            for(int j = i + 1; j < arr.length; j++){
//                sum = sum + getNum(arr[i], arr[j]);
//            }
//        }
//        System.out.print(sum);
//    }
//
//    public static int getNum(int a, int b){
//        int c = (a^b);
//        int res = 0;
//        while(c != 0){
//            if((c&1) == 1){
//                res++;
//            }
//            c = (c >>> 1);
//        }
//        return res;
//    }
//}



//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int len = in.nextInt();
//        int[] arr = new int[len];
//        for (int i = 0; i < len; i++) {
//            arr[i] = in.nextInt();
//        }
//        ArrayList<Integer> al = new ArrayList<Integer>();
//
//        int index = 0;
//        int ii = 0;
//
//        int l = arr[0];
//        int temp = 0;
//        int b=0;
//        while(b < n){
//            temp = arr[ii];
//            for (int i = 0; i < l; i++) {
//                al.add(temp);
//                System.out.println(temp);
//                b++;
//            }
//            index++;
//            ii = ii+1;
//            ii = ii%4;
//            l = al.get(index);
//        }
//
//    }
//
//}

//import java.util.ArrayList;
//import java.util.Scanner;
//class Main {
//
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int len = in.nextInt();
//        int[] arr = new int[len];
//        for (int i = 0; i < len; i++) {
//            arr[i] = in.nextInt();
//        }
//        ArrayList<Integer> al = new ArrayList<Integer>();
//
//        int index = 0;
//        int ii = 0;
//
//        int l = arr[0];
//        int temp = 0;
//        int b=0;
//        int sum = 0;
//        while(b < n){
//            temp = arr[ii];
//            for (int i = 0; i < l; i++) {
//                al.add(temp);
//                System.out.println(temp);
//                sum++;
//                b++;
//            }
//            index++;
//            ii = ii+1;
//            ii = ii%4;
//            l = al.get(index);
//
//        }
//        System.out.print(sum);
//    }
//
//}

//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int res = 0;
//        int x1 = 0;
//        int x2 = 0;
//        int x3 = 0;
//        int x4 = 0;
//        int x5 = 0;
//        int x6 = 0;
//
//        int x = 0;
//        int y = 0;
//
//        int[] h = {0,5,3,1};
//
//        while(true){
//
//            x1 = in.nextInt();
//            x2 = in.nextInt();
//            x3 = in.nextInt();
//            x4 = in.nextInt();
//            x5 = in.nextInt();
//            x6 = in.nextInt();
//
//            if (x1 == 0 && x2 == 0 && x3 == 0 && x4 == 0 && x5 == 0 && x6 == 0) {
//                break;
//            }
//
//            res = x6 + x5 + x4 +(x3 + 3)/4;
//            y = 5 * x4 + h[x3%4];
//            if(x2 > y) res += (x2 - y + 8)/9;
//            x = 36 * res - 36 * x6 - 25 * x5 - 16 * x4 - 9 * x3 - 4 * x2;
//            if(x1 > x) res += (x1 - x + 35)/36;
//            System.out.println(res);
//
//            x = 0;
//            y = 0;
//
//        }
//    }
//}
/*
0 0 4 0 0 1
7 5 1 0 0 0
0 0 0 0 0 0
 */

//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int[][] matrix = new int[n][m];
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < m; j++){
//                matrix[i][j] = sc.nextInt();
//            }
//        }
//        int[][] dp = new int[n][m];
//        dp[0][0] = matrix[0][0];
//
//
//        int res1 = dp[0][0];
//        int res2 = dp[0][0];
//
//        boolean f = false;
//        for(int i = 1; i < matrix[0].length; i++){
//            dp[0][i] = dp[0][i - 1] + matrix[0][i];
//            res1 = Math.min(res1, dp[0][i]);
//        }
//        for(int i = 1; i < matrix.length; i++){
//            dp[i][0] = dp[i - 1][0] + matrix[i][0];
//            res2 = Math.min(res2, dp[0][i]);
//        }
//        int res = Math.max(res1, res2);
//
//        for(int i = 1; i < n; i++){
//            for(int j = 1; j < m; j++){
//                dp[i][j] = matrix[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
//            }
//        }
//
//
//        int x = n - 1;
//        int y = m - 1;
//
//        while(x >= 0 && y >= 0){
//            if(x - 1 >= 0 && dp[x][y] == dp[x - 1][y] + matrix[x][y]){
//                x--;
//            }else if(y - 1 >= 0 && dp[x][y] == dp[x][y - 1] + matrix[x][y]){
//                y--;
//            }
//            res = Math.min(res, dp[x][y]);
//        }
//
//        System.out.println(Math.abs(res) + 1);
//    }
//}

//import java.util.*;
//public class Main
//{
//    public static void main(String args[]) {
//        Scanner cin = new Scanner(System.in);
//        int n = cin.nextInt();
//        int[] arr = new int[n];
//        //int max = 0;
//        for (int i = 0; i < n; i++) {
//            arr[i] = cin.nextInt();
//        }
//        int[] res = new int[n];
//        for(int i = n - 1; i >= 0; i--){
//            int tmp = 0;
//            for(int j = i; j >= 0; j--){
//                if(arr[j] > arr[i]){
//                    tmp++;
//                }
//            }
//            res[i] = tmp;
//        }
//
//
//        int max = Integer.MIN_VALUE;
//        for(int i = 0; i < res.length; i++){
//            if(arr[i] >= max){
//                res[i] = 0;
//                max = arr[i];
//            }else{
//                for(int j = i - 1; j >= 0; j --){
//                    if(arr[i] < arr[j]){
//                        if (res[j] == 0 || arr[j] != arr[i] + 1) {
//                            res[i]++;
//                            continue;
//                        }else {
//                            res[i] = res[i] + res[j] + 1;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
//
//    }
//}



//import java.lang.reflect.Array;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args){
//        Scanner cin = new Scanner(System.in);
//        int n = cin.nextInt();
//        int m = cin.nextInt();
//
//        int a = cin.nextInt();
//        int b = cin.nextInt();
//        int c = cin.nextInt();
//        int d = cin.nextInt();
//
//        int x = cin.nextInt();
//        int y = cin.nextInt();
//        int z = cin.nextInt();
//
//        int max = 0;
//        int index = 1;
//        for (int i = n, j = m; i - a >= 0 && j - b >= 0; i = i - a,j = j - b) {
//            int sum = 0;
//            sum = sum + index * x;
//            index++;
//            max = Math.max(max, sum);
//            int uindex = 1;
//            for (int u = j; u - c >= 0; u = u - c) {
//                sum = sum + uindex * y;
//                uindex++;
//                max = Math.max(max, sum);
//                int vindex = 1;
//                for (int v = i; v - d >= 0; v = v - d) {
//                    sum = sum + vindex * z;
//                    vindex++;
//                    max = Math.max(max, sum);
//                }
//            }
//
//        }
//        System.out.println(max);
//
//    }
//}

//public class Main {
///** 请完成下面这个process函数，实现题目要求的功能 **/
//    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
//
//    private static int process(Model[] ms, int bl, int bw, int bh) {
//
//        return (int)Math.random()*ms.length;
//    }
//
//    public static void main(String args[]){
//        Scanner scanner = new Scanner(System.in);
//
//
//        int blength = scanner.nextInt();
//        int bwidth = scanner.nextInt();
//        int bheight = scanner.nextInt();
//
//        int max = Math.max(blength, Math.max(bwidth, bheight));
//
//        int n = scanner.nextInt();
//
//        if (blength <= 0 || bwidth <= 0 || bheight <= 0 || n <= 0) {
//            System.out.println(-1);
//            return;
//        }
//
//        Model[] ms = new Model[n];
//
//        for(int i = 0; i< n; i++){
//            Model item = new Model();
//            item.price = scanner.nextInt();
//            item.length = scanner.nextInt();
//            item.width = scanner.nextInt();
//            item.height = scanner.nextInt();
//            int min = Math.min(item.length, Math.min(item.width, item.height));
//            if (item.price > 200 || min > max) {
//                System.out.println(-1);
//                return;
//            }
//            ms[i] = item;
//        }
//
//        System.out.println (process(ms, blength, bwidth, bheight));
//
//    }
//
//}
//class Model{
//    int price;
//    int length;
//    int width;
//    int height;
//}

//import java.util.*;
//public class Main {
//    public static void resetList(List<Integer> dataList){
//        dataList.subList(2, 4).set(0,40);
//        dataList = new ArrayList<Integer>(dataList);
//        dataList.add(50);
//    }
//    public static void setOne(List<Integer> dataList){
//        dataList.set(3, 100);
//    }
//    public static void main(String[] args) {
//        List<Integer> dataList = new ArrayList<Integer>(Arrays.asList(10,20,30,null));
//        resetList(dataList);
//        setOne(dataList);
//        int sum = 0;
//        for(Integer v:dataList){
//            sum += v;
//        }
//        System.out.println(sum);
//    }
//}
//import java.util.*;
//public class Main {
//    private static final  String flag = "0123456789abcdefGhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int n = in.nextInt();
//        int m = in.nextInt();
//        int x = in.nextInt();
//
//        System.out.println(convertN2M(n, m, x));
//
//        char[] arr = {'1', '2', '0', 0};
//        String fuck = new String(arr);
//        System.out.println(fuck);
//
//        StringBuilder sb = new StringBuilder();
//        for (char c : arr) {
//            if (c != 0) {
//                sb.append(c);
//            }
//        }
//        sb.toString();
//
//    }
//    public static String convertN2M(int n, int m, int x){
//        if (n == m) {
//            return String.valueOf(x);
//        }
//        String str = String.valueOf(x);
//
//        if (n == 10) {
//            return fuckKandMod(m, x);
//        }else {
//            int num = 0;
//            for (int i = str.length() - 1; i >= 0; i--) {
//                num += flag.indexOf(str.charAt(i)) * Math.pow(n, str.length() - 1 - i);
//            }
//            //return String.valueOf(num);
//            return fuckKandMod(m, num);
//        }
//    }
//
//    public static String fuckKandMod(int m, int x){
//        String res = "";
//        while (x != 0) {
//            res += flag.charAt((x%m));
//            x = x / m;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = res.length() - 1; i >= 0; i--) {
//            sb.append(res.charAt(i));
//        }
//        return sb.toString();
//    }
//
//
//
//}


//import java.util.*;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        String str = in.nextLine().trim();
//        char[] carr = str.toCharArray();
//
//        StringBuilder sb = new StringBuilder();
//
//
//        HashSet<Character> set = new HashSet<Character>();
//
//        int[] map = new int[11];
//
//        for(int i = 0; i < carr.length; i++){
//            map[carr[i] - '0']++;
//        }
//        for(int i = 0; i < carr.length - 1; i++){
//            if (set.contains(carr[i]) || (carr[i] < carr[i + 1] && map[carr[i] - '0'] > 1)) {
//                map[carr[i] - '0']--;
//                carr[i] = '*';
//            }else {
//                set.add(carr[i]);
//            }
//        }
//        carr[carr.length - 1] = set.contains(carr[carr.length - 1]) ? '*' : carr[carr.length - 1];
//
//        for(int i = 0; i < carr.length; i++){
//            if(carr[i] != '*'){
//                sb.append(carr[i]);
//            }
//        }
//
//        System.out.println(sb.toString());
//
//    }
//}

//import java.util.*;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int a = in.nextInt();
//        int b = in.nextInt();
//        //String str = in.nextLine();
//        int c = in.nextInt();
//
//        System.out.println(baseNum(a,b,c).trim());
//    }
//    public static String baseString(int num,int base){
//
//        StringBuffer str = new StringBuffer("");
//        String digths = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        Stack<Character> s = new Stack<Character>();
//        while(num != 0){
//            s.push(digths.charAt(num%base));
//            num/=base;
//        }
//        while(!s.isEmpty()){
//            str.append(s.pop());
//        }
//        return str.toString();
//    }
//
//    public static String baseNum(int srcBase,int destBase,int input){
//
//        String num = input+"";
//
//        if(srcBase == destBase){
//            return num;
//        }
//        String digths = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        char[] chars = num.toCharArray();
//        int len = chars.length;
//        if(destBase != 10){
//            num = baseNum(srcBase,10,Integer.parseInt(num));
//        }else{
//            int n = 0;
//            for(int i = len - 1; i >=0; i--){
//                n+=digths.indexOf(chars[i])*Math.pow(srcBase, len - i - 1);
//            }
//            return n + "";
//        }
//        return baseString(Integer.valueOf(num),destBase);
//    }
//}


//class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        if(str == null || str.length() == 0){
//            System.out.println("ERROR!");
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < str.length(); i++){
//            if((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') ||
//                    (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') ||
//                    (str.charAt(i) >= '0' && str.charAt(i) <= '9')){
//                if((i&1) == 0){
//                    sb.append(str.charAt(i));
//                }
//
//            }else{
//                System.out.println("ERROR!");
//                return;
//            }
//        }
//        System.out.println(sb.toString());
//
//    }
//}

//    int i = 0;
//    Integer i1 = 0;
//    Integer i2 = new Integer(0);
//        System.out.println(i == i1);
//                System.out.println(i == i2);
//                System.out.println(i1 == i2);

//    public static String longestCommonPrefix(String[] strs) {
//        if(strs == null || strs.length == 0){
//            return "";
//        }
//        String prefix = strs[0];
//        int index = 0;
//        while(index < strs.length){
//            while(strs[index].indexOf(prefix) != 0){
//                prefix = prefix.substring(0, prefix.length() - 1);
//            }
//            index++;
//        }
//        return prefix;
//    }
//

//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner cin = new Scanner(System.in);
//
//        int[] arr = new int[10];
//        for(int i = 0; i < 10; i++){
//            arr[i] = cin.nextInt();
//        }
//        int ok = cin.nextInt() + 30;
//        int res = 0;
//        for(int i = 0; i < 10; i++){
//            if(ok >= arr[i]){
//                res++;
//            }
//        }
//        System.out.println(res);
//
//        String a = new String("abc");
//        String b = new String("abc");
//
//        System.out.println(a == b);
//        System.out.print(a.equals(b));
//
//    }
//}

//import java.io.*;
//import java.util.*;
//public class Main
//{
//    public static void main(String args[]){
//        Scanner cin = new Scanner(System.in);
//
//        int len1 = cin.nextInt();
//        int[] arr1 = new int[len1];
//        int[] h = new int[len1];
//        int sum = 0;
//        for(int i = 0; i < len1; i++){
//            arr1[i] = cin.nextInt();
//            sum += arr1[i];
//            h[i] = sum;
//        }
//
//        int len2 = cin.nextInt();
//        int[] arr2 = new int[len2];
//        for(int i = 0; i < len2; i++){
//            arr2[i] = cin.nextInt();
//        }
//        for(int i = 0; i < len2; i++){
//            for(int j = 0; j < len1; j++){
//                if(arr2[i] <= h[j]){
//                    System.out.println(j + 1);
//                    break;
//                }
//            }
//        }
//
//    }
//}

//import java.io.*;
//import java.util.*;
//public class Main
//{
//    public static void main(String args[]){
//        Scanner cin = new Scanner(System.in);
//        int len = cin.nextInt();
//        int[] arr = new int[len];
//        for(int i = 0; i < len; i++){
//            arr[i] = cin.nextInt();
//        }
//        Arrays.sort(arr);
//        //sort(arr, 0, arr.length - 1);
//        for(int i = 0; i < arr.length; i++){
//            if(i == 0 || arr[i] != arr[i - 1]){
//                System.out.print(arr[i] + " ");
//            }
//        }
//
//    }
//}
//import java.util.Scanner;
//
//class Main {
//    public static void main(String[] args){
//        String str = "abcde";
//        System.out.print(str.substring(1,3));
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = sc.nextInt();
//        }
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < arr.length - 1; i++) {
//            int tmp1 = arr[i];
//            for(int j = i + 1; j < arr.length; j++){
//                int tmp2 = tmp1 * arr[j];
//                for(int z = 0; z < arr.length; z++){
//                    if(z != i && z != j){
//                        max = Math.max(max, tmp2 * arr[z]);
//                    }
//                }
//            }
//        }
//        System.out.println(max);
//    }
//}

//public class Main {
//       public static void main(String[] args) {
//           String str1 = "99";
//           String str2 = "99";
//
//           long l1 = (long)(Integer.MAX_VALUE) + 123456L;
//           System.out.println(l1);
//           long l2 = 123L;
//
//           System.out.println(l1 * l2);
//
//           System.out.println(getProduce(l1 + "", l2 + ""));
//
//       }

//        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);
//
//            int hn = in.nextInt();
//            int[] h = new int[hn];
//
//            for(int i = 0; i < hn; i++){
//                h[i] = in.nextInt();
//            }
//
//
//            int wn = in.nextInt();
//            int[] w = new int[wn];
//
//            for(int i = 0; i < wn; i++) {
//                w[i] = in.nextInt();
//            }
//
//            int[] h = {2,2,3};
//            int[] w = {3,1};
//
//            Arrays.sort(h);
//            Arrays.sort(w);
//            int res = 0;
//            int n = h.length - 1;
//            for(int i = w.length - 1; i >= 0; i--){
//                if(n >= 0){
//                    if(w[i] >= h[n]){
//                        res++;
//                        n--;
//                    }
//                }
//            }
//            System.out.println(res);
//        }
//
//    public static String getProduce(String str1, String str2){
//
//        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
//            return "0";
//        }
//        char[] carr = new char[str1.length() + str2.length()];
//
//        for(int i = 0; i < carr.length; i++){
//            carr[i] = '0';
//        }
//
//        int ca = 0;
//        int tmp = 0;
//        int k = carr.length - 1;
//        for(int i = str1.length() - 1; i >= 0; i--){
//            if(str1.charAt(i) != '0'){
//                for(int j = str2.length() - 1; j >= 0; j--){
//                    k = i + j + 1;
//                    tmp = (str1.charAt(i) - '0') * (str2.charAt(j) - '0') + carr[k] - '0';
//                    carr[k--] = (char)('0' + tmp % 10);
//                    ca = tmp / 10;
//                    while(ca != 0){
//                        tmp = ca + (carr[k] - '0');
//                        carr[k--] = (char)((tmp % 10) + '0');
//                        ca = tmp / 10;
//                    }
//                }
//            }
//        }
//        int index = 0;
//        while(carr[index] == '0'){
//            carr[index++] = 0;
//            if(carr[index] != '0'){
//                break;
//            }
//        }
//        return new String(carr).trim();
//    }
//
//}

//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int len = sc.nextInt();
//
//        int[] arrx = new int[len];
//        int[] arry = new int[len];
//
//
//        for(int i = 0; i < len; i++){
//            arrx[i] = sc.nextInt();
//        }
//        for(int i = 0; i < len; i++){
//            arry[i] = sc.nextInt();
//        }
//
//
//        int[] res = new int[len];
//
//        for(int i = 1; i <= len; i++){
//            res[i - 1] = getRes(arrx, arry, i);
//        }
//
//        for(int i = 0; i < len; i++){
//            if(i == len - 1){
//                System.out.print(res[i]);
//            }else{
//                System.out.print(res[i] + " ");
//            }
//        }
//
//    }

//    public static int getRes(int[] arrx, int[] arry, int index){
//        int sum = Integer.MAX_VALUE;
//        int tmp = 0;
//
//        int maxX = Integer.MIN_VALUE;
//        int maxY = Integer.MIN_VALUE;
//
//        for(int i = 0; i < index; i++){
//            maxX = Math.max(maxX, arrx[i]);
//        }
//        for(int i = 0; i < index; i++){
//            maxY = Math.max(maxY, arry[i]);
//        }
//
//
//        for(int i = 1; i <= maxX; i++){
//            for(int j = 1; j <= maxY; j++){
//                tmp = 0;
//                for(int z = 0; z < index; z++){
//                    tmp = tmp + Math.abs(i - arrx[z]);
//                    tmp = tmp + Math.abs(j - arry[z]);
//                }
//                sum = Math.min(sum, tmp);
//            }
//        }
//        return sum;
//    }
//}