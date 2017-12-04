/**
 * Created by baidu on 2017/7/6.
 */
public class ABprint {
    public static void main(String[] args){
        ABResource res = new ABResource();
        new Thread(new WriteA(res)).start();
        new Thread(new WriteB(res)).start();
    }

}
class WriteA implements Runnable{
    ABResource res;
    public WriteA(ABResource res){
        this.res = res;
    }
    @Override
    public void run() {
        while (true){
            res.write();
        }
    }
}
class WriteB implements Runnable{
    ABResource res;
    public WriteB(ABResource res){
        this.res = res;
    }
    @Override
    public void run() {
        while (true){
            res.write();
        }
    }
}

class ABResource{
    private boolean flag = true;
    public synchronized void write(){
        if (flag){
            System.out.println("A");
        }else {
            System.out.println("B");
        }
        flag = !flag;
    }
}
