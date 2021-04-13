package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 단어수학_1339 {

    private static int N, existWordLength, result;
    private static String[] words;
    private static Map<Character, Integer> strToInt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for(int i = 0; i < N; i++) words[i] = br.readLine();

        existWordLength = findExistWords();
        findMaximumSumOfWords(0, new int[existWordLength], new boolean[10]);
        System.out.println(result);
    }

    private static void findMaximumSumOfWords(int index, int[] nums, boolean[] used) {
        if(index == existWordLength) {
            // System.out.println(Arrays.toString(nums));
            result = Math.max(result, calculate(nums));
            return;
        }
        for(int i = 0; i < 10; i++) {
            if(used[i]) continue;
            used[i] = true;
            nums[index] = i;
            findMaximumSumOfWords(index + 1, nums, used);
            used[i] = false;
        }
    }

    private static int calculate(int[] nums) {
        int sum = 0;
        for(int i = 0; i < N; i++) {
            int flag = 0;
            for (int j = 0; j < words[i].length(); j++) {
                flag *= 10;
                flag += nums[strToInt.get(words[i].charAt(j))];
            }
            sum += flag;
        }
        return sum;
    }

    private static int findExistWords() {
        Set<Character> hs = new HashSet<>();
        for(int i = 0; i < N; i++) {
            for(char c : words[i].toCharArray())
                if(!hs.contains(c)) hs.add(c);
        }

        Iterator<Character> itr = hs.iterator();
        strToInt = new HashMap<>();
        int cnt = 0;
        while(itr.hasNext()) {
            strToInt.put(itr.next(), cnt++);
        }
        return cnt;
    }
}
