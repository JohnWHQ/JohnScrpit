public class hello{
    public native void test();

    static {
        // //设置查找路径为当前项目路径
        // System.setProperty("java.library.path", ".");
        // //加载动态库的名称
        // System.loadLibrary("hello");
        System.load("/Users/baidu/Development/codes/c++/m_so/libhello.so");
    }

    public static void main(String[] args){
        new hello().test();
    }
}
