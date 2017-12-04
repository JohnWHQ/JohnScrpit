/**
 * Created by baidu on 2017/7/3.
 */
public enum Singleton3 {
    INSTANCE;
    Singleton3(){
    }
    public static Singleton3 getInstance(){
        return INSTANCE;
    }

}
