/**
 * Created by baidu on 2017/8/1.
 */
public class Matrix {
    public static void main(String[] args){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        zigzagPrintMatrix(matrix);

    }
    public static void zigzagPrintMatrix(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return;
        }
        int tx = 0;
        int ty = 0;
        int bx = m.length - 1;
        int by = m[0].length - 1;

        int dlx = 0;
        int dly = 0;
        int rdx = 0;
        int rdy = 0;

        boolean flag = true;

        while(dly != by + 1 && rdx != bx + 1){


//            System.out.println("dlx: " + dlx + " dly: " + dly + " rdx: "  + rdx + " rdy: " + rdy);
            if (flag){
                int x = dlx;
                int y = dly;
                while(x >= rdx && y <= rdy){
                    System.out.print(m[x--][y++] + " ");
                }
            }else {
                int x = rdx;
                int y = rdy;
                while (x <= dlx && y >= dly) {
                    System.out.print(m[x++][y--] + " ");
                }
            }
            flag = !flag;


            dly = dlx == bx ? dly + 1:  dly;
            dlx = dlx == bx ? dlx : dlx + 1;

            rdx = rdy == by ? rdx + 1 : rdx;
            rdy = rdy == by ? rdy : rdy + 1;



        }
    }
}
