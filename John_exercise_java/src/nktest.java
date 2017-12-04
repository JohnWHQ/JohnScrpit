import java.util.Scanner;
class nktest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] carr = str.toCharArray();
        if(carr.length == 0){
            System.out.println(0);
            return;
        }
        if(carr.length <= 2){
            System.out.println(1);
            return;
        }
        int[] res = new int[carr.length + 1];
        int n = 0;
        res[0] = -1;
        res[1] = 0;
        int index = 2;
        int cn = 0;
        int result = 0;

        while(index < carr.length){
            if(carr[index - 1] == carr[cn]){
                res[index++] = ++cn;
                n = Math.max(n, cn);
                if(str.substring(0, cn).equals(str.substring(cn, index - 1))){
                    result = Math.max(result, cn * 2);
                }
            }else if(res[cn] > 0){
                cn = res[cn];
            }else{
                res[index++] = 0;
            }
        }

        System.out.println(result);
        System.out.println("n:  " + n);
    }
}