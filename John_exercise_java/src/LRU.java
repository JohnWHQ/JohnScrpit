import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by baidu on 2017/7/1.
 */
public class LRU {
    public static void main(String[] args) {
//        final int cacheSize = 5;
//        LinkedHashMap lruCache = new LinkedHashMap<String, String>(
//                (int)Math.ceil(5/0.75) + 1, 0.75f, true
//        ){
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<String, String> entry){
//                return this.size() > cacheSize;
//            }
//        };

        LRUCache<String, String> lruCache = new LRUCache<>(5);
        lruCache.put("a", "1");
        lruCache.put("b", "2");
        lruCache.put("c", "3");
        lruCache.put("d", "4");
        lruCache.put("e", "5");

        lruCache.get("a");

        lruCache.put("f", "6");

//        System.out.println(lruCache);
//        iterSysOut(lruCache);

        LRUCache2 cache = new LRUCache2(5);
        cache.put("a", "1");
        cache.put("b", "2");
        cache.put("c", "3");
        cache.put("d", "4");
        cache.put("e", "5");

        cache.get("a");

        cache.put("f", "6");

        System.out.println(cache);


    }

    public static void iterSysOut(LinkedHashMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print("key:" + (String) entry.getKey() + " " + "value" + (String) entry.getValue());
            System.out.println();
        }
    }
//    key:c val:3
//    key:d val:4
//    key:e val:5
//    key:a val:1
//    key:f val:6
}