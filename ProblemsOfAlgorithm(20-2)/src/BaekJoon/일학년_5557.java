package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일학년_5557 {

    private static int N;
    private static int[] nums;
    private static long[][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        memo = new long[N][21]; // memo[i][j] = i번째 수까지 계산했을 때 j를 만들 수 있는 경우의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        findCurrectEqualation();
        System.out.println(memo[N - 2][nums[N - 1]]);
    }

    private static void findCurrectEqualation() {
        memo[0][nums[0]] = 1;
        for(int i = 1; i < N - 1; i++) {
            for(int j = 0; j < 21; j++) {
                if(memo[i - 1][j] != 0) {
                    if(j - nums[i] >= 0) memo[i][j - nums[i]] += memo[i - 1][j];
                    if(j + nums[i] <= 20) memo[i][j + nums[i]] += memo[i - 1][j];
                }
            }
        }
    }
}
