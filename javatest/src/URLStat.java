import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by baidu on 2017/7/23.
 */
public class URLStat {
    public static void main(String[] args){

//        char[] mocUrl = new char[8];
//        Random rand = new Random();
//        File file = new File("/Users/baidu/Desktop/url.txt");
//        FileWriter fos = null;
//        try {
//            fos = new FileWriter(file, true);
//            for (int i = 0; i < 10000; i++) {
//                for (int j = 0; j < mocUrl.length; j++){
//                    mocUrl[j] = (char)(rand.nextInt(24) + 'a');
//                }
//                int n = rand.nextInt(10);
//                while (n-- != 0){
//                    fos.write(new String(mocUrl) + "\n");
//                }
//            }
//            fos.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        File file = new File("/Users/baidu/Desktop/url.txt");


        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine())  != null ) {
                if (map.containsKey(str)) {
                    map.put(str, map.get(str) + 1);
                }else {
                    map.put(str, 1);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int check = 0;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/baidu/Desktop/result.txt")));
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                bw.write(key + " " + val + "\n");
                check += val;
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(check);
    }
}
