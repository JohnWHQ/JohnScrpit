/**
 * Created by baidu on 2017/7/3.
 */
public class Singleton {
    private static volatile Singleton instance;
    private Singleton(){
    }
    public static Singleton getInstance(){
        if (instance == null) {
            synchronized (Singleton.class){
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
