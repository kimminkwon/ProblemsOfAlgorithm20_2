package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기4_11659 {

    private static int N, M;
    private static int[] nums, rangeSumDp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];
        rangeSumDp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        nums[0] = 0;
        for(int i = 1; i < N + 1; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        makeDp();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(rangeSumDp[end] - rangeSumDp[start - 1]).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void makeDp() {
        rangeSumDp[1] = nums[1];
        for(int i = 1; i < N + 1; i++) rangeSumDp[i] = rangeSumDp[i - 1] + nums[i];
    }
}
