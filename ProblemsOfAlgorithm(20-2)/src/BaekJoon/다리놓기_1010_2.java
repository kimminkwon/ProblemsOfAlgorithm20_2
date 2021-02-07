package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 다리놓기_1010_2 {
    private static long[][] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        comb = new long[31][31];

        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(combination(M, N));
        }
    }

    private static long combination(int n, int r) {
        if(comb[n][r] != 0) return comb[n][r];
        if(r == 0 || r == n) return comb[n][r] = 1;

        return comb[n][r] = combination(n-1, r-1) + combination(n-1, r);
    }
}
