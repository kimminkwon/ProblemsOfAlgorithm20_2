package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 펠린드롬_10942 {

    private static int[] nums;
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) nums[i] = Integer.parseInt(st.nextToken());

        boolean[][] isP = makePalindromeDp();

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(isP[S][E] ? "1" : "0").append("\n");
        }
        System.out.print(sb.toString());
    }

    private static boolean isOut(int s, int e) {
        return s >= N + 1 || e >= N + 1 || s < 1 || e < 1;
    }

    private static boolean[][] makePalindromeDp() {
        boolean[][] isP = new boolean[N + 1][N + 1];

        for(int i = 1; i <= N; i++) isP[i][i] = true;

        int flag = 2;
        while(flag <= N) {
            int s = 1, e = flag++;
            while(!isOut(s, e)) {
                if(Math.abs(s - e) % 2 == 1) {
                    if(isP[s][e - 1] && nums[s] == nums[e]) isP[s][e] = true;
                } else {
                    if (isP[s + 1][e - 1] && nums[s] == nums[e]) isP[s][e] = true;
                }
                s++; e++;
            }
        }
        return isP;
    }

}
