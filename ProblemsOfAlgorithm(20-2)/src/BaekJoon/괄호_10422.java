package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 괄호_10422 {

    private static final long DIVIDE_NUM = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        long[] pStrDPDupl = makePStrDp();
        for(int tc = 0; tc < T; tc++) {
            int L = Integer.parseInt(br.readLine());
            sb.append(pStrDPDupl[L]).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static long[] makePStrDp() {
        long[] pStrDPDupl = new long[5001];
        pStrDPDupl[0] = 1; pStrDPDupl[2] = 1;
        for(int i = 2; i <= 2500; i++)
            for(int j = 0; j < i; j++) {
                pStrDPDupl[i * 2] += ((pStrDPDupl[j * 2] % DIVIDE_NUM) * (pStrDPDupl[(i - j - 1) * 2] % DIVIDE_NUM)) % DIVIDE_NUM;
                pStrDPDupl[i * 2] = pStrDPDupl[i * 2] % DIVIDE_NUM;
            }
        return pStrDPDupl;
    }
}
