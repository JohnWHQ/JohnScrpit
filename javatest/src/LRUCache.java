import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by baidu on 2017/7/1.
 */
public class LRUCache<K, V> {
    private final int cacheSize;
    private final float LOAD_FACTOR = 0.75f;
    LinkedHashMap<K, V> map;
    public LRUCache(int size){
        cacheSize = size;
        map = new LinkedHashMap<K, V>((int)Math.ceil(size/0.75) + 1, LOAD_FACTOR, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> entry){
                return this.size() > cacheSize;
            }
        };
    }

    public synchronized void put(K key, V val){
        map.put(key, val);
    }
    public synchronized V get(K key){
        return map.get(key);
    }
    public synchronized void remove(K key){
        map.remove(key);
    }
    public synchronized Set<Map.Entry<K, V>> getAll(){
        return map.entrySet();
    }
    public synchronized int size(){
        return map.size();
    }
    public synchronized void clear(){
        map.clear();
    }
    public static void iterSysOut(LinkedHashMap<String, String> map){
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print("key:" + (String)entry.getKey() + " " + "value" + (String)entry.getValue());
            System.out.println();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<K, V> entry : this.getAll()){
            sb.append("key:").append((String)entry.getKey()).append(" val:").append((String)entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
