/**
 * Created by baidu on 2017/8/11.
 */


public class Test03 {
    private static Object obj=new Object();
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
//        synchronized (obj){
        synchronized (t1){
            try {
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();

                System.out.println(Thread.currentThread().getName()+" wait");
//                obj.wait();
                t1.wait();

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread{
        public ThreadA(String name){
            super(name);
        }
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(Thread.currentThread().getName()+" call notify()");
                System.out.println("sb");
                obj.notify();
            }
        }
    }
}