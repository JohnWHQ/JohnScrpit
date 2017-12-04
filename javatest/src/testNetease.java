import java.util.Scanner;

/**
 * Created by baidu on 2017/8/12.
 */
public class testNetease {
//    public static void main(String[] args){
//        System.out.println("ABCDEFG".substring(2,5));
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int x = in.nextInt();
//        int f = in.nextInt();
//        int d = in.nextInt();
//        int p = in.nextInt();
//
//        if(f > (d / x)){
//            System.out.println(d/x);
//        }else{
//            System.out.println(f + (d - (f * x))/(x + p));
//        }
//
//    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int maxX = Integer.MAX_VALUE;
        int minX = Integer.MIN_VALUE;

        int maxY = Integer.MAX_VALUE;
        int minY = Integer.MIN_VALUE;

        int len = in.nextInt();
        int[] arrx = new int[len];
        for(int i = 0; i < len; i++){
            arrx[i] = in.nextInt();
            maxX = Math.max(arrx[i], maxX);
            minX = Math.min(arrx[i], minX);
        }
        int[] arry = new int[len];
        for(int i = 0; i < len; i++){
            arry[i] = in.nextInt();
            maxY = Math.max(arry[i], maxY);
            minY = Math.min(arry[i], minY);
        }

//        int rX = minX + (maxX - minX)/2;
//        int rY = minY + (maxY - minY)/2;
//
        int[] res = new int[len];
//        for(int i = 0; i < len; i++){
//            res[i] = Math.abs(rX - arrx[i]) + Math.abs(rY - arry[i]);
//        }

        for(int i = 0; i < len; i++){
            res[i] = getNum(i + 1, arrx, arry);
        }
        for(int i = 0; i < len; i++){
            if(i == len - 1){
                System.out.print(res[i]);
            }else{
                System.out.print(res[i] + " ");
            }
        }


    }
    public static int getNum(int num,int[] rows,int[] cols){
        int rmax=0;
        int cmax=0;
        for(int i=0;i<rows.length;i++){
            if(rows[i]>rmax)
                rmax=rows[i];
            if(cols[i]>cmax)
                cmax=cols[i];
        }
        int[][] res= new int[rmax][cmax];

        int min=Integer.MAX_VALUE;
        for(int i=1; i<=rmax; i++){
            for(int j=1; j<=cmax; j++){
                int sum=0;
                for(int k=0; k<num; k++){

                    sum=sum+Math.abs(rows[k]-i)+Math.abs(cols[k]-j);
                }
                res[i-1][j-1]=sum;
            }
        }
        for(int i=1;i<=rmax;i++){
            for(int j=1;j<=cmax;j++){
                if(res[i-1][j-1]<min){
                    min=res[i-1][j-1];
                }
            }
        }
        return min;

    }
}
