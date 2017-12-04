import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by baidu on 2017/8/4.
 */
public class calculate {
    public static void main(String[] args){
        System.out.println(getProduct("99", "99"));
        System.out.println(calculateString("(1+(5*6-29)/2+10*10+1)*(-1)"));
    }
    public static String getProduct(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return "0";
        }
        char[] carr1 = str1.toCharArray();
        char[] carr2 = str2.toCharArray();
        char[] res = new char[carr1.length + carr2.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = '0';
        }
        int tmp = 0;
        int ca = 0;
        int k = 0;
        for (int i = carr1.length - 1; i >= 0; i--) {
            for (int j = carr2.length - 1; j >= 0; j--){
                k = i + j + 1;
                tmp = (carr1[i] - '0') * (carr2[j] - '0') + (res[k] - '0');
                res[k--] = (char)((tmp % 10) + '0');
                ca = tmp / 10;
                while (ca != 0) {
                    tmp = ca + (res[k] - '0');
                    res[k--] = (char)((tmp % 10) + '0');
                    ca = tmp / 10;
                }
            }
        }
        int index = 0;
        while(res[index] == '0'){
            index++;
        }
        return new String(res).substring(index);
    }

    public static int calculateString(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        return calculateValue(str.toCharArray(), 0)[0];
    }
    public static int[] calculateValue(char[] carr, int i){
        Deque<String> de = new LinkedList<String>();
        int[] arr = null;
        int num = 0;
        while (i < carr.length && carr[i] != ')') {
            if (carr[i] >= '0' && carr[i] <= '9') {
                num = num * 10 + (carr[i++] - '0');
            }else if (carr[i] != '('){
                productOrPush(de, num);
                de.addLast(String.valueOf(carr[i++]));
                num = 0;
            }else {
                arr = calculateValue(carr, i + 1);
                num = arr[0];
                i = arr[1] + 1;
            }
        }
//        System.out.println(System.currentTimeMillis());
        productOrPush(de, num);
        return new int[]{getNum(de), i};
    }
    public static void productOrPush(Deque<String> de, int num){
        if(!de.isEmpty()){
            String cur = de.pollLast();
            if (cur.equals("+") || cur.equals("-")) {
                de.addLast(cur);
            }else {
                int tmp = Integer.valueOf(de.pollLast());
                num = cur.equals("*") ? (tmp * num) : (tmp / num);
            }
        }
        de.addLast(String.valueOf(num));
    }
    public static int getNum(Deque<String> de){
        int res = 0;
        boolean pos = true;
        String cur = null;
        while(!de.isEmpty()){
            cur = de.pollFirst();
            if (cur.equals("+")) {
                pos = true;
            }else if (cur.equals("-")){
                pos = false;
            }else {
                res = res + (pos ? Integer.valueOf(cur) : -Integer.valueOf(cur));
            }
        }
        return res;
    }
}
