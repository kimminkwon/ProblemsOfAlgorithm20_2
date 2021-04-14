package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 생태학_4358 {

    private static List<String> trees = new ArrayList<>();
    private static Map<String, Integer> hm = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = 0;
        while(true) {
            String tree = br.readLine();
            if(tree == null || tree.length() == 0) break;
            totalNum++;
            if(hm.containsKey(tree)) {
                hm.put(tree, hm.get(tree) + 1);
            } else {
                hm.put(tree, 1);
            }
        }

        for(String key : hm.keySet()) trees.add(key);
        Collections.sort(trees);
        StringBuilder sb = new StringBuilder();
        for(String key : trees) {
            double percent = (double)(hm.get(key) * 100.0d) / totalNum;
            sb.append(key).append(" ").append(String.format("%.4f", percent)).append("\n");
        }
        System.out.print(sb.toString());
    }

}
