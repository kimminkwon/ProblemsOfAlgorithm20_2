package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 로마숫자만들기_16922 {

    private static int N;
    private static Set<Integer> hashSet = new HashSet<>();
    private static int[] frequency = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        findNumOfDiffNumber(0, 0);
        System.out.println(hashSet.size());
    }

    private static void findNumOfDiffNumber(int currFre, int index) {
        if(currFre == N) {
            int num = getValue();
            hashSet.add(num);
            return;
        }
        if(index > 3) return;
        for(int i = 0; i <= N - currFre; i++) {
            frequency[index] = i;
            findNumOfDiffNumber(currFre + i, index + 1);
            frequency[index] = 0;
        }
    }

    private static int getValue() {
        return ((frequency[0] * 1) + (frequency[1] * 5) + (frequency[2] * 10) + (frequency[3] * 50));
    }
}
