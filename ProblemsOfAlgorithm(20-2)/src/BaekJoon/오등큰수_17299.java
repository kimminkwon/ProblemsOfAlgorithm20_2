package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 오등큰수_17299 {

    private static class NumSet {
        int index, num, fre;

        public NumSet(int index, int num, int fre) {
            this.index = index;
            this.num = num;
            this.fre = fre;
        }
    }

    private static int N;
    private static int[] nums, frequency, result;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        frequency = new int[N];
        result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        makeFrequency();
        findNGF();
        System.out.println(printResult());
    }

    private static String printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(result[i]).append(" ");
        return sb.toString();
    }

    private static void findNGF() {
        Stack<NumSet> st = new Stack<>();
        Arrays.fill(result, -1);
        for(int i = 0; i < N; i++) {
            if(st.isEmpty()) st.push(new NumSet(i, nums[i], frequency[i]));
            else {
                while(!st.isEmpty() && st.peek().fre < frequency[i])
                    result[st.pop().index] = nums[i];
                st.push(new NumSet(i, nums[i], frequency[i]));
            }
        }
    }

    private static void makeFrequency() {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int n : nums) {
            if(hashMap.containsKey(n)) hashMap.put(n, hashMap.get(n) + 1);
            else hashMap.put(n, 1);
        }
        for(int i = 0; i < N; i++)
            frequency[i] = hashMap.get(nums[i]);
    }
}
