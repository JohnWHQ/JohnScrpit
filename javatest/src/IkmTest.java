/**
 * Created by baidu on 2017/10/9.
 */
public class IkmTest {
    public IkmTest() {
        this(10);
    }
    public IkmTest(int data) {
        this.data = data;
    }
    void display() {
        System.out.println("data = " + data);
    }
    int data;

    class Decrementer {
        public void decrement(double data) {
            data = data - 1.0;
        }
    }

    public static void main (String [] args) {
        int data = 0;
        IkmTest t = new IkmTest(data);
        IkmTest.Decrementer d = t.new Decrementer();
        d.decrement(data);
        t.display();
    }
}