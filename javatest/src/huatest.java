/**
 * Created by baidu on 2017/8/23.
 */
import java.util.Stack;
public class huatest {



        public static void main(String[] args) {
            System.out.println(baseNum(8,32,12345670));
        }


        public static String baseString(int num,int base){

            StringBuffer str = new StringBuffer("");
            String digths = "0123456789abcdefGhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Stack<Character> s = new Stack<Character>();
            while(num != 0){
                s.push(digths.charAt(num%base));
                num/=base;
            }
            while(!s.isEmpty()){
                str.append(s.pop());
            }
            return str.toString();
        }

        public static String baseNum(int srcBase,int destBase,int input){

            String num=input+"";

            if(srcBase == destBase){
                return num;
            }
            String digths = "0123456789abcdefGhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char[] chars = num.toCharArray();
            int len = chars.length;
            if(destBase != 10){
                num = baseNum(srcBase,10,Integer.parseInt(num));
            }else{
                int n = 0;
                for(int i = len - 1; i >=0; i--){
                    n+=digths.indexOf(chars[i])*Math.pow(srcBase, len - i - 1);
                }
                return n + "";
            }
            return baseString(Integer.valueOf(num),destBase);
        }

}
