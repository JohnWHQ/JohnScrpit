/**
 * Created by baidu on 2017/7/3.
 */
public class Singleton1 {
    private Singleton1(){}

    private static class SingletonHolder{
        private static final Singleton1 INSTANCE = new Singleton1();
    }
    public static Singleton1 getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
