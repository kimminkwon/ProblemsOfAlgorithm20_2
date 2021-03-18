package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일이삼더하기8_15993 {

    private static final long MOD = 1000000009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] oddOneTwoThreeDp = new long[100001];
        long[] evenOneTwoThreeDp = new long[100001];

        oddOneTwoThreeDp[1] = 1; evenOneTwoThreeDp[1] = 0;
        oddOneTwoThreeDp[2] = 1; evenOneTwoThreeDp[2] = 1;
        oddOneTwoThreeDp[3] = 2; evenOneTwoThreeDp[3] = 2;

        for(int i = 4; i <= 100000; i++) {
            oddOneTwoThreeDp[i] = (evenOneTwoThreeDp[i - 1] + evenOneTwoThreeDp[i - 2] + evenOneTwoThreeDp[i - 3]) % MOD;
            evenOneTwoThreeDp[i] = (oddOneTwoThreeDp[i - 1] + oddOneTwoThreeDp[i - 2] + oddOneTwoThreeDp[i - 3]) % MOD;
        }

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(oddOneTwoThreeDp[N]).append(" ").append(evenOneTwoThreeDp[N]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
