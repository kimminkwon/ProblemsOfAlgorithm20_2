package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 뮤탈리스크_12869 {

    private static int N;
    private static int[][][] memo = new int[61][61][61];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] hp = new int[3];
        for(int i = 0; i < N; i++)
            hp[i] = Integer.parseInt(st.nextToken());

        System.out.println(findMinimumKillSCV(hp));;
    }

    private static int findMinimumKillSCV(int[] hp) {
        if(hp[0] == 0 && hp[1] == 0 && hp[2] == 0) return 0;

        if(memo[hp[0]][hp[1]][hp[2]] != 0) return memo[hp[0]][hp[1]][hp[2]];

        int result = Integer.MAX_VALUE;
        result = Math.min(result, findMinimumKillSCV(new int[]{hp[0] - 9 < 0 ? 0 : hp[0] - 9, hp[1] - 3 < 0 ? 0 : hp[1] - 3, hp[2] - 1 < 0 ? 0 : hp[2] - 1}) + 1);
        result = Math.min(result, findMinimumKillSCV(new int[]{hp[0] - 9 < 0 ? 0 : hp[0] - 9, hp[1] - 1 < 0 ? 0 : hp[1] - 1, hp[2] - 3 < 0 ? 0 : hp[2] - 3}) + 1);
        result = Math.min(result, findMinimumKillSCV(new int[]{hp[0] - 3 < 0 ? 0 : hp[0] - 3, hp[1] - 9 < 0 ? 0 : hp[1] - 9, hp[2] - 1 < 0 ? 0 : hp[2] - 1}) + 1);
        result = Math.min(result, findMinimumKillSCV(new int[]{hp[0] - 3 < 0 ? 0 : hp[0] - 3, hp[1] - 1 < 0 ? 0 : hp[1] - 1, hp[2] - 9 < 0 ? 0 : hp[2] - 9}) + 1);
        result = Math.min(result, findMinimumKillSCV(new int[]{hp[0] - 1 < 0 ? 0 : hp[0] - 1, hp[1] - 3 < 0 ? 0 : hp[1] - 3, hp[2] - 9 < 0 ? 0 : hp[2] - 9}) + 1);
        result = Math.min(result, findMinimumKillSCV(new int[]{hp[0] - 1 < 0 ? 0 : hp[0] - 1, hp[1] - 9 < 0 ? 0 : hp[1] - 9, hp[2] - 3 < 0 ? 0 : hp[2] - 3}) + 1);
        return memo[hp[0]][hp[1]][hp[2]] = result;
    }
}
