import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by baidu on 2017/7/13.
 */
public class IOtest {
    public static void main(String[] args) {

        String EOL = System.getProperty("line.separator");

        File file = new File("xxx");
        BufferedReader reader = null;
        LinkedList<String> list = new LinkedList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] scarr = line.split(",");
                if (scarr.length == 8) {
                    String tmp = scarr[0];
                    String[] tarr = tmp.split(" ");

                    if (tarr.length < 2) {
                        tmp = tmp.substring(0, tmp.length() - 1);
                        scarr[0] = tmp + " 00:00:00\"";
                        line = "";
//                        for (String s : scarr) {
//                            line = line + s;
//                        }
                        for (int i = 0; i < scarr.length; i++) {
                            if (i == scarr.length - 1){
                                line = line + scarr[i];
                            }else {
                                line = line + scarr[i] + ",";
                            }
                        }

//                        System.out.println(scarr[0]);
                    }

                    scarr = line.split(",");
                    String last = scarr[0];
                    String[] larr = last.split(" ");
                    scarr[0] = larr[0] + "\"" + ","  + "\"" + larr[1];
                    line = "";
                    for (int i = 0; i < scarr.length; i++) {
                        if (i == scarr.length - 1){
                            line = line + scarr[i];
                        }else {
                            line = line + scarr[i] + ",";
                        }
                    }

                    list.add(line);
                    System.out.println(line);
                }

            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}