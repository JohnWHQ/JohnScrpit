import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by baidu on 2017/7/1.
 */
public class LRUCache2 {
    class Node{
        Node pre;
        Node next;
        Object key;
        Object val;
    }

    private int cacheSize;
    private Hashtable<Object, Node> map;
    private int curSize;

    private Node head;
    private Node tail;

    public LRUCache2 (int size){
        curSize = 0;
        cacheSize = size;
        map = new Hashtable<Object, Node>(size);
    }

    public Object get(Object key){
        Node node = map.get(key);
        if (node == null) {
            return null;
        }else{
            moveToHead(node);
            return node;
        }
    }

    public void put(Object key, Object val){
        Node node = map.get(key);
        if (node == null) {
            if (curSize >= cacheSize) {
                map.remove(tail.key);
                removeTail();
            } else {
                curSize++;
            }
            node = new Node();
        }
        node.key = key;
        node.val = val;
        map.put(key, node);
        moveToHead(node);
    }

    public void remove(Object key){
        Node node = map.get(key);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == tail) {
                tail = node.pre;
            }
            if (node == head) {
                head = node.next;
            }
            map.remove(key);
        }
    }

    private void removeTail() {
        if (tail != null) {
            if (tail.pre != null) {
                tail.pre.next = null;
            } else {
                head = null;
            }
            tail = tail.pre;
        }
    }

    private void moveToHead(Node node) {
        if (node == head) {
            return;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node == tail) {
            tail = node.pre;
        }
        if (head != null) {
            head.pre = node;
            node.next = head;
        }
        head = node;
        node.pre = null;

        if (tail == null) {
            tail = head;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while(cur != null){
            sb.append("key:").append((String)cur.key)
                    .append(" val:").append((String)cur.val)
                    .append("\n");
            cur = cur.next;
        }
        return sb.toString();
    }


}
