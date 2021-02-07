package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합_1912_2 {

    private static int N;
    private static int[] numArr;

    public static void main(String[] args) throws IOException {
        makeInput();
        System.out.println(findMaximumPrefix());
    }

    private static int findMaximumPrefix() {
        int[] maxPrefix = new int[N];
        maxPrefix[0] = numArr[0];
        int max = maxPrefix[0];
        for(int i = 1; i < N; i++)
            max = Math.max(max, maxPrefix[i] = Math.max((maxPrefix[i - 1] + numArr[i]), numArr[i]));
        return max;
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for(int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());
    }
}
