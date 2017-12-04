import java.util.*;

/**
 * Created by baidu on 2017/7/2.
 */
public class alitest {
    public static void main(String[] args) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);

        ArrayList<Integer> parents = new ArrayList<Integer>();
        parents.add(0);
        parents.add(0);
        parents.add(1);
        parents.add(1);

        ArrayList<Integer> cost = new ArrayList<Integer>();
        cost.add(2);
        cost.add(3);
        cost.add(2);
        cost.add(3);

        System.out.println(getMaxCost(ids, parents, cost));
    }
    public static int getMaxCost(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> cost){
        HashMap<Integer, Integer> costMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < ids.size(); i++) {
            costMap.put(ids.get(i), cost.get(i));
        }
        HashMap<Integer, ArrayList<Integer>> p2c = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < parents.size(); i++){
            if(parents.get(i) != 0){
                if (!p2c.containsKey(parents.get(i))) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(ids.get(i));
                    p2c.put(parents.get(i), tmp);
                }else {
                    p2c.get(parents.get(i)).add(ids.get(i));
                }
            }
        }
        int res = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : p2c.entrySet()) {
            int key = (int)entry.getKey();
            ArrayList<Integer> list = (ArrayList<Integer>) entry.getValue();
            int curCost = 0;
            curCost = curCost + costMap.get(key);

            int tmp = 0;

            for (int i = 0; i < list.size(); i++){
               tmp = Math.max(tmp, recursion(p2c, costMap, list.get(i)));

            }
            curCost = curCost + tmp;
            res = Math.max(res, curCost);
        }
        return res;
    }
    public static int recursion(HashMap<Integer, ArrayList<Integer>> map, HashMap<Integer, Integer> cost, int key){
        int res = cost.get(key);
        int sum = 0;
        while (map.containsKey(key)) {
            ArrayList<Integer> tmp = map.get(key);
            for (int i = 0; i < tmp.size(); i++){
                sum =  Math.max(sum, recursion(map, cost, tmp.get(i)));
            }
        }
        return res + sum;
    }
}
