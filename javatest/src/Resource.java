/**
 * Created by baidu on 2017/7/3.
 */
public class Resource {
    public  int cur = 0;
    private int max = 20;
    private int min = 0;

    public synchronized void add(){
        while (cur == max) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cur++;
        notify();
    }

    public synchronized void min(){
        while (cur == min){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cur--;
        notify();
    }

}
