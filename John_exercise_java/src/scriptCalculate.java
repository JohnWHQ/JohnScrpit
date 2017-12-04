import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by baidu on 2017/8/7.
 */
public class scriptCalculate {
    public static void main(String[] args) {

    }


    // 宏观绝对阀值计算方法函数

    public static void functionMacro(){
        // 面积
        int a = 0;

        // 密度
        int d = 0;

        // 阀值
        double c = a / (d * 1.0);

        String EOL = System.getProperty("line.separator");

        // 待处理的文件
        File file = new File("5.xls");
        //

        BufferedReader reader = null;
        ArrayList<String> res = new ArrayList<String>();

        // 读取并且计算过程 得出结果存储在 res List中
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            String[] headArr = line.split(",");
            line = reader.readLine();
            int c4012 = 0;
            int c4037 = 0;
            int c4057 = 0;
            int c40128 = 0;

            for (int i = 0; i < headArr.length; i++) {
                if (headArr[i].equals("4012")) {
                    c4012 = i;
                }
                if (headArr[i].equals("4037")) {
                    c4037 = i;
                }
                if (headArr[i].equals("4057")) {
                    c4057 = i;
                }
                if (headArr[i].equals("40128")) {
                    c40128 = i;
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] scarr = line.split(",");
                String cur = "";
                cur = cur + scarr[0] + " "
                        + (Double.valueOf(scarr[c4012]) > c ? "1" : " 0" )+ " "
                        + (Double.valueOf(scarr[c4037]) > c ? "1" : " 0" ) + " "
                        + (Double.valueOf(scarr[c4057]) > c ? "1" : " 0" )+ " "
                        + (Double.valueOf(scarr[c40128]) > c ? "1" : " 0" );
                res.add(cur);

            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将List中的结果按行打印成为文件名 为 result.txt的文件保存
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result1.txt")));
            for (String s : res) {
                bw.write(s + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 中观绝对阀值计算方法函数
    public static void functionMeso(){
        // 面积
        int a = 0;

        // 密度
        int d = 0;

        // 阀值
        double c = a / (d * 1.0);

        String EOL = System.getProperty("line.separator");

        // 待处理的文件
        File file = new File("tbl_stat_byminute.xls");
        //

        BufferedReader reader = null;
        ArrayList<String> res = new ArrayList<String>();

        // 读取并且计算过程 得出结果存储在 res List中
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            String[] headArr = line.split(",");
            line = reader.readLine();
            int cEAST_NUM = 0;
            int cMIDDLE_NUM = 0;
            int cWEST_NUM = 0;

            for (int i = 0; i < headArr.length; i++) {
                if (headArr[i].equals("EAST_NUM")) {
                    cEAST_NUM = i;
                }
                if (headArr[i].equals("MIDDLE_NUM")) {
                    cMIDDLE_NUM = i;
                }
                if (headArr[i].equals("WEST_NUM")) {
                    cWEST_NUM = i;
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] scarr = line.split(",");
                String cur = "";
                cur = cur + scarr[0] + " "
                        + (Double.valueOf(scarr[cEAST_NUM]) > c ? "1" : " 0" )+ " "
                        + (Double.valueOf(scarr[cMIDDLE_NUM]) > c ? "1" : " 0" ) + " "
                        + (Double.valueOf(scarr[cWEST_NUM]) > c ? "1" : " 0" );
                res.add(cur);

            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将List中的结果按行打印成为文件名 为 result.txt的文件保存
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result2.txt")));
            for (String s : res) {
                bw.write(s + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 微观绝对阀值计算方法函数

    public static void functionMicro(){
        // 面积
        int a = 0;

        // 密度
        int d = 0;

        // 阀值
        double c = a / (d * 1.0);

        String EOL = System.getProperty("line.separator");

        // 待处理的文件
        File file = new File("tbl_video_people.xls");
        //

        BufferedReader reader = null;
        ArrayList<String> res = new ArrayList<String>();

        // 读取并且计算过程 得出结果存储在 res List中
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            String[] headArr = line.split(",");
            line = reader.readLine();
            int cPEOPLE_NUM = 0;

            for (int i = 0; i < headArr.length; i++) {
                if (headArr[i].equals("PEOPLE_NUM")) {
                    cPEOPLE_NUM = i;
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] scarr = line.split(",");
                String cur = "";
                cur = cur + scarr[0] + " " + scarr[1] + " "
                        + (Double.valueOf(scarr[cPEOPLE_NUM]) > c ? "1" : " 0" );
                res.add(cur);

            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将List中的结果按行打印成为文件名 为 result3.txt的文件保存
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result3.txt")));
            for (String s : res) {
                bw.write(s + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    // ****************************************************************************





    public static boolean isWeekend(String str){
        // to do
        return true;
    }



    // 相对宏观阀值计算方法函数

    public static void CMacro(){


        String EOL = System.getProperty("line.separator");

        // 待处理的文件
        File file = new File("5.xls");
        //

        BufferedReader reader = null;
        ArrayList<String> res = new ArrayList<String>();


        ArrayList<String> al4012_1 = new ArrayList<String>();
        ArrayList<String> al4037_1 = new ArrayList<String>();
        ArrayList<String> al4057_1 = new ArrayList<String>();
        ArrayList<String> al40128_1 = new ArrayList<String>();

        ArrayList<String> al4012_2 = new ArrayList<String>();
        ArrayList<String> al4037_2 = new ArrayList<String>();
        ArrayList<String> al4057_2 = new ArrayList<String>();
        ArrayList<String> al40128_2 = new ArrayList<String>();

        // 读取并且计算过程 得出结果存储在 res List中
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            String[] headArr = line.split(",");
            line = reader.readLine();
            int cTIME = 0;
            int cFINT_TAZID = 0;
            int cFINT_STAY = 0;



            for (int i = 0; i < headArr.length; i++) {
                if (headArr[i].equals("TIME")) {
                    cTIME = i;
                }
                if (headArr[i].equals("FINT_TAZID")) {
                    cFINT_TAZID = i;
                }
                if (headArr[i].equals("FINT_STAY")) {
                    cFINT_STAY = i;
                }

            }

            while ((line = reader.readLine()) != null) {
                String[] scarr = line.split(",");
                String cur = scarr[cTIME] + " " + scarr[cFINT_TAZID] +" "+scarr[cFINT_STAY];
                if (scarr[2].equals("4012")) {
                    if (isWeekend(scarr[cTIME]) ){
                        al4012_1.add(cur);
                    }else{
                        al4012_2.add(cur);
                    }
                }
                if (scarr[2].equals("4037")) {
                    if (isWeekend(scarr[cTIME]) ){
                        al4037_1.add(cur);
                    }else{
                        al4037_2.add(cur);
                    }
                }
                if (scarr[2].equals("4057")) {
                    if (isWeekend(scarr[cTIME]) ){
                        al4057_1.add(cur);
                    }else{
                        al4037_2.add(cur);
                    }
                }
                if (scarr[2].equals("40128")) {
                    if (isWeekend(scarr[cTIME]) ){
                        al40128_1.add(cur);
                    }else{
                        al40128_2.add(cur);
                    }
                }

            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortAl(al4012_1);
        sortAl(al4012_2);
        sortAl(al4037_1);
        sortAl(al4037_2);
        sortAl(al4057_1);
        sortAl(al4057_2);
        sortAl(al40128_1);
        sortAl(al40128_2);

        for (int i =0; i < 3; i++) {

            System.out.println("4012_weekday" + al4012_1.get(i));
        }
        for (int i =0; i < 2; i++) {
            System.out.println("4012_weekend" + al4012_2.get(i));
        }

        for (int i =0; i < 3; i++) {

            System.out.println("4037_weekday" + al4037_1.get(i));
        }
        for (int i =0; i < 2; i++) {
            System.out.println("4037_weekend" + al4037_2.get(i));
        }

        for (int i =0; i < 3; i++) {

            System.out.println("4057_weekday" + al4012_1.get(i));
        }
        for (int i =0; i < 2; i++) {
            System.out.println("4057_weekend" + al4012_2.get(i));
        }

        for (int i =0; i < 3; i++) {

            System.out.println("40128_weekday" + al40128_1.get(i));
        }
        for (int i =0; i < 2; i++) {
            System.out.println("40128_weekend" + al40128_2.get(i));
        }



    }
    public static void sortAl(ArrayList<String> al){
        Collections.sort(al, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] s1 =o1.split(" ");
                String[] s2 =o2.split(" ");
                return Integer.valueOf(s2[2]) - Integer.valueOf(s1[2]);
            }
        });
    }


}
