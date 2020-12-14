package Others;

import java.util.Map;
import java.util.TreeMap;

class NUMB implements Comparable {
    private Integer num;
    private Integer flag;

    public NUMB(Integer num, Integer flag) {
        this.num = num;
        this.flag = flag;
    }

    @Override
    public int compareTo(Object o) {
        NUMB numb2 = (NUMB)o;
        return this.flag.compareTo(numb2.flag);
    }
}
public class TreeMapPratice {
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 3, 5, 5, 7, 3, 2};
        Map<Integer, Integer> treeMapTest = new TreeMap<>();

        for(int i = 0; i < nums.length; i++) {
            int flag = 1;
            for(int j = 0; j < nums.length; j++) {
                if(i != j && nums[i] == nums[j]) {
                    flag++;
                }
            }
            treeMapTest.put(flag, nums[i]);
        }

        System.out.println(treeMapTest);

    }


}
